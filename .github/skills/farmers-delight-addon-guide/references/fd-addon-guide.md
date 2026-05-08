# Farmer's Delight 附属模组参考指南

这份指南是给 AI 用的，不是写给普通玩家的。目标是让 AI 在设计、评审或实现 Farmer's Delight 附属模组时，优先基于当前工作区已经收集的 12 个参考模组作判断，而不是凭空发挥。

## 1. 先做任务分类

在开始前，先把用户请求归到下面一种主类型。每次只选一个主类型，必要时再加一个次类型。

| 主类型 | 典型内容 | 优先参考 |
|------|------|------|
| 作物链扩展 | 新作物、种子、掉落、烹饪产物 | Corn Delight, Veggies Delight, More Delight |
| 维度/生态食材 | 末地、下界、海洋、生物掉落食材 | End's Delight, Nether's Delight, Ocean's Delight, Crabber's Delight |
| 饮品/加工系统 | 发酵、饮品、新工作流程、新容器 | Brewin' And Chewin', Delightful |
| 功能型扩展 | 村民、打包、存储、工作站 | Chef's Delight, Packed Up |
| 文化/风味主题 | 区域菜系、主题菜肴、地域化命名 | Cultural Delights, Delightful |
| 轻兼容与补全 | 对接其他食物模组、补 recipe/tag | Delightful, More Delight |

如果请求本质上只是“加几道菜”，不要把它误判成世界生成或系统级改造。

## 2. 12 个参考模组应该怎么用

### 2.1 维度与生态型

- End's Delight：适合研究“维度主题食材 -> 掉落/拾取 -> 成品料理”的闭环。
- Nether's Delight：适合研究危险环境食材、敌对生物掉落和维度风味化命名。
- Ocean's Delight：适合研究海鲜食材的处理链和相对轻量的数据组织。
- Crabber's Delight：适合研究围绕特定海洋生物做成一条专精支线。

### 2.2 标准内容扩展型

- More Delight：适合看典型的 recipe/tag/lang/resource 组织方式，结构直白。
- Delightful：适合看兼容面很广时，如何把附属内容拆成多个依赖面和补充 recipe。
- Corn Delight：适合看围绕一个核心作物衍生出完整食品链。
- Veggies Delight：适合看蔬菜类内容与世界内容共存时如何分层。

### 2.3 功能与系统型

- Brewin' And Chewin'：适合看饮品、处理流程、新交互方块和较重的 Java 逻辑。
- Chef's Delight：适合看如何把 Farmer's Delight 扩到村民职业和交易生态。
- Packed Up：适合看“不做新作物，也能做成 FD 附属”的功能侧路线。

### 2.4 风味主题型

- Cultural Delights：适合看主题化命名、菜系包装和内容边界控制。

## 3. 选参考对象的规则

不要平均参考所有模组，按下面规则选：

1. 找一个“最像用户目标”的主参考模组。
2. 再找一个“补足短板”的副参考模组。
3. 只有在涉及跨层面问题时，才加第三或第四个参考模组。

建议组合：

- 新作物 + 新食品：Corn Delight + More Delight
- 海鲜/生物掉落料理：Ocean's Delight + Crabber's Delight
- 维度主题附属：End's Delight + Nether's Delight
- 饮品或发酵链：Brewin' And Chewin' + Delightful
- 村民/功能扩展：Chef's Delight + Packed Up
- 地域化菜单：Cultural Delights + Delightful

## 4. AI 必须先建立内容闭环

在任何实现建议前，先把内容闭环写明白：

1. 原料从哪里来：作物、生物掉落、战利品、交易、世界生成、工作站产出。
2. 原料如何加工：切菜板、锅、工作站、发酵、合成、熏制、营火。
3. 成品是什么：食材、中间品、可食物品、方块化筵席、饮品。
4. 成品如何回流玩法：回复、饱和度、交易、装饰、再加工、兼容 recipe。

如果这四段有任何一段说不清，就不要急着写代码。

## 5. 文件级最小闭环清单

### 5.1 纯食物/配方扩展

至少检查：

- Item 注册
- lang
- item model
- texture
- recipes
- tags
- loot 或获取来源说明

### 5.2 新作物扩展

至少检查：

- Crop block
- Wild crop block 或其他自然获取方案
- Seed item
- Produce item
- blockstates
- crop stage models
- textures
- loot tables
- recipes
- worldgen JSON
- Java worldgen 注册（如果平台需要）
- lang

### 5.3 新工作站或功能方块

至少检查：

- Block 和 BlockItem 注册
- 方块模型、方块状态、纹理
- 必要的 BlockEntity/Menu/Screen 或交互逻辑
- recipe serializer 或配套数据
- lang
- 粒子、音效、标签或战利品表

## 6. 对 bobiDelight 的落地规则

### 6.1 依赖和平台

- Farmer's Delight 在这个项目里是硬依赖，不能按“可选兼容”去设计核心内容。
- 默认假设 Fabric 和 Forge 都要补齐，除非用户明确只做单端。
- 涉及野生作物时，Fabric 端要检查 Java 注册，不要以为补 JSON 就够了。

### 6.2 命名与范围控制

- 优先做一条短而完整的链路，不要一次铺太多食材。
- 命名应该围绕食材来源和用途，少用纯装饰性命名。
- 如果一个点子无法自然落到 recipe、loot、worldgen、lang 这些文件上，说明它还不够具体。

### 6.3 借鉴方式

- 借鉴结构，不借鉴 IP、贴图和直接文本内容。
- 借鉴 recipe 组织方式、文件布局、注册顺序、内容边界。
- 不要把其他模组的整套依赖面直接搬进来，尤其是 Delightful 这类兼容面很广的项目。

## 7. AI 输出模板

当用户要“方案”时，按这个顺序输出：

1. 任务归类
2. 选中的 2 到 4 个参考模组及原因
3. 最小内容闭环
4. 需要修改或新增的文件清单
5. 平台差异点
6. 验证命令

当用户要“评审”时，按这个顺序输出：

1. 当前方案最像哪个参考模组
2. 缺失的闭环环节
3. 缺失的资源或数据文件
4. 平台风险
5. 最小修正路径

当用户要“直接实现”时，按这个顺序行动：

1. 先做第一条完整链路
2. 立刻做最相关验证
3. 通过后再补邻接内容

## 8. 高风险误区

- 不要把“附属模组”理解成只需要写 recipes。
- 不要只参考下载量最高的模组而忽略任务类型是否匹配。
- 不要新增世界生成却漏掉掉落、lang、模型或平台侧注册。
- 不要把兼容类模组的海量依赖抄进当前项目。
- 不要写出无法映射到实际文件和注册点的空泛规划。

## 9. 本地参考语料入口

优先读：

- `示范文件/示范mod/农夫乐事附属参考/总览指南.md`
- `示范文件/示范mod/农夫乐事附属参考/addons-manifest.json`

按需深入：

- `示范文件/示范mod/农夫乐事附属参考/01_ends-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/02_chefs-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/03_oceans-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/04_more-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/05_brewin-and-chewin/README.md`
- `示范文件/示范mod/农夫乐事附属参考/06_delightful/README.md`
- `示范文件/示范mod/农夫乐事附属参考/07_crabbers-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/08_nethers-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/09_cultural-delights/README.md`
- `示范文件/示范mod/农夫乐事附属参考/10_corn-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/11_veggies-delight/README.md`
- `示范文件/示范mod/农夫乐事附属参考/12_packed-up/README.md`

如果任务已经明确落到某一类实现，再去看对应模组的 `extracted` 目录，不要反过来。