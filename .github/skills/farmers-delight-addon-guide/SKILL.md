---
name: farmers-delight-addon-guide
description: |
  为 Minecraft 1.20.1 的 Farmer's Delight 附属模组提供可落地的设计、评审和实现指南。
  Use when user asks: "写农夫乐事附属", "设计 Farmer's Delight addon", "参考 Farmer's Delight 附属模组", "扩展作物/菜谱/饮品/村民/世界生成", "review Farmer's Delight addon plan".
  Reads this workspace's 12-addon reference corpus and produces grounded guidance instead of generic modding advice.
argument-hint: 描述你要做的附属方向、目标平台、内容范围，以及是要规划、评审还是直接实现
user-invocable: true
disable-model-invocation: false
---

# Farmer's Delight Addon Guide

## Scope

这个 skill 只用于 Farmer's Delight 附属模组相关工作，尤其适合以下任务：

- 为 bobiDelight 设计新作物、新菜谱、新饮品、新加工链。
- 参考现有附属模组，为当前项目写实现计划或代码改造方案。
- 审查一个 Farmer's Delight addon 设计是否完整，是否缺资源、缺依赖、缺数据链路。
- 从多个已发布附属模组中挑参考对象，而不是凭空给出通用建议。

如果用户的问题与 Farmer's Delight addon 无关，不要使用这个 skill。

## Required Reading

开始前先读下面这份参考：

- `references/fd-addon-guide.md`

然后根据任务类型，继续读取工作区中的本地参考语料：

- `示范文件/示范mod/农夫乐事附属参考/总览指南.md`
- `示范文件/示范mod/农夫乐事附属参考/addons-manifest.json`
- `示范文件/示范mod/农夫乐事附属参考/<目标模组>/README.md`

只有在已经确定要借鉴某个模组时，才继续深入其 `extracted` 目录；不要一开始就把 12 个模组全部展开阅读。

## Inputs To Collect

在回答或动手前，明确下面几点：

- 目标是 `规划`、`评审` 还是 `直接实现`
- 平台是 `Fabric`、`Forge` 还是双端
- 内容类型是 `作物链`、`维度/生态食材`、`饮品/加工`、`功能型扩展`、`兼容层`
- 是否必须做世界生成、村民交易、战利品掉落或工作站交互
- 是否要求保持与 bobiDelight 现有命名、资源布局和 Farmer's Delight 依赖方式一致

## Example Requests

下面这些请求都应该优先命中这个 skill：

- `帮我设计一个农夫乐事附属，用稻米做一整条食品链`
- `参考 Farmer's Delight 附属模组，给 bobiDelight 规划新的海鲜支线`
- `review 这个 Farmer's Delight addon 方案，看看缺哪些文件和依赖`
- `按现有附属模组的做法，给我补一个新作物和世界生成闭环`
- `我想给 bobiDelight 加一个饮品或发酵系统，先选参考模组再出方案`

## Workflow

1. 先把需求归类到一个明确的附属模式，不要混成“大而全”方案。
2. 从 12 个参考模组里挑 2 到 4 个最贴近的，而不是平均引用全部模组。
3. 先输出“内容闭环”判断：原料从哪里来，如何处理，最后变成什么成品。
4. 再输出文件级清单：需要哪些 `java`、`assets`、`data`、metadata 文件。
5. 如果任务是实现，优先做最小闭环：先让第一条内容链完整，再补拓展内容。
6. 改代码后必须做最相关的验证，优先用当前项目已可用的 Gradle 编译命令。

## Output Contract

当你使用这个 skill 时，输出必须尽量是下面三种之一，而不是泛泛而谈：

- `实现计划`：按文件和内容链拆分，说明先改什么、后改什么。
- `设计评审`：指出缺口、风险、依赖遗漏、资源缺失和最像哪个参考模组。
- `直接实现`：在代码、资源和数据层完成最小闭环，并说明参考了哪些模组。

如果用户只是要思路，也要至少给出：

- 参考模组选择
- 为什么选它们
- 最小落地闭环
- 必做文件清单
- 验证方式

## bobiDelight-Specific Notes

在当前工作区里，默认带上这些项目事实：

- Farmer's Delight 在 Fabric 和 Forge 元数据里都是硬依赖，不是可选兼容。
- Fabric 端野生作物生成并不完全数据驱动，新增野生作物还要补 Java 注册。
- 当前项目是双端结构，新增内容时要检查 `fabric` 和 `forge` 两侧是否都补齐。
- 涉及作物或野生植物渲染时，要警惕 cutout/render layer 这类平台差异。

## Do And Don't

### Do

- 先选参考对象，再下方案。
- 优先借鉴资源分层、命名和闭环，而不是照抄菜名。
- 明确每条内容链的入口、加工节点、成品出口。
- 优先最小完整实现，再逐步扩内容。

### Don't

- 不要一上来把 12 个参考模组全部展开对比。
- 不要只给“可以加一些食物/作物”的空泛建议。
- 不要漏掉 metadata、lang、recipe、loot、tag 这些基础配套。
- 不要改完就停，至少做一次最相关的构建或编译验证。