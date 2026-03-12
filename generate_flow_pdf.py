# -*- coding: utf-8 -*-
from reportlab.lib.pagesizes import A4
from reportlab.lib import colors
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle, HRFlowable
from reportlab.lib.styles import ParagraphStyle
from reportlab.lib.units import mm
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont
import os

FONT_NAME = 'Chinese'
for fp in ['/System/Library/Fonts/PingFang.ttc', '/System/Library/Fonts/STHeiti Light.ttc']:
    if os.path.exists(fp):
        try:
            pdfmetrics.registerFont(TTFont(FONT_NAME, fp))
            break
        except Exception:
            continue

W = A4[0] - 36*mm

C = {
    'title':  colors.HexColor('#0D47A1'),
    'phase':  colors.HexColor('#1565C0'),
    'chain':  colors.HexColor('#E8F0FE'),
    'chain_b':colors.HexColor('#7BAAF7'),
    'node':   colors.HexColor('#FFFFFF'),
    'node_b': colors.HexColor('#4285F4'),
    'end':    colors.HexColor('#1B5E20'),
    'note':   colors.HexColor('#FFF8E1'),
    'note_b': colors.HexColor('#FFB300'),
    'arrow':  colors.HexColor('#4285F4'),
}

def ps(n, sz=9, ld=13, col=colors.black, left=0, align='LEFT', bold=False):
    return ParagraphStyle(n, fontName=FONT_NAME, fontSize=sz, leading=ld,
                          textColor=col, leftIndent=left, alignment={'LEFT':0,'CENTER':1,'RIGHT':2}[align])

TS  = ps('t',  16, 22, colors.white, align='CENTER')
PS  = ps('ph', 10, 14, colors.white)
CS  = ps('ch',  8, 12, colors.HexColor('#1A237E'))
NS  = ps('nt',  7, 10, colors.HexColor('#4E342E'), left=2)
AS  = ps('ar', 11, 14, C['arrow'], align='CENTER')
VS  = ps('vs', 14, 16, C['arrow'], align='CENTER')

def title_block(text):
    t = Table([[Paragraph(text, TS)]], colWidths=[W])
    t.setStyle(TableStyle([
        ('BACKGROUND',(0,0),(-1,-1),C['title']),
        ('TOPPADDING',(0,0),(-1,-1),8), ('BOTTOMPADDING',(0,0),(-1,-1),8),
        ('ROUNDEDCORNERS',[5]),
    ]))
    return t

def phase_block(num, name):
    t = Table([[Paragraph('<b>' + num + '  ' + name + '</b>', PS)]], colWidths=[W])
    t.setStyle(TableStyle([
        ('BACKGROUND',(0,0),(-1,-1),C['phase']),
        ('TOPPADDING',(0,0),(-1,-1),5), ('BOTTOMPADDING',(0,0),(-1,-1),5),
        ('LEFTPADDING',(0,0),(-1,-1),10),
    ]))
    return t

def chain_block(steps, note=None):
    """steps: list of str, connected by →"""
    text = '  →  '.join(steps)
    rows = [[Paragraph(text, CS)]]
    if note:
        rows.append([Paragraph('⚠ ' + note, NS)])
    t = Table(rows, colWidths=[W])
    ts = [
        ('BACKGROUND',(0,0),(-1,-1),C['chain']),
        ('BOX',(0,0),(-1,-1),0.8,C['chain_b']),
        ('TOPPADDING',(0,0),(-1,-1),5), ('BOTTOMPADDING',(0,0),(-1,-1),5),
        ('LEFTPADDING',(0,0),(-1,-1),8), ('RIGHTPADDING',(0,0),(-1,-1),8),
    ]
    if note:
        ts += [('BACKGROUND',(0,1),(0,1),C['note']),
               ('BOX',(0,1),(0,1),0.5,C['note_b'])]
    t.setStyle(TableStyle(ts))
    return t

def down_arrow():
    t = Table([[Paragraph('▼', VS)]], colWidths=[W])
    t.setStyle(TableStyle([
        ('TOPPADDING',(0,0),(-1,-1),0),('BOTTOMPADDING',(0,0),(-1,-1),0),
    ]))
    return t

S  = Spacer(1, 1.5*mm)
DA = down_arrow()

story = []
story += [title_block('权利卫士取证自动化  完整流程图  V4.2'), Spacer(1,3*mm)]

# ① 用户输入
story += [phase_block('①', '用户输入阶段'), S]
story += [chain_block(['粘贴取证信息', '解析备注(原创名-抖音:侵权人)', '提取侵权链接(最后一个URL)', '启动自动化服务']), DA, S]

# ② 打开视频
story += [phase_block('②', '打开侵权视频阶段'), S]
story += [chain_block(['启动WebView', '加载侵权链接', '拦截URL Scheme', 'Intent跳转抖音APP', '观看视频3秒', '清空剪贴板']), DA, S]

# ③ 录屏启动
story += [phase_block('③', '启动录屏取证阶段'), S]
story += [chain_block(['HOME最小化', '启动权利卫士', '点击【录屏取证】', '填写备注', '点击【开始录屏取证】']), S]
story += [chain_block(['系统弹窗 → 检测【立即开始】', '若为单应用 → 切换【整个屏幕】', '点击【立即开始】→ 录屏启动']), S]
story += [chain_block(['点击【开始取证】→ 跳转抖音']), DA, S]

# ④ 作者主页取证
story += [phase_block('④', '抖音作者设置页取证阶段'), S]
story += [chain_block(['等待抖音启动(3s)', '检测是否在首页', '随机延迟', '点击【我】(972,2273)']), S]
story += [chain_block(['点击【更多】(984,192)', '点击【设置】(627,186)', '下滑页面到"关于"部分']), S]
story += [chain_block(['查找【资质证照】', '点击', '截图(资质证照详情页)']), S]
story += [chain_block(['查找【营业执照】', '点击', '截图(营业执照页)', '停留1.5s']), S]
story += [chain_block(['智能返回"我"页面(最多4次)', '关闭右侧更多菜单']), DA, S]

# ⑤ 订单&资质规则
story += [phase_block('⑤', '订单与资质规则取证阶段'), S]
story += [chain_block(['点击【我的订单】', '点击【更多】(957,514)', '截图(订单更多页)']), S]
story += [chain_block(['OCR识别屏幕', '定位【资质规则】文字坐标', '点击', 'OCR轮询等待页面加载(最多5s)']), S]
story += [chain_block(['截图(资质规则页)', '上拉到底部', '智能返回"我"页面(最多5次)']), DA, S]

# ⑥ 视频播放+智能截图
story += [phase_block('⑥', '视频播放与智能截图阶段'), S]
story += [chain_block(['点击【观看历史】', '关键词匹配侵权视频', '点击播放']), S]
story += [chain_block(['ML Kit人脸检测(每200ms扫一帧)', '检测到有效人脸 → 保存截图', '视频<30s截4张 / >=30s截5张', '兜底帧补全不足张数']), S]
story += [chain_block(['截图完成', '暂停视频', '打开评论区']), S]
story += [chain_block(['读取评论总数', '滚动扫描购买意图关键词(70+)', '截图(1-3张)', '关闭评论区']), S]
story += [chain_block(['OCR找【进店】→ 截图 → 点击', '等待店铺主页加载 → 截图', '点击店铺卡片(400,386) → 截图详情页', 'OCR找【资质证照】→ 截图', '智能返回视频播放页']), DA, S]

# ⑦ 作者主页+分享
story += [phase_block('⑦', '作者主页与分享链接取证阶段'), S]
story += [chain_block(['点击头像 → 进入作者主页', '截图(作者主页)']), S]
story += [chain_block(['检测【店铺账号】标签', '点击 → OCR等待加载 → 截图(店铺账号认证页)', '智能返回作者主页(最多8次)']), S]
story += [chain_block(['点击【更多】(984,192)', '等800ms', '截图(作者更多菜单)', '智能返回视频播放页']), S]
story += [chain_block(['点击【分享】按钮', '右滑弹窗 → 点击【分享链接】(链接自动复制)']), S]
story += [chain_block(['点击【QQ】', '进入QQ → 点击【我的电脑】',
                       '点击输入框(466,2165)', '点击粘贴浮窗(484,1503)', '点击发送(956,1244)']), S]
story += [chain_block(['等1s → 截图(QQ发送取证)', 'HOME最小化QQ', 'Intent打开权利卫士']), DA, S]

# ⑧ 停止录屏+PDF
story += [phase_block('⑧', '停止录屏与生成PDF阶段'), S]
story += [chain_block(['等待3s界面加载', '点击【停止录屏取证】',
                       '策略1:文本查找 / 策略2:ID查找 / 策略3:坐标(540,727)']), S]
story += [chain_block(['等待3s录屏停止', '收集本次所有截图URI', 'PdfDocument逐页绘制(每张截图1页 1080x2400)',
                       '命名: 原创名称-抖音：侵权人账号名称.pdf', '保存至 Download/权利卫士取证/'],
                      note='取证全程闭环完成！PDF自动保存到手机下载目录'), S]

# 结束
end_t = Table([[Paragraph('<b>✅  取证流程结束</b>', PS)]], colWidths=[W])
end_t.setStyle(TableStyle([
    ('BACKGROUND',(0,0),(-1,-1),C['end']),
    ('TOPPADDING',(0,0),(-1,-1),7), ('BOTTOMPADDING',(0,0),(-1,-1),7),
    ('ALIGN',(0,0),(-1,-1),'CENTER'),
]))
story.append(end_t)

OUT = '权利卫士取证自动化_完整流程树状图_V4.2.pdf'
doc = SimpleDocTemplate(OUT, pagesize=A4,
    leftMargin=18*mm, rightMargin=18*mm,
    topMargin=12*mm, bottomMargin=12*mm)
doc.build(story)
print('PDF已生成: ' + OUT)
