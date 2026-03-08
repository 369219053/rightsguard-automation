package com.rightsguard.automation;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * OCR识别工具类
 * 使用Google ML Kit进行文字识别
 */
public class OcrHelper {

    private static final String TAG = "OcrHelper";
    private final TextRecognizer recognizer;
    private final LogCallback logCallback;

    /**
     * 日志回调接口
     */
    public interface LogCallback {
        void onLog(String message);
    }

    public OcrHelper(LogCallback logCallback) {
        // 初始化中文文字识别器(设备端,免费,支持中文+拉丁文)
        recognizer = TextRecognition.getClient(new ChineseTextRecognizerOptions.Builder().build());
        this.logCallback = logCallback;
    }

    /**
     * 识别图片中的文字并查找目标文字的位置
     *
     * @param bitmap     要识别的图片
     * @param targetText 要查找的目标文字
     * @param callback   回调接口
     */
    public void findTextPosition(Bitmap bitmap, String targetText, OcrCallback callback) {
        if (bitmap == null) {
            String msg = "❌ Bitmap为空,无法识别";
            Log.e(TAG, msg);
            if (logCallback != null) logCallback.onLog(msg);
            callback.onFailure("Bitmap为空");
            return;
        }

        String msg1 = "🔍 开始OCR识别,查找文字: " + targetText;
        String msg2 = "📐 图片尺寸: " + bitmap.getWidth() + "x" + bitmap.getHeight();
        Log.d(TAG, msg1);
        Log.d(TAG, msg2);
        if (logCallback != null) {
            logCallback.onLog(msg1);
            logCallback.onLog(msg2);
        }

        // 创建InputImage
        InputImage image = InputImage.fromBitmap(bitmap, 0);

        // 执行文字识别
        recognizer.process(image)
                .addOnSuccessListener(text -> {
                    String msg = "✅ OCR识别成功,识别到 " + text.getTextBlocks().size() + " 个文本块";
                    Log.d(TAG, msg);
                    if (logCallback != null) logCallback.onLog(msg);

                    // 查找目标文字
                    List<TextMatch> matches = new ArrayList<>();
                    for (Text.TextBlock block : text.getTextBlocks()) {
                        String blockText = block.getText();
                        String blockMsg = "📝 文本块: " + blockText;
                        Log.d(TAG, blockMsg);
                        if (logCallback != null) logCallback.onLog(blockMsg);

                        // 检查是否包含目标文字
                        if (blockText.contains(targetText)) {
                            Rect bounds = block.getBoundingBox();
                            if (bounds != null) {
                                Point center = new Point(bounds.centerX(), bounds.centerY());
                                matches.add(new TextMatch(blockText, bounds, center));
                                String foundMsg = "🎯 找到目标文字: " + blockText + " 位置: " + bounds.toShortString();
                                Log.d(TAG, foundMsg);
                                if (logCallback != null) logCallback.onLog(foundMsg);
                            }
                        }

                        // 也检查每一行
                        for (Text.Line line : block.getLines()) {
                            String lineText = line.getText();
                            if (lineText.contains(targetText)) {
                                Rect bounds = line.getBoundingBox();
                                if (bounds != null) {
                                    Point center = new Point(bounds.centerX(), bounds.centerY());
                                    matches.add(new TextMatch(lineText, bounds, center));
                                    String foundLineMsg = "🎯 找到目标文字(行): " + lineText + " 位置: " + bounds.toShortString();
                                    Log.d(TAG, foundLineMsg);
                                    if (logCallback != null) logCallback.onLog(foundLineMsg);
                                }
                            }
                        }
                    }

                    // 返回结果
                    if (!matches.isEmpty()) {
                        // 如果有多个匹配,返回第一个
                        TextMatch match = matches.get(0);
                        String successMsg1 = "✅ OCR识别完成,找到目标文字: " + match.text;
                        String successMsg2 = "📍 坐标: (" + match.center.x + ", " + match.center.y + ")";
                        Log.d(TAG, successMsg1);
                        Log.d(TAG, successMsg2);
                        if (logCallback != null) {
                            logCallback.onLog(successMsg1);
                            logCallback.onLog(successMsg2);
                        }
                        callback.onSuccess(match);
                    } else {
                        String failMsg = "❌ 未找到目标文字: " + targetText;
                        Log.e(TAG, failMsg);
                        if (logCallback != null) logCallback.onLog(failMsg);
                        callback.onFailure("未找到目标文字: " + targetText);
                    }
                })
                .addOnFailureListener(e -> {
                    String errorMsg = "❌ OCR识别失败: " + e.getMessage();
                    Log.e(TAG, errorMsg);
                    if (logCallback != null) logCallback.onLog(errorMsg);
                    e.printStackTrace();
                    callback.onFailure("OCR识别失败: " + e.getMessage());
                });
    }

    /**
     * 识别图片中的文字并返回所有匹配项（用于需要自行过滤多个结果的场景）
     *
     * @param bitmap     要识别的图片
     * @param targetText 要查找的目标文字
     * @param callback   回调接口（返回所有匹配列表）
     */
    public void findAllTextPositions(Bitmap bitmap, String targetText, OcrMultiCallback callback) {
        if (bitmap == null) {
            String msg = "❌ Bitmap为空,无法识别";
            Log.e(TAG, msg);
            if (logCallback != null) logCallback.onLog(msg);
            callback.onFailure("Bitmap为空");
            return;
        }

        String msg1 = "🔍 开始OCR识别(全量),查找文字: " + targetText;
        String msg2 = "📐 图片尺寸: " + bitmap.getWidth() + "x" + bitmap.getHeight();
        Log.d(TAG, msg1);
        Log.d(TAG, msg2);
        if (logCallback != null) {
            logCallback.onLog(msg1);
            logCallback.onLog(msg2);
        }

        InputImage image = InputImage.fromBitmap(bitmap, 0);
        recognizer.process(image)
                .addOnSuccessListener(text -> {
                    String msg = "✅ OCR识别成功,识别到 " + text.getTextBlocks().size() + " 个文本块";
                    Log.d(TAG, msg);
                    if (logCallback != null) logCallback.onLog(msg);

                    List<TextMatch> matches = new ArrayList<>();
                    for (Text.TextBlock block : text.getTextBlocks()) {
                        String blockText = block.getText();
                        if (blockText.contains(targetText)) {
                            Rect bounds = block.getBoundingBox();
                            if (bounds != null) {
                                Point center = new Point(bounds.centerX(), bounds.centerY());
                                matches.add(new TextMatch(blockText, bounds, center));
                                String foundMsg = "🎯 [全量] 找到目标文字(块): " + blockText + " 位置: " + bounds.toShortString();
                                Log.d(TAG, foundMsg);
                                if (logCallback != null) logCallback.onLog(foundMsg);
                            }
                        }
                        for (Text.Line line : block.getLines()) {
                            String lineText = line.getText();
                            if (lineText.contains(targetText)) {
                                Rect bounds = line.getBoundingBox();
                                if (bounds != null) {
                                    Point center = new Point(bounds.centerX(), bounds.centerY());
                                    // 去重：避免block和line都匹配时重复添加相同坐标
                                    boolean duplicate = false;
                                    for (TextMatch m : matches) {
                                        if (Math.abs(m.center.x - center.x) < 20 && Math.abs(m.center.y - center.y) < 20) {
                                            duplicate = true;
                                            break;
                                        }
                                    }
                                    if (!duplicate) {
                                        matches.add(new TextMatch(lineText, bounds, center));
                                        String foundLineMsg = "🎯 [全量] 找到目标文字(行): " + lineText + " 位置: " + bounds.toShortString();
                                        Log.d(TAG, foundLineMsg);
                                        if (logCallback != null) logCallback.onLog(foundLineMsg);
                                    }
                                }
                            }
                        }
                    }

                    if (!matches.isEmpty()) {
                        String successMsg = "✅ OCR全量识别完成,共找到 " + matches.size() + " 个匹配";
                        Log.d(TAG, successMsg);
                        if (logCallback != null) logCallback.onLog(successMsg);
                        callback.onSuccess(matches);
                    } else {
                        String failMsg = "❌ 未找到目标文字: " + targetText;
                        Log.e(TAG, failMsg);
                        if (logCallback != null) logCallback.onLog(failMsg);
                        callback.onFailure("未找到目标文字: " + targetText);
                    }
                })
                .addOnFailureListener(e -> {
                    String errorMsg = "❌ OCR识别失败: " + e.getMessage();
                    Log.e(TAG, errorMsg);
                    if (logCallback != null) logCallback.onLog(errorMsg);
                    callback.onFailure("OCR识别失败: " + e.getMessage());
                });
    }

    /**
     * 释放资源
     */
    public void release() {
        if (recognizer != null) {
            recognizer.close();
        }
    }

    /**
     * OCR回调接口（单个结果）
     */
    public interface OcrCallback {
        void onSuccess(TextMatch match);

        void onFailure(String error);
    }

    /**
     * OCR回调接口（全部结果，用于需要自行过滤的场景）
     */
    public interface OcrMultiCallback {
        void onSuccess(List<TextMatch> matches);

        void onFailure(String error);
    }

    /**
     * 文字匹配结果
     */
    public static class TextMatch {
        public final String text;      // 识别到的文字
        public final Rect bounds;      // 文字边界
        public final Point center;     // 文字中心点坐标

        public TextMatch(String text, Rect bounds, Point center) {
            this.text = text;
            this.bounds = bounds;
            this.center = center;
        }
    }
}

