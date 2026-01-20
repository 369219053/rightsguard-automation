#!/bin/bash
# 权利卫士取证自动化 - 编译脚本

# 设置JAVA_HOME为Android Studio的JDK
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"

# 清理并编译
echo "🚀 开始编译权利卫士取证自动化..."
./gradlew clean assembleDebug

# 检查编译结果
if [ $? -eq 0 ]; then
    echo "✅ 编译成功!"
    echo "📦 APK位置: app/build/outputs/apk/debug/app-debug.apk"
    ls -lh app/build/outputs/apk/debug/app-debug.apk
else
    echo "❌ 编译失败!"
    exit 1
fi

