# Changelog

## 1.3.0 - 多平台重构

- 项目重构为多模块结构 (common / fabric / forge)
- common 模块：共享物品定义、配方、语言文件和资源
- fabric 模块：Fabric 平台入口 (ModInitializer + ClientModInitializer)
- forge 模块：Forge 平台入口 (@Mod + 事件注册)
- 共享源码通过 Gradle sourceSets 包含到各平台模块
- 使用 Gradle 8.14 统一构建环境

## 1.2.0 - 初始版本

- 5 个食物物品：披萨、汉堡、冰淇淋、鸡翅、薯条
- 1 个创意标签页
- 中英文语言文件
- 基于 Farmer's Delight 的合成配方
