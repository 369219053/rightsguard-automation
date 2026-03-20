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

                        // 也检查每一行和每个词(Element)
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
                            // Element级别搜索，精准定位单词/短语坐标
                            for (Text.Element element : line.getElements()) {
                                String elementText = element.getText();
                                if (elementText.contains(targetText)) {
                                    Rect bounds = element.getBoundingBox();
                                    if (bounds != null) {
                                        Point center = new Point(bounds.centerX(), bounds.centerY());
                                        // Element级别优先级最高，插入到最前面
                                        matches.add(0, new TextMatch(elementText, bounds, center));
                                        String foundElemMsg = "🎯 找到目标文字(词): " + elementText + " 位置: " + bounds.toShortString();
                                        Log.d(TAG, foundElemMsg);
                                        if (logCallback != null) logCallback.onLog(foundElemMsg);
                                    }
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
                            // Element级别搜索，精准定位单词/短语坐标
                            for (Text.Element element : line.getElements()) {
                                String elementText = element.getText();
                                if (elementText.contains(targetText)) {
                                    Rect bounds = element.getBoundingBox();
                                    if (bounds != null) {
                                        Point center = new Point(bounds.centerX(), bounds.centerY());
                                        // Element级别优先级最高，插入到最前面
                                        matches.add(0, new TextMatch(elementText, bounds, center));
                                        String foundElemMsg = "🎯 [全量] 找到目标文字(词): " + elementText + " 位置: " + bounds.toShortString();
                                        Log.d(TAG, foundElemMsg);
                                        if (logCallback != null) logCallback.onLog(foundElemMsg);
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
     * 识别图片中的文字，检测是否包含候选关键词列表中的任意一个（只跑一次OCR，效率高）
     *
     * @param bitmap      要识别的图片
     * @param candidates  候选关键词数组，任意命中一个即回调 onSuccess
     * @param callback    回调接口，onSuccess 携带命中的关键词；onFailure 表示全部未命中
     */
    public void findAnyTextPosition(Bitmap bitmap, String[] candidates, OcrAnyCallback callback) {
        if (bitmap == null) {
            String msg = "❌ Bitmap为空,无法识别";
            Log.e(TAG, msg);
            if (logCallback != null) logCallback.onLog(msg);
            callback.onFailure("Bitmap为空");
            return;
        }

        String msg1 = "🔍 开始OCR识别(多关键词),候选: " + java.util.Arrays.toString(candidates);
        Log.d(TAG, msg1);
        if (logCallback != null) logCallback.onLog(msg1);

        InputImage image = InputImage.fromBitmap(bitmap, 0);
        recognizer.process(image)
                .addOnSuccessListener(text -> {
                    String msg = "✅ OCR识别成功,识别到 " + text.getTextBlocks().size() + " 个文本块";
                    Log.d(TAG, msg);
                    if (logCallback != null) logCallback.onLog(msg);

                    // 拼接全部识别文字，方便一次性匹配
                    StringBuilder fullText = new StringBuilder();
                    for (Text.TextBlock block : text.getTextBlocks()) {
                        fullText.append(block.getText()).append("\n");
                    }
                    String fullStr = fullText.toString();

                    // 逐一检查候选关键词
                    for (String candidate : candidates) {
                        if (fullStr.contains(candidate)) {
                            String foundMsg = "🎯 检测到关键词: [" + candidate + "]";
                            Log.d(TAG, foundMsg);
                            if (logCallback != null) logCallback.onLog(foundMsg);
                            callback.onSuccess(candidate);
                            return;
                        }
                    }

                    String failMsg = "❌ 候选关键词均未命中";
                    Log.d(TAG, failMsg);
                    if (logCallback != null) logCallback.onLog(failMsg);
                    callback.onFailure(failMsg);
                })
                .addOnFailureListener(e -> {
                    String errorMsg = "❌ OCR识别失败: " + e.getMessage();
                    Log.e(TAG, errorMsg);
                    if (logCallback != null) logCallback.onLog(errorMsg);
                    callback.onFailure(errorMsg);
                });
    }

    /**
     * 同时检测两组候选词（各组任意命中一个即视为找到），仅跑一次OCR，效率高。
     * 典型用途：同时找"带货数据"（含OCR误识别变体）和"受众数据"（含误识别变体），
     * 两者都命中才视为目标区域完整显示。
     *
     * @param bitmap   要识别的图片
     * @param group1   第一组候选词（任意命中一个即算找到），例如 {"带货数据","带货教据","带货数"}
     * @param group2   第二组候选词（任意命中一个即算找到），例如 {"受众数据","受众教据","受众数"}
     * @param callback 回调：onSuccess(g1Match, g1Y, g2Match, g2Y) 两组都命中；
     *                 onPartial(g1Match, g1Y) 只有第一组命中；
     *                 onFailure 两组都未命中
     */
    public void findDualTextPositions(Bitmap bitmap, String[] group1, String[] group2, DualOcrCallback callback) {
        if (bitmap == null) {
            String msg = "❌ Bitmap为空,无法识别(双组)";
            Log.e(TAG, msg);
            if (logCallback != null) logCallback.onLog(msg);
            callback.onFailure("Bitmap为空");
            return;
        }

        String msg1 = "🔍 开始OCR识别(双组),组1=" + java.util.Arrays.toString(group1)
                + " 组2=" + java.util.Arrays.toString(group2);
        Log.d(TAG, msg1);
        if (logCallback != null) logCallback.onLog(msg1);

        InputImage image = InputImage.fromBitmap(bitmap, 0);
        recognizer.process(image)
                .addOnSuccessListener(text -> {
                    String msg = "✅ OCR识别成功,识别到 " + text.getTextBlocks().size() + " 个文本块";
                    Log.d(TAG, msg);
                    if (logCallback != null) logCallback.onLog(msg);

                    int g1Y = -1, g2Y = -1;
                    String g1Match = null, g2Match = null;

                    for (Text.TextBlock block : text.getTextBlocks()) {
                        String blockText = block.getText();
                        String blockMsg = "📝 文本块: " + blockText;
                        Log.d(TAG, blockMsg);
                        if (logCallback != null) logCallback.onLog(blockMsg);

                        Rect bounds = block.getBoundingBox();
                        if (bounds == null) continue;

                        // 检查第一组
                        if (g1Y < 0) {
                            for (String c : group1) {
                                if (blockText.contains(c)) {
                                    g1Y = bounds.centerY();
                                    g1Match = c;
                                    logCallback.onLog("🎯 [双组] 命中组1: '" + c + "' Y=" + g1Y);
                                    break;
                                }
                            }
                        }
                        // 检查第二组
                        if (g2Y < 0) {
                            for (String c : group2) {
                                if (blockText.contains(c)) {
                                    g2Y = bounds.centerY();
                                    g2Match = c;
                                    logCallback.onLog("🎯 [双组] 命中组2: '" + c + "' Y=" + g2Y);
                                    break;
                                }
                            }
                        }
                        if (g1Y >= 0 && g2Y >= 0) break; // 两组都找到，提前退出
                    }

                    if (g1Y >= 0 && g2Y >= 0) {
                        callback.onSuccess(g1Match, g1Y, g2Match, g2Y);
                    } else if (g1Y >= 0) {
                        callback.onPartial(g1Match, g1Y);
                    } else {
                        callback.onFailure("两组候选词均未命中");
                    }
                })
                .addOnFailureListener(e -> {
                    String errorMsg = "❌ OCR识别失败(双组): " + e.getMessage();
                    Log.e(TAG, errorMsg);
                    if (logCallback != null) logCallback.onLog(errorMsg);
                    callback.onFailure(errorMsg);
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
     * OCR回调接口（多候选关键词，任意命中即回调 onSuccess）
     */
    public interface OcrAnyCallback {
        void onSuccess(String matchedKeyword);

        void onFailure(String error);
    }

    /**
     * OCR回调接口（双组候选词）
     */
    public interface DualOcrCallback {
        /** 两组都命中 */
        void onSuccess(String g1Match, int g1Y, String g2Match, int g2Y);
        /** 仅第一组命中（第二组未出现） */
        void onPartial(String g1Match, int g1Y);
        /** 两组都未命中 */
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

