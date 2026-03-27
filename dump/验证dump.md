# UI结构 Dump

**生成时间**: 2026-03-27 19:20:54

---

=== UI结构 Dump (所有窗口) ===
时间: 2026-03-27 19:20:54

窗口总数: 4

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #1
类型: 系统窗口
层级: 3
活动: 否
聚焦: 否
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.vivo.upslide

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [0,2346] → [1080,2400] (1080x54)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #2
类型: 系统窗口
层级: 2
活动: 否
聚焦: 否
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.android.systemui

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [0,0] → [1080,114] (1080x114)
├─ **[android.widget.FrameLayout]** `enabled visible `
│     🆔 **ID**: `com.android.systemui:id/status_bar`
│     📐 **Bounds**: [0,0] → [1080,114] (1080x114)
│   ├─ **[android.widget.TextView]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/clock`
│   │     📝 **Text**: "19:20"
│   │     💬 **Desc**: "19:20"
│   │     📐 **Bounds**: [60,48] → [180,95] (120x47)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "浏览器通知："
│   │     📐 **Bounds**: [180,46] → [215,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "通话管理通知："
│   │     📐 **Bounds**: [227,46] → [262,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "信息通知："
│   │     📐 **Bounds**: [273,46] → [308,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "应用商店通知："
│   │     📐 **Bounds**: [320,46] → [355,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "手机管家通知："
│   │     📐 **Bounds**: [367,46] → [402,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "vivo账号通知："
│   │     📐 **Bounds**: [413,46] → [448,97] (35x51)
│   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/system_icons`
│   │     📐 **Bounds**: [585,29] → [1020,114] (435x85)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/networkspeed_vertical`
│   │   │     💬 **Desc**: "127KB/s"
│   │   │     📐 **Bounds**: [826,52] → [883,91] (57x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/mobile_combo`
│   │   │     💬 **Desc**: "中国移动 5G 信号强度四格，共四格"
│   │   │     📐 **Bounds**: [892,33] → [948,111] (56x78)
│   │   ├─ **[]** `enabled visible `
│   │   │     💬 **Desc**: "电池电量为百分之 38。"
│   │   │     📐 **Bounds**: [957,54] → [1020,90] (63x36)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #3
类型: 系统窗口
层级: 1
活动: 否
聚焦: 否
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.rightsguard.automation

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [120,766] → [1080,1048] (960x282)
├─ **[android.view.View]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/view_drag_handle`
│     📐 **Bounds**: [150,796] → [1050,826] (900x30)
├─ **[android.widget.TextView]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/tv_float_status`
│     📝 **Text**: "空闲"
│     📐 **Bounds**: [210,844] → [282,892] (72x48)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_start`
│     📝 **Text**: "开始"
│     📐 **Bounds**: [150,915] → [354,1018] (204x103)
├─ **[android.widget.Button]** `clickable visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_stop`
│     📝 **Text**: "停止"
│     📐 **Bounds**: [372,915] → [576,1018] (204x103)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_test_coordinate`
│     📝 **Text**: "📍"
│     📐 **Bounds**: [594,912] → [750,1018] (156x106)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_dump`
│     📝 **Text**: "Dump"
│     📐 **Bounds**: [768,917] → [924,1018] (156x101)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_minimize`
│     📝 **Text**: "—"
│     📐 **Bounds**: [942,910] → [1050,1018] (108x108)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #4
类型: 应用窗口
层级: 0
活动: 是
聚焦: 是
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.ss.android.ugc.aweme

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [0,0] → [1080,2400] (1080x2400)
├─ **[android.webkit.WebView]** `enabled visible `
│     📐 **Bounds**: [108,769] → [972,1690] (864x921)
│   ├─ **[android.webkit.WebView]** `enabled visible focusable `
│   │     📝 **Text**: "验证码"
│   │     📐 **Bounds**: [108,769] → [972,1690] (864x921)
│   │   ├─ **[android.view.View]** `enabled visible `
│   │   │     🆔 **ID**: `captcha_container`
│   │   │     📐 **Bounds**: [108,769] → [972,1690] (864x921)
│   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │     🆔 **ID**: `vc_captcha_box`
│   │   │   │     📐 **Bounds**: [108,769] → [972,1690] (864x921)
│   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │     📐 **Bounds**: [108,769] → [972,1690] (864x921)
│   │   │   │   │   ├─ **[android.widget.Button]** `clickable enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [849,769] → [972,892] (123x123)
│   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [108,769] → [972,898] (864x129)
│   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible focusable `
│   │   │   │   │   │   │     📝 **Text**: "请完成下列验证后继续 "
│   │   │   │   │   │   │     📐 **Bounds**: [141,814] → [648,874] (507x60)
│   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [141,895] → [939,1393] (798x498)
│   │   │   │   │   │   ├─ **[android.widget.Image]** `clickable enabled visible `
│   │   │   │   │   │   │     🆔 **ID**: `captcha_verify_image`
│   │   │   │   │   │   │     📝 **Text**: "basicImg"
│   │   │   │   │   │   │     📐 **Bounds**: [141,895] → [939,1393] (798x498)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [141,1060] → [303,1222] (162x162)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [141,1060] → [303,1222] (162x162)
│   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     🆔 **ID**: `captcha-verify_img_slide`
│   │   │   │   │   │   │   │   │     📝 **Text**: "actionImg"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [141,1060] → [303,1222] (162x162)
│   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [141,1399] → [939,1519] (798x120)
│   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │     📝 **Text**: "按住左边按钮拖动完成上方拼图"
│   │   │   │   │   │   │     📐 **Bounds**: [141,1399] → [939,1519] (798x120)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [141,1399] → [939,1519] (798x120)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [141,1399] → [234,1519] (93x120)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [141,1399] → [294,1507] (153x108)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [147,1405] → [300,1513] (153x108)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [189,1429] → [255,1489] (66x60)
│   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │     🆔 **ID**: `footer-wrap-col`
│   │   │   │   │   │     📐 **Bounds**: [108,1522] → [972,1690] (864x168)
│   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [141,1543] → [288,1606] (147x63)
│   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [141,1543] → [201,1606] (60x63)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "刷新"
│   │   │   │   │   │   │   │     📐 **Bounds**: [204,1549] → [288,1600] (84x51)
│   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [315,1543] → [462,1606] (147x63)
│   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [315,1543] → [375,1606] (60x63)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "反馈"
│   │   │   │   │   │   │   │     📐 **Bounds**: [378,1549] → [462,1600] (84x51)
│   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │     📝 **Text**: "20260327192049542B33128CCA98A0F65C"
│   │   │   │   │   │   │     📐 **Bounds**: [141,1603] → [720,1639] (579x36)
