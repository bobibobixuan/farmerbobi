# AGENTS.md — bobiDelight 开发心得与纪要

> 本文档记录在开发 bobiDelight（波比乐事）过程中积累的经验、踩过的坑、行之有效的模式，以及随着版本迭代逐步形成的判断准则。面向后续继续维护本模组或开发类似 Farmer's Delight 附属模组的 AI 和人类协作者。

---

## 1. 项目演化脉络

### 1.2.0 → 1.3.0：从单平台到双平台

最初项目只有 Fabric 端，代码直接混在一起。切换到 Fabric + Forge 双平台时面临一个核心决策：**要不要共享源码目录？**

最终结论是**不做 common 共享目录**，Fabric 和 Forge 各自维护独立源码树。原因：

- Fabric 和 Forge 的注册 API、世界生成入口、mod 元数据格式完全不同
- 共享目录会引入大量条件编译或反射，得不偿失
- 对于内容型模组（大量注册项），独立维护的认知负担反而更低

**教训**：内容型模组的双平台维护成本主要在"保证两端注册项同步"，而非"共享逻辑代码"。两端各自 copy 一份注册代码，比抽象一层 common API 更不容易出错。

### 1.3.0 → 1.4.0：资源闭环与构建稳定化

1.4.0 是项目从"能跑"到"能发版"的关键转折。这一版暴露了多个工程问题：

- **BMCLAPI 镜像问题**：国内 Maven 镜像在某些网络环境下会返回 403，导致 Gradle 依赖解析失败。最终移除了镜像，直接走官方源。
- **PowerShell 生图脚本的 reparse point 问题**：直接 `Save()` PNG 到 Gradle 资源目录会生成 NTFS reparse point，Gradle processResources 不认这种文件。解决方案是先写临时文件，再用字节流重写正式路径。
- **Forge mods.toml 版本注入**：Forged `mods.toml` 中的 `${mod_version}` 需要 Gradle `processResources` 任务展开，否则 jar 里的版本号永远是占位符。

**教训**：构建链路的问题往往不在代码层面，而在工具链与文件系统交互的边界。Gradle + Windows + PowerShell 的组合容易出现冷门问题。

### 1.4.0 → 1.4.1 → 1.4.2：参考库贴图复用

这两版是典型的"资源补完"工作，核心挑战是判断哪些参考库资源可以安全复用：

- **同名资源可直接复用**：corn、cucumber、eggplant 这种 crop 名在多个模组中完全一致的，贴图直接复制覆盖
- **阶段映射要谨慎**：Cultural Delights 的作物阶段只有 stage0~5，而 bobiDelight 用的是 stage0/4/5/6/7（因为跳过了 stage1~3 中间态贴图）。直接覆盖贴图前必须核对 blockstate 映射关系
- **排除近似但不相同的资源**：corn soup、fried eggplant pasta 这类菜名里含作物词但实际是成品菜的，绝对不能替换作物贴图

**教训**：资源复用最危险的不是"找不到资源"，而是"看起来很像但其实不是"的资源。每次替换前核对源模组的 model/blockstate 结构，抽查贴图哈希，避免把不兼容的贴图覆盖进来。

---

## 2. 架构设计原则

### 2.1 方块类的层次结构

```
Block (Minecraft)
 ├─ BushBlock           → WildCropBlock  (野生作物)
 ├─ CropBlock           → BobiCropBlock  (种植作物)
 └─ Block               → FeastBlock     (筵席方块)
```

三个自定义方块类都非常薄，只重写必要的方法。**不要为了"设计完整性"增加不需要的抽象层**——每个类只做一件事且做到位。

### 2.2 Supplier 延迟引用模式

`BobiCropBlock` 使用 `Supplier<? extends ItemLike>` 获取种子引用，而非直接传入 `Item`：

```java
new BobiCropBlock(() -> ModItems.BOBIXUAN_CORN_SEEDS)
```

这是必须的，不是因为代码风格，而是因为 **Java 静态初始化顺序不可控**：`ModBlocks` 和 `ModItems` 互相引用，谁先加载取决于 JVM。Supplier 将求值推迟到实际调用 `getBaseSeedId()` 时，此时两个类都已初始化完成。

### 2.3 ItemNameBlockItem vs BlockItem

种子物品使用 `ItemNameBlockItem` 而非普通的 `BlockItem`。区别在于：
- `BlockItem`：物品名跟随方块名
- `ItemNameBlockItem`：方块名跟随物品名（即方块用物品的翻译 key）

对于作物种子，玩家看到的是"XX种子"而不是"XX作物"，用 `ItemNameBlockItem` 更合理。这个选择会影响 lang 文件中需要写哪些翻译 key。

### 2.4 空 init() 方法模式

`ModBlocks.init()` 和 `ModItems.init()` 是空方法，作用纯粹是**触发类加载**：

```java
public static void init() {}
```

调用这些方法会触发类的静态初始化器执行，从而执行所有 `register()` 调用。这是一种显式的"副作用初始化"模式，替代了依赖 import 副作用的隐式初始化（1.3.0 之前的做法）。显式调用虽然多写了一行，但加载顺序清晰可控。

---

## 3. 作物系统的关键数字

### 3.1 每种作物需要的文件数量

| 类别 | 数量 | 说明 |
|------|------|------|
| 方块注册 (Java) | 2 | WildCropBlock + BobiCropBlock |
| 物品注册 (Java) | 2 | Seeds (ItemNameBlockItem) + Produce (Item) |
| 纹理 PNG | 11 | wild ×1 + crop stage ×8 + seeds item ×1 + produce item ×1 |
| Blockstate JSON | 2 | wild + crop (8 variants) |
| Block Model JSON | 9 | wild ×1 + crop_stage0~7 ×8 |
| Item Model JSON | 2 | seeds + produce |
| Configured Feature JSON | 1 | random_patch 斑块配置 |
| Placed Feature JSON | 1 | rarity + in_square + heightmap + biome |
| Loot Table JSON | 2 | wild + crop |
| Lang entries | 4 | wild block + crop block + seeds + produce (×2 语言) |
| WorldGen 注册行 | 1 | ModWorldGen.java 中一行 registerWildCrop |
| Forge biome_modifier | 1 | 仅 Forge 端需要额外 JSON |

**总计约 35 个文件/条目** 才能完成一种作物的完整闭环。33 种作物乘以这个数，约 1155 个资源点需要维护。

### 3.2 食物属性分级

```
叶菜类: nutrition=1, saturation=0.2  → 最低，主要作为食材
蔬菜类: nutrition=2, saturation=0.3  → 一般，可直接食用
豆类:   nutrition=2, saturation=0.4  → 略高于蔬菜
蘑菇类: nutrition=2, saturation=0.3  → 同蔬菜
水果类: nutrition=3, saturation=0.4  → 高于蔬菜
坚果类: nutrition=4, saturation=0.5  → 最高，鼓励直接食用
调味品: nutrition=1, saturation=0.1  → 极低，主要是合成材料
油类:   nutrition=1, saturation=0.2
酱类:   nutrition=3, saturation=0.4
```

这个分级的核心逻辑是：**让不同作物有不同的直接食用价值，但都不如烹饪后的菜品**。这也是 Farmer's Delight 生态的核心设计理念——鼓励玩家烹饪而非生吃。

### 3.3 世界生成稀有度

| 等级 | chance 范围 | 代表值 | 含义 |
|------|------------|--------|------|
| 常见 | 18~22 | 20 | 每 20 个区块约出现 1 次 |
| 普通 | 25~30 | 28 | 每 28 个区块约出现 1 次 |
| 稀有 | 32~35 | 35 | 每 35 个区块约出现 1 次 |
| 罕见 | 40~50 | 50 | 每 50 个区块约出现 1 次 |

chance 值越大越稀有。调试时如果要让作物更常见，减小 chance；如果要减少生成负载，增大 chance。

---

## 4. 常见陷阱与避免方法

### 4.1 循环引用导致的 NPE

**症状**：启动时 `NullPointerException`，堆栈指向 `ModBlocks` 或 `ModItems` 的静态字段。

**原因**：`ModBlocks` 的 `BobiCropBlock` 构造需要 `ModItems.SEEDS`，而 `ModItems` 尚未加载。

**解决**：用 `Supplier<ItemLike>` 延迟获取引用。不要在 `BobiCropBlock` 构造函数中直接传入 `Item` 实例。

### 4.2 双端资源不同步

**症状**：Fabric 端正常，Forge 端贴图缺失或显示错误，或反之。

**原因**：Fabric 和 Forge 各自维护独立的 `src/main/resources/assets/` 目录，更容易出现只改了一端的情况。

**避免方法**：新增或修改资源文件时，同时操作两端的对应路径。可以用 `diff -r fabric/src/main/resources/assets/ forge/src/main/resources/assets/` 抽查同步状态。

### 4.3 Forge 世界生成不生效

**症状**：Fabric 端作物正常生成，Forge 端找不到野生作物。

**原因**：Fabric 用 `BiomeModifications.addFeature()` 在 Java 代码中注册，Forge 用 `forge:add_features` biome_modifier JSON。Forge 端忘了创建 `forge/biome_modifier/add_*.json` 是高频遗漏项。

**检查清单**：
- [ ] `forge/.../worldgen/configured_feature/` 下有对应 JSON
- [ ] `forge/.../worldgen/placed_feature/` 下有对应 JSON
- [ ] `forge/.../forge/biome_modifier/add_*.json` 存在且 `features` 字段引用正确的 placed feature ID

### 4.4 cutout 渲染缺失

**症状**：作物方块显示为实心方块而非十字形植物，或者贴图的透明部分显示为黑色。

**原因**：方块未注册到 cutout 渲染层。Minecraft 默认渲染固体层，透明贴图需要 cutout 层。

**检查**：`ModBlocks.getCutoutBlocks()` 是否覆盖了所有 `BushBlock` 和 `FeastBlock` 实例。Fabric 端在 `BobiDelightClient.java` 中调用此方法注册 cutout。

### 4.5 Gradle 构建在 Windows 上的字符编码问题

**症状**：`./gradlew build` 报错，日志中出现乱码或无法解析的字符。

**原因**：Gradle 在 Windows 上默认使用系统编码（GBK），而非 UTF-8。

**解决**：在 `gradle.properties` 中设置 `org.gradle.jvmargs=-Dfile.encoding=UTF-8`。

---

## 5. 开发流程惯例

### 5.1 新增一种作物的步骤顺序

按依赖关系排列，不能打乱：

1. **注册方块** (`ModBlocks.java`) — 先注册 `WildCropBlock` 和 `BobiCropBlock`
2. **注册物品** (`ModItems.java`) — 种子用 `ItemNameBlockItem` 关联方块，收获物用普通 `Item`
3. **世界生成注册** (`ModWorldGen.java`) — Fabric 端；Forge 端创建 biome_modifier JSON
4. **创建 JSON 配置** — configured_feature → placed_feature → biome_modifier (Forge)
5. **创建掉落表** — crop loot table + wild loot table
6. **创建资源文件** — blockstate → block model → item model → 贴图 PNG
7. **本地化** — zh_cn.json + en_us.json
8. **创意标签页** — 在 `BobiDelight.java` 的 `displayItems` 中添加条目
9. **Forge 端同步** — 重复步骤 3~7 到 Forge 资源目录
10. **构建验证** — `./gradlew build --no-daemon`

### 5.2 提交粒度

- 资源替换（纯 PNG/JSON 覆盖）→ patch 版本 (x.y.Z)
- 新增作物/物品/方块 → minor 版本 (x.Y.z)
- 架构变更、双端结构重组 → major 版本 (X.y.z)

### 5.3 资源复用决策

遇到参考库中有现成贴图时，按以下优先级判断：

1. **同名同结构**：直接复用（如 Corn Delight 的 corn 贴图）
2. **同名不同结构**：做阶段映射后再复用（如 Cultural Delights 的 cucumber stage 映射）
3. **近似但不同物**：不复用，重新制作（如黄瓜贴图不能用于西葫芦）
4. **找不到**：用 nanobanana 生图流程自制

---

## 6. 技术债务与改进方向

以下是在开发过程中识别到但尚未处理的技术债务：

- **ModItems.java 过于庞大**：当前 530+ 行，所有物品注册堆在一个类里。如果继续扩内容，考虑按类别拆分为 `ModCropItems`、`ModFoodItems`、`ModDrinkItems` 等
- **BobiDelight.java 的创意标签页注册**：`displayItems` lambda 中有 180+ 行 `entries.accept()` 调用。可以考虑用循环或反射自动收集
- **双端注册代码重复**：虽然"独立源码树"是主动选择，但 `ModItems.java` 的结构在两端几乎一致，后续如果作物数量再增长，可以考虑用代码生成器从一份作物清单生成两端的注册代码
- **Test 缺失**：目前项目没有自动化测试，验证全靠 `./gradlew build` + 进游戏目测。至少应该有一个贴图文件存在性检查脚本，确保每注册一种作物就有对应的 PNG 文件

---

## 7. 关键文件速查

| 用途 | Fabric 路径 | Forge 路径 |
|------|-----------|-----------|
| 主入口 | `fabric/.../BobiDelight.java` | — |
| 方块注册 | `fabric/.../block/ModBlocks.java` | `forge/.../block/ModBlocks.java` |
| 物品注册 | `fabric/.../item/ModItems.java` | `forge/.../item/ModItems.java` |
| 世界生成 | `fabric/.../worldgen/ModWorldGen.java` | `forge/.../worldgen/ModWorldGen.java` |
| WildCropBlock | `fabric/.../block/WildCropBlock.java` | `forge/.../block/WildCropBlock.java` |
| BobiCropBlock | `fabric/.../block/BobiCropBlock.java` | `forge/.../block/BobiCropBlock.java` |
| FeastBlock | `fabric/.../block/FeastBlock.java` | `forge/.../block/FeastBlock.java` |
| 客户端渲染 | `fabric/.../BobiDelightClient.java` | — |
| 版本号 | `gradle.properties` | `gradle.properties` |
| 详细设计文档 | `PLANT_GENERATION_GUIDE.md` | — |

---

> **最后更新**: 2026-05-08 | **适用版本**: bobiDelight 1.4.2
