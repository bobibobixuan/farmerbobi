# -*- coding: utf-8 -*-
"""
Export bobiDelight texture assets with Chinese names and categorized folders.
"""
import os
import shutil

SRC_DIR = r"fabric\src\main\resources\assets\bobidelight"
OUT_DIR = "导出素材"

NAME_MAP = {
    "corn": "玉米", "soybean": "大豆", "chili_pepper": "辣椒",
    "garlic": "大蒜", "ginger": "姜", "cucumber": "黄瓜",
    "eggplant": "茄子", "radish": "白萝卜", "green_onion": "小葱",
    "cilantro": "香菜", "lettuce": "生菜", "celery": "芹菜",
    "bell_pepper": "青椒", "peanut": "花生", "sesame": "芝麻",
    "mung_bean": "绿豆", "strawberry": "草莓", "blueberry": "蓝莓",
    "pineapple": "菠萝", "grape": "葡萄", "lemon": "柠檬",
    "sweet_potato": "红薯", "taro": "芋头", "bamboo_shoot": "竹笋",
    "mint": "薄荷", "rosemary": "迷迭香", "oyster_mushroom": "平菇",
    "spinach": "菠菜", "mustard_green": "芥菜", "seaweed": "紫菜",
    "bok_choy": "青江菜", "shiitake": "香菇", "chinese_yam": "山药",
    "chicken_wings": "鸡翅", "hamburger": "汉堡", "fries": "薯条",
    "pizza": "披萨", "ice_cream": "冰淇淋", "hot_pot": "火锅",
}

STAGE_MAP = {
    "stage0": "种子阶段", "stage4": "生长阶段1",
    "stage5": "生长阶段2", "stage6": "生长阶段3", "stage7": "成熟阶段",
}

FEAST_STAGE_MAP = {
    "stage0": "空锅阶段", "stage1": "食材阶段1",
    "stage2": "食材阶段2", "stage3": "完成阶段",
}

CATEGORIES = {
    "01_生长阶段": "Crop Growth Stages",
    "02_成品作物": "Crop Items",
    "03_种子": "Seeds",
    "04_野生植物": "Wild Plants",
    "05_容器箱子": "Storage Crates",
    "06_容器袋子": "Storage Bags",
    "07_快餐": "Fast Food",
    "08_盛宴": "Feast Blocks",
    "09_图标": "Icon",
}

FAST_FOOD = {"chicken_wings", "hamburger", "fries", "pizza", "ice_cream"}
FEAST_IDS = {"hot_pot"}

PART_MAP = {"side": "侧面", "top": "顶面", "bottom": "底面"}


def copy_file(src, dst):
    os.makedirs(os.path.dirname(dst), exist_ok=True)
    shutil.copy2(src, dst)


def classify_block(base):
    """Classify a block texture base name (without bobixuan_ prefix and .png).
    Returns (chinese_name, category_key) or (None, None)."""
    
    # Bag textures: <crop>_bag_<part> (must check BEFORE side/bottom patterns)
    if "_bag_" in base:
        parts = base.split("_bag_")
        crop_id = parts[0]
        part_label = PART_MAP.get(parts[1], parts[1])
        if crop_id in NAME_MAP:
            return (f"袋装{NAME_MAP[crop_id]}_{part_label}", "06_容器袋子")
        return (None, None)

    # Crate textures: <crop>_crate_<part> (must check BEFORE side patterns)
    if "_crate_" in base:
        parts = base.split("_crate_")
        crop_id = parts[0]
        part_label = PART_MAP.get(parts[1], parts[1])
        if crop_id in NAME_MAP:
            return (f"箱装{NAME_MAP[crop_id]}_{part_label}", "05_容器箱子")
        return (None, None)

    # Wild plants: wild_<crop>
    if base.startswith("wild_"):
        crop_id = base[5:]
        if crop_id in NAME_MAP:
            return (f"野生{NAME_MAP[crop_id]}", "04_野生植物")
        return (None, None)

    # Crop growth stages: <crop>_crop_stage<N>
    if "_crop_stage" in base:
        parts = base.split("_crop_stage")
        crop_id = parts[0]
        stage_key = "stage" + parts[1]
        if crop_id in NAME_MAP and stage_key in STAGE_MAP:
            return (f"{NAME_MAP[crop_id]}_{STAGE_MAP[stage_key]}", "01_生长阶段")
        return (None, None)

    # Feast block specific patterns
    # Feast stages: <feast>_stage<N>
    if "_stage" in base:
        parts = base.rsplit("_stage", 1)
        feast_id = parts[0]
        stage_key = "stage" + parts[1]
        if feast_id in FEAST_IDS and stage_key in FEAST_STAGE_MAP:
            return (f"{NAME_MAP[feast_id]}_{FEAST_STAGE_MAP[stage_key]}", "08_盛宴")
        return (None, None)

    # Feast side/bottom: <feast>_side or <feast>_bottom
    for suffix in ("_side", "_bottom"):
        if base.endswith(suffix):
            feast_id = base[:-len(suffix)]
            if feast_id in FEAST_IDS:
                part_label = PART_MAP[suffix[1:]]
                return (f"{NAME_MAP[feast_id]}_{part_label}", "08_盛宴")
            break

    return (None, None)


def classify_item(base):
    """Classify an item texture base name.
    Returns (chinese_name, category_key) or (None, None)."""

    # Seeds: <crop>_seeds
    if base.endswith("_seeds"):
        crop_id = base[:-6]
        if crop_id in NAME_MAP:
            return (f"{NAME_MAP[crop_id]}种子", "03_种子")
        return (None, None)

    if base in NAME_MAP:
        cn = NAME_MAP[base]
        if base in FEAST_IDS:
            return (cn, "08_盛宴")
        elif base in FAST_FOOD:
            return (cn, "07_快餐")
        else:
            return (cn, "02_成品作物")

    return (None, None)


def process():
    if os.path.exists(OUT_DIR):
        shutil.rmtree(OUT_DIR)
    for cat_key in CATEGORIES:
        os.makedirs(os.path.join(OUT_DIR, cat_key), exist_ok=True)

    stats = {cat: 0 for cat in CATEGORIES}
    unmapped = []

    # Icon
    icon_src = os.path.join(SRC_DIR, "icon.png")
    if os.path.exists(icon_src):
        copy_file(icon_src, os.path.join(OUT_DIR, "09_图标", "波比乐事图标.png"))
        stats["09_图标"] += 1
        print(f"  [09_图标] icon.png -> 波比乐事图标.png")

    # Block textures
    block_dir = os.path.join(SRC_DIR, "textures", "block")
    if os.path.exists(block_dir):
        for fname in sorted(os.listdir(block_dir)):
            if not fname.endswith(".png"):
                continue
            base = fname[:-4].replace("bobixuan_", "", 1)
            chinese, category = classify_block(base)
            if chinese:
                src = os.path.join(block_dir, fname)
                dst = os.path.join(OUT_DIR, category, chinese + ".png")
                copy_file(src, dst)
                stats[category] += 1
                print(f"  [{category}] {fname} -> {chinese}.png")
            else:
                unmapped.append(f"block/{fname}")
                print(f"  [未映射] {fname}")

    # Item textures
    item_dir = os.path.join(SRC_DIR, "textures", "item")
    if os.path.exists(item_dir):
        for fname in sorted(os.listdir(item_dir)):
            if not fname.endswith(".png"):
                continue
            base = fname[:-4].replace("bobixuan_", "", 1)
            chinese, category = classify_item(base)
            if chinese:
                src = os.path.join(item_dir, fname)
                dst = os.path.join(OUT_DIR, category, chinese + ".png")
                copy_file(src, dst)
                stats[category] += 1
                print(f"  [{category}] {fname} -> {chinese}.png")
            else:
                unmapped.append(f"item/{fname}")
                print(f"  [未映射] {fname}")

    # Summary
    print()
    print("=" * 50)
    print("  Export Complete! Output: " + OUT_DIR)
    print("=" * 50)
    total = 0
    for cat_key in CATEGORIES:
        cnt = stats[cat_key]
        total += cnt
        print(f"  {cat_key} ({CATEGORIES[cat_key]}): {cnt} files")
    print(f"  Total: {total} PNG files")
    if unmapped:
        print()
        print("  Unmapped files:")
        for u in unmapped:
            print(f"    - {u}")


if __name__ == "__main__":
    process()
