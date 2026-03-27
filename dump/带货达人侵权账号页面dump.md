# UI结构 Dump

**生成时间**: 2026-03-26 11:38:40

---

=== UI结构 Dump (所有窗口) ===
时间: 2026-03-26 11:38:40

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
│   │     📝 **Text**: "11:38"
│   │     💬 **Desc**: "11:38"
│   │     📐 **Bounds**: [60,47] → [174,94] (114x47)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "应用商店通知："
│   │     📐 **Bounds**: [174,45] → [209,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "浏览器通知："
│   │     📐 **Bounds**: [221,45] → [256,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "通话管理通知："
│   │     📐 **Bounds**: [267,45] → [302,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "手机管家通知："
│   │     📐 **Bounds**: [314,45] → [349,96] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "vivo账号通知："
│   │     📐 **Bounds**: [361,45] → [396,96] (35x51)
│   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/system_icons`
│   │     📐 **Bounds**: [585,28] → [1020,114] (435x86)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/networkspeed_vertical`
│   │   │     💬 **Desc**: "0.00KB/s"
│   │   │     📐 **Bounds**: [826,51] → [883,90] (57x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/mobile_combo`
│   │   │     💬 **Desc**: "中国移动 5G+ 信号强度四格，共四格"
│   │   │     📐 **Bounds**: [892,32] → [948,110] (56x78)
│   │   ├─ **[]** `enabled visible `
│   │   │     💬 **Desc**: "电池电量为百分之 81。"
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
  📐 **Bounds**: [100,214] → [1060,496] (960x282)
├─ **[android.view.View]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/view_drag_handle`
│     📐 **Bounds**: [130,244] → [1030,274] (900x30)
├─ **[android.widget.TextView]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/tv_float_status`
│     📝 **Text**: "空闲"
│     📐 **Bounds**: [190,292] → [262,340] (72x48)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_start`
│     📝 **Text**: "开始"
│     📐 **Bounds**: [130,363] → [334,466] (204x103)
├─ **[android.widget.Button]** `clickable visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_stop`
│     📝 **Text**: "停止"
│     📐 **Bounds**: [352,363] → [556,466] (204x103)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_test_coordinate`
│     📝 **Text**: "📍"
│     📐 **Bounds**: [574,360] → [730,466] (156x106)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_dump`
│     📝 **Text**: "Dump"
│     📐 **Bounds**: [748,365] → [904,466] (156x101)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_minimize`
│     📝 **Text**: "—"
│     📐 **Bounds**: [922,358] → [1030,466] (108x108)
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
│   │   │   │   │   │     📐 **Bounds**: [726,129] → [1035,207] (309x78)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [747,144] → [798,192] (51x48)
│   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "ic_calendar_outlined_16"
│   │   │   │   │   │   │   │     📐 **Bounds**: [747,144] → [798,192] (51x48)
│   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │     📝 **Text**: "近7日动态"
│   │   │   │   │   │   │     📐 **Bounds**: [807,138] → [966,198] (159x60)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     📐 **Bounds**: [975,150] → [1011,186] (36x36)
│   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "icon_down"
│   │   │   │   │   │   │   │     📐 **Bounds**: [975,150] → [1011,186] (36x36)
│   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │     🆔 **ID**: `alliance-mobile-layout-content-container`
│   │   │   │   │     📐 **Bounds**: [0,222] → [1080,2346] (1080x2124)
│   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [0,0] → [1080,2346] (1080x2346)
│   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │     📐 **Bounds**: [0,237] → [1080,2346] (1080x2109)
│   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │     🆔 **ID**: `header-info`
│   │   │   │   │   │   │     📐 **Bounds**: [0,237] → [1080,540] (1080x303)
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
│   │   │   │   │   │   │   │     📝 **Text**: "2"
│   │   │   │   │   │   │   │     📐 **Bounds**: [45,483] → [75,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [72,483] → [102,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [99,483] → [129,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "-"
│   │   │   │   │   │   │   │     📐 **Bounds**: [126,483] → [150,537] (24x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "4"
│   │   │   │   │   │   │   │     📐 **Bounds**: [147,483] → [180,537] (33x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │     📐 **Bounds**: [177,483] → [204,537] (27x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [201,483] → [231,537] (30x54)
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
│   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │     📐 **Bounds**: [426,483] → [456,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [453,483] → [483,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [480,483] → [510,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "-"
│   │   │   │   │   │   │   │     📐 **Bounds**: [507,483] → [531,537] (24x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │     📐 **Bounds**: [528,483] → [558,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [555,483] → [585,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [582,483] → [612,537] (30x54)
│   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │     📝 **Text**: "0"
│   │   │   │   │   │   │   │     📐 **Bounds**: [609,483] → [642,537] (33x54)
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
│   │   │   │   │   │   │     📐 **Bounds**: [0,594] → [1080,2346] (1080x1752)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [0,594] → [1080,735] (1080x141)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,594] → [1080,714] (1080x120)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     🆔 **ID**: `container-fh541r9jvl`
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,594] → [1080,714] (1080x120)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `leftCustomDomId`
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,594] → [45,714] (0x120)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabbars-container`
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,594] → [1035,714] (990x120)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabbars-list29eq3c3vdvg`
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,594] → [1035,714] (990x120)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-0`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "商品"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,594] → [246,714] (201x120)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-1`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "直播"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [243,594] → [444,714] (201x120)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-2`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [441,594] → [639,714] (198x120)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-3`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "橱窗"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [636,594] → [837,714] (201x120)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     🆔 **ID**: `tabBarIndex-4`
│   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "数据"
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [834,594] → [1035,714] (201x120)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,702] → [246,714] (201x12)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [45,753] → [156,798] (111x45)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │     📝 **Text**: "销量"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [45,753] → [123,798] (78x45)
│   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [129,756] → [150,774] (21x18)
│   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [129,774] → [150,792] (21x18)
│   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │     📐 **Bounds**: [0,813] → [1080,2346] (1080x1533)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,813] → [1080,1116] (1080x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,813] → [1080,1116] (1071x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,813] → [1071,1116] (1062x303)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,963] → [1071,1116] (1062x153)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,813] → [1071,1116] (1062x303)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [1047,837] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [1047,1092] (1014x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1092] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1092] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1092] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1092] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,837] → [288,1092] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,1092] (738x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,1002] (738x165)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,1002] (738x165)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,885] (738x48)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,885] (738x48)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,882] → [1047,882] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,882] → [1047,984] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,888] → [429,930] (120x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,927] → [333,969] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,921] → [354,972] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,927] → [363,969] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,927] → [381,969] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,888] → [669,930] (222x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,921] → [495,972] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,927] → [522,969] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,930] → [624,969] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,930] → [642,969] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,930] → [669,969] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,888] → [858,930] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "450+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,936] → [858,978] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,888] → [1047,930] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥5000+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,936] → [1047,978] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1020] → [1047,1092] (738x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1020] → [864,1092] (555x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1035] → [864,1077] (555x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1035] → [489,1077] (180x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "带货视频"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1035] → [438,1077] (129x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: " 7"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [435,1035] → [465,1077] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [474,1041] → [489,1068] (15x27)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1020] → [1047,1092] (186x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1020] → [1047,1092] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1089] → [1047,1089] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,963] → [1068,1116] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │     📝 **Text**: "没有更多内容啦"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1146] → [1080,1287] (1080x141)
