# UI结构 Dump

**生成时间**: 2026-03-26 17:14:50

---

=== UI结构 Dump (所有窗口) ===
时间: 2026-03-26 17:14:50

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
│   │     📝 **Text**: "17:14"
│   │     💬 **Desc**: "17:14"
│   │     📐 **Bounds**: [57,50] → [170,97] (113x47)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "应用商店通知："
│   │     📐 **Bounds**: [170,48] → [205,99] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "浏览器通知："
│   │     📐 **Bounds**: [217,48] → [252,99] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "通话管理通知："
│   │     📐 **Bounds**: [263,48] → [298,99] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "手机管家通知："
│   │     📐 **Bounds**: [310,48] → [345,99] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "vivo账号通知："
│   │     📐 **Bounds**: [357,48] → [392,99] (35x51)
│   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/system_icons`
│   │     📐 **Bounds**: [582,31] → [1017,114] (435x83)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/networkspeed_vertical`
│   │   │     💬 **Desc**: "0.70KB/s"
│   │   │     📐 **Bounds**: [823,54] → [880,93] (57x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/mobile_combo`
│   │   │     💬 **Desc**: "中国移动 5G+ 信号强度四格，共四格"
│   │   │     📐 **Bounds**: [889,35] → [945,113] (56x78)
│   │   ├─ **[]** `enabled visible `
│   │   │     💬 **Desc**: "电池电量为百分之 76。"
│   │   │     📐 **Bounds**: [954,56] → [1017,92] (63x36)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🪟 窗口 #3
类型: 系统窗口
层级: 1
活动: 否
聚焦: 否
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

包名: com.rightsguard.automation

**[android.widget.FrameLayout]** `enabled visible `
  📐 **Bounds**: [120,523] → [1080,805] (960x282)
├─ **[android.view.View]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/view_drag_handle`
│     📐 **Bounds**: [150,553] → [1050,583] (900x30)
├─ **[android.widget.TextView]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/tv_float_status`
│     📝 **Text**: "空闲"
│     📐 **Bounds**: [210,601] → [282,649] (72x48)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_start`
│     📝 **Text**: "开始"
│     📐 **Bounds**: [150,672] → [354,775] (204x103)
├─ **[android.widget.Button]** `clickable visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_stop`
│     📝 **Text**: "停止"
│     📐 **Bounds**: [372,672] → [576,775] (204x103)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_test_coordinate`
│     📝 **Text**: "📍"
│     📐 **Bounds**: [594,669] → [750,775] (156x106)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_dump`
│     📝 **Text**: "Dump"
│     📐 **Bounds**: [768,674] → [924,775] (156x101)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_minimize`
│     📝 **Text**: "—"
│     📐 **Bounds**: [942,667] → [1050,775] (108x108)
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
│     📐 **Bounds**: [0,0] → [1080,2346] (1080x2346)
│   ├─ **[android.webkit.WebView]** `enabled visible focusable `
│   │     📝 **Text**: "Rsbuild App"
│   │     📐 **Bounds**: [0,0] → [1080,2346] (1080x2346)
│   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │     📐 **Bounds**: [0,0] → [1080,2346] (1080x2346)
│   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │     🆔 **ID**: `root`
│   │   │   │     📐 **Bounds**: [0,0] → [1080,2346] (1080x2346)
│   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │     🆔 **ID**: `shopWindowHeaderElement`
│   │   │   │   │     📐 **Bounds**: [0,0] → [1080,225] (1080x225)
│   │   │   │   │   ├─ **[android.widget.Button]** `clickable enabled visible focusable `
│   │   │   │   │   │     📝 **Text**: "返回"
│   │   │   │   │   │     📐 **Bounds**: [45,120] → [93,216] (48x96)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     📝 **Text**: "达人主页"
│   │   │   │   │   │     📐 **Bounds**: [441,132] → [639,204] (198x72)
│   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [705,129] → [1035,207] (330x78)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [726,144] → [777,192] (51x48)
│   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "ic_calendar_outlined_16"
│   │   │   │   │   │   │   │     📐 **Bounds**: [726,144] → [777,192] (51x48)
│   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │     📝 **Text**: "近30日动态"
│   │   │   │   │   │   │     📐 **Bounds**: [786,138] → [966,198] (180x60)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [975,150] → [1011,186] (36x36)
│   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "icon_down"
│   │   │   │   │   │   │   │     📐 **Bounds**: [975,150] → [1011,186] (36x36)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [561,234] → [933,768] (372x534)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [561,234] → [933,768] (372x534)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [843,207] → [885,237] (42x30)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [843,216] → [885,258] (42x42)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [561,234] → [933,768] (372x534)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [561,234] → [933,378] (372x144)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "今日"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [618,279] → [708,333] (90x54)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "17:11更新"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [726,276] → [876,336] (150x60)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [561,375] → [933,507] (372x132)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "昨日"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [618,414] → [708,468] (90x54)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [561,504] → [933,639] (372x135)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "近7日"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [618,546] → [732,597] (114x51)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [561,636] → [933,771] (372x135)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "近30日"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [618,678] → [759,729] (141x51)
│   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │     🆔 **ID**: `alliance-mobile-layout-content-container`
│   │   │   │   │     📐 **Bounds**: [0,222] → [1080,2346] (1080x2124)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [0,0] → [1080,2346] (1080x2346)
│   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [0,237] → [1080,2346] (1080x2109)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     🆔 **ID**: `header-info`
│   │   │   │   │   │   │     📐 **Bounds**: [0,237] → [1080,543] (1080x306)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [0,237] → [1080,378] (1080x141)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,237] → [186,378] (141x141)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,234] → [186,378] (141x144)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "tos-cn-i-0813c001_oUBXDf5PAEDDQJgJBApgFCi8IO9kAA1SCAfAwg~tplv-dy-aweme-images-sr-c:92:0:q75"
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,234] → [186,378] (141x144)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [147,339] → [186,378] (39x39)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "douyin_icon"
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [147,339] → [186,378] (39x39)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │     📝 **Text**: "家加鲜餐饮具旗舰店授权号"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [207,237] → [840,309] (633x72)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [207,327] → [312,378] (105x51)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "svg+xml;base64,PHN2ZyB3aWR0aD0iMzciIGhlaWdodD0iMTciIHZpZXdCb3g9IjAgMCAzNyAxNyIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB4PSIuMTAzIiB5PSIuMTAzIiB3aWR0aD0iMzYuNzkzIiBoZWlnaHQ9IjE2LjQ2NCIgcng9IjguMSIgc3Ryb2tlPSIjQkJCIiBzdHJva2Utd2lkdGg9Ii4yIi8+PGcgZmlsdGVyPSJ1cmwoIzg3NWRlYTZmYTNmZThjNjE5YmZiMDc0Zjk2ZWVjM2ZlZGY4NzkzOGJlMWU0OWEyMGEzY2VhN2EwMzMxYWI3NTNhKSI+PHBhdGggZD0iTS4yMDMgOC4zMzVBOC4xMzIgOC4xMzIgMCAwMTguMzM1LjIwM2gyMC4zM2E4LjEzMiA4LjEzMiAwIDExMCAxNi4yNjRIOC4zMzVBOC4xMzIgOC4xMzIgMCAwMS4yMDMgOC4zMzV6IiBmaWxsPSJ1cmwoIzg3NWRlYTZmYTNmZThjNjE5YmZiMDc0Zjk2ZWVjM2ZlZGY4NzkzOGJlMWU0OWEyMGEzY2VhN2EwMzMxYWI3NTNiKSIvPjxwYXRoIGQ9Ik0uMjAzIDguMzM1QTguMTMyIDguMTMyIDAgMDE4LjMzNS4yMDNoMjAuMzNhOC4xMzIgOC4xMzIgMCAxMTAgMTYuMjY0SDguMzM1QTguMTMyIDguMTMyIDAgMDEuMjAzIDguMzM1eiIgZmlsbD0idXJsKCM4NzVkZWE2ZmEzZmU4YzYxOWJmYjA3NGY5NmVlYzNmZWRmODc5MzhiZTFlNDlhMjBhM2NlYTdhMDMzMWFiNzUzYykiIGZpbGwtb3BhY2l0eT0iLjc1Ii8+PHBhdGggZD0iTS4yMDMgOC4zMzVBOC4xMzIgOC4xMzIgMCAwMTguMzM1LjIwM2gyMC4zM2E4LjEzMiA4LjEzMiAwIDExMCAxNi4yNjRIOC4zMzVBOC4xMzIgOC4xMzIgMCAwMS4yMDMgOC4zMzV6IiBmaWxsPSJ1cmwoIzg3NWRlYTZmYTNmZThjNjE5YmZiMDc0Zjk2ZWVjM2ZlZGY4NzkzOGJlMWU0OWEyMGEzY2VhN2EwMzMxYWI3NTNkKSIvPjwvZz48cGF0aCBkPSJNMTYuMjM3IDExLjM2NGEuMTMzLjEzMyAwIDAxLS4wODQtLjAyOC4xMzIuMTMyIDAgMDEtLjAyMy0uMDg2bC40My02LjQ1NmEuMTEuMTEgMCAwMS4wMzQtLjA3Ni4xMjguMTI4IDAgMDEuMDg5LS4wMzloMS4zNmMuMDMxIDAgLjA1Ni4wMTMuMDczLjAzOS4wMjQuMDE5LjAzNS4wNDQuMDMzLjA3NmwtLjM0NCA1LjE2NmMtLjAwMi4wMzIuMDEzLjA0OC4wNDQuMDQ4aDMuMDU0Yy4wMzIgMCAuMDU2LjAxMy4wNzQuMDM4LjAyNC4wMi4wMzUuMDQ1LjAzMy4wNzdsLS4wNzUgMS4xMjdhLjEyNy4xMjcgMCAwMS0uMDQ0LjA4Ni4xMS4xMSAwIDAxLS4wNzkuMDI4aC00LjU3NXptNi43NiAwYy0uMDYzIDAtLjEwMi0uMDMyLS4xMTctLjA5NWwtMS41MDMtNi40NTYtLjAwNy0uMDM4Yy4wMDQtLjA2NC4wNDEtLjA5Ni4xMTEtLjA5NmgxLjQ2NWMuMDcgMCAuMTEyLjAzMi4xMjcuMDk2bC44MTEgNC4zNDVjLjAwNS4wMi4wMTQuMDI5LjAyNy4wMjlzLjAyMy0uMDEuMDMtLjAyOWwxLjM3Mi00LjM0NWMuMDIzLS4wNjQuMDctLjA5Ni4xNC0uMDk2aDEuNDM2Yy4wMzggMCAuMDY2LjAxMy4wODQuMDM5LjAxNy4wMjUuMDE4LjA1Ny4wMDMuMDk1bC0yLjM5MyA2LjQ1NmMtLjAyMy4wNjMtLjA2Ny4wOTUtLjEzLjA5NWgtMS40NTV6bTYuMzgxLTEuNDEzYy0uMDE0LjAxMy0uMDE4LjAyNS0uMDEyLjAzOC4wMDUuMDEzLjAxOC4wMi4wMzcuMDJoMi40NGMuMDMyIDAgLjA1Ny4wMTIuMDc1LjAzNy4wMjQuMDIuMDM1LjA0NS4wMzMuMDc3bC0uMDc1IDEuMTI3YS4xMjguMTI4IDAgMDEtLjA0NS4wODYuMTEuMTEgMCAwMS0uMDc4LjAyOGgtNC40ODlhLjEzMi4xMzIgMCAwMS0uMDg0LS4wMjguMTMyLjEzMiAwIDAxLS4wMjMtLjA4NmwuMDctMS4wNmEuMjA4LjIwOCAwIDAxLjA2OC0uMTQ0Yy40MDYtLjM1Ni45NDgtLjg2OSAxLjYyNC0xLjUzN2wuNjE0LS42MDJjLjYyLS41OTguOTQtMS4wMzQuOTU5LTEuMzA4YS41NDIuNTQyIDAgMDAtLjE4LS40NThjLS4xMjYtLjEyMS0uMjk4LS4xODItLjUxNS0uMTgyYS44MDEuODAxIDAgMDAtLjUzOC4xODIuNjU4LjY1OCAwIDAwLS4yMzMuNDc3bC0uMDE1LjIzYS4xMjguMTI4IDAgMDEtLjA0NC4wODUuMTEuMTEgMCAwMS0uMDc5LjAyOUgyNy41MWEuMTMzLjEzMyAwIDAxLS4wODQtLjAyOS4xMzMuMTMzIDAgMDEtLjAyMy0uMDg2bC4wMzItLjQ3N2MuMDQ5LS4zNTcuMTg4LS42NjkuNDE2LS45MzYuMjI5LS4yNjguNTItLjQ3MS44NzMtLjYxMS4zNTUtLjE0Ny43NDItLjIyIDEuMTY0LS4yMi40NzIgMCAuODc3LjA5IDEuMjE2LjI2N2ExLjcyMyAxLjcyMyAwIDAxLjk4NyAxLjcgMS45MjIgMS45MjIgMCAwMS0uMjQ0Ljc5M2MtLjE0NS4yNjctLjM1NS41NS0uNjMuODUtLjE5NC4yMTYtLjQxLjQzNi0uNjQ3LjY1OS0uMjM4LjIyMy0uNTY4LjUyMi0uOTg5Ljg5N2wtLjIwMy4xODJ6IiBmaWxsPSIjNTY2NTdGIi8+PHBhdGggZD0iTTYuNTE5IDUuMTI2aC0uMzU3Yy0uOTQ5IDAtMS43NTIuNy0xLjgyMiAxLjY0Ni0uMDQuNTMyLS4wNyAxLjEzOC0uMDcgMS43MzcgMCAuNzIzLjA0NCAxLjQ1Ny4wOTUgMi4wNTZhMS43MDUgMS43MDUgMCAwMDEuNDU3IDEuNTUzYy45My4xMzYgMi4yNDQuMjgzIDMuNTgyLjI4MyAxLjMzNyAwIDIuNjUxLS4xNDcgMy41ODEtLjI4M2ExLjcwNSAxLjcwNSAwIDAwMS40NTctMS41NTNjLjA1MS0uNTk5LjA5Ni0xLjMzMy4wOTYtMi4wNTYgMC0uNTk5LS4wMy0xLjIwNS0uMDctMS43MzctLjA3LS45NDYtLjg3NC0xLjY0Ni0xLjgyMy0xLjY0NmgtLjM1NmMtLjQ1MiAwLS44NzctLjIxNC0xLjE0Ni0uNTc4bC0uMTU1LS4yMWExLjQyNSAxLjQyNSAwIDAwLTEuMTQ1LS41NzdoLS44NzhjLS40NTIgMC0uODc3LjIxNC0xLjE0Ni41NzhsLS4xNTUuMjFhMS40MjUgMS40MjUgMCAwMS0xLjE0NS41Nzd6IiBmaWxsPSJ1cmwoIzg3NWRlYTZmYTNmZThjNjE5YmZiMDc0Zjk2ZWVjM2ZlZGY4NzkzOGJlMWU0OWEyMGEzY2VhN2EwMzMxYWI3NTNlKSIvPjxwYXRoIGQ9Ik02LjE2MiA1LjIyOGguMzU3Yy40ODQgMCAuOTQtLjIzIDEuMjI3LS42MmwuMTU1LS4yMDljLjI1LS4zMzcuNjQ0LS41MzYgMS4wNjQtLjUzNmguODc4Yy40MiAwIC44MTQuMTk5IDEuMDYzLjUzNmwuMTU1LjIxYy4yODguMzkuNzQ0LjYxOSAxLjIyOC42MTloLjM1NmMuOSAwIDEuNjU1LjY2MiAxLjcyMSAxLjU1Mi4wNC41My4wNyAxLjEzNC4wNyAxLjczIDAgLjcxOC0uMDQ0IDEuNDUtLjA5NSAyLjA0NmExLjYwNCAxLjYwNCAwIDAxLTEuMzcgMS40NjJjLS45MjguMTM1LTIuMjM2LjI4MS0zLjU2Ny4yODFzLTIuNjQtLjE0Ni0zLjU2Ny0uMjgxYTEuNjA0IDEuNjA0IDAgMDEtMS4zNy0xLjQ2MkEyNC44NiAyNC44NiAwIDAxNC4zNyA4LjUxYzAtLjU5NS4wMy0xLjIuMDctMS43MjkuMDY2LS44OS44MjItMS41NTIgMS43MjEtMS41NTJ6IiBzdHJva2U9IiMyQzJFMkYiIHN0cm9rZS1vcGFjaXR5PSIuMTIiIHN0cm9rZS13aWR0aD0iLjIiLz48cGF0aCBkPSJNMTIuNjYyIDguOTY3YzAtLjczNy0uNjA2LTEuMzM2LTEuMzUzLTEuMzM2Ljc0NyAwIDEuMzUzLS41OTggMS4zNTMtMS4zMzUgMCAuNzM3LjYwNiAxLjMzNSAxLjM1MyAxLjMzNS0uNzQ3IDAtMS4zNTMuNTk5LTEuMzUzIDEuMzM2ek0xMC40NyA4Ljc2N2ExLjgxIDEuODEgMCAwMS0zLjAzLTEuMTgzLjY4My42ODMgMCAxMC0xLjM2NiAwIDMuMTc2IDMuMTc2IDAgMDAzLjE3IDMuMDIxYy44MTUgMCAxLjU5OC0uMzE0IDIuMTg4LS44NzYuNDY1LS40NDIuNjc4LS43OTguNDQ3LTEuMTM2LS40NjQtLjY3Ny0xLjEyMi0uMDktMS40MS4xNzR6IiBmaWxsPSIjZmZmIi8+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSI4NzVkZWE2ZmEzZmU4YzYxOWJmYjA3NGY5NmVlYzNmZWRmODc5MzhiZTFlNDlhMjBhM2NlYTdhMDMzMWFiNzUzYiIgeDE9Ii0uODY2IiB5MT0iMS4zNzQiIHgyPSIyOC45OTkiIHkyPSIyMy45MjkiIGdyYWRpZW50VW5pdHM9InVzZXJTcGFjZU9uVXNlIj48c3RvcCBzdG9wLWNvbG9yPSIjRDNGMkY5Ii8+PHN0b3Agb2Zmc2V0PSIuNTIiIHN0b3AtY29sb3I9IiNFMkYxRjQiLz48c3RvcCBvZmZzZXQ9IjEiIHN0b3AtY29sb3I9IiNEMUVBRUYiLz48L2xpbmVhckdyYWRpZW50PjxsaW5lYXJHcmFkaWVudCBpZD0iODc1ZGVhNmZhM2ZlOGM2MTliZmIwNzRmOTZlZWMzZmVkZjg3OTM4YmUxZTQ5YTIwYTNjZWE3YTAzMzFhYjc1M2MiIHgxPSIyNS4wMTYiIHkxPSI4LjMzNSIgeDI9IjguOTg1IiB5Mj0iLTUuNzI2IiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+PHN0b3Agb2Zmc2V0PSIuMDkxIiBzdG9wLWNvbG9yPSIjZmZmIiBzdG9wLW9wYWNpdHk9IjAiLz48c3RvcCBvZmZzZXQ9Ii40MiIgc3RvcC1jb2xvcj0iI2ZmZiIgc3RvcC1vcGFjaXR5PSIuNjczIi8+PHN0b3Agb2Zmc2V0PSIuNzk2IiBzdG9wLWNvbG9yPSIjZmZmIi8+PC9saW5lYXJHcmFkaWVudD48bGluZWFyR3JhZGllbnQgaWQ9Ijg3NWRlYTZmYTNmZThjNjE5YmZiMDc0Zjk2ZWVjM2ZlZGY4NzkzOGJlMWU0OWEyMGEzY2VhN2EwMzMxYWI3NTNkIiB4MT0iOC4yMjgiIHkxPSIyLjg3MSIgeDI9IjQuMDEiIHkyPSItMi4yOCIgZ3JhZGllbnRVbml0cz0idXNlclNwYWNlT25Vc2UiPjxzdG9wIG9mZnNldD0iLjI0MSIgc3RvcC1jb2xvcj0iI0Q4RjhGRiIgc3RvcC1vcGFjaXR5PSIwIi8+PHN0b3Agb2Zmc2V0PSIxIiBzdG9wLWNvbG9yPSIjRTVGQUZGIi8+PC9saW5lYXJHcmFkaWVudD48bGluZWFyR3JhZGllbnQgaWQ9Ijg3NWRlYTZmYTNmZThjNjE5YmZiMDc0Zjk2ZWVjM2ZlZGY4NzkzOGJlMWU0OWEyMGEzY2VhN2EwMzMxYWI3NTNlIiB4MT0iOS40MDQiIHkxPSIzLjc2MSIgeDI9IjkuNDA0IiB5Mj0iMTEuODkzIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSI+PHN0b3Agc3RvcC1jb2xvcj0iIzhBQThENyIvPjxzdG9wIG9mZnNldD0iMSIgc3RvcC1jb2xvcj0iIzUyNjA3OSIvPjwvbGluZWFyR3JhZGllbnQ+PGZpbHRlciBpZD0iODc1ZGVhNmZhM2ZlOGM2MTliZmIwNzRmOTZlZWMzZmVkZjg3OTM4YmUxZTQ5YTIwYTNjZWE3YTAzMzFhYjc1M2EiIHg9Ii0uODEzIiB5PSItLjgxMyIgd2lkdGg9IjM3LjYwOSIgaGVpZ2h0PSIxNy4yOCIgZmlsdGVyVW5pdHM9InVzZXJTcGFjZU9uVXNlIiBjb2xvci1pbnRlcnBvbGF0aW9uLWZpbHRlcnM9InNSR0IiPjxmZUZsb29kIGZsb29kLW9wYWNpdHk9IjAiIHJlc3VsdD0iQmFja2dyb3VuZEltYWdlRml4Ii8+PGZlQmxlbmQgaW49IlNvdXJjZUdyYXBoaWMiIGluMj0iQmFja2dyb3VuZEltYWdlRml4IiByZXN1bHQ9InNoYXBlIi8+PGZlQ29sb3JNYXRyaXggaW49IlNvdXJjZUFscGhhIiB2YWx1ZXM9IjAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDEyNyAwIiByZXN1bHQ9ImhhcmRBbHBoYSIvPjxmZU9mZnNldCBkeD0iLTEiIGR5PSItMSIvPjxmZUdhdXNzaWFuQmx1ciBzdGREZXZpYXRpb249IjIuNSIvPjxmZUNvbXBvc2l0ZSBpbjI9ImhhcmRBbHBoYSIgb3BlcmF0b3I9ImFyaXRobWV0aWMiIGsyPSItMSIgazM9IjEiLz48ZmVDb2xvck1hdHJpeCB2YWx1ZXM9IjAgMCAwIDAgMSAwIDAgMCAwIDEgMCAwIDAgMCAxIDAgMCAwIDAuNSAwIi8+PGZlQmxlbmQgaW4yPSJzaGFwZSIgcmVzdWx0PSJlZmZlY3QxX2lubmVyU2hhZG93XzQwN18yNzc2MCIvPjwvZmlsdGVyPjwvZGVmcz48L3N2Zz4="
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [207,327] → [312,378] (105x51)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [324,333] → [360,372] (36x39)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "daren_icon"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [324,333] → [360,372] (36x39)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │     📝 **Text**: "832"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [363,327] → [420,378] (57x51)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [444,333] → [483,372] (39x39)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "category_icon"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [444,333] → [483,372] (39x39)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │     📝 **Text**: "智能家居"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [486,327] → [615,378] (129x51)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [849,294] → [1047,366] (198x72)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📝 **Text**: "收藏"
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [912,306] → [984,354] (72x48)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "销量"
│   │   │   │   │   │   │   │     📐 **Bounds**: [45,432] → [375,471] (330x39)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "1"
│   │   │   │   │   │   │   │     📐 **Bounds**: [45,483] → [66,537] (21x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [63,483] → [93,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [90,483] → [120,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [117,483] → [147,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "-"
│   │   │   │   │   │   │   │     📐 **Bounds**: [144,483] → [168,537] (24x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "2"
│   │   │   │   │   │   │   │     📐 **Bounds**: [165,483] → [195,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │     📐 **Bounds**: [192,483] → [222,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [219,483] → [249,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [246,483] → [276,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │     📐 **Bounds**: [372,432] → [798,471] (426x39)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │     📐 **Bounds**: [372,489] → [402,540] (30x51)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "2"
│   │   │   │   │   │   │   │     📐 **Bounds**: [399,483] → [429,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │     📐 **Bounds**: [426,483] → [444,537] (18x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │     📐 **Bounds**: [441,483] → [468,537] (27x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "万"
│   │   │   │   │   │   │   │     📐 **Bounds**: [465,489] → [510,543] (45x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "-"
│   │   │   │   │   │   │   │     📐 **Bounds**: [507,483] → [528,537] (21x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │     📐 **Bounds**: [525,483] → [555,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "万"
│   │   │   │   │   │   │   │     📐 **Bounds**: [552,489] → [597,543] (45x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "动销商品"
│   │   │   │   │   │   │   │     📐 **Bounds**: [795,432] → [1080,471] (285x39)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [795,483] → [825,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "-"
│   │   │   │   │   │   │   │     📐 **Bounds**: [822,483] → [843,537] (21x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "1"
│   │   │   │   │   │   │   │     📐 **Bounds**: [840,483] → [861,537] (21x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [858,483] → [888,537] (30x54)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [0,597] → [1080,2346] (1080x1749)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [0,597] → [1080,738] (1080x141)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,597] → [1080,714] (1080x117)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     🆔 **ID**: `container-mi07efa9c1o`
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,597] → [1080,714] (1080x117)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `leftCustomDomId`
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,597] → [45,714] (0x117)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabbars-container`
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,597] → [1035,714] (990x117)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabbars-list4m9to87drdc`
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,597] → [1035,714] (990x117)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-0`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "商品"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,597] → [246,714] (201x117)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-1`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "直播"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [243,597] → [444,714] (201x117)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-2`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [441,597] → [639,714] (198x117)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-3`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "橱窗"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [636,597] → [837,714] (201x117)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-4`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "数据"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [834,597] → [1035,714] (201x117)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [441,702] → [642,714] (201x12)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [45,753] → [156,801] (111x48)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │     📝 **Text**: "销量"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,753] → [123,801] (78x48)
│   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [129,759] → [150,777] (21x18)
│   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [129,777] → [150,795] (21x18)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [0,816] → [1080,2346] (1080x1530)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,816] → [1080,1119] (1080x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,816] → [1080,1119] (1071x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,816] → [1071,1119] (1062x303)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,966] → [1071,1119] (1062x153)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,816] → [1071,1119] (1062x303)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [1047,837] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [1047,1095] (1014x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1095] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1095] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1095] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1095] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1095] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,1095] (738x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,996] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,996] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,888] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,888] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,885] → [1047,885] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,885] → [1047,987] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,891] → [429,930] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,930] → [333,972] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,921] → [354,972] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,930] → [363,972] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,930] → [381,972] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,891] → [669,930] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,921] → [495,972] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,930] → [522,972] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,933] → [624,972] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,933] → [642,972] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,933] → [669,972] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,891] → [858,930] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "1000+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,936] → [858,981] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,891] → [1047,930] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥1万+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,936] → [1047,981] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1017] → [1047,1095] (738x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1017] → [864,1095] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1017] → [864,1095] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1020] → [381,1092] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o0BXXMwzgGahpDEReBfQfPvRlQmzARXKSCC1U8?x-expires=1774537200&x-signature=w6hcWfXKKYN2EDc5pifiyZihrDs%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1020] → [381,1092] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,1035] → [495,1074] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "54.25万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,1035] → [609,1077] (114x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1017] → [1047,1095] (186x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1017] → [1047,1089] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1092] → [1047,1092] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,966] → [1068,1119] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1137] → [1080,1440] (1080x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1137] → [1080,1440] (1071x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1137] → [1071,1440] (1062x303)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1287] → [1071,1440] (1062x153)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1137] → [1071,1440] (1062x303)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [1047,1161] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [1047,1416] (1014x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [288,1416] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [288,1416] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [288,1416] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [288,1416] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1161] → [288,1416] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1161] → [1047,1416] (738x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1161] → [1047,1320] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1161] → [1047,1320] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1161] → [1047,1212] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1161] → [1047,1212] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1209] → [1047,1209] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1209] → [1047,1308] (738x99)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1215] → [429,1254] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1254] → [333,1293] (24x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,1245] → [354,1296] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,1254] → [363,1293] (12x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,1254] → [381,1293] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1215] → [669,1254] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1245] → [495,1296] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,1254] → [522,1293] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,1254] → [624,1296] (99x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,1254] → [642,1296] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,1254] → [669,1296] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1215] → [858,1254] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "100+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1260] → [858,1302] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1215] → [1047,1254] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥1000+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1260] → [1047,1302] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [1047,1416] (738x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [864,1416] (555x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [864,1416] (555x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [381,1413] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o8zGIDs4tmqAGZeCQpyuD14Q349LgNAdHeBsHg?x-expires=1774537200&x-signature=nPMy4lyTF%2F%2BfakPjP0XgSDj3u6g%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [381,1413] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,1359] → [495,1398] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "368.56万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,1356] → [630,1401] (135x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1341] → [1047,1416] (186x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1341] → [1047,1413] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1413] → [1047,1413] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,1287] → [1068,1440] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1461] → [1080,1764] (1080x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1461] → [1080,1764] (1071x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1461] → [1071,1764] (1062x303)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1611] → [1071,1764] (1062x153)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1461] → [1071,1764] (1062x303)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [1047,1482] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [1047,1740] (1014x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1740] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1740] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1740] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1740] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1740] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1740] (738x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1641] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1641] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1533] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1533] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1530] → [1047,1530] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1530] → [1047,1632] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1536] → [429,1575] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1575] → [333,1617] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,1566] → [354,1617] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,1575] → [363,1617] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,1575] → [381,1617] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1536] → [669,1575] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1566] → [495,1617] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,1575] → [522,1617] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,1578] → [624,1617] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,1578] → [642,1617] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,1578] → [669,1617] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1536] → [858,1575] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "60+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1581] → [858,1626] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1536] → [1047,1575] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥1000+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1581] → [1047,1626] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1662] → [1047,1740] (738x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1662] → [864,1740] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1662] → [864,1740] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1665] → [381,1737] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "oQXfNLfgRWBGsVv0CjLIAlG5RtGzwmZgQ7ehaP?x-expires=1774537200&x-signature=ZPXvyQMfLRW7YUZpSVSqYtZ8y0o%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1665] → [381,1737] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,1680] → [495,1719] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "22.57万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,1680] → [609,1722] (114x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1662] → [1047,1740] (186x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1662] → [1047,1734] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1737] → [1047,1737] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,1611] → [1068,1764] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1782] → [1080,2085] (1080x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1782] → [1080,2085] (1071x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1782] → [1071,2085] (1062x303)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1932] → [1071,2085] (1062x153)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1782] → [1071,2085] (1062x303)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [1047,1806] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [1047,2061] (1014x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [288,2061] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [288,2061] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [288,2061] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [288,2061] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1806] → [288,2061] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1806] → [1047,2061] (738x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1806] → [1047,1965] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1806] → [1047,1965] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1806] → [1047,1857] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1806] → [1047,1857] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1854] → [1047,1854] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1854] → [1047,1953] (738x99)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1860] → [429,1899] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1899] → [333,1938] (24x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,1890] → [354,1941] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,1899] → [363,1938] (12x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,1899] → [381,1938] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1860] → [669,1899] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1890] → [495,1941] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,1899] → [522,1938] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,1899] → [624,1941] (99x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,1899] → [642,1941] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,1899] → [669,1941] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1860] → [858,1899] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "60+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1905] → [858,1947] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1860] → [1047,1899] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥450+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1905] → [1047,1947] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [1047,2061] (738x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [864,2061] (555x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [864,2061] (555x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [381,2061] (72x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "owiRlv8iA8H7HUayETHIsZPF9VIBDZQ2KlO8A?x-expires=1774537200&x-signature=Z1SdOG7Xky%2B5wuEzoeUl6%2FWQ7Lo%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [381,2061] (72x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2004] → [495,2043] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "52.17万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2001] → [603,2046] (108x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1986] → [1047,2061] (186x75)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1986] → [1047,2058] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2058] → [1047,2058] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,1932] → [1068,2085] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2106] → [1080,2346] (1080x240)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2106] → [1080,2346] (1071x240)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2106] → [1071,2346] (1062x240)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2256] → [1071,2346] (1062x90)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2106] → [1071,2346] (1062x240)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [1047,2130] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [1047,2346] (1014x216)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [288,2346] (255x216)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [288,2346] (255x216)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [288,2346] (255x216)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [288,2346] (255x216)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2130] → [288,2346] (255x216)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2130] → [1047,2346] (738x216)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2130] → [1047,2286] (738x156)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2130] → [1047,2286] (738x156)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2130] → [1047,2178] (738x48)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2130] → [1047,2178] (738x48)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2175] → [1047,2175] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2175] → [1047,2277] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2181] → [429,2220] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2220] → [333,2262] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,2214] → [354,2265] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,2220] → [363,2262] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,2220] → [381,2262] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2181] → [669,2220] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2214] → [495,2265] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,2220] → [522,2262] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,2223] → [624,2262] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,2223] → [642,2262] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,2223] → [669,2262] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2181] → [858,2220] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "60+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2226] → [858,2271] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2181] → [1047,2220] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥450+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2226] → [1047,2271] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2307] → [1047,2346] (738x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2307] → [864,2346] (555x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2307] → [864,2346] (555x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2310] → [381,2346] (72x36)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o0IWANJEljjf4tG3e2cY5ADHoeE4ZvNGfQ9ggf?x-expires=1774537200&x-signature=K9tKw5mbrpse%2Biy14o2fv7KpNiQ%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2310] → [381,2346] (72x36)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2325] → [495,2346] (99x21)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "35.09万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2325] → [612,2346] (117x21)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2307] → [1047,2346] (186x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2307] → [1047,2346] (186x39)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,2256] → [1068,2346] (0x90)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2346] → [1080,2346] (1080x0)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1080,2346] (1071x0)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [429,2346] (120x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [333,2346] (24x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,2346] → [354,2346] (24x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,2346] → [363,2346] (12x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,2346] → [381,2346] (21x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2346] → [669,2346] (222x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2346] → [495,2346] (48x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,2346] → [522,2346] (30x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,2346] → [624,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,2346] → [642,2346] (21x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,2346] → [669,2346] (30x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "20+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥200+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [864,2346] (555x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [864,2346] (555x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o0JBLvSUFfziIh7RLXGWoQgGCAeGwo3CwqetLL?x-expires=1774537200&x-signature=8BzpF1NjyOQO5o%2BdxGkaVmybKlc%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2346] → [495,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "1.09万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2346] → [591,2346] (96x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2346] → [1047,2346] (186x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2346] → [1047,2346] (186x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,2346] → [1068,2346] (0x0)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2346] → [1080,2346] (1080x0)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1080,2346] (1071x0)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [429,2346] (120x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [333,2346] (24x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,2346] → [354,2346] (24x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,2346] → [363,2346] (12x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,2346] → [381,2346] (21x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2346] → [669,2346] (222x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2346] → [495,2346] (48x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,2346] → [522,2346] (30x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,2346] → [624,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,2346] → [642,2346] (21x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,2346] → [669,2346] (30x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "10+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥200+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [864,2346] (555x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [864,2346] (555x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "ocQxgLCZm9aYMbGj9LW3edHGEZXjzIfA0SfqEf?x-expires=1774537200&x-signature=DwSUZgYxn%2FiEPoHE7W%2FZZFttqDw%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2346] → [495,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5.03万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2346] → [591,2346] (96x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2346] → [1047,2346] (186x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2346] → [1047,2346] (186x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,2346] → [1068,2346] (0x0)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2346] → [1080,2346] (1080x0)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1080,2346] (1071x0)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2346] → [1071,2346] (1062x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [288,2346] (255x0)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [429,2346] (120x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [333,2346] (24x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,2346] → [354,2346] (24x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,2346] → [363,2346] (12x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,2346] → [381,2346] (21x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2346] → [669,2346] (222x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2346] → [495,2346] (48x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,2346] → [522,2346] (30x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,2346] → [624,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,2346] → [642,2346] (21x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,2346] → [669,2346] (30x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "10+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥100+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [1047,2346] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [864,2346] (555x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [864,2346] (555x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "oUQPj9yP8gBIOITOsYR8Hity1BqWviEaACj53?x-expires=1774537200&x-signature=zDr%2BK1BLntnEP9vnHIkhK039HJY%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2346] → [495,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "21.39万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2346] → [606,2346] (111x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2346] → [1047,2346] (186x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2346] → [1047,2346] (186x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,2346] → [1068,2346] (0x0)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │     📝 **Text**: "没有更多内容啦"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2346] → [1080,2346] (1080x0)
