# UI结构 Dump

**生成时间**: 2026-03-27 22:09:26

---

=== UI结构 Dump (所有窗口) ===
时间: 2026-03-27 22:09:25

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
│   │     📝 **Text**: "22:09"
│   │     💬 **Desc**: "22:09"
│   │     📐 **Bounds**: [60,47] → [184,94] (124x47)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "浏览器通知："
│   │     📐 **Bounds**: [184,45] → [219,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "通话管理通知："
│   │     📐 **Bounds**: [231,45] → [266,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "信息通知："
│   │     📐 **Bounds**: [277,45] → [312,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "应用商店通知："
│   │     📐 **Bounds**: [324,45] → [359,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "手机管家通知："
│   │     📐 **Bounds**: [371,45] → [406,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "vivo账号通知："
│   │     📐 **Bounds**: [417,45] → [452,96] (35x51)
│   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/system_icons`
│   │     📐 **Bounds**: [585,28] → [1020,114] (435x86)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/networkspeed_vertical`
│   │   │     💬 **Desc**: "730KB/s"
│   │   │     📐 **Bounds**: [826,51] → [883,90] (57x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/mobile_combo`
│   │   │     💬 **Desc**: "中国移动 5G 信号强度四格，共四格"
│   │   │     📐 **Bounds**: [892,32] → [948,110] (56x78)
│   │   ├─ **[]** `enabled visible `
│   │   │     💬 **Desc**: "电池电量为百分之 28。"
│   │   │     📐 **Bounds**: [957,53] → [1020,89] (63x36)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #3
类型: 系统窗口
层级: 1
活动: 否
聚焦: 否
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.rightsguard.automation

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [120,504] → [1080,786] (960x282)
├─ **[android.view.View]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/view_drag_handle`
│     📐 **Bounds**: [150,534] → [1050,564] (900x30)
├─ **[android.widget.TextView]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/tv_float_status`
│     📝 **Text**: "空闲"
│     📐 **Bounds**: [210,582] → [282,630] (72x48)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_start`
│     📝 **Text**: "开始"
│     📐 **Bounds**: [150,653] → [354,756] (204x103)
├─ **[android.widget.Button]** `clickable visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_stop`
│     📝 **Text**: "停止"
│     📐 **Bounds**: [372,653] → [576,756] (204x103)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_test_coordinate`
│     📝 **Text**: "📍"
│     📐 **Bounds**: [594,650] → [750,756] (156x106)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_dump`
│     📝 **Text**: "Dump"
│     📐 **Bounds**: [768,655] → [924,756] (156x101)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_minimize`
│     📝 **Text**: "—"
│     📐 **Bounds**: [942,648] → [1050,756] (108x108)
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
├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/hsk`
│     📐 **Bounds**: [0,0] → [1080,241] (1080x241)
│   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │     📐 **Bounds**: [36,139] → [108,211] (72x72)
│   ├─ **[android.view.ViewGroup]** `clickable enabled focusable `
│   │     📐 **Bounds**: [120,109] → [492,241] (372x132)
│   │   ├─ **[android.view.ViewGroup]** `enabled `
│   │   │     📐 **Bounds**: [177,175] → [216,223] (39x48)
│   │   │   ├─ **[android.widget.ImageView]** `clickable enabled focusable `
│   │   │   │     📐 **Bounds**: [183,181] → [216,223] (33x42)
│   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │     📝 **Text**: "家加鲜餐饮具旗舰店"
│   │   │     📐 **Bounds**: [240,130] → [492,178] (252x48)
│   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │     📝 **Text**: "4.5"
│   │   │     📐 **Bounds**: [395,180] → [442,217] (47x37)
│   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │     📐 **Bounds**: [579,121] → [936,229] (357x108)
│   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │     📝 **Text**: "搜索本店商品"
│   │   │     📐 **Bounds**: [669,146] → [912,204] (243x58)
│   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │     📐 **Bounds**: [972,139] → [1044,211] (72x72)
├─ **[android.widget.FrameLayout]** `enabled visible `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/hqh`
│     📐 **Bounds**: [0,253] → [1080,591] (1080x338)
│   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │     📐 **Bounds**: [35,389] → [1045,487] (1010x98)
│   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │     📐 **Bounds**: [35,392] → [656,484] (621x92)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "家加鲜餐饮具旗舰店"
│   │   │   │     📐 **Bounds**: [35,392] → [656,484] (621x92)
│   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │     📐 **Bounds**: [889,404] → [1045,473] (156x69)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "关注"
│   │   │   │     💬 **Desc**: "关注"
│   │   │   │     📐 **Bounds**: [947,416] → [1021,460] (74x44)
│   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │     📐 **Bounds**: [35,499] → [1045,545] (1010x46)
│   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │     📝 **Text**: "4.5"
│   │   │     📐 **Bounds**: [190,503] → [237,540] (47x37)
│   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │     📝 **Text**: "粉丝2.6万"
│   │   │     📐 **Bounds**: [261,500] → [398,543] (137x43)
├─ **[androidx.viewpager.widget.ViewPager]** `enabled visible focusable `
│     🆔 **ID**: `com.ss.android.ugc.aweme:id/ht0`
│     📐 **Bounds**: [0,591] → [1080,2346] (1080x1755)
│   ├─ **[androidx.recyclerview.widget.RecyclerView]** `enabled visible focusable `
│   │     📐 **Bounds**: [0,591] → [1080,2346] (1080x1755)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/wb+`
│   │   │     📐 **Bounds**: [0,591] → [1080,696] (1080x105)
│   │   │   ├─ **[androidx.recyclerview.widget.RecyclerView]** `enabled visible focusable `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/02t`
│   │   │   │     📐 **Bounds**: [0,591] → [1080,696] (1080x105)
│   │   │   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/mm-`
│   │   │   │   │     📐 **Bounds**: [60,591] → [140,696] (80x105)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/yos`
│   │   │   │   │   │     📝 **Text**: "综合"
│   │   │   │   │   │     📐 **Bounds**: [60,617] → [140,670] (80x53)
│   │   │   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/mm-`
│   │   │   │   │     📐 **Bounds**: [212,591] → [292,696] (80x105)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/yos`
│   │   │   │   │   │     📝 **Text**: "销量"
│   │   │   │   │   │     📐 **Bounds**: [212,617] → [292,670] (80x53)
│   │   │   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/mm-`
│   │   │   │   │     📐 **Bounds**: [364,591] → [444,696] (80x105)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/yos`
│   │   │   │   │   │     📝 **Text**: "上新"
│   │   │   │   │   │     📐 **Bounds**: [364,617] → [444,670] (80x53)
│   │   │   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/mm-`
│   │   │   │   │     📐 **Bounds**: [516,591] → [638,696] (122x105)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/yos`
│   │   │   │   │   │     📝 **Text**: "价格"
│   │   │   │   │   │     📐 **Bounds**: [516,617] → [596,670] (80x53)
│   │   │   ├─ **[android.widget.LinearLayout]** `clickable enabled visible focusable `
│   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/juz`
│   │   │   │     📐 **Bounds**: [874,616] → [1034,669] (160x53)
│   │   │   │   ├─ **[android.widget.ImageView]** `clickable enabled visible focusable `
│   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/ju0`
│   │   │   │   │     📐 **Bounds**: [891,620] → [937,666] (46x46)
│   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible focusable `
│   │   │   │   │     🆔 **ID**: `com.ss.android.ugc.aweme:id/ju1`
│   │   │   │   │     📝 **Text**: "单列"
│   │   │   │   │     📐 **Bounds**: [954,616] → [1034,669] (80x53)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     📐 **Bounds**: [24,696] → [1056,1085] (1032x389)
│   │   │   ├─ **[android.view.ViewGroup]** `clickable enabled visible focusable `
│   │   │   │     📐 **Bounds**: [128,987] → [311,1050] (183x63)
│   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │     📝 **Text**: "看讲解"
│   │   │   │   │     💬 **Desc**: "看讲解"
│   │   │   │   │     📐 **Bounds**: [192,1000] → [285,1037] (93x37)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "【​5​0​-​1​0​0​只​装​】​加​厚​加​硬​大​容​量​航​空​杯​酒​店​K​T​V​商​务​接​待​防​烫​一​次​性​杯​"
│   │   │   │     💬 **Desc**: "【​5​0​-​1​0​0​只​装​】​加​厚​加​硬​大​容​量​航​空​杯​酒​店​K​T​V​商​务​接​待​防​烫​一​次​性​杯​"
│   │   │   │     📐 **Bounds**: [436,708] → [1033,822] (597x114)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "730万+人逛过"
│   │   │   │     📐 **Bounds**: [436,829] → [633,872] (197x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "50+达人推荐"
│   │   │   │     📐 **Bounds**: [651,829] → [832,872] (181x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "¥ 9.9"
│   │   │   │     💬 **Desc**: "现价9.90元"
│   │   │   │     📐 **Bounds**: [436,955] → [522,1007] (86x52)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "已售53.6万"
│   │   │   │     💬 **Desc**: "已售53.6万"
│   │   │   │     📐 **Bounds**: [534,973] → [689,1010] (155x37)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "先用后付"
│   │   │   │     📐 **Bounds**: [442,1022] → [554,1061] (112x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "运费险"
│   │   │   │     📐 **Bounds**: [578,1022] → [662,1061] (84x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "7天无理由退货"
│   │   │   │     📐 **Bounds**: [686,1022] → [870,1061] (184x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "极速退款"
│   │   │   │     📐 **Bounds**: [894,1022] → [1006,1061] (112x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     📐 **Bounds**: [24,1097] → [1056,1486] (1032x389)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "1​5​0​毫​升​P​P​材​质​安​全​卫​生​耐​高​温​塑​杯​餐​饮​杯​商​用​硬​质​一​次​性​茶​杯​水​杯​"
│   │   │   │     💬 **Desc**: "1​5​0​毫​升​P​P​材​质​安​全​卫​生​耐​高​温​塑​杯​餐​饮​杯​商​用​硬​质​一​次​性​茶​杯​水​杯​"
│   │   │   │     📐 **Bounds**: [436,1109] → [1033,1223] (597x114)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "4千+人逛过"
│   │   │   │     📐 **Bounds**: [436,1230] → [599,1273] (163x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "¥ 18.6"
│   │   │   │     💬 **Desc**: "现价18.60元"
│   │   │   │     📐 **Bounds**: [436,1356] → [546,1408] (110x52)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "已售19"
│   │   │   │     💬 **Desc**: "已售19"
│   │   │   │     📐 **Bounds**: [558,1374] → [653,1411] (95x37)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "先用后付"
│   │   │   │     📐 **Bounds**: [442,1423] → [554,1462] (112x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "运费险"
│   │   │   │     📐 **Bounds**: [578,1423] → [662,1462] (84x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "明日发"
│   │   │   │     📐 **Bounds**: [686,1423] → [770,1462] (84x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "7天无理由退货"
│   │   │   │     📐 **Bounds**: [794,1423] → [978,1462] (184x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     📐 **Bounds**: [24,1498] → [1056,1887] (1032x389)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "【​大​容​量​】​硬​质​航​空​杯​家​用​商​用​加​厚​水​杯​耐​高​温​茶​杯​高​透​明​一​次​性​杯​子​"
│   │   │   │     💬 **Desc**: "【​大​容​量​】​硬​质​航​空​杯​家​用​商​用​加​厚​水​杯​耐​高​温​茶​杯​高​透​明​一​次​性​杯​子​"
│   │   │   │     📐 **Bounds**: [436,1510] → [1033,1624] (597x114)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "2千+人逛过"
│   │   │   │     📐 **Bounds**: [436,1631] → [597,1674] (161x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "好评率97.4%"
│   │   │   │     📐 **Bounds**: [615,1631] → [794,1674] (179x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "627人加购"
│   │   │   │     📐 **Bounds**: [812,1631] → [958,1674] (146x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "¥ 13.59"
│   │   │   │     💬 **Desc**: "现价13.59元"
│   │   │   │     📐 **Bounds**: [436,1757] → [565,1809] (129x52)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "已售1094"
│   │   │   │     💬 **Desc**: "已售1094"
│   │   │   │     📐 **Bounds**: [577,1775] → [710,1812] (133x37)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "先用后付"
│   │   │   │     📐 **Bounds**: [442,1824] → [554,1863] (112x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "运费险"
│   │   │   │     📐 **Bounds**: [578,1824] → [662,1863] (84x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "7天无理由退货"
│   │   │   │     📐 **Bounds**: [686,1824] → [870,1863] (184x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "极速退款"
│   │   │   │     📐 **Bounds**: [894,1824] → [1006,1863] (112x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     📐 **Bounds**: [24,1899] → [1056,2288] (1032x389)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "航​空​杯​一​次​性​杯​子​水​杯​家​用​加​厚​防​烫​茶​杯​太​空​杯​透​明​食​品​级​塑​料​杯​"
│   │   │   │     💬 **Desc**: "航​空​杯​一​次​性​杯​子​水​杯​家​用​加​厚​防​烫​茶​杯​太​空​杯​透​明​食​品​级​塑​料​杯​"
│   │   │   │     📐 **Bounds**: [436,1911] → [1033,2025] (597x114)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "1万+人逛过"
│   │   │   │     📐 **Bounds**: [436,2032] → [594,2075] (158x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "好评率99.2%"
│   │   │   │     📐 **Bounds**: [612,2032] → [793,2075] (181x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "568人加购"
│   │   │   │     📐 **Bounds**: [811,2032] → [959,2075] (148x43)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "¥ 7.8"
│   │   │   │     💬 **Desc**: "现价7.80元"
│   │   │   │     📐 **Bounds**: [436,2158] → [522,2210] (86x52)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "已售1696"
│   │   │   │     💬 **Desc**: "已售1696"
│   │   │   │     📐 **Bounds**: [534,2176] → [667,2213] (133x37)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "先用后付"
│   │   │   │     📐 **Bounds**: [442,2225] → [554,2264] (112x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "运费险"
│   │   │   │     📐 **Bounds**: [578,2225] → [662,2264] (84x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "明日发"
│   │   │   │     📐 **Bounds**: [686,2225] → [770,2264] (84x39)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "7天无理由退货"
│   │   │   │     📐 **Bounds**: [794,2225] → [978,2264] (184x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     📐 **Bounds**: [24,2300] → [1056,2346] (1032x46)
│   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │     📝 **Text**: "航​空​杯​一​次​性​杯​子​小​号​加​厚​加​硬​塑​料​杯​食​品​级​防​烫​无​异​味​批​发​茶​杯​"
│   │   │   │     💬 **Desc**: "航​空​杯​一​次​性​杯​子​小​号​加​厚​加​硬​塑​料​杯​食​品​级​防​烫​无​异​味​批​发​茶​杯​"
│   │   │   │     📐 **Bounds**: [436,2312] → [1033,2346] (597x34)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "2千+人逛过"
│   │   │   │     📐 **Bounds**: [436,2433] → [597,2346] (161x-87)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "¥ 35.6"
│   │   │   │     💬 **Desc**: "现价35.60元"
│   │   │   │     📐 **Bounds**: [436,2559] → [551,2346] (115x-213)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "已售9"
│   │   │   │     💬 **Desc**: "已售9"
│   │   │   │     📐 **Bounds**: [563,2577] → [644,2346] (81x-231)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "先用后付"
│   │   │   │     📐 **Bounds**: [442,2626] → [554,2346] (112x-280)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "运费险"
│   │   │   │     📐 **Bounds**: [578,2626] → [662,2346] (84x-280)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "7天无理由退货"
│   │   │   │     📐 **Bounds**: [686,2626] → [870,2346] (184x-280)
│   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │     📝 **Text**: "极速退款"
│   │   │   │     📐 **Bounds**: [894,2626] → [1006,2346] (112x-280)
├─ **[android.widget.FrameLayout]** `clickable enabled visible focusable `
│     📐 **Bounds**: [888,1704] → [1080,1896] (192x192)
├─ **[android.widget.FrameLayout]** `enabled focusable `
│     📐 **Bounds**: [0,2346] → [1080,2346] (1080x0)
