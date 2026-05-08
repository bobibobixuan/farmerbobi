---
name: fd-modpack-asset-reuse
description: |
  从当前整合包中提取、筛选并复用 Farmer's Delight 关联模组的素材与数据资源。
  Use when user asks: "看看整合包里的农夫乐事模组", "从整合包里找可复用材质", "参考整合包里的 Delight 素材", "先查整合包素材再做贴图", "运行有问题去看指定 logs".
  Reads the extracted modpack reference corpus under 设计文档/示范文件/示范mod/整合包农夫乐事素材参考 and prefers reuse before creating new textures.
argument-hint: 描述要找的素材类型、目标作物/物品/方块、是否允许直接复用，以及是要检索、评审还是直接接入
user-invocable: true
disable-model-invocation: false
---

# FD Modpack Asset Reuse

## Scope

这个 skill 只用于当前工作区整合包里的 Farmer's Delight 关联素材复用工作，尤其适合这些任务：

- 从整合包现有模组里找更好的作物、食物、方块贴图参考。
- 决定某个 bobiDelight 素材是直接套用、局部改造还是必须自制。
- 遇到运行问题时，先查用户指定的外部 logs 目录再判断是资源问题还是逻辑问题。
- 扩作物、补 item/block model、补 lang 或 recipe 时，先复查整合包已存在的命名和素材分层。

如果任务与 Farmer's Delight 或 Delight 系素材复用无关，不要使用这个 skill。

## Required Reading

开始前按这个顺序读取：

- `设计文档/示范文件/示范mod/整合包农夫乐事素材参考/素材总览.md`
- `设计文档/示范文件/示范mod/整合包农夫乐事素材参考/mods-manifest.json`
- 目标模组目录下的 `README.md`
- 目标模组目录下的 `asset-index.json`

如果任务已经定位到具体素材，再继续打开对应目录下的：

- `extracted/assets/...`
- `extracted/data/...`

运行异常时，优先检查用户指定日志目录：

- `C:/Users/21996/Desktop/1.20.1/.minecraft/versions/波比打金服1.20.1-Forge_47.4.6/logs`

## Inputs To Collect

在回答或动手前，明确这些信息：

- 目标内容是 `作物贴图`、`食物 item`、`方块材质`、`模型`、`语言` 还是 `配方/标签`
- 目标平台是 `Fabric`、`Forge` 还是双端同步
- 是否允许 `直接复用`、`局部改色改形复用`，还是必须 `完全原创`
- 当前问题是 `美术质量不足`、`资源缺失`、`命名对不上`，还是 `运行报错`
- 如果是运行问题，是否已经查看指定 logs 目录

## Workflow

1. 先从 `素材总览.md` 找出贴图量高、主题最接近的 2 到 4 个候选模组。
2. 再看这些模组的 `asset-index.json`，确认是否存在同类 crop、item、block、lang、recipe 资源。
3. 如果候选素材和 bobiDelight 的命名或闭环足够接近，优先复用命名、分层和视觉语言。
4. 如果只能借鉴一部分，就明确哪些文件可直接套用，哪些必须二次修改。
5. 如果整合包里没有合适素材，再转入自制流程，不要一开始就跳过参考库。
6. 遇到运行异常时，先读指定 logs，再决定是资源缺失、json 结构问题还是代码逻辑问题。

## Output Contract

使用这个 skill 时，输出尽量落成下面三种之一：

- `复用建议`：指出该看哪些模组、哪些目录、哪些文件值得直接参考。
- `接入方案`：说明哪些素材直接套用，哪些文件需要改名、改色、改 model 或改 recipe。
- `问题排查`：结合 logs 与资源目录，指出是缺文件、命名错误、平台差异还是实现 bug。

不要只说“可以参考某个模组”。必须至少给出：

- 参考模组名
- 具体目录或文件类型
- 复用方式
- 风险点
- 如果不适合复用，为什么要改成自制

## bobiDelight-Specific Notes

- 当前整合包参考库由 `tools/extract_fd_modpack_assets.ps1` 生成，可重复刷新。
- 参考输出目录固定在 `设计文档/示范文件/示范mod/整合包农夫乐事素材参考`。
- bobiDelight 是双端结构，任何最终接入都要检查 `fabric` 和 `forge` 两边资源是否同步。
- 复用时优先借鉴文件结构、命名规则和视觉表达，不要直接整包搬运别人的实现细节。

## Do And Don't

### Do

- 先查参考库，再决定是否自制。
- 优先从同主题模组里挑素材，而不是按贴图数量盲选。
- 复用前先看 `asset-index.json`，避免手工翻目录浪费时间。
- 运行报错先查指定日志目录。

### Don't

- 不要绕过参考库直接重做全部材质。
- 不要只复用 png 而忽略对应的 model、blockstate、lang、recipe。
- 不要把整合包里的模组都混着看，先收窄到最相关的几项。
- 不要做完资源改动就停，至少跑一次最相关的构建或资源验证。