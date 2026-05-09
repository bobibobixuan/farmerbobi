# Changelog

## 1.4.3 - 材质重制与可摆放食品模型补完

- 重做全套作物生长阶段材质，按真实植物形态拆分为三批生成与裁切，覆盖 Fabric / Forge 两端的 `stage0 / stage4 / stage5 / stage6 / stage7`。
- 针对黄瓜、茄子、花生、芝麻、绿豆、大豆、草莓重新生成差异化生长材质，减少同质化问题。
- 修正葡萄与柠檬的生长逻辑：葡萄改为藤架/挂串表现，柠檬改为木质小树开花结果表现，并参考本地 Croptopia 资源结构重新制作。
- 补完 15 个可摆放食品方块的立体模型，按锅、篮、盒、烤物、盘装食品分类制作多元素 block model。
- 调整 `FeastBlock` 选择框与碰撞框高度，使其匹配新的低矮食品模型，减少穿模和整格方块观感。
- 同步修正 Fabric / Forge 两端资源，检查模型 JSON、贴图引用、阶段贴图尺寸和双端哈希一致性。
- 构建验证：`./gradlew.bat clean build --no-daemon` 通过。

## 1.4.2 - 同名作物全库复用补完

- 重新扫描整合包 Farmer's Delight 参考库，只保留 produce、seeds、wild crop、crop stage、crate 这五类同名安全资源
- 确认整库可直接复用的同名目标集中在 corn、cucumber、eggplant、ginger、hamburger，其中 cucumber / eggplant 的 crop stage 继续沿用映射方案
- 为 corn、cucumber、eggplant 新增独立 crate block model，并把 Fabric / Forge 双端的 blockstate 与 item model 从 `template_crate` 切到各自作物 crate 模型
- 复用 Corn Delight 与 Cultural Delights 的 crate 顶面 / 侧面贴图，补齐三组箱装作物的实际显示资源
- 已执行 `./gradlew.bat clean build --no-daemon`，验证资源接线与版本更新后构建成功
- 详细记录见 [更新日志/1.4.2-2026-05-06.md](更新日志/1.4.2-2026-05-06.md)

## 1.4.1 - 首批参考库贴图替换

- 从整合包参考库复用 Corn Delight、Cultural Delights、Farmer's Delight 与 Display Delight 的现成贴图资源
- 重做玉米、黄瓜、茄子三条作物线的 crop stage / wild crop / produce / seeds 贴图，并同步覆盖 Fabric / Forge 双端资源树
- 替换姜、汉堡、鸡翅物品贴图，其中黄瓜与茄子的阶段图按 Cultural Delights 原始 blockstate 关系映射到现有 stage0 / 4 / 5 / 6 / 7 命名
- 已抽查代表性贴图哈希并执行 `./gradlew.bat build --no-daemon`，验证资源替换后构建成功
- 详细记录见 [更新日志/1.4.1-2026-05-06.md](更新日志/1.4.1-2026-05-06.md)

## 1.4.0 - 资源补完与构建修复

- 为玉米、大蒜、姜、黄瓜、茄子补齐作物阶段、野生植株、产物与种子贴图，并同步到 Fabric / Forge 双端资源树
- 补全小白菜、山药、香菇相关的 crop cross 模型、袋装 / 箱装模型与贴图资源，完善 Hot Pot 相关贴图
- 新增 crops / wild_crops / seeds / farmersdelight_seeds / compostables 标签，补齐作物资源与数据闭环
- 修复 `ModItems.init()` 初始化入口、`FeastBlock` 生存判定与进食逻辑，并调整 cutout 方块收集逻辑
- 移除会导致依赖解析异常的 BMCLAPI 镜像，修正 Forge `mods.toml` 的 `${mod_version}` 注入流程
- 校正文档与设计文档中的内容统计和版本描述；详细记录见 [更新日志/1.4.0-2026-05-06.md](更新日志/1.4.0-2026-05-06.md)

## 1.3.0 - 多平台重构

- 项目整理为 Fabric / Forge 双平台独立目录结构，不再保留 common 共享源码目录
- Fabric 与 Forge 分别维护自己的入口类、注册表代码、语言、模型、纹理与数据包资源
- 去除 hack 式类加载，统一改为显式 `ModItems.init()` 初始化入口
- 修复 FeastBlock 生存判定与双重进食判断，补齐两端的 cutout 渲染注册
- 补充 crops / wild_crops / seeds 等标签资源，完善 Forge `mods.toml` 版本注入流程
- 版本号提升到 1.3.0，并统一使用 Gradle 8.14 构建环境

## 1.2.0 - 初始版本

- 5 个食物物品：披萨、汉堡、冰淇淋、鸡翅、薯条
- 1 个创意标签页
- 中英文语言文件
- 基于 Farmer's Delight 的合成配方
