# 仓库修复记录

## 当前状态

原审查中列出的 4 项已知问题均已修复。

当前仓库结构已进一步重构为 Fabric / Forge 双平台独立目录，不再共享 common 源码与资源目录。

验证情况：

- `./gradlew.bat :fabric:compileJava --no-daemon` -> 成功
- `./gradlew.bat :forge:compileJava --no-daemon` -> 成功
- `./gradlew.bat build --no-daemon` -> 成功

构建产物：

- `fabric/build/libs/bobidelight-fabric-1.2.0.jar`
- `forge/build/libs/bobidelight-forge-1.2.0.jar`

## 已完成修复

### 1. 共享源码统一到 Mojmap 命名，Fabric 和 Forge 均可编译

- 将共享物品定义与两端入口从 Yarn/Fabric 命名切换为 Mojmap 命名。
- Fabric 构建改为使用 `loom.officialMojangMappings()`，与 Forge 的官方映射保持一致。
- 根项目构建不再被 Forge 编译错误阻断。

### 2. 移除了受版本控制的本机代理配置

- 已从 `gradle.properties` 删除本机 `127.0.0.1:7897` 代理设置。
- 仓库现在不再依赖开发者机器上的本地代理才能解析依赖。

### 3. 补齐了自定义物品的模型和纹理资源

- 已为 5 个物品新增 `assets/bobidelight/models/item/*.json`。
- 已为 5 个物品新增 `assets/bobidelight/textures/item/*.png`，消除了缺失模型/缺失纹理问题。

### 4. 修正了 CI 的构建产物上传目录

- GitHub Actions 现在上传 `fabric/build/libs/*` 和 `forge/build/libs/*`。
- CI JDK 版本已切换为 Java 21。

### 5. Fabric / Forge 已拆分为独立实现目录

- 共享的 common 目录已移除，平台源码分别位于 `fabric/src/main` 和 `forge/src/main`。
- 两端各自持有独立的 `ModItems`、语言、模型、纹理和配方资源。
- 构建脚本不再通过 sourceSets 复用跨平台源码或资源。

## 残余说明

- 我没有实际启动 Minecraft 客户端，因此未验证游戏内行为、配方体验和贴图观感。
- 当前新增的 5 张物品纹理先复用了现有 `icon.png`，可以正常打包和渲染，但后续仍建议替换为独立美术资源。
