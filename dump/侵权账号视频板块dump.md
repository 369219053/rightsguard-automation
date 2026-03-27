# UI结构 Dump

**生成时间**: 2026-03-26 11:40:37

---

=== UI结构 Dump (所有窗口) ===
时间: 2026-03-26 11:40:37

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
│   │     📝 **Text**: "11:40"
│   │     💬 **Desc**: "11:40"
│   │     📐 **Bounds**: [60,48] → [176,95] (116x47)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "应用商店通知："
│   │     📐 **Bounds**: [176,46] → [211,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "浏览器通知："
│   │     📐 **Bounds**: [223,46] → [258,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "通话管理通知："
│   │     📐 **Bounds**: [269,46] → [304,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "手机管家通知："
│   │     📐 **Bounds**: [316,46] → [351,97] (35x51)
│   ├─ **[]** `enabled visible `
│   │     💬 **Desc**: "vivo账号通知："
│   │     📐 **Bounds**: [363,46] → [398,97] (35x51)
│   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │     🆔 **ID**: `com.android.systemui:id/system_icons`
│   │     📐 **Bounds**: [585,29] → [1020,114] (435x85)
│   │   ├─ **[android.widget.LinearLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/networkspeed_vertical`
│   │   │     💬 **Desc**: "0.90KB/s"
│   │   │     📐 **Bounds**: [826,52] → [883,91] (57x39)
│   │   ├─ **[android.widget.FrameLayout]** `enabled visible `
│   │   │     🆔 **ID**: `com.android.systemui:id/mobile_combo`
│   │   │     💬 **Desc**: "中国移动 5G+ 信号强度四格，共四格"
│   │   │     📐 **Bounds**: [892,33] → [948,111] (56x78)
│   │   ├─ **[]** `enabled visible `
│   │   │     💬 **Desc**: "电池电量为百分之 81。"
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
  📐 **Bounds**: [112,217] → [1072,499] (960x282)
├─ **[android.view.View]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/view_drag_handle`
│     📐 **Bounds**: [142,247] → [1042,277] (900x30)
├─ **[android.widget.TextView]** `enabled visible `
│     🆔 **ID**: `com.rightsguard.automation:id/tv_float_status`
│     📝 **Text**: "空闲"
│     📐 **Bounds**: [202,295] → [274,343] (72x48)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_start`
│     📝 **Text**: "开始"
│     📐 **Bounds**: [142,366] → [346,469] (204x103)
├─ **[android.widget.Button]** `clickable visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_stop`
│     📝 **Text**: "停止"
│     📐 **Bounds**: [364,366] → [568,469] (204x103)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_test_coordinate`
│     📝 **Text**: "📍"
│     📐 **Bounds**: [586,363] → [742,469] (156x106)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_dump`
│     📝 **Text**: "Dump"
│     📐 **Bounds**: [760,368] → [916,469] (156x101)
├─ **[android.widget.Button]** `clickable enabled visible focusable `
│     🆔 **ID**: `com.rightsguard.automation:id/btn_float_minimize`
│     📝 **Text**: "—"
│     📐 **Bounds**: [934,361] → [1042,469] (108x108)
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
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [441,702] → [642,714] (201x12)
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,996] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,837] → [1047,996] (738x159)
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,888] → [858,930] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "200+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,936] → [858,978] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,888] → [1047,930] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥2500+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,936] → [1047,978] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1014] → [1047,1092] (738x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1014] → [864,1092] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1014] → [864,1092] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1017] → [381,1089] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o0BXXMwzgGahpDEReBfQfPvRlQmzARXKSCC1U8?x-expires=1774515600&x-signature=ND6ouY5TqOSnpkzBRuVHpw6gAeE%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1017] → [381,1089] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,1032] → [495,1074] (99x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "53.83万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,1032] → [609,1077] (114x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1014] → [1047,1092] (186x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1014] → [1047,1086] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1089] → [1047,1089] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,963] → [1068,1116] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1137] → [1080,1437] (1080x300)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1137] → [1080,1437] (1071x300)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1137] → [1071,1437] (1062x300)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1287] → [1071,1437] (1062x150)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1137] → [1071,1437] (1062x300)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [1047,1158] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [1047,1416] (1014x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [288,1416] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [288,1416] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [288,1416] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [288,1416] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1158] → [288,1416] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1158] → [1047,1416] (738x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1158] → [1047,1317] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1158] → [1047,1317] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1158] → [1047,1209] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1158] → [1047,1209] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1206] → [1047,1206] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1206] → [1047,1308] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1212] → [429,1251] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1251] → [333,1293] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,1242] → [354,1293] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,1251] → [363,1293] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,1251] → [381,1293] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1212] → [669,1251] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1242] → [495,1293] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,1251] → [522,1293] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,1254] → [624,1293] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,1254] → [642,1293] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,1254] → [669,1293] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1212] → [858,1251] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "30+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1257] → [858,1302] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1212] → [1047,1251] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥200+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1257] → [1047,1302] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1338] → [1047,1416] (738x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1338] → [864,1416] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1338] → [864,1416] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [381,1413] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "owiRlv8iA8H7HUayETHIsZPF9VIBDZQ2KlO8A?x-expires=1774515600&x-signature=TgWZGTJqisF8jleG12mfwSzNzbw%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1341] → [381,1413] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,1356] → [495,1395] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "52.14万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,1356] → [606,1398] (111x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1338] → [1047,1416] (186x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1338] → [1047,1410] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1413] → [1047,1413] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,1287] → [1068,1437] (0x150)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1458] → [1080,1761] (1080x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1458] → [1080,1761] (1071x303)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1458] → [1071,1761] (1062x303)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1608] → [1071,1761] (1062x153)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1458] → [1071,1761] (1062x303)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [1047,1482] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [1047,1737] (1014x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1737] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1737] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1737] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1737] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1482] → [288,1737] (255x255)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1737] (738x255)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1641] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1641] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1530] (738x48)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1482] → [1047,1530] (738x48)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1527] → [1047,1527] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1527] → [1047,1629] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1533] → [429,1575] (120x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1572] → [333,1614] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,1566] → [354,1617] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,1572] → [363,1614] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,1572] → [381,1614] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1533] → [669,1575] (222x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1566] → [495,1617] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,1572] → [522,1614] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,1575] → [624,1614] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,1575] → [642,1614] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,1575] → [669,1614] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1533] → [858,1575] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "10+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1581] → [858,1623] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1533] → [1047,1575] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥200+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1581] → [1047,1623] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1659] → [1047,1737] (738x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1659] → [864,1737] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1659] → [864,1737] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1662] → [381,1734] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o8zGIDs4tmqAGZeCQpyuD14Q349LgNAdHeBsHg?x-expires=1774515600&x-signature=6YR3Cw%2F1qulQB3AFvan3oULGgfQ%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1662] → [381,1734] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,1677] → [495,1719] (99x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "368.55万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,1677] → [630,1722] (135x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1659] → [1047,1737] (186x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1659] → [1047,1731] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1734] → [1047,1734] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,1608] → [1068,1761] (0x153)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,1782] → [1080,2082] (1080x300)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1782] → [1080,2082] (1071x300)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1782] → [1071,2082] (1062x300)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1932] → [1071,2082] (1062x150)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,1782] → [1071,2082] (1062x300)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [1047,1803] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [1047,2061] (1014x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [288,2061] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [288,2061] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [288,2061] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [288,2061] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,1803] → [288,2061] (255x258)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1803] → [1047,2061] (738x258)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1803] → [1047,1962] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1803] → [1047,1962] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1803] → [1047,1854] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1803] → [1047,1854] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1851] → [1047,1851] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1851] → [1047,1953] (738x102)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1857] → [429,1896] (120x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1896] → [333,1938] (24x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,1887] → [354,1938] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,1896] → [363,1938] (12x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,1896] → [381,1938] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1857] → [669,1896] (222x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,1887] → [495,1938] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,1896] → [522,1938] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,1899] → [624,1938] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,1899] → [642,1938] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,1899] → [669,1938] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1857] → [858,1896] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "0-10"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,1902] → [858,1947] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1857] → [1047,1896] (168x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥60+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,1902] → [1047,1947] (168x45)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1983] → [1047,2061] (738x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1983] → [864,2061] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1983] → [864,2061] (555x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [381,2058] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "oQXfNLfgRWBGsVv0CjLIAlG5RtGzwmZgQ7ehaP?x-expires=1774515600&x-signature=aS1HlJAlaqdQC%2FnrQKeXSARQdpU%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,1986] → [381,2058] (72x72)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2001] → [495,2040] (99x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "22.57万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2001] → [609,2043] (114x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1983] → [1047,2061] (186x78)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,1983] → [1047,2055] (186x72)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2058] → [1047,2058] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,1932] → [1068,2082] (0x150)
│   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2103] → [1080,2346] (1080x243)
│   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2103] → [1080,2346] (1071x243)
│   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2103] → [1071,2346] (1062x243)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2253] → [1071,2346] (1062x93)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [9,2103] → [1071,2346] (1062x243)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [1047,2127] (1014x0)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [1047,2346] (1014x219)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [288,2346] (255x219)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [288,2346] (255x219)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [288,2346] (255x219)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "jpeg_m_7028a541c836f6261c9f16a74a112c81_sx_469823_www1259-1259~tplv-qzsgku4lz6-wallet:168:0:q75"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [288,2346] (255x219)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2127] → [288,2346] (255x219)
│   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2127] → [1047,2346] (738x219)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2127] → [1047,2286] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2127] → [1047,2286] (738x159)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2127] → [1047,2178] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "【100只装】加厚加硬大容量航空杯酒店KTV商务接待防烫一次性杯子"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2127] → [1047,2178] (738x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2175] → [1047,2175] (738x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2175] → [1047,2274] (738x99)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "到手价"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2178] → [429,2220] (120x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2220] → [333,2259] (24x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [330,2211] → [354,2262] (24x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "."
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [351,2220] → [363,2259] (12x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "9"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [360,2220] → [381,2259] (21x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "双佣金"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2178] → [669,2220] (222x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "28"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [447,2211] → [495,2262] (48x51)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [492,2220] → [522,2259] (30x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "投放期"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [525,2220] → [624,2262] (99x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [621,2220] → [642,2262] (21x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "%"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [639,2220] → [669,2262] (30x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "视频销量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2178] → [858,2220] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "0-10"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2226] → [858,2268] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2178] → [1047,2220] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥100+"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2226] → [1047,2268] (168x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2304] → [1047,2346] (738x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2304] → [864,2346] (555x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2304] → [864,2346] (555x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2307] → [381,2346] (72x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.Image]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "o0IWANJEljjf4tG3e2cY5ADHoeE4ZvNGfQ9ggf?x-expires=1774515600&x-signature=vLltJ8vFKj9kn9EmbLCzreM%2FY0I%3D&from=3657850223"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2307] → [381,2346] (72x39)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2322] → [495,2346] (99x24)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "35.09万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2322] → [612,2346] (117x24)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.view.View]** `enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2304] → [1047,2346] (186x42)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `clickable enabled visible `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "加选品车"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [861,2304] → [1047,2346] (186x42)
│   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [33,2346] → [1047,2346] (1014x0)
│   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [1068,2253] → [1068,2346] (0x93)
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "0-10"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥40+"
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2346] → [495,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "1.08万"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [495,2346] → [588,2346] (93x0)
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "0-10"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [690,2346] → [858,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "销售额"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [879,2346] → [1047,2346] (168x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "¥20+"
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
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [309,2346] → [381,2346] (72x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "播放量"
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📐 **Bounds**: [396,2346] → [495,2346] (99x0)
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │     📝 **Text**: "5.02万"
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
│   │   │   │   │   │   │   │   ├─ **[android.widget.TextView]** `enabled `
│   │   │   │   │   │   │   │   │     📝 **Text**: "没有更多内容啦"
│   │   │   │   │   │   │   │   │     📐 **Bounds**: [0,2346] → [1080,2346] (1080x0)
