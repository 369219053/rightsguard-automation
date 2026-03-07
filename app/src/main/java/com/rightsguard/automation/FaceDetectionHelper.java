package com.rightsguard.automation;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 人脸检测工具类
 * 使用Google ML Kit进行离线人脸检测
 */
public class FaceDetectionHelper {
    private static final String TAG = "FaceDetectionHelper";
    private FaceDetector detector;

    public FaceDetectionHelper() {
        // 配置人脸检测器
        FaceDetectorOptions options = new FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE) // 准确模式(提高准确度)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE) // 不需要关键点
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE) // 不需要分类
                .setMinFaceSize(0.4f) // 最小人脸大小(相对于图片,提高到0.4避免误判)
                .enableTracking() // 启用人脸追踪
                .build();

        detector = FaceDetection.getClient(options);
        Log.d(TAG, "人脸检测器初始化完成(准确模式,最小人脸0.4)");
    }

    /**
     * 检测图片中是否有人脸(同步方法)
     * @param bitmap 要检测的图片
     * @return true表示有人脸, false表示没有人脸
     */
    public boolean detectFace(Bitmap bitmap) {
        if (bitmap == null) {
            Log.w(TAG, "图片为空,无法检测人脸");
            return false;
        }

        try {
            // 转换为InputImage
            InputImage image = InputImage.fromBitmap(bitmap, 0);

            // 使用CountDownLatch实现同步等待
            CountDownLatch latch = new CountDownLatch(1);
            AtomicBoolean hasFace = new AtomicBoolean(false);

            // 异步检测人脸
            detector.process(image)
                    .addOnSuccessListener(faces -> {
                        // 检查是否有足够大的人脸
                        boolean hasValidFace = false;
                        if (faces != null && !faces.isEmpty()) {
                            // 检查人脸大小,确保人脸足够大
                            for (Face face : faces) {
                                float faceWidth = face.getBoundingBox().width();
                                float faceHeight = face.getBoundingBox().height();
                                float imageWidth = bitmap.getWidth();
                                float imageHeight = bitmap.getHeight();

                                // 人脸宽度或高度至少占图片的10%
                                if (faceWidth / imageWidth > 0.1f || faceHeight / imageHeight > 0.1f) {
                                    hasValidFace = true;
                                    Log.d(TAG, "✅ 检测到有效人脸,大小: " + (int)faceWidth + "x" + (int)faceHeight +
                                             " (图片: " + (int)imageWidth + "x" + (int)imageHeight + ")");
                                    break;
                                }
                            }

                            if (!hasValidFace) {
                                Log.d(TAG, "⚠️ 检测到人脸但太小,忽略 (数量: " + faces.size() + ")");
                            }
                        } else {
                            Log.d(TAG, "❌ 未检测到人脸");
                        }

                        hasFace.set(hasValidFace);
                        latch.countDown();
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "人脸检测失败: " + e.getMessage());
                        hasFace.set(false);
                        latch.countDown();
                    });

            // 等待检测完成(最多等待2秒)
            boolean completed = latch.await(2, TimeUnit.SECONDS);
            if (!completed) {
                Log.w(TAG, "人脸检测超时");
                return false;
            }

            return hasFace.get();

        } catch (Exception e) {
            Log.e(TAG, "人脸检测异常: " + e.getMessage());
            return false;
        }
    }

    /**
     * 检测图片中的人脸数量(同步方法)
     * @param bitmap 要检测的图片
     * @return 人脸数量
     */
    public int getFaceCount(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }

        try {
            InputImage image = InputImage.fromBitmap(bitmap, 0);
            CountDownLatch latch = new CountDownLatch(1);
            final int[] faceCount = {0};

            detector.process(image)
                    .addOnSuccessListener(faces -> {
                        faceCount[0] = faces != null ? faces.size() : 0;
                        Log.d(TAG, "检测到人脸数量: " + faceCount[0]);
                        latch.countDown();
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "人脸检测失败: " + e.getMessage());
                        latch.countDown();
                    });

            latch.await(2, TimeUnit.SECONDS);
            return faceCount[0];

        } catch (Exception e) {
            Log.e(TAG, "人脸检测异常: " + e.getMessage());
            return 0;
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        if (detector != null) {
            detector.close();
            Log.d(TAG, "人脸检测器已释放");
        }
    }
}

