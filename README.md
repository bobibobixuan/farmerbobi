<div align="center">

# Farmer Bobi / 波比乐事

**A Farmer's Delight add-on bringing Chinese & Asian cuisine to Minecraft 1.20.1**

[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green?style=flat-square)](https://www.minecraft.net)
[![License](https://img.shields.io/badge/License-CC0--1.0-blue?style=flat-square)](LICENSE)
[![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric%20%7C%20Forge-orange?style=flat-square)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-17%2B-red?style=flat-square)](https://adoptium.net)

</div>

---

## About / 简介

**Farmer Bobi (bobiDelight / 波比乐事)** is a content-rich add-on mod for [Farmer's Delight](https://modrinth.com/mod/farmers-delight) that introduces a complete farm-to-table Chinese and Asian cuisine system into Minecraft 1.20.1.

Starting from just 5 fast-food items, the mod has grown into a full-featured dual-platform (Fabric & Forge) content pack with **33 plantable crops, 80+ recipes, 20 cooked dishes, 8 drinks, 15 feast blocks**, and more.

> ⚠️ **Hard Dependency**: Farmer's Delight (Fabric or Forge) must be installed. The mod will not load without it.

---

## Features / 特色

### 🌱 Crops & Farming / 作物与种植
- **33 growable crops** — corn, soybeans, peanuts, sesame, chili peppers, garlic, ginger, cucumber, bok choy, shiitake, chinese yam, and more
- **Wild crop generation** — 33 matching wild variants naturally spawn in various biomes (forests, savannas, jungles, rivers, taiga, beaches, etc.)

### 🍳 Cooking & Recipes / 烹饪与配方
- **80+ recipes** — vanilla crafting, smelting, Farmer's Delight cutting board & cooking pot
- **Processed ingredients** — cornmeal, tofu, soy sauce, sesame oil, vinegar, peanut butter, and more
- **Sauces & condiments** — chili sauce, garlic sauce, sweet & sour sauce, fermented bean paste, ginger-soy marinade

### 🥘 Dishes & Specials / 菜肴与小食
- **20 cooked dishes** — Mapo Tofu, Kung Pao Chicken, Sweet & Sour Pork, Hand-Pulled Noodles, Fried Rice, Dumplings, Zongzi, Mooncake, and more
- **Fast food items** — Pizza, Hamburger, Ice Cream, Chicken Wings, Fries
- **Specials** — Century Egg, Rice Cake, Dumpling Wrappers, Raw Noodles, Tofu Skin

### 🍹 Drinks / 饮品
- **8 beverages** — Soy Milk, Lemonade, Grape Juice, Mint Tea, Ginger Tea, Strawberry Smoothie, Blueberry Juice, Taro Bubble Tea

### 🎉 Feast Blocks / 盛宴方块
- **15 placeable feast blocks** — Hot Pot, Peking Duck, Dumpling Feast, BBQ Platter, Dim Sum Basket, Seafood Platter, Candy Box, Cheese Platter, and more
- Shareable multi-serving meals right-click to eat, break after last serving

### 📦 Storage / 存储
- **Crates & Bags** — bulk storage blocks for produce and leafy crops

### 🌍 Internationalization
- English (en_us) and Simplified Chinese (zh_cn) language support

---

## Installation / 安装

### Requirements / 前置需求
- **Minecraft** 1.20.1
- **Java** 17+
- **For Fabric**: Fabric Loader 0.18.4+, Fabric API 0.92.7+, [Farmer's Delight Fabric](https://modrinth.com/mod/farmers-delight-fabric) 1.4.3
- **For Forge**: Forge 47.3.0+, [Farmer's Delight Forge](https://www.curseforge.com/minecraft/mc-mods/farmers-delight)

### Download / 下载
1. Download the appropriate version for your mod loader from [Releases](https://github.com/bobibobixuan/farmerbobi/releases)
2. Place the `.jar` file into your `mods/` folder
3. Ensure Farmer's Delight is also in your `mods/` folder
4. Launch Minecraft and enjoy!

---

## Building from Source / 从源码构建

### Prerequisites / 构建环境
- **JDK 21** (runtime target: Java 17)
- **Gradle 8.14+** (wrapper included)

### Build Commands / 构建命令

```powershell
# Build both Fabric and Forge
.\gradlew.bat build

# Build Fabric only
.\gradlew.bat :fabric:build

# Build Forge only
.\gradlew.bat :forge:build
```

### Run Client / 运行客户端

```powershell
# Fabric
.\gradlew.bat :fabric:runClient

# Forge
.\gradlew.bat :forge:runClient
```

Output jars will be in `fabric/build/libs/` and `forge/build/libs/`.

---

## Project Structure / 项目结构

```
farmerbobi/
├── fabric/                  # Fabric platform source
│   ├── src/main/            # Shared code & resources
│   └── src/client/          # Client-only code
├── forge/                   # Forge platform source
│   └── src/main/            # Code & resources
├── build.gradle             # Root build script
├── settings.gradle          # Subproject configuration
├── gradle.properties        # Version properties
├── CHANGELOG.md             # Version history
└── LICENSE                  # CC0-1.0
```

The Fabric and Forge implementations maintain **independent source trees** — no shared `common/` directory. Both platforms use the same item/block IDs and game content, compiled with Mojang official mappings.

---

## License / 许可证

This project is licensed under [CC0-1.0 (Public Domain)](LICENSE) — you are free to use, modify, and distribute this work for any purpose.

---

## Credits / 鸣谢

- [Farmer's Delight](https://github.com/vectorwing/FarmersDelight) — the excellent modding framework this add-on builds upon
- Minecraft modding communities on Fabric and Forge
- All players who enjoy the mod!

