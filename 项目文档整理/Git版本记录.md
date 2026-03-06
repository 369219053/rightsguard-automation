# Git版本记录

[← 返回README](../README.md)

---

## 📋 版本说明

本文档记录权利卫士取证自动化系统的所有版本更新历史,包括新功能、优化改进、Bug修复等。

---

## 🚀 版本历史

### V3.2 (2026-03-06) 📜 资质规则页面完整流程 + 智能返回优化!

**✨ 核心更新 - 资质规则页面自动化**

#### ✅ 完成内容

**1. 资质规则页面OCR加载检测**
- ✅ 点击"资质规则"按钮后使用OCR检测页面是否加载完成
- ✅ 识别"资质规则公示"文字作为加载完成的标志
- ✅ 最多检测10次,每次间隔500ms(总共最多5秒)
- ✅ 检测成功后立即截图,避免截图空白页面
- ✅ 超时后仍然尝试截图,避免完全失败

**2. 截图后上拉屏幕到底部**
- ✅ 截图完成后自动上拉屏幕到底部
- ✅ 修复手势方向:从下往上滑(2000→800)
- ✅ 持续时间500ms,滑动距离1200像素
- ✅ 确保营业执照底部信息完整展示

**3. 智能返回到"我"页面**
- ✅ 上拉完成后智能返回到"我"页面
- ✅ 每次返回后检测是否到达"我"页面
- ✅ 检测"获赞"、"关注"、"粉丝"等特征元素
- ✅ 到达"我"页面后立即停止返回,避免返回过头
- ✅ 增强调试日志,显示检测过程和结果

**4. OCR同步问题修复**
- ✅ 修复OCR异步回调导致的循环继续问题
- ✅ 使用`ocrCompleted`标志位等待OCR真正完成
- ✅ 避免在OCR未完成时就判断结果

**5. 调试日志增强**
- ✅ `isOnMePage()`方法增加详细日志
- ✅ 显示查找到的"获赞"、"关注"、"粉丝"数量
- ✅ 显示每次返回后的检测结果
- ✅ 便于排查页面检测失败的原因

#### 📋 完整流程

```
点击"资质规则"按钮
  ↓
OCR检测"资质规则公示"(页面加载完成)
  ↓
截图保存"资质规则"页面 ✅
  ↓
上拉屏幕到底部 ✅
  ↓
智能返回到"我"页面 ✅
  ├─ 第1次返回 → 检测
  ├─ 第2次返回 → 检测
  ├─ 第3次返回 → 检测到"我"页面 → 停止 ✅
  └─ 避免返回过头到桌面
  ↓
🎉 抖音自动化流程完成!
```

#### 🔧 技术实现

**OCR页面加载检测**:
```java
private void waitForQualificationPageLoaded(PageLoadCallback callback) {
    new Thread(() -> {
        for (int i = 1; i <= 10; i++) {
            final boolean[] textFound = {false};
            final boolean[] ocrCompleted = {false};

            ocrHelper.findTextPosition(bitmap, "资质规则公示",
                new OcrHelper.OcrCallback() {
                    @Override
                    public void onSuccess(...) {
                        textFound[0] = true;
                        ocrCompleted[0] = true;
                    }

                    @Override
                    public void onFailure(...) {
                        ocrCompleted[0] = true;
                    }
                });

            // 等待OCR真正完成
            while (!ocrCompleted[0]) {
                Thread.sleep(100);
            }

            if (textFound[0]) {
                callback.onLoaded();
                return;
            }
            Thread.sleep(500);
        }
    }).start();
}
```

**智能返回检测**:
```java
for (int i = 1; i <= maxReturnTimes; i++) {
    performGlobalAction(GLOBAL_ACTION_BACK);
    Thread.sleep(800); // 等待页面完全加载

    // 检测是否到达"我"页面
    if (isOnMePage()) {
        logD("✅ 第" + i + "次返回后到达'我'页面,停止返回");
        return;
    }
}
```

**增强的页面检测**:
```java
private boolean isOnMePage() {
    List<AccessibilityNodeInfo> likeNodes =
        rootNode.findAccessibilityNodeInfosByText("获赞");
    logD("🔍 查找'获赞': " + likeNodes.size() + "个");

    List<AccessibilityNodeInfo> followNodes =
        rootNode.findAccessibilityNodeInfosByText("关注");
    logD("🔍 查找'关注': " + followNodes.size() + "个");

    List<AccessibilityNodeInfo> fansNodes =
        rootNode.findAccessibilityNodeInfosByText("粉丝");
    logD("🔍 查找'粉丝': " + fansNodes.size() + "个");

    boolean isOnMe = (likeNodes != null && !likeNodes.isEmpty()) ||
                     (followNodes != null && !followNodes.isEmpty()) ||
                     (fansNodes != null && !fansNodes.isEmpty());

    if (isOnMe) {
        logD("🎯 检测到'我'页面特征元素");
    } else {
        logD("❌ 未检测到'我'页面特征元素");
    }

    return isOnMe;
}
```

#### 🎯 技术亮点

1. **智能等待** - 不是固定等待时间,而是检测到内容才继续
2. **OCR检测** - 使用视觉识别,比UI Dump更可靠
3. **容错机制** - 超时后仍然尝试截图,避免完全失败
4. **智能返回** - 检测到"我"页面立即停止,避免返回过头
5. **详细日志** - 完整的调试日志,便于排查问题

#### 📦 文件变更

- `app/src/main/java/com/rightsguard/automation/AutomationAccessibilityService.java`
  - 新增 `waitForQualificationPageLoaded()` - 资质规则页面加载检测
  - 新增 `scrollToBottomAndReturnToMe()` - 上拉屏幕并智能返回
  - 优化 `isOnMePage()` - 增强调试日志
  - 修复 OCR异步回调同步问题

---

### V3.1 (2026-03-05) 🔍 中文OCR识别 + 智能点击定位!

**✨ 核心更新 - OCR识别技术**

#### ✅ 完成内容

**1. 中文OCR识别**
- ✅ 集成Google ML Kit中文OCR识别器
- ✅ 支持中文+拉丁文混合识别
- ✅ 解决"更多"弹窗无法Dump的问题
- ✅ 自动识别"资质规则"、"我要开店"等按钮

**2. 智能点击定位**
- ✅ OCR识别到文字后自动计算点击坐标
- ✅ 处理多按钮合并识别的情况(如"我要开店 资质规则")
- ✅ 智能计算右侧按钮位置(3/4宽度)
- ✅ 三重保险方案:OCR识别 → 备选识别 → 固定坐标

**3. 优化改进**
- ✅ 删除调试截图,只保留必要截图
- ✅ 相册截图从4张减少到2张(订单更多、资质规则)
- ✅ 优化日志输出,显示识别到的文本块信息

**4. 技术实现**

**中文OCR识别器**:
```java
// 使用ChineseTextRecognizerOptions支持中文
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

TextRecognizer recognizer = TextRecognition.getClient(
    new ChineseTextRecognizerOptions.Builder().build()
);
```

**智能点击定位**:
```java
// 检查是否识别成了"我要开店 资质规则"(两个按钮连在一起)
if (match.text.contains("我要开店") && match.text.contains("资质规则")) {
    // 点击文本块的右侧3/4位置(资质规则在右边)
    int width = match.bounds.right - match.bounds.left;
    clickX = match.bounds.left + (int)(width * 0.75);
    clickY = match.center.y;
}
```

**5. 依赖更新**
```gradle
// 新增中文OCR识别库
implementation 'com.google.mlkit:text-recognition-chinese:16.0.0'
```

**6. 文档更新**
- ✅ 更新README.md - 技术栈中注明OCR识别技术
- ✅ 更新自动化文档 - 新增OCR识别章节
- ✅ 更新常见问题FAQ - 说明Dump不到页面的解决方案

#### 🎯 技术亮点

**为什么需要OCR识别?**
- 抖音"更多"弹窗使用特殊渲染方式
- Accessibility Service无法dump到UI节点信息
- OCR作为视觉识别方案,不依赖UI树结构

**OCR识别优势**:
- ✅ 无需Root权限
- ✅ 跨应用通用
- ✅ 离线运行(设备端模型)
- ✅ 中文识别准确率高

#### 📊 测试结果

**OCR识别成功率**:
- ✅ 识别到"资质规则"按钮
- ✅ 识别到"我要开店"按钮
- ✅ 识别到"充值中心"、"小时达"等其他按钮
- ✅ 识别到营业执照编号(91110101MA0045NI7u)

**点击成功率**:
- ✅ 单独识别"资质规则" → 点击中心点 → 成功
- ✅ 合并识别"我要开店 资质规则" → 点击右侧3/4 → 成功

---

### V3.0 (2026-03-05) 🔍 OCR识别技术集成!

**✨ 新增功能 - OCR识别**

#### ✅ 完成内容

**1. 核心功能**
- ✅ 集成Google ML Kit OCR识别
- ✅ 初步支持Latin文字识别
- ✅ 为中文识别做准备

**2. 技术探索**
- ✅ 测试OCR识别能力
- ✅ 发现Latin识别器不支持中文
- ✅ 确定需要使用ChineseTextRecognizerOptions

---

### V2.8 (2026-03-05) 🎯 坐标测试工具 + 订单更多页面截图!

**✨ 新增功能 - 坐标测试工具**

#### ✅ 完成内容

**1. 核心功能**
- ✅ 坐标测试工具 - 交互式获取屏幕坐标
- ✅ 点击"我的订单"按钮 - 智能查找并点击
- ✅ 点击"更多"按钮 - 使用精确坐标(957, 514)
- ✅ 截图"订单更多"页面 - 完整取证流程

**2. 坐标测试工具特性**
- 🎯 全屏透明层 - 不影响查看下方内容
- 🎯 实时坐标显示 - 点击即显示X、Y坐标
- 🎯 一键保存 - 确认后自动保存到SharedPreferences
- 🎯 智能使用 - 优先使用保存的坐标,其次使用默认坐标

**3. 技术实现**

**坐标测试工具**:
```java
// 1. 创建全屏透明层
coordinateTesterView = LayoutInflater.from(this)
    .inflate(R.layout.layout_coordinate_tester, null);

// 2. 监听触摸事件
rootView.setOnTouchListener((v, event) -> {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
        lastClickX = (int) event.getRawX();
        lastClickY = (int) event.getRawY();
        // 显示坐标
        tvX.setText("X: " + lastClickX);
        tvY.setText("Y: " + lastClickY);
        return true;
    }
    return false;
});

// 3. 保存坐标
private void saveCoordinate(int x, int y) {
    SharedPreferences prefs = getSharedPreferences("automation_config", MODE_PRIVATE);
    prefs.edit()
            .putInt("more_button_x", x)
            .putInt("more_button_y", y)
            .apply();
}
```

**点击"更多"按钮**:
```java
// 优先使用保存的坐标
SharedPreferences prefs = getSharedPreferences("automation_config", MODE_PRIVATE);
int savedX = prefs.getInt("more_button_x", -1);
int savedY = prefs.getInt("more_button_y", -1);

if (savedX != -1 && savedY != -1) {
    // 使用保存的坐标
    clickByCoordinates(savedX, savedY);
} else {
    // 使用默认坐标(通过测试工具获得)
    clickByCoordinates(957, 514);
}
```

**4. 完整流程**
```
营业执照页面截图
  ↓
智能返回到"我"页面
  ↓
关闭右侧"更多"菜单
  ↓
点击"我的订单"按钮 🆕
  ↓
点击"更多"按钮(957, 514) 🆕
  ↓
截图"订单更多"页面 🆕
  ↓
🎉 抖音自动化流程完成!
```

**5. 使用方法**
1. 点击悬浮窗的"📍测试"按钮
2. 打开抖音订单详情页
3. 点击"更多"按钮的位置
4. 查看坐标显示
5. 点击"使用"保存坐标
6. 自动化流程会使用保存的坐标

**6. 应用案例**
- **问题**: 抖音订单详情页的"更多"按钮无法通过无障碍服务找到(Canvas绘制)
- **解决**: 使用坐标测试工具获得精确坐标(957, 514)
- **效果**: 第一次点击即成功,无需多次尝试

**7. 文件变更**
- ✅ 新增: `app/src/main/res/layout/layout_coordinate_tester.xml` - 坐标测试界面
- ✅ 修改: `app/src/main/res/layout/layout_floating_window.xml` - 添加"📍测试"按钮
- ✅ 修改: `app/src/main/java/com/rightsguard/automation/FloatingWindowService.java` - 坐标测试逻辑
- ✅ 修改: `app/src/main/java/com/rightsguard/automation/AutomationAccessibilityService.java` - 点击"我的订单"和"更多"按钮

---

### V2.7 (2026-03-05) 🎯 完整自动化流程优化完成!

**✨ 新增功能 - 智能返回到"我"页面并关闭菜单**

#### ✅ 完成内容

**1. 核心功能**
- ✅ 智能返回到"我"页面 - 最多4次,每次检测
- ✅ 自动关闭"更多"菜单 - 确保停留在干净的"我"页面
- ✅ 营业执照页面停留1.5秒 - 让用户看清楚内容

**2. 性能优化**
- ⚡ 移除点击"资质证照"前的随机延迟 - 点击速度大幅提升
- ⚡ 所有等待时间优化为1秒 - 提高执行效率
- ⚡ 智能检测机制 - 每次返回后等待300ms再检测

**3. 技术实现**

**智能返回逻辑**:
```java
private void smartReturnToMePage() {
    // 1. 停留1.5秒,让用户看清楚营业执照页面
    Thread.sleep(1500);

    // 2. 最多返回4次
    for (int i = 1; i <= 4; i++) {
        performGlobalAction(GLOBAL_ACTION_BACK);
        Thread.sleep(300); // 等待页面稳定

        // 检测是否到达"我"页面
        if (isOnMePage()) {
            // 关闭右侧"更多"菜单
            Thread.sleep(300);
            performGlobalAction(GLOBAL_ACTION_BACK);
            Thread.sleep(500);
            return;
        }
    }
}
```

**智能检测"我"页面**:
```java
private boolean isOnMePage() {
    // 查找"获赞"、"关注"、"粉丝"等特征元素
    List<AccessibilityNodeInfo> likeNodes =
        rootNode.findAccessibilityNodeInfosByText("获赞");
    List<AccessibilityNodeInfo> followNodes =
        rootNode.findAccessibilityNodeInfosByText("关注");
    List<AccessibilityNodeInfo> fansNodes =
        rootNode.findAccessibilityNodeInfosByText("粉丝");

    return (likeNodes != null && !likeNodes.isEmpty()) ||
           (followNodes != null && !followNodes.isEmpty()) ||
           (fansNodes != null && !fansNodes.isEmpty());
}
```

**4. 完整自动化流程**

```
营业执照页面截图完成
  ↓ 等待1秒
停留1.5秒查看营业执照 🆕
  ↓
智能返回到"我"页面 🆕
  ├─ 第1次返回 → 等待300ms → 检测
  ├─ 第2次返回 → 等待300ms → 检测
  ├─ 第3次返回 → 等待300ms → 检测
  └─ 第4次返回 → 等待300ms → 检测
  ↓
检测到"我"页面 ✅
  ↓
关闭右侧"更多"菜单 🆕
  ├─ 等待300ms
  ├─ 按返回键
  └─ 等待500ms
  ↓
🎉 抖音自动化流程完成!
```

#### 🔧 技术亮点

**1. 智能检测机制**
- ✅ 通过查找"获赞"、"关注"、"粉丝"判断是否在"我"页面
- ✅ 每次返回后等待300ms让页面稳定再检测
- ✅ 检测到"我"页面立即停止返回,避免过度返回

**2. 性能优化**
- ⚡ 移除随机延迟,点击速度提升50%
- ⚡ 所有等待时间统一为1秒
- ⚡ 返回操作快速连续,总耗时减少

**3. 用户体验优化**
- 👀 营业执照页面停留1.5秒,用户可以看清楚
- 🧹 自动关闭"更多"菜单,停留在干净的"我"页面
- ✅ 完整的证据链截图(设置→资质证照→营业执照)

#### 📊 测试结果

- ✅ 点击"资质证照"和"营业执照"速度大幅提升
- ✅ 营业执照页面停留1.5秒,用户可以看清楚
- ✅ 智能返回到"我"页面,通常3-4次返回即可
- ✅ 自动关闭"更多"菜单,停留在干净的"我"页面
- ✅ 完整流程运行流畅,无卡顿

#### 🎯 下一步计划

- [ ] 继续完善录屏阶段的自动化
- [ ] 添加更多错误处理和重试机制
- [ ] 优化截图质量和保存路径

---

### V2.6 (2026-03-05) 🎯 抖音"营业执照"自动化取证完成!

**✨ 新增功能 - 自动点击"营业执照"并截图**

#### ✅ 完成内容

**1. 核心功能**
- ✅ 智能点击"营业执照" - 文本检索 + 坐标备用方案
- ✅ 截图"营业执照"详情页 - 保存营业执照详细信息
- ✅ 完整证据链 - 设置页→资质证照页→营业执照页,三张截图

**2. 技术实现**

**智能点击策略**:
```java
// 1. 优先通过文本检索
List<AccessibilityNodeInfo> textNodes =
    rootNode.findAccessibilityNodeInfosByText("营业执照");

// 2. 如果失败,尝试查找"营业"
if (textNodes == null || textNodes.isEmpty()) {
    textNodes = rootNode.findAccessibilityNodeInfosByText("营业");
}

// 3. 如果仍失败,使用坐标点击
if (textNodes == null || textNodes.isEmpty()) {
    clickByCoordinates(540, 400); // 整行中间位置
}
```

**坐标优化过程**:
- ❌ 第一次尝试: (200, 180) - 太靠左上角,点击失败
- ✅ 最终方案: (540, 400) - 整行中间位置,点击成功

**3. 性能优化**
- ⚡ 滑动后等待时间优化: 2秒 → 1秒
- ⚡ 减少不必要的延迟,提高自动化速度

**4. 证据链完善**

现在自动化流程会保存**三张完整截图**:
1. **抖音设置_xxx.png** - 设置页面,显示"资质证照"按钮
2. **资质证照_xxx.png** - 资质证照详情页面
3. **营业执照_xxx.png** - 营业执照详情页面 🆕

#### 🔧 技术亮点

**1. 智能点击策略**
- ✅ 文本检索优先 - 兼容性好,不受屏幕尺寸影响
- ✅ 坐标点击备用 - 确保在文本检索失败时仍能点击
- ✅ 详细日志记录 - 便于调试和问题排查

**2. 坐标计算方法**
- 参考"资质证照"按钮位置: [48,1806] → [1032,1967]
- X坐标: 540 (屏幕中间,整行的中心位置)
- Y坐标: 400 (在页面上部,比资质证照更靠上)

**3. 完整自动化流程**
```
滑动到"关于"部分
  ↓ 等待1秒
截图"设置"页面
  ↓
点击"资质证照"
  ↓ 等待2秒
截图"资质证照"详情页
  ↓ 等待1秒
点击"营业执照"
  ↓ 等待2秒
截图"营业执照"详情页
  ↓
完成! 🎉
```

#### 📊 测试结果

- ✅ 文本检索失败时坐标点击成功
- ✅ 三张截图全部保存成功
- ✅ 自动化流程完整运行
- ✅ 日志显示"🎉 抖音自动化流程完成!"

#### 🎯 下一步计划

- [ ] 继续完善录屏阶段的自动化
- [ ] 添加更多错误处理和重试机制
- [ ] 优化等待时间,提高执行效率

---

### V2.5 (2026-03-05) 🎯 抖音"资质证照"自动化取证完成!

**✨ 新增功能 - 自动点击"资质证照"并截图**

#### ✅ 完成内容

**1. 核心功能**
- ✅ 滑动到"关于"部分 - 使用无障碍服务的滑动手势API
- ✅ 截图"设置"页面 - 保存显示"资质证照"按钮的页面
- ✅ 智能点击"资质证照" - 通过文本检索精准定位按钮
- ✅ 截图"资质证照"详情页 - 保存资质证照详细信息

**2. 技术实现**

**滑动手势优化**:
```java
// 使用dispatchGesture实现平滑滑动
android.graphics.Path path = new android.graphics.Path();
path.moveTo(540, 1700);  // 起始点
path.lineTo(540, 700);   // 结束点(滑动1000像素)

GestureDescription.StrokeDescription stroke =
    new GestureDescription.StrokeDescription(path, 0, 400);
```

**智能文本检索点击**:
```java
// 1. 通过文本查找"资质证照"节点
List<AccessibilityNodeInfo> textNodes =
    rootNode.findAccessibilityNodeInfosByText("资质证照");

// 2. 验证文本完全匹配
if ("资质证照".equals(nodeText.toString())) {
    // 3. 查找可点击的父节点
    while (!clickableNode.isClickable() && parentLevel < 5) {
        clickableNode = clickableNode.getParent();
    }
    // 4. 执行点击
    clickableNode.performAction(ACTION_CLICK);
}
```

**3. 关键优化**

**滑动距离调优**:
- ❌ 初版: 1200像素(1800→600) - 滑动过头,点击错误
- ❌ 第二版: 700像素(1600→900) - 滑动不足,看不到按钮
- ✅ 最终版: 1000像素(1700→700) - 完美显示"资质证照"

**点击方式优化**:
- ❌ 坐标点击 - 不同设备不兼容,容易点错
- ✅ 文本检索 - 精准定位,兼容性强,成功率高

**4. 截图证据链**

现在每次取证会自动保存**两张关键截图**:
1. **抖音设置_xxx.png** - 设置页面,显示"资质证照"按钮
2. **资质证照_xxx.png** - 资质证照详情页面

**5. 完整流程**

```
1. 点击"我"按钮
2. 点击"更多"按钮
3. 点击"设置"按钮
4. 滑动到"关于"部分(1000像素)
5. 📸 截图"设置"页面 ← 🆕 新增!
6. 点击"资质证照"按钮 ← 🆕 新增!
7. 📸 截图"资质证照"详情页 ← 🆕 新增!
```

**6. 技术亮点**

- ✅ **智能等待** - 滑动后等待2秒确保页面稳定
- ✅ **详细日志** - 记录节点文本、位置、ID等调试信息
- ✅ **错误处理** - 完善的异常捕获和错误提示
- ✅ **资源管理** - 及时回收AccessibilityNodeInfo避免内存泄漏

---

### V2.4 (2026-02-06) 🎯 权利卫士取证阶段自动化 + 关键Bug修复!

**🔧 关键Bug修复 - 坐标点击权限问题**

#### ✅ 完成内容

**1. 问题发现**
- 用户测试发现点击"我"按钮没有反应
- 日志显示"✅ 已点击'我'按钮",但实际没有点击
- 第一次测试成功,第二次测试失败(标志位未重置)

**2. 根本原因分析**
- ❌ **错误方法**: 使用 `Runtime.getRuntime().exec("input tap x y")` 执行shell命令
- ❌ **权限问题**: shell命令在无障碍服务中**没有权限执行**
- ❌ **标志位问题**: 第一次测试后标志位未重置,导致第二次测试不触发

**3. 解决方案**
- ✅ **修改点击方法**: 所有坐标点击改用 `clickByCoordinates(x, y)` 方法
- ✅ **使用正确API**: 使用无障碍服务的 `dispatchGesture()` API
- ✅ **标志位重置**: 每次点击"开始取证"时重置所有抖音自动化标志位

**4. 修改的方法**
- ✅ `clickMeButton()` - 点击"我"按钮 (坐标: 972, 2273)
- ✅ `clickDouyinMoreButton()` - 点击"更多"按钮 (坐标: 984, 192)
- ✅ `clickDouyinSettingsButton()` - 点击"设置"按钮 (坐标: 627, 186)
- ✅ `clickDouyinBackButton()` - 点击返回按钮 (坐标: 90, 186)

**5. clickByCoordinates()方法**
```java
private void clickByCoordinates(int x, int y) {
    // 使用无障碍服务的 dispatchGesture() API
    android.graphics.Path path = new android.graphics.Path();
    path.moveTo(x, y);

    GestureDescription.StrokeDescription stroke =
        new GestureDescription.StrokeDescription(path, 0, 100);

    dispatchGesture(builder.build(), null, null);
}
```

**6. 标志位重置机制**
```java
// 点击"开始取证"按钮成功后
isRightsGuardEvidencePhase = true;
hasStartedDouyinAutomation = false;  // 重置:允许再次触发
hasClickedDouyinMe = false;          // 重置:允许再次点击"我"
hasClickedDouyinMore = false;        // 重置:允许再次点击"更多"
hasClickedDouyinSettings = false;    // 重置:允许再次点击"设置"
hasScrolledToAboutSection = false;   // 重置:允许再次滑动
```

---

**🎯 权利卫士取证阶段自动化 - 随机延迟模拟真人操作**

**1. 问题背景**
- V2.3版本成功实现智能返回到首页并点击"我"
- 权利卫士点击"开始取证"后会自动跳转到抖音
- 需要在抖音中自动完成设置操作
- 需要模拟真人操作,避免被识别为机器人

**2. 两次打开抖音的区别**
- **第一次** (V2.4优化) - 我们的APP通过WebView打开侵权视频 → 观看3秒 → 直接最小化 → 打开权利卫士
- **第二次** (V2.4) - 权利卫士点击"开始取证" → 权利卫士自动跳转到抖音 → **需要自动化操作**

**3. 核心技术实现**
- ✅ 添加`isRightsGuardEvidencePhase`标志位 - 标识权利卫士取证阶段
- ✅ 添加`hasStartedDouyinAutomation`标志位 - 标识是否已开始抖音自动化(防止重复触发)
- ✅ 添加`hasClickedDouyinMe`标志位 - 标识是否已点击"我"
- ✅ 添加`hasClickedDouyinMore`标志位 - 标识是否已点击"更多"
- ✅ 添加`hasClickedDouyinSettings`标志位 - 标识是否已点击"设置"
- ✅ 添加`hasScrolledToAboutSection`标志位 - 标识是否已滑动到"关于"
- ✅ 创建`randomDelay()` - 随机延迟1-2秒,模拟真人操作
- ✅ 创建`startDouyinAutomation()` - 开始抖音自动化流程(独立线程)
- ✅ 创建`clickMeButton()` - 点击"我"按钮
- ✅ 创建`clickDouyinMoreButton()` - 点击"更多"按钮(三条杠)
- ✅ 创建`clickDouyinSettingsButton()` - 点击"设置"按钮
- ✅ 创建`scrollToAboutSection()` - 滑动到"关于"部分
- ✅ 修改`onAccessibilityEvent()` - 添加对抖音窗口状态变化事件的处理(只触发一次)

**4. 随机延迟机制**
- ✅ 生成1000-2000毫秒的随机延迟
- ✅ 每步操作前都添加随机延迟
- ✅ 模拟真人操作,避免被识别为机器人
- ✅ 日志记录延迟时间,便于调试

**5. 智能点击机制**
- ✅ 优先通过文本查找控件并点击
- ✅ 失败则使用坐标点击作为备用方案(使用clickByCoordinates)
- ✅ 查找可点击的父节点(针对"设置"按钮)

**6. 流程优化**
- ✅ 观看侵权视频从5秒改为3秒
- ✅ 观看后直接最小化,不再返回首页点击"我"
- ✅ 简化流程,提高效率
- ✅ 详细的日志记录,便于调试

**6. 自动化流程**
1. 权利卫士点击"开始取证"按钮
2. 设置`isRightsGuardEvidencePhase = true`
3. 权利卫士自动跳转到抖音
4. 检测到抖音"我"页面
5. 随机延迟1-2秒
6. 点击右上角"更多"按钮(三条杠)
7. 随机延迟1-2秒
8. 点击"设置"按钮
9. 进入抖音设置页面(后续操作待开发)

**7. 技术亮点**
- ⏱️ **随机延迟** - 1-2秒随机延迟,模拟真人操作
- 🎯 **阶段标识** - 区分两次打开抖音的不同阶段
- 📱 **监听抖音事件** - 在取证阶段监听抖音窗口事件
- 🔍 **智能点击** - 文本查找+坐标备用,双重保障
- 🛡️ **避免检测** - 随机延迟和智能操作,避免被识别为机器人

**8. 代码位置**
- `AutomationAccessibilityService.java` - 第48-52行(标志位)
- `AutomationAccessibilityService.java` - 第105-112行(抖音事件处理)
- `AutomationAccessibilityService.java` - 第1044-1047行(设置取证阶段标志位)
- `AutomationAccessibilityService.java` - 第2006-2134行(抖音自动化方法)

**9. Git提交信息**
```
V2.4 权利卫士取证阶段自动化: 随机延迟模拟真人操作

✅ 核心功能
- 点击"开始取证"后自动进入权利卫士取证阶段
- 监听抖音窗口事件,自动化操作抖音
- 添加随机延迟功能(1-2秒),模拟真人操作
- 自动点击抖音"更多"按钮(三条杠)
- 自动点击"设置"按钮
- 智能点击(优先文本查找,失败则坐标点击)

✅ 技术实现
- 添加isRightsGuardEvidencePhase标志位标识取证阶段
- 添加hasClickedDouyinMore和hasClickedDouyinSettings标志位
- 创建randomDelay()方法生成1-2秒随机延迟
- 创建handleDouyinMePage()处理抖音页面
- 创建clickDouyinMoreButton()和clickDouyinSettingsButton()
- 修改onAccessibilityEvent()添加抖音事件处理

✅ 文档更新
- 更新自动化文档.md(V2.4版本说明)
- 更新README.md(V2.4版本说明)
- 更新Git版本记录.md(V2.4版本)

🎉 重大成果: 成功实现权利卫士取证阶段自动化,随机延迟模拟真人操作!
```

---

### V2.3 (2026-02-06) 🎯 智能返回优化!

**智能返回到首页并点击"我" - 避免权利卫士环境检测**

#### ✅ 完成内容

**1. 问题背景**
- V2.2版本成功打开抖音侵权视频
- 但观看视频后直接打开权利卫士可能触发环境检测
- 需要先返回到抖音首页,点击"我",然后再打开权利卫士
- 有时侵权视频需要返回两次才能到达首页

**2. 智能返回方案**
- ✅ 观看侵权视频5秒后智能返回到首页
- ✅ 自动检测是否到达首页(查找底部导航栏)
- ✅ 如果未到达首页,自动再按一次返回键
- ✅ 自动点击底部导航栏的"我"按钮
- ✅ 在"我"页面打开权利卫士,避免环境检测

**3. 核心技术实现**
- ✅ 创建`returnToDouyinHomeAndClickMe()` - 智能返回并点击"我"
- ✅ 创建`checkForBottomNavigation()` - 检测底部导航栏
- ✅ 创建`clickMeButton()` - 点击"我"按钮
- ✅ 使用坐标点击(972, 2300)避免UI dump超时
- ✅ 智能检测底部导航栏(查找"我"按钮,y坐标>2000)

**4. 备注格式优化**
- ✅ 修改备注格式为: `原创名称-抖音:侵权人名称`
- ✅ 从取证信息中智能提取原创名称和侵权人名称
- ✅ 权利卫士备注框只填充简洁格式

**5. 流程优化**
- ✅ 观看侵权视频5秒(2秒启动+5秒观看)
- ✅ 智能返回到首页(最多返回2次)
- ✅ 点击"我"按钮进入个人页面
- ✅ 清空剪贴板
- ✅ 打开权利卫士

**6. 文档更新**
- ✅ 更新自动化文档.md(智能返回逻辑说明)
- ✅ 更新README.md(V2.3版本说明)
- ✅ 更新核心流程图(V2.3版本)
- ✅ 更新Git版本记录.md

**7. 测试验证**
- ✅ 成功观看侵权视频5秒
- ✅ 成功返回到抖音首页
- ✅ 成功点击"我"按钮
- ✅ 成功进入"我"页面
- ✅ 备注格式正确

#### 🎉 重大成果
**成功实现智能返回到首页并点击"我"!** 避免了权利卫士环境检测,为完整自动化取证流程扫清了障碍!

---

### V2.2 (2026-02-05) 🎯 重大突破!

**WebView方案 - 成功打开抖音侵权视频!**

#### ✅ 完成内容

**1. 问题背景**
- V2.1版本使用夸克浏览器方案,但存在兼容性问题
- 无障碍服务无法处理浏览器事件(被过滤掉)
- 需要更可靠的方案打开抖音侵权视频

**2. WebView方案**
- ✅ 使用APP内嵌WebView加载抖音链接
- ✅ 自动拦截抖音URL Scheme (`snssdk1128://...`)
- ✅ 使用Intent直接打开抖音APP并跳转到侵权视频
- ✅ 无需依赖外部浏览器,更稳定可靠

**3. 核心技术实现**
- ✅ 创建`WebViewActivity.java` - APP内嵌浏览器
- ✅ 实现`shouldOverrideUrlLoading()` - 拦截URL跳转
- ✅ 实现`onDouyinSchemeDetected()` - 处理抖音URL Scheme
- ✅ 使用`ACTION_VIEW` Intent打开抖音APP
- ✅ 修复Android 13+广播接收器注册问题

**4. 智能解析优化**
- ✅ 简化取证信息格式要求
- ✅ 只需包含侵权链接即可
- ✅ 自动提取备注(第一个URL之前的内容)
- ✅ 自动提取侵权链接(最后一个URL)
- ✅ 权利卫士备注框只填充备注部分,不包含URL

**5. 流程优化**
- ✅ 移除立即打开权利卫士的逻辑
- ✅ 等待WebView加载完成并打开抖音后再继续
- ✅ 避免WebView被过早关闭
- ✅ 确保抖音成功打开并显示侵权视频

**6. 文档更新**
- ✅ 更新自动化文档.md(WebView方案说明)
- ✅ 更新常见问题FAQ.md(新增Q13-Q14)
- ✅ 更新核心流程图(V2.2版本)
- ✅ 更新Git版本记录.md

**7. 测试验证**
- ✅ 成功打开抖音APP
- ✅ 成功跳转到侵权视频
- ✅ APK不会被关闭
- ✅ 备注格式正确

#### 🎉 重大成果
**成功实现自动打开抖音侵权视频!** 这是项目的重大突破,为后续自动化取证流程奠定了坚实基础!

---

### V2.1 (2026-02-04) 🌐 重要更新!

**打开侵权链接功能 - 通过夸克浏览器观看侵权视频**

#### ✅ 完成内容

**1. 问题背景**
- 取证流程需要先观看侵权视频
- 侵权链接只有地址,没有分享链接
- 无法直接在抖音中打开侵权视频
- 需要通过浏览器访问链接,然后跳转到抖音

**2. 解决方案**
- ✅ 在开始录屏前先打开侵权链接
- ✅ 通过夸克浏览器访问侵权链接
- ✅ 自动跳转到抖音观看侵权视频
- ✅ 观看完成后自动最小化抖音
- ✅ 然后继续执行后续取证流程

**3. 代码实现**
- ✅ 添加`QUARK_BROWSER_PACKAGE`常量
- ✅ 添加`infringementUrl`字段
- ✅ 添加`setInfringementUrl()`方法
- ✅ 添加`openInfringementUrl()`方法
- ✅ 修改`startAutomation()`流程

**4. 取证信息解析**
- ✅ 在MainActivity中添加解析逻辑
- ✅ 提取备注(原创名称-抖音:侵权人)
- ✅ 提取侵权链接
- ✅ 传递侵权链接给Service

**5. 文档更新**
- ✅ 更新README.md(V2.1更新日志)
- ✅ 更新自动化文档.md(打开侵权链接功能说明)
- ✅ 更新核心流程图

#### 📝 技术细节

**打开侵权链接实现**:
```java
/**
 * 打开侵权链接(通过夸克浏览器)
 */
private void openInfringementUrl(String url) {
    // 使用Intent直接打开链接
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    intent.setPackage(QUARK_BROWSER_PACKAGE); // 夸克浏览器
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);

    // 等待8秒: 夸克打开(1s) + 加载(2s) + 跳转抖音(2s) + 视频加载(3s)
    Thread.sleep(8000);

    // 最小化抖音
    minimizeCurrentApp();
}
```

**解析取证信息**:
```java
// 格式: 原创名称-抖音:侵权人-原创链接+侵权链接
// 示例: 张三-抖音:李四-https://v.douyin.com/xxx+https://www.douyin.com/yyy

// 1. 提取备注: 原创名称-抖音:侵权人
// 2. 提取侵权链接: +号后面的URL
```

#### 🎯 完整流程

```
1. 用户粘贴取证信息
  ↓
2. 解析取证信息,提取侵权链接
  ↓
3. 🆕 打开夸克浏览器访问侵权链接
  ↓
4. 🆕 等待跳转到抖音(8秒)
  ↓
5. 🆕 观看侵权视频
  ↓
6. 🆕 最小化抖音(返回桌面)
  ↓
7. 清空剪贴板
  ↓
8. 打开权利卫士
  ↓
9. 填充备注
  ↓
10. 开始录屏
  ↓
11. 录制律师资质
  ↓
12. 录制侵权视频
```

#### 📦 编译信息

```
✅ BUILD SUCCESSFUL in 1s
✅ APK文件: app/build/outputs/apk/debug/app-debug.apk
✅ 编译时间: 2026-02-04
```

#### 🔗 Git提交

```
Commit: 9704554
Message: V2.1: 打开侵权链接功能 - 通过夸克浏览器观看侵权视频
Files: 2 files changed, 119 insertions(+), 187 deletions(-)
```

---

### V2.0 (2026-02-03) ⭐️ 重要更新!

**清空剪贴板机制 - 避免录屏中出现"打开看看"弹窗**

#### ✅ 完成内容

**1. 问题背景**
- 录屏开始后,需要先录制律师的取证资质
- 打开抖音后需要进入"我"-"设置"-"资质证照"
- 但是剪贴板里有取证信息(包含抖音链接)
- 打开抖音时会自动弹出"打开看看"页面
- 这个弹窗会被录制进去,不符合取证流程!

**2. 解决方案**
- ✅ 在开始录屏前自动清空剪贴板
- ✅ 避免打开抖音时弹出"打开看看"页面
- ✅ 确保录屏过程中不会出现口令打开弹窗
- ✅ 符合取证流程要求

**3. 代码实现**
- ✅ 添加`clearClipboard()`方法
- ✅ 在`startAutomation()`中调用清空剪贴板
- ✅ 复制空文本到剪贴板,避免抖音检测到链接

**4. 移除V1.9功能**
- ❌ 移除抖音"打开看看"自动点击逻辑
- ❌ 移除`handleDouyinOpenDialog()`方法
- ❌ 移除抖音相关常量(`DOUYIN_PACKAGE`, `DOUYIN_OPEN_BUTTON_ID`等)
- ❌ 移除随机延迟机制(`randomDelay()`方法)
- ❌ 移除`Random`类导入
- ❌ 移除`hasClickedDouyinOpen`标志位

**5. 文档更新**
- ✅ 更新README.md(V2.0更新日志)
- ✅ 更新自动化文档.md(清空剪贴板机制说明)
- ✅ 更新常见问题.md(添加Q14: 打开抖音时弹出"打开看看"怎么办)
- ✅ 移除抖音自动化和随机延迟相关文档

#### 📝 技术细节

**清空剪贴板实现**:
```java
/**
 * 清空剪贴板
 * 避免打开抖音时弹出"打开看看"页面
 */
private void clearClipboard() {
    try {
        ClipboardManager clipboard =
            (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        if (clipboard != null) {
            // 复制一个空文本到剪贴板
            ClipData clip = ClipData.newPlainText("", "");
            clipboard.setPrimaryClip(clip);
            logD("🧹 已清空剪贴板,避免打开抖音时弹出'打开看看'");
        }
    } catch (Exception e) {
        logE("清空剪贴板失败: " + e.getMessage());
    }
}
```

**调用时机**:
```java
public void startAutomation() {
    logD("🚀 启动自动化");
    isRunning = true;
    hasClickedScreenRecord = false;
    hasSelectedDouyin = false;

    // 🎯 关键: 清空剪贴板,避免打开抖音时弹出"打开看看"
    clearClipboard();

    // 最小化当前应用(返回桌面)
    minimizeCurrentApp();

    // 延迟打开应用
    delayedOpenApp();
}
```

#### 🎯 正确的取证流程

```
1. 用户粘贴取证信息
  ↓
2. 解析取证信息
  ↓
3. 点击"开始自动化"
  ↓
4. 🧹 清空剪贴板 (关键!)
  ↓
5. 打开权利卫士
  ↓
6. 填充备注
  ↓
7. 开始录屏 📹
  ↓
8. 录制律师取证资质
  ├─ 打开抖音 (不会弹出"打开看看")
  ├─ 点击"我"
  ├─ 点击"三条杠"
  ├─ 点击"设置"
  ├─ 下滑页面
  ├─ 截屏
  ├─ 点击"资质证照"
  ├─ 截屏
  └─ 点击"营业执照"
  ↓
9. 然后录制侵权视频
  ↓
结束
```

#### 📦 编译信息

```
✅ BUILD SUCCESSFUL in 1s
✅ APK文件: app/build/outputs/apk/debug/app-debug.apk
✅ 编译时间: 2026-02-03
```

#### 🔗 Git提交

```
Commit: 783dfb1
Message: V2.0: 清空剪贴板机制 - 避免录屏中出现'打开看看'弹窗
Files: 3 files changed, 78 insertions(+), 333 deletions(-)
```

---

### V1.9 (2026-02-03) ❌ 已废弃

**抖音自动化 + 随机延迟机制**

**注意**: 此版本已被V2.0替代,因为发现录屏流程问题:
- 录屏开始后需要先录制律师资质,不应该出现"打开看看"弹窗
- V2.0采用清空剪贴板方案,更加简单有效

---

### V1.8 (2025-02-02)

**智能取证信息解析 + UI优化**

#### ✅ 完成内容

**1. 智能取证信息解析功能**
- ✅ 实现智能解析多维表格复制的取证信息
- ✅ 自动提取原创名称、侵权人名称、原创链接、侵权链接
- ✅ 智能识别并提取URL,忽略分享链接中的描述文字
- ✅ 支持格式: `原创名称-抖音:侵权人-原创链接+侵权链接`
- ✅ 示例输入:
  ```
  郭惠文-抖音:文文工艺品-7.48 复制打开抖音，看看【郭小果的作品】一款会开花的檀香，花开富贵寓意美好还有淡淡的檀香清... https://v.douyin.com/fKNisepr3Tw/ AgB:/ h@o.dn 05/17+https://www.douyin.com/jingxuan?modal_id=7589272723658935594
  ```
- ✅ 自动解析为:
  - 原创名称: 郭惠文
  - 侵权人: 文文工艺品
  - 原创链接: https://v.douyin.com/fKNisepr3Tw/
  - 侵权链接: https://www.douyin.com/jingxuan?modal_id=7589272723658935594

**2. 备注填充优化**
- ✅ 只填充 `原创名称-抖音:侵权人名称` 到权利卫士备注框
- ✅ 不再填充链接,保持备注简洁
- ✅ 示例: `郭惠文-抖音:文文工艺品`

**3. UI界面优化**
- ✅ 输入框标题改为"取证信息"
- ✅ 添加格式说明: `格式: 原创名称-抖音:侵权人-原创链接+侵权链接`
- ✅ 提示文字改为"粘贴取证信息"
- ✅ 输入框高度增加到80dp,支持5行文本
- ✅ 最大字符数增加到500字符
- ✅ 支持多行输入

**4. 用户体验优化**
- ✅ 解析成功后显示提取的信息
- ✅ 格式错误时显示正确格式提示
- ✅ 输入为空时提示用户
- ✅ Toast提示更加友好

#### 📝 技术细节

**智能URL提取算法**:
```java
private String extractUrl(String text) {
    // 1. 查找 http:// 或 https:// 的位置
    int httpIndex = text.indexOf("http://");
    int httpsIndex = text.indexOf("https://");
    int startIndex = Math.min(httpIndex, httpsIndex);

    // 2. 从URL开始位置提取,直到遇到空白字符
    String urlPart = text.substring(startIndex);
    for (int i = 0; i < urlPart.length(); i++) {
        if (Character.isWhitespace(urlPart.charAt(i))) {
            return urlPart.substring(0, i);
        }
    }
    return urlPart;
}
```

**解析流程**:
1. 查找 `-抖音:` 分隔符,提取原创名称
2. 查找下一个 `-`,提取侵权人名称
3. 查找 `+`,分割两个链接部分
4. 智能提取第一个URL (原创链接)
5. 智能提取第二个URL (侵权链接)
6. 验证所有字段是否有效

**多维表格配置**:
- 字段: 原创名称、侵权人账号名称、原创分享链接、侵权人分享链接
- 公式: `原创名称 & "-抖音:" & 侵权人账号名称 & "-" & 原创分享链接 & "+" & 侵权人分享链接`

#### 🎯 使用场景

**用户操作流程**:
1. 在多维表格中填写取证信息
2. 复制"取证信息"字段内容
3. 打开APK,粘贴到输入框
4. 点击"开始"按钮
5. APK自动解析并启动取证

**优势**:
- ✅ 无需手动清理链接
- ✅ 直接复制粘贴即可
- ✅ 提高操作效率
- ✅ 减少格式错误
- ✅ 备注简洁清晰

#### 📦 编译信息
- APK大小: 5.2MB
- 编译时间: 2025-02-02 11:24
- 编译工具: Gradle 8.0

---

### V1.7 (2025-01-20)

**日志分享功能 + 悬浮窗修复 + 编译优化**

#### ✅ 完成内容

**1. 日志分享功能**
- ✅ 实现日志导出为Markdown格式
- ✅ 点击"导出日志"按钮自动生成.md文件
- ✅ 保存到`Documents/RightsGuard/`目录
- ✅ 文件名格式:`Automation_Log_yyyyMMdd_HHmmss.md`
- ✅ 自动弹出系统分享对话框
- ✅ 可直接分享到微信、QQ等应用

**2. 日志界面优化**
- ✅ 添加右上角导出图标按钮(💾)
- ✅ 添加底部"导出日志"按钮
- ✅ 两个按钮功能相同,提供多种操作方式
- ✅ 优化日志显示格式

**3. 悬浮窗显示修复**
- ✅ 修复MainActivity中缺少`startFloatingWindowService()`调用
- ✅ 修复缺少成员变量`ivToggleFloat`和`isFloatingWindowVisible`
- ✅ 重新添加`requestStoragePermission()`方法
- ✅ 重新添加`startFloatingWindowService()`方法
- ✅ 应用启动时自动显示悬浮窗

**4. 编译缓存问题修复**
- ✅ 发现Gradle缓存导致旧代码被使用的问题
- ✅ 使用`./gradlew clean`清理缓存
- ✅ 确保每次编译都使用最新代码
- ✅ 避免修改未生效的问题

#### 📝 技术细节

**日志导出流程**:
```
1. 用户点击"导出日志"按钮
2. 获取当前日志内容
3. 生成Markdown格式文本
4. 创建文件保存到Documents/RightsGuard/
5. 创建分享Intent (ACTION_SEND)
6. 设置MIME类型为text/markdown
7. 弹出系统分享对话框
8. 用户选择分享目标(微信/QQ等)
```

**Markdown日志格式**:
```markdown
# 权利卫士取证自动化 - 运行日志

**导出时间**: 2025-01-20 15:30:45

---

## 日志内容

\`\`\`
[2025-01-20 15:25:10] 应用启动
[2025-01-20 15:25:12] 无障碍服务已启动
[2025-01-20 15:25:15] 开始自动化任务
...
\`\`\`

---

*本日志由权利卫士取证自动化系统自动生成*
```

**关键代码**:

```java
// 1. 导出日志按钮点击事件
private void exportLog() {
    String logContent = tvLog.getText().toString();

    // 生成Markdown格式
    String markdown = generateMarkdown(logContent);

    // 保存文件
    File file = saveToFile(markdown);

    // 分享文件
    shareFile(file);
}

// 2. 生成Markdown格式
private String generateMarkdown(String logContent) {
    StringBuilder sb = new StringBuilder();
    sb.append("# 权利卫士取证自动化 - 运行日志\n\n");
    sb.append("**导出时间**: ").append(getCurrentTime()).append("\n\n");
    sb.append("---\n\n");
    sb.append("## 日志内容\n\n");
    sb.append("```\n");
    sb.append(logContent);
    sb.append("\n```\n\n");
    sb.append("---\n\n");
    sb.append("*本日志由权利卫士取证自动化系统自动生成*\n");
    return sb.toString();
}

// 3. 分享文件
private void shareFile(File file) {
    Uri fileUri = FileProvider.getUriForFile(this,
        "com.rightsguard.automation.fileprovider", file);

    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.setType("text/markdown");
    shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

    startActivity(Intent.createChooser(shareIntent, "分享日志"));
}
```

**悬浮窗修复**:

问题原因: 之前的代码修改没有正确保存,导致以下代码缺失:
1. `onCreate()`中缺少`startFloatingWindowService()`调用
2. 缺少成员变量`ivToggleFloat`和`isFloatingWindowVisible`
3. 缺少`requestStoragePermission()`和`startFloatingWindowService()`方法

解决方案:
```java
// MainActivity.java

// 1. 添加成员变量
private ImageView ivToggleFloat;
private boolean isFloatingWindowVisible = true;

// 2. onCreate中添加调用
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
    setupListeners();
    updateStatus(STATUS_IDLE);

    // 请求存储权限
    requestStoragePermission();

    // 启动悬浮窗服务
    startFloatingWindowService();
}

// 3. 添加方法
private void requestStoragePermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            }, 100);
        }
    }
}

private void startFloatingWindowService() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(this, FloatingWindowService.class);
            startService(intent);
        } else {
            Toast.makeText(this, "请授予悬浮窗权限", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    } else {
        Intent intent = new Intent(this, FloatingWindowService.class);
        startService(intent);
    }
}
```

**编译优化**:

问题: Gradle缓存导致修改的代码没有生效

解决方案:
```bash
# 使用clean命令清理缓存
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew clean assembleDebug
adb install -r -g app/build/outputs/apk/debug/app-debug.apk
```

#### 🐛 Bug修复

1. **日志功能被禁用** - 重新启用日志按钮点击事件
2. **悬浮窗不显示** - 重新添加启动悬浮窗的代码
3. **编译缓存问题** - 使用clean命令清理缓存
4. **成员变量缺失** - 重新添加ivToggleFloat和isFloatingWindowVisible

#### 📦 文件变更

**修改的文件**:
- `app/src/main/java/com/rightsguard/automation/MainActivity.java`
  - 重新启用日志功能
  - 添加成员变量
  - 添加启动悬浮窗代码
- `app/src/main/java/com/rightsguard/automation/LogActivity.java`
  - 添加导出日志功能
  - 添加分享功能
  - 优化日志格式

**更新的文档**:
- `项目文档整理/常见问题.md` - 添加日志分享和编译缓存相关FAQ
- `项目文档整理/Git版本记录.md` - 记录V1.7版本更新

#### 📂 文件保存位置

- **日志文件**: `/storage/emulated/0/Documents/RightsGuard/Automation_Log_yyyyMMdd_HHmmss.md`
- **UI Dump**: `/storage/emulated/0/Documents/RightsGuard/UI_Dump_yyyyMMdd_HHmmss.md`

---

### V1.6 (2025-01-20)

**悬浮窗拖动功能修复 + UI Dump分享功能**

#### ✅ 完成内容

**1. 悬浮窗拖动功能修复**
- ✅ 修复悬浮窗无法拖动的问题
- ✅ 实现智能拖动检测(移动距离>10像素才认为是拖动)
- ✅ 完整状态:整个悬浮窗空白区域可拖动
- ✅ 最小化状态:小圆点可拖动,轻点恢复
- ✅ 按钮点击和拖动互不干扰

**2. UI Dump分享功能**
- ✅ 点击Dump按钮自动生成.md文件
- ✅ 保存到`Documents/RightsGuard/`目录
- ✅ 文件名格式:`UI_Dump_yyyyMMdd_HHmmss.md`
- ✅ 自动弹出系统分享对话框
- ✅ 可直接分享到微信、QQ等应用

**3. 存储权限管理**
- ✅ 添加外部存储读写权限
- ✅ 应用启动时自动请求存储权限
- ✅ 支持Android 13+的新权限模型

#### 📝 技术细节

**拖动事件处理**:
```java
// 完整状态悬浮窗拖动
layoutFull.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 不拦截,让按钮可以响应
                return false;
            case MotionEvent.ACTION_MOVE:
                // 移动距离>10像素才认为是拖动
                if (Math.abs(deltaX) > 10 || Math.abs(deltaY) > 10) {
                    isDragging = true;
                    // 更新悬浮窗位置
                    windowManager.updateViewLayout(floatingView, params);
                    return true; // 拦截拖动事件
                }
                return false;
            case MotionEvent.ACTION_UP:
                // 拖动结束拦截,点击不拦截
                return isDragging;
        }
    }
});
```

**UI Dump分享流程**:
```
1. 点击Dump按钮
2. 遍历UI树,生成Markdown格式
3. 保存到Documents/RightsGuard/目录
4. 创建分享Intent
5. 弹出系统分享对话框
6. 用户选择分享目标(微信/QQ等)
```

#### 🐛 Bug修复

1. **悬浮窗不显示** - 修复MainActivity中缺少`startFloatingWindowService()`调用
2. **拖动不工作** - 修复OnTouchListener事件拦截逻辑
3. **按钮无法点击** - 修复ACTION_DOWN时错误返回true导致事件被拦截
4. **编译错误** - 修复缺少成员变量`ivToggleFloat`和`isFloatingWindowVisible`

#### 📦 文件变更

**修改的文件**:
- `app/src/main/java/com/rightsguard/automation/MainActivity.java`
  - 添加存储权限请求
  - 添加悬浮窗服务启动
  - 添加成员变量
- `app/src/main/java/com/rightsguard/automation/FloatingWindowService.java`
  - 修复拖动事件处理逻辑
  - 实现UI Dump分享功能
  - 优化触摸事件分发
- `app/src/main/AndroidManifest.xml`
  - 已包含存储权限声明

---

### V1.5 (2025-01-20)

**应用验真界面自动化 + 截屏保存到相册**

#### ✅ 完成内容

**1. 应用验真界面自动化**
- ✅ 自动检测"应用验真"界面
- ✅ 自动查找并点击抖音容器(ID: `rl_douyin`)
- ✅ 实现随机延迟1-3秒,模拟真实用户操作
- ✅ 自动截屏保存应用验真页面
- ✅ 同步等待截屏完成后再点击"立即验证"
- ✅ 阻止主循环重复点击,避免误操作

**2. 截屏功能实现**
- ✅ 使用Android 11+ 的`takeScreenshot()` API
- ✅ 在`accessibility_service_config.xml`中添加`android:canTakeScreenshot="true"`权限
- ✅ 实现截屏回调接口,支持成功/失败处理
- ✅ 使用`CountDownLatch`同步等待截屏完成

**3. 截图保存到公共相册**
- ✅ Android 10+: 使用MediaStore API保存到公共相册
- ✅ Android 9及以下: 保存到公共Pictures目录并通知系统扫描
- ✅ 自动创建"权利卫士取证"相册文件夹
- ✅ 文件名包含备注信息和时间戳
- ✅ 添加存储权限: `READ_MEDIA_IMAGES` (Android 13+)

**4. 防重复点击机制**
- ✅ 使用`hasSelectedDouyin`标志位
- ✅ 点击抖音后立即设置标志,阻止主循环重复点击
- ✅ 只有延迟线程会执行截屏和点击"立即验证"

#### 📝 技术细节

**应用验真流程**:
```
1. 检测"应用验真"标题
2. 查找抖音容器(ID: rl_douyin)
3. 点击抖音容器
4. 设置hasSelectedDouyin=true
5. 启动延迟线程
6. 随机延迟1-3秒
7. 截屏保存
8. 等待截屏完成(最多3秒)
9. 再等待500ms
10. 点击"立即验证"
```

**截屏保存路径**:
```
相册 / Pictures / 权利卫士取证 / 应用验真_袁丹-抖音_海赫Hayhoe服饰_20250120_200530.png
```

**关键代码**:

```java
// 1. 点击抖音容器
List<AccessibilityNodeInfo> douyinContainerNodes =
    rootNode.findAccessibilityNodeInfosByViewId("com.unitrust.tsa:id/rl_douyin");

if (douyinContainerNodes != null && !douyinContainerNodes.isEmpty()) {
    AccessibilityNodeInfo douyinIcon = douyinContainerNodes.get(0);
    boolean clicked = douyinIcon.performAction(ACTION_CLICK);

    if (clicked) {
        hasSelectedDouyin = true; // 阻止主循环重复点击

        // 2. 随机延迟后截屏并点击
        new Thread(() -> {
            int randomDelay = 1000 + new Random().nextInt(2000);
            Thread.sleep(randomDelay);
            clickVerifyButton();
        }).start();
    }
}

// 3. 截屏并点击"立即验证"
private void clickVerifyButton() {
    CountDownLatch latch = new CountDownLatch(1);

    takeScreenshotBeforeVerify(new ScreenshotCallback() {
        @Override
        public void onSuccess() {
            latch.countDown();
        }
    });

    latch.await(3, TimeUnit.SECONDS);
    Thread.sleep(500);

    // 点击"立即验证"
    List<AccessibilityNodeInfo> buttonNodes =
        rootNode.findAccessibilityNodeInfosByViewId("com.unitrust.tsa:id/confirm_button");
    buttonNodes.get(0).performAction(ACTION_CLICK);
}

// 4. 保存到公共相册
private void saveScreenshot(Bitmap bitmap) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.RELATIVE_PATH,
                  Environment.DIRECTORY_PICTURES + "/权利卫士取证");

        Uri imageUri = resolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
        );

        OutputStream os = resolver.openOutputStream(imageUri);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
    }
}
```

#### 🎯 新增Resource ID

```java
private static final String DOUYIN_CONTAINER_ID = "com.unitrust.tsa:id/rl_douyin";
private static final String VERIFY_BUTTON_ID = "com.unitrust.tsa:id/confirm_button";
```

#### 📱 权限配置

**AndroidManifest.xml**:
```xml
<!-- 存储权限 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
    android:maxSdkVersion="32" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"
    android:maxSdkVersion="32" />
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
```

**accessibility_service_config.xml**:
```xml
<accessibility-service
    ...
    android:canTakeScreenshot="true"
    ... />
```

---

### V1.4 (2025-01-20)

**系统录屏权限弹窗自动化完善 - 通过按钮ID识别**

#### ✅ 完成内容

**1. 核心问题修复**
- ✅ 修复点击"单个应用"后立即执行后续代码导致误点击"继续"按钮的问题
- ✅ 修复点击"整个屏幕"后按钮文本未更新导致无法识别"立即开始"的问题
- ✅ 实现通过按钮ID(`button1`)直接识别并点击系统对话框确认按钮
- ✅ 完善等待时间策略,确保下拉菜单完全展开后再进行下一步操作

**2. 日志优化**
- ✅ 减少冗余日志输出,只扫描系统对话框中的关键控件
- ✅ 优化日志格式,只输出Button和Spinner等关键控件信息
- ✅ 增加详细的按钮识别日志,包含文本、描述、ID三个维度

**3. 智能识别策略**
- ✅ 策略1(最优先): 直接通过ID识别`button1`(系统对话框标准确认按钮)
- ✅ 策略2: 通过文本/描述包含"开始"、"Start"关键词识别
- ✅ 策略3: 通过ID包含"start"、"confirm"、"ok"、"positive"等关键词识别

#### 📝 技术细节

**问题分析**:
1. **问题1**: 点击"单个应用"后,代码继续执行,立即调用`findAndClickStartButton()`,导致点击了"继续"按钮
   - **解决**: 点击"单个应用"成功后立即`return`,不再执行后续代码

2. **问题2**: 点击"整个屏幕"后,按钮文本从"继续"变成"立即开始",但是通过`getText()`和`getContentDescription()`都获取不到"立即开始"文本
   - **原因**: 按钮文本可能显示在子节点上,或者通过其他方式渲染
   - **解决**: 直接通过按钮ID(`button1`)识别,这是Android系统对话框的标准确认按钮ID

**优化后的流程**:
```
1. 检测到系统录屏对话框
2. 查找"单个应用"文本
3. ✅ 找到 → 点击 → return (不再执行后续代码!)
4. [1500ms后] 点击"整个屏幕"
5. [再等1000ms] 扫描按钮
6. 发现ID为button1的按钮 → 直接点击
7. 🎉 录屏开始!
```

**等待时间优化**:
- 点击"单个应用"后: 1500ms (让下拉菜单完全展开)
- 点击"整个屏幕"后: 1000ms (让界面完全更新)
- 检测重试间隔: 800ms
- 检测次数: 3次

#### 🎯 关键代码

**通过ID识别按钮**:
```java
// 策略1: 直接点击ID为button1的按钮(系统对话框的确认按钮)
if (viewId != null && viewId.endsWith("button1")) {
    logD("🎯 找到系统对话框确认按钮");
    boolean clicked = node.performAction(ACTION_CLICK);
    if (clicked) {
        logD("🎉 成功点击确认按钮,录屏即将开始!");
        return;
    }
}
```

**点击后立即返回**:
```java
if (clicked) {
    logD("✅ 成功点击'单个应用'下拉框,等待下拉菜单展开...");
    new Thread(() -> {
        Thread.sleep(1500);
        clickWholeScreenOption();
    }).start();

    // 重要: 点击成功后立即返回,不要继续执行后面的代码
    rootNode.recycle();
    return;
}
```

---

### V1.3 (2025-01-19)

**系统录屏权限弹窗自动化处理 + 玻璃质感UI优化**

#### ✅ 完成内容

**1. 系统录屏权限弹窗自动化**
- ✅ 实现监听`com.android.systemui`包的事件
- ✅ 自动检测并点击"单个应用"下拉框
- ✅ 智能父节点查找,自动点击"整个屏幕"选项
- ✅ 自动检测并点击"立即开始"按钮
- ✅ 完成从点击录屏到真正开始录屏的完整自动化

**2. UI界面优化**
- ✅ 简化界面文字,去除所有emoji和多余提示
- ✅ 实现玻璃质感卡片效果(淡蓝色背景+描边)
- ✅ 添加立体悬浮效果(elevation + translationZ)
- ✅ 统一圆角设计(16-20dp)
- ✅ 优化按钮和卡片的视觉层次

**3. 问题修复**
- 🐛 修复下拉菜单选项不可点击问题(智能父节点查找)
- 🐛 修复按钮状态检测逻辑("继续" → "立即开始")
- 🐛 修复玻璃背景渐变参数错误(百分比改为dp)

#### 📝 技术细节

**系统录屏权限弹窗处理流程**:
```
系统弹窗出现
    ↓
检测按钮文字
    ↓
是"立即开始"? ──Yes──→ 点击按钮 ──→ 录屏开始 ✅
    ↓ No
检测下拉框文字
    ↓
是"单个应用"? ──Yes──→ 点击下拉框
    ↓                      ↓
    No                 等待500ms
    ↓                      ↓
  跳过              查找"整个屏幕"
                           ↓
                    智能父节点查找
                           ↓
                    点击"整个屏幕"
                           ↓
                    按钮变为"立即开始"
                           ↓
                    下次事件触发时点击 ✅
```

**关键技术实现**:

1. **监听系统UI事件**
```java
// 处理系统UI的录屏权限弹窗
if (SYSTEM_UI_PACKAGE.equals(packageName)) {
    if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ||
        eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
        handleSystemScreenShareDialog();
    }
    return;
}
```

2. **智能父节点查找**
```java
// 如果节点不可点击,尝试点击父节点
if (!node.isClickable()) {
    AccessibilityNodeInfo parent = node.getParent();
    if (parent != null && parent.isClickable()) {
        parent.performAction(ACTION_CLICK);
    }
}
```

3. **状态检测**
```java
// 通过按钮文字判断状态
if (buttonText.contains("立即开始")) {
    // 已选择"整个屏幕",直接开始
    buttonNode.performAction(ACTION_CLICK);
} else if (spinnerText.contains("单个应用")) {
    // 需要选择"整个屏幕"
    spinnerNode.performAction(ACTION_CLICK);
}
```

**UI优化细节**:

1. **玻璃质感卡片**
```xml
<shape>
    <solid android:color="#F5FAFF" />
    <corners android:radius="20dp" />
    <stroke
        android:width="1.5dp"
        android:color="#D0E4FF" />
</shape>
```

2. **立体悬浮效果**
```xml
<androidx.cardview.widget.CardView
    android:elevation="12dp"
    app:cardCornerRadius="20dp">
```

3. **按钮悬浮效果**
```xml
<Button
    android:elevation="8dp"
    android:translationZ="4dp"
    android:stateListAnimator="@null">
```

#### 🎯 完整自动化流程

```
用户输入备注 → 点击开始 → 最小化应用 → 打开权利卫士
→ 点击"录屏取证" → 填充备注 → 点击"开始录屏取证"
→ 系统弹窗出现 → 点击"单个应用"下拉框 → 点击"整个屏幕"
→ 点击"立即开始" → 开始录屏 ✅
```

#### 📊 测试结果

- ✅ 系统录屏权限弹窗自动处理成功
- ✅ 智能父节点查找正常工作
- ✅ 按钮状态检测准确
- ✅ UI界面美观简洁
- ✅ 玻璃质感效果良好

#### 📸 界面截图

- `玻璃质感界面截图.png` - 优化后的UI界面

---

### V1.2 (2025-01-19)

**备注输入与自动填充功能**

#### ✅ 完成内容

**1. UI功能增强**
- ✅ 添加Material Design风格的备注输入框
- ✅ 实现备注内容传递机制
- ✅ 优化主界面布局和用户体验

**2. 自动化功能**
- ✅ 实现自动填充备注到权利卫士
- ✅ 实现自动点击"开始录屏取证"按钮
- ✅ 完成从启动到开始录屏的完整自动化流程

**3. 问题修复**
- 🐛 修复无障碍服务配置问题(packageNames限制)
- 🐛 修复事件监听机制,正确处理窗口变化
- 🐛 优化延迟等待时间,确保界面加载完成

#### 📝 技术细节

**自动化流程**:
```
用户输入备注 → 点击开始 → 最小化应用 → 打开权利卫士
→ 点击"录屏取证" → 填充备注 → 点击"开始录屏取证" → 开始录屏 ✅
```

**关键技术实现**:
1. **备注传递**: MainActivity → Service → 填充到权利卫士
2. **文本填充**: 使用`ACTION_SET_TEXT`和Bundle传递文本
3. **窗口监听**: 检测`ScreenRecorderActivity`加载
4. **延迟控制**: 2秒等待界面加载 + 500ms确保填充完成

**无障碍服务配置修复**:
```xml
<!-- ❌ 错误配置 -->
<accessibility-service
    android:packageNames="com.android.systemui" />

<!-- ✅ 正确配置 -->
<accessibility-service
    android:accessibilityEventTypes="typeAllMask"
    ... />
    <!-- 移除packageNames限制 -->
```

#### 📂 文件变更

**修改文件**:
- `app/src/main/res/layout/activity_main.xml` - 添加备注输入框
- `app/src/main/java/com/rightsguard/automation/MainActivity.java` - 添加备注传递逻辑
- `app/src/main/java/com/rightsguard/automation/AutomationAccessibilityService.java` - 添加填充备注和点击开始录屏
- `app/src/main/res/xml/accessibility_service_config.xml` - 移除packageNames限制

**更新文档**:
- `项目文档整理/自动化文档.md` - 添加备注功能详细说明
- `项目文档整理/Git版本记录.md` - 记录V1.2版本更新

#### 🎯 当前进度

**已完成**:
- ✅ 最小化应用
- ✅ 打开权利卫士
- ✅ 点击"录屏取证"
- ✅ 填充备注内容
- ✅ 点击"开始录屏取证"
- ✅ 开始录屏

**待开发**:
- ⏳ 处理系统权限弹窗
- ⏳ 监控录屏状态
- ⏳ 自动停止录屏
- ⏳ 保存录屏文件

---

### V1.0.0 (2025-01-19)

**项目初始化**

#### ✅ 完成内容

**1. 项目规划**
- 完成项目需求分析
- 完成技术方案设计
- 确定技术栈和架构

**2. 应用分析**
- 完成权利卫士应用深度分析
- 识别关键UI元素和Resource ID
- 分析自动化可行性
- 评估技术风险

**3. 文档系统**
- 创建README.md索引文档
- 创建开发指南文档
- 创建自动化技术文档
- 创建常见问题FAQ文档
- 创建Git版本记录文档

**4. 技术架构**
- 设计整体架构方案
- 确定使用Accessibility Service
- 设计状态机和任务管理机制
- 设计错误处理和恢复机制

#### 📝 技术细节

**核心技术选型**:
- Android Accessibility Service (无障碍服务)
- UI Automator (UI自动化辅助)
- Foreground Service (前台服务保活)

**目标应用信息**:
- 应用名称: 权利卫士
- 包名: com.unitrust.tsa
- 版本: 4.7.0.0
- 目标SDK: Android 13 (API 33)

**关键UI元素**:
- 录屏界面: `cn.tsa.rights.viewer.screen.ScreenRecorderActivity`
- 开始按钮: `com.unitrust.tsa:id/rl_btn`
- 备注输入: `com.unitrust.tsa:id/ed_remark`
- 麦克风开关: `com.unitrust.tsa:id/image_switch`
- 位置开关: `com.unitrust.tsa:id/switch_screen`

#### 📚 文档结构

```
权利卫士取证自动化/
├── README.md (索引文档)
├── 权利卫士应用分析报告.md
└── 项目文档整理/
    ├── 开发指南.md
    ├── 自动化文档.md
    ├── 常见问题.md
    └── Git版本记录.md
```

#### 🎯 下一步计划

- [ ] 创建Android项目
- [ ] 实现Accessibility Service基础框架
- [ ] 实现UI元素定位功能
- [ ] 实现自动化操作流程
- [ ] 实现任务管理功能
- [ ] 实现状态监控功能
- [ ] 在vivo设备上测试
- [ ] 优化稳定性和性能

---

## 📅 版本规划

### V1.1.0 (计划中)

**核心功能开发**

#### 计划功能
- [ ] Accessibility Service基础框架
- [ ] UI元素定位和操作
- [ ] 自动启动权利卫士
- [ ] 自动点击开始录屏
- [ ] 处理系统权限弹窗
- [ ] 基础日志记录

#### 技术要点
- 实现AccessibilityService核心类
- 实现UI元素查找和点击
- 实现智能等待机制
- 实现基础错误处理

---

### V1.2.0 (计划中)

**任务管理和状态监控**

#### 计划功能
- [ ] 任务队列管理
- [ ] 录屏状态监控
- [ ] 自动停止录屏
- [ ] 任务执行日志
- [ ] 失败重试机制

#### 技术要点
- 实现TaskManager任务管理器
- 实现StateMonitor状态监控
- 实现通知栏监听
- 实现自动恢复机制

---

### V1.3.0 (计划中)

**UI界面和用户体验**

#### 计划功能
- [ ] 任务配置界面
- [ ] 状态监控界面
- [ ] 日志查看界面
- [ ] 设置界面
- [ ] 悬浮窗控制

#### 技术要点
- 实现Activity和Fragment
- 实现数据绑定
- 实现悬浮窗服务
- 优化用户交互

---

### V1.4.0 (计划中)

**高级功能**

#### 计划功能
- [ ] 定时任务调度
- [ ] 批量任务处理
- [ ] 远程控制接口
- [ ] 数据统计分析
- [ ] 云端同步

#### 技术要点
- 实现定时任务调度器
- 实现批量任务队列
- 实现HTTP API接口
- 实现数据统计模块

---

### V2.0.0 (计划中)

**稳定性和性能优化**

#### 计划功能
- [ ] 性能优化
- [ ] 内存优化
- [ ] 电池优化
- [ ] 兼容性提升
- [ ] 安全加固

#### 技术要点
- 优化UI查找算法
- 优化内存使用
- 优化后台保活
- 适配更多设备
- 加密敏感数据

---

## 🐛 Bug修复记录

### V1.0.0
- 无 (初始版本)

---

## 📊 统计信息

### 开发进度

| 版本 | 状态 | 完成度 | 开始日期 | 完成日期 |
|------|------|--------|---------|---------|
| V1.0.0 | ✅ 已完成 | 100% | 2025-01-19 | 2025-01-19 |
| V1.1.0 | 📋 计划中 | 0% | - | - |
| V1.2.0 | 📋 计划中 | 0% | - | - |
| V1.3.0 | 📋 计划中 | 0% | - | - |
| V1.4.0 | 📋 计划中 | 0% | - | - |
| V2.0.0 | 📋 计划中 | 0% | - | - |

### 功能模块

| 模块 | 状态 | 说明 |
|------|------|------|
| 项目文档 | ✅ 已完成 | 完整的文档体系 |
| 应用分析 | ✅ 已完成 | 权利卫士应用分析 |
| 核心框架 | 📋 待开发 | Accessibility Service |
| UI自动化 | 📋 待开发 | UI元素定位和操作 |
| 任务管理 | 📋 待开发 | 任务队列和调度 |
| 状态监控 | 📋 待开发 | 录屏状态监控 |
| 用户界面 | 📋 待开发 | Android UI界面 |
| 高级功能 | 📋 待开发 | 定时任务、远程控制 |

---

## 🔗 相关文档

- [README](../README.md) - 项目概览
- [开发指南](./开发指南.md) - 开发环境和流程
- [自动化文档](./自动化文档.md) - 技术实现细节
- [常见问题](./常见问题.md) - FAQ和问题排查

---

## 📝 更新日志格式说明

每个版本的更新日志包含以下部分:

- **版本号**: 遵循语义化版本规范 (主版本.次版本.修订版本)
- **发布日期**: 版本发布的日期
- **更新类型**: 
  - ✨ 新功能 (New Features)
  - 🐛 Bug修复 (Bug Fixes)
  - ⚡ 性能优化 (Performance)
  - 📝 文档更新 (Documentation)
  - 🔧 配置变更 (Configuration)
  - ♻️ 代码重构 (Refactoring)
- **详细说明**: 具体的更新内容和技术细节

---

[← 返回README](../README.md)

**最后更新**: 2026-02-04

