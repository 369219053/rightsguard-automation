# UI结构 Dump

**生成时间**: 2026-03-26 10:29:17

---

=== UI结构 Dump (所有窗口) ===
时间: 2026-03-26 10:29:16

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
│   │     📝 **Text**: "10:29"
│   │     💬 **Desc**: "10:29"
│   │     📐 **Bounds**: [60,52] → [180,99] (120x47)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "浏览器通知："
│   │     📐 **Bounds**: [180,50] → [215,101] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "通话管理通知："
│   │     📐 **Bounds**: [227,50] → [262,101] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "应用商店通知："
│   │     📐 **Bounds**: [273,50] → [308,101] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "手机管家通知："
│   │     📐 **Bounds**: [320,50] → [355,101] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "vivo账号通知："
│   │     📐 **Bounds**: [367,50] → [402,101] (35x51)
│   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/system_icons`
│   │     📐 **Bounds**: [585,33] → [1020,114] (435x81)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/networkspeed_vertical`
│   │   │     💬 **Desc**: "336KB/s"
│   │   │     📐 **Bounds**: [826,56] → [883,95] (57x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/mobile_combo`
│   │   │     💬 **Desc**: "中国移动 5G+ 信号强度四格，共四格"
│   │   │     📐 **Bounds**: [892,37] → [948,114] (56x77)
│   │   ├─ **[]** `enabled visible `
│   │   │     💬 **Desc**: "电池电量为百分之 87。"
│   │   │     📐 **Bounds**: [957,58] → [1020,94] (63x36)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #3
类型: 系统窗口
层级: 1
活动: 否
聚焦: 否
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.rightsguard.automation

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [120,661] → [1080,943] (960x282)
├─ **[android.view.View]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/view_drag_handle`
│     📐 **Bounds**: [150,691] → [1050,721] (900x30)
├─ **[android.widget.TextView]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/tv_float_status`
│     📝 **Text**: "空闲"
│     📐 **Bounds**: [210,739] → [282,787] (72x48)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_start`
│     📝 **Text**: "开始"
│     📐 **Bounds**: [150,810] → [354,913] (204x103)
├─ **[android.widget.Button]** `clickable visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_stop`
│     📝 **Text**: "停止"
│     📐 **Bounds**: [372,810] → [576,913] (204x103)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_test_coordinate`
│     📝 **Text**: "📍"
│     📐 **Bounds**: [594,807] → [750,913] (156x106)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_dump`
│     📝 **Text**: "Dump"
│     📐 **Bounds**: [768,812] → [924,913] (156x101)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_minimize`
│     📝 **Text**: "—"
│     📐 **Bounds**: [942,805] → [1050,913] (108x108)
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
├─ **[android.widget.FrameLayout]** `enabled visible `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/ww_`
│     📐 **Bounds**: [0,0] → [1080,2199] (1080x2199)
│   ├─ **[android.widget.Button]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/j1_`
│   │     💬 **Desc**: "关注"
│   │     📐 **Bounds**: [897,1136] → [1080,1223] (183x87)
│   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/user_avatar`
│   │     💬 **Desc**: "家加鲜餐饮具旗舰店授权号"
│   │     📐 **Bounds**: [915,1034] → [1059,1178] (144x144)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/gl-`
│   │     💬 **Desc**: "未点赞，喜欢3965，按钮"
│   │     📐 **Bounds**: [900,1223] → [1080,1424] (180x201)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/evc`
│   │     💬 **Desc**: "评论85，按钮"
│   │     📐 **Bounds**: [900,1424] → [1080,1625] (180x201)
│   │   ├─ **[android.widget.ImageView]** `enabled visible `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/epn`
│   │   │     💬 **Desc**: "评论85，按钮"
│   │   │     📐 **Bounds**: [936,1448] → [1044,1556] (108x108)
│   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │     📝 **Text**: "85"
│   │   │     📐 **Bounds**: [969,1556] → [1011,1598] (42x42)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/d-c`
│   │     💬 **Desc**: "未选中，收藏1894，按钮"
│   │     📐 **Bounds**: [897,1625] → [1080,1821] (183x196)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/zzf`
│   │     💬 **Desc**: "分享791，按钮"
│   │     📐 **Bounds**: [900,1821] → [1080,2022] (180x201)
│   ├─ **[android.widget.FrameLayout]** `enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s4s`
│   │     📐 **Bounds**: [921,2046] → [1053,2178] (132x132)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s4v`
│   │   │     💬 **Desc**: "音乐，@家加鲜餐饮具旗舰店授权号创作的原声，按钮"
│   │   │     📐 **Bounds**: [921,2046] → [1053,2178] (132x132)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/qmc`
│   │     📐 **Bounds**: [36,1843] → [650,1939] (614x96)
│   │   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/nwo`
│   │   │     📐 **Bounds**: [54,1861] → [114,1921] (60x60)
│   │   ├─ **[android.widget.TextView]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/2jr`
│   │   │     📝 **Text**: "购物"
│   │   │     📐 **Bounds**: [132,1862] → [216,1919] (84x57)
│   │   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/qmd`
│   │   │     📐 **Bounds**: [234,1862] → [431,1919] (197x57)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/3z+`
│   │   │   │     📝 **Text**: "|"
│   │   │   │     📐 **Bounds**: [234,1866] → [245,1914] (11x48)
│   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/30f`
│   │   │     📝 **Text**: "·"
│   │   │     📐 **Bounds**: [437,1867] → [455,1915] (18x48)
│   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/30d`
│   │   │     📝 **Text**: "已售53.5w"
│   │   │     📐 **Bounds**: [461,1867] → [632,1915] (171x48)
│   ├─ **[android.widget.FrameLayout]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/nua`
│   │     📐 **Bounds**: [36,1957] → [810,2032] (774x75)
│   │   ├─ **[android.widget.TextView]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/title`
│   │   │     📝 **Text**: "@家加鲜餐饮具旗舰店授权号"
│   │   │     📐 **Bounds**: [36,1957] → [562,2032] (526x75)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/6mr`
│   │   │     📐 **Bounds**: [568,1968] → [810,2021] (242x53)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible focusable `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/41w`
│   │   │   │     📝 **Text**: " · 01月26日"
│   │   │   │     💬 **Desc**: "发布时间：01月26日"
│   │   │   │     📐 **Bounds**: [572,1968] → [810,2021] (238x53)
│   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/6p4`
│   │     📐 **Bounds**: [0,2032] → [1080,2172] (1080x140)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/ga5`
│   │   │     📐 **Bounds**: [0,2032] → [801,2172] (801x140)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled visible focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/gb6`
│   │   │     📐 **Bounds**: [36,2032] → [801,2160] (765x128)
│   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible focusable `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/desc`
│   │   │   │     📝 **Text**: "好物推荐非常实用的航空杯，价格还不贵，家家户户都离不开它 #... 展开"
│   │   │   │     📐 **Bounds**: [36,2032] → [801,2160] (765x128)
│   │   ├─ **[android.widget.FrameLayout]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s+n`
│   │   │     📐 **Bounds**: [36,2202] → [36,2172] (0x-30)
│   ├─ **[android.widget.FrameLayout]** `enabled `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s+n`
│   │     📐 **Bounds**: [36,2208] → [36,2178] (0x-30)
│   ├─ **[android.view.View]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/qde`
│   │     📐 **Bounds**: [0,0] → [1080,2199] (1080x2199)
├─ **[android.widget.FrameLayout]** `enabled `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/ww_`
│     📐 **Bounds**: [0,2199] → [1080,2199] (1080x0)
│   ├─ **[android.widget.Button]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/j1_`
│   │     💬 **Desc**: "关注"
│   │     📐 **Bounds**: [897,3215] → [1080,2199] (183x-1016)
│   ├─ **[android.widget.ImageView]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/user_avatar`
│   │     💬 **Desc**: "家加鲜餐饮具旗舰店推广号"
│   │     📐 **Bounds**: [915,3113] → [1059,2199] (144x-914)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/gl-`
│   │     💬 **Desc**: "未点赞，喜欢1401，按钮"
│   │     📐 **Bounds**: [900,3302] → [1080,2199] (180x-1103)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/evc`
│   │     💬 **Desc**: "评论25，按钮"
│   │     📐 **Bounds**: [900,3503] → [1080,2199] (180x-1304)
│   │   ├─ **[android.widget.ImageView]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/epn`
│   │   │     💬 **Desc**: "评论25，按钮"
│   │   │     📐 **Bounds**: [936,3527] → [1044,2199] (108x-1328)
│   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │     📝 **Text**: "25"
│   │   │     📐 **Bounds**: [969,3635] → [1010,2199] (41x-1436)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/d-c`
│   │     💬 **Desc**: "未选中，收藏668，按钮"
│   │     📐 **Bounds**: [897,3704] → [1080,2199] (183x-1505)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/zzf`
│   │     💬 **Desc**: "分享218，按钮"
│   │     📐 **Bounds**: [900,3900] → [1080,2199] (180x-1701)
│   ├─ **[android.widget.FrameLayout]** `enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s4s`
│   │     📐 **Bounds**: [921,4125] → [1053,2199] (132x-1926)
│   │   ├─ **[android.widget.FrameLayout]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s4v`
│   │   │     💬 **Desc**: "音乐，@家加鲜餐饮具旗舰店推广号创作的原声，按钮"
│   │   │     📐 **Bounds**: [921,4125] → [1053,2199] (132x-1926)
│   ├─ **[android.widget.FrameLayout]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/nua`
│   │     📐 **Bounds**: [36,4036] → [810,2199] (774x-1837)
│   │   ├─ **[android.widget.TextView]** `clickable enabled focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/title`
│   │   │     📝 **Text**: "@家加鲜餐饮具旗舰店推广号"
│   │   │     📐 **Bounds**: [36,4036] → [562,2199] (526x-1837)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/6mr`
│   │   │     📐 **Bounds**: [568,4047] → [810,2199] (242x-1848)
│   │   │   ├─ **[android.widget.TextView]** `enabled focusable `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/41w`
│   │   │   │     📝 **Text**: " · 01月26日"
│   │   │   │     💬 **Desc**: "发布时间：01月26日"
│   │   │   │     📐 **Bounds**: [572,4047] → [810,2199] (238x-1848)
│   ├─ **[android.widget.FrameLayout]** `enabled `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/6p4`
│   │     📐 **Bounds**: [0,4111] → [1080,2199] (1080x-1912)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/ga5`
│   │   │     📐 **Bounds**: [0,4111] → [801,2199] (801x-1912)
│   │   ├─ **[android.widget.FrameLayout]** `clickable enabled focusable `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/gb6`
│   │   │     📐 **Bounds**: [36,4111] → [801,2199] (765x-1912)
│   │   │   ├─ **[android.widget.TextView]** `clickable enabled focusable `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/desc`
│   │   │   │     📝 **Text**: "【足足到手50个】一次性塑料杯硬塑航空杯子家用热卖 "
│   │   │   │     📐 **Bounds**: [36,4111] → [801,2199] (765x-1912)
│   │   ├─ **[android.widget.FrameLayout]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s+n`
│   │   │     📐 **Bounds**: [36,4281] → [36,2199] (0x-2082)
│   ├─ **[android.widget.FrameLayout]** `enabled `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/s+n`
│   │     📐 **Bounds**: [36,4287] → [36,2199] (0x-2088)
│   ├─ **[android.view.ViewGroup]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/container`
│   │     📐 **Bounds**: [0,4278] → [1080,2199] (1080x-2079)
│   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/title`
│   │   │     📝 **Text**: "相关搜索"
│   │   │     📐 **Bounds**: [108,4312] → [264,2199] (156x-2113)
│   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/content`
│   │   │     📝 **Text**: "航空杯子加厚款2026最新款"
│   │   │     📐 **Bounds**: [303,4312] → [786,2199] (483x-2113)
│   │   ├─ **[android.widget.ImageView]** `enabled `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/h=_`
│   │   │     💬 **Desc**: "点击查看更多，按钮"
│   │   │     📐 **Bounds**: [1014,4308] → [1044,2199] (30x-2109)
│   ├─ **[android.view.View]** `clickable enabled focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/qde`
│   │     📐 **Bounds**: [0,2199] → [1080,2199] (1080x0)
├─ **[android.widget.EditText]** `clickable enabled visible `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/eny`
│     📝 **Text**: "分享你此刻的想法"
│     📐 **Bounds**: [84,2238] → [666,2310] (582x72)
├─ **[android.widget.LinearLayout]** `enabled visible `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/enq`
│     📐 **Bounds**: [666,2238] → [1044,2310] (378x72)
│   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/iv_image`
│   │     💬 **Desc**: "插入图片"
│   │     📐 **Bounds**: [666,2238] → [792,2310] (126x72)
│   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/bak`
│   │     💬 **Desc**: "at"
│   │     📐 **Bounds**: [792,2238] → [918,2310] (126x72)
│   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/n1v`
│   │     💬 **Desc**: "表情"
│   │     📐 **Bounds**: [918,2238] → [1044,2310] (126x72)
├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/back_btn`
│     💬 **Desc**: "返回"
│     📐 **Bounds**: [18,114] → [162,258] (144x144)
├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/j+r`
│     💬 **Desc**: "搜索"
│     📐 **Bounds**: [964,138] → [1060,234] (96x96)
├─ **[android.widget.SeekBar]** `enabled focusable `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/6n0`
│     💬 **Desc**: "进度条"
│     📐 **Bounds**: [0,2175] → [1080,2229] (1080x54)
