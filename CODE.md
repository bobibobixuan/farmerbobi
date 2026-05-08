# CODE.md — AI 协作者持久记忆

> 从 Copilot 持久记忆迁移。供 Claude Code、Codex 及其他 AI 协作者读取。

---

## 用户偏好

- **构建验证**: 每次修复或代码改动完成后默认要做一次构建验证（`./gradlew build --no-daemon` 或最相关的子项目编译）。对可构建项目不要只改文件就结束。
- **更新日志**: 每次功能或资源更新都要补 CHANGELOG 和 `更新日志/` 目录下的详细条目。版本号默认按 patch 递增，较大更新升 minor，major 只在用户明确要求时提升。
- **遵守项目事实**: bobiDelight 对 Farmer's Delight 是硬依赖，Fabric 和 Forge 双端独立维护，新增内容必须在两端同步。

---

## Windows 环境陷阱

1. **Reparse Point 问题**: Windows 上 `System.Drawing.Bitmap.Save` 直接写目标 PNG，或 apply_patch 写入文本文件，可能产生 NTFS reparse point，导致 Gradle 报 "not a regular file"。
   - 解决: 先写临时文件，再用字节流重写正式路径。
   - apply_patch: Delete+Add 同一路径需拆为两步。

2. **PowerShell UTF-8 编码**: PowerShell 5.1 处理 UTF-8 无 BOM 的中文 JSON 时，`Get-Content` / `ConvertFrom-Json` 须显式加 `-Encoding UTF8`，否则会按 ANSI 读写乱码。

3. **PowerShell 二维数组**: `@($x + 1, $y)` 写法会触发 `object[]` 的 `op_Addition` 解析错误，改用 `@(($x + 1), $y)` 或坐标 helper 函数。

4. **PowerShell .NET 反射**: 反射调用带数组参数的 .NET 方法时，受 PSObject/数组装箱干扰。精确二进制验证优先用内嵌 C# 探针直调 `MethodInfo.Invoke`。

---

## Minecraft 项目关键信息

- **日志排查目录**: `C:/Users/21996/Desktop/1.20.1/.minecraft/versions/波比打金服1.20.1-Forge_47.4.6/logs`
- **Mod ID**: `bobidelight`
- **Minecraft 版本**: 1.20.1
- **平台**: Fabric (0.18.4+) + Forge (47.3.0+)
- **Java**: JDK 21 构建, JDK 17 运行目标
- **构建**: `./gradlew build --no-daemon` (Fabric+Forge), `./gradlew :fabric:build --no-daemon` (仅 Fabric), `./gradlew :forge:build --no-daemon` (仅 Forge)
