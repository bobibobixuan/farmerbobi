package top.bobixuan.bobidelight.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import top.bobixuan.bobidelight.block.ModBlocks;

import java.util.List;

public class ModItems {
    public static final String MOD_ID = "bobidelight";

    public static final FoodProperties PIZZA_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.6f).build();
    public static final FoodProperties HAMBURGER_FOOD = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.7f).build();
    public static final FoodProperties ICE_CREAM_FOOD = new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.3f).build();
    public static final FoodProperties CHICKEN_WINGS_FOOD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.5f).build();
    public static final FoodProperties FRIES_FOOD = new FoodProperties.Builder()
            .nutrition(5).saturationMod(0.4f).build();

    public static final Item BOBIXUAN_PIZZA = register("bobixuan_pizza",
            new Item(new Item.Properties().food(PIZZA_FOOD)));
    public static final Item BOBIXUAN_HAMBURGER = register("bobixuan_hamburger",
            new Item(new Item.Properties().food(HAMBURGER_FOOD)));
    public static final Item BOBIXUAN_ICE_CREAM = register("bobixuan_ice_cream",
            new Item(new Item.Properties().food(ICE_CREAM_FOOD)));
    public static final Item BOBIXUAN_CHICKEN_WINGS = register("bobixuan_chicken_wings",
            new Item(new Item.Properties().food(CHICKEN_WINGS_FOOD)));
    public static final Item BOBIXUAN_FRIES = register("bobixuan_fries",
            new Item(new Item.Properties().food(FRIES_FOOD)));

    private static final FoodProperties VEGETABLE_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.3f).build();
    private static final FoodProperties FRUIT_FOOD = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.4f).build();
    private static final FoodProperties LEAFY_FOOD = new FoodProperties.Builder()
            .nutrition(1).saturationMod(0.2f).build();
    private static final FoodProperties NUT_FOOD = new FoodProperties.Builder()
            .nutrition(4).saturationMod(0.5f).build();
    private static final FoodProperties BEAN_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.4f).build();
    private static final FoodProperties MUSHROOM_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.3f).build();
    private static final FoodProperties CONDIMENT_FOOD = new FoodProperties.Builder()
            .nutrition(1).saturationMod(0.1f).build();
    private static final FoodProperties INGREDIENT_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.3f).build();
    private static final FoodProperties OIL_FOOD = new FoodProperties.Builder()
            .nutrition(1).saturationMod(0.2f).build();
    private static final FoodProperties PASTE_FOOD = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.4f).build();
    private static final FoodProperties SPECIALTY_FOOD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.6f).build();
    private static final FoodProperties HIGH_NUTRITION_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();

    private static final Item.Properties DEFAULT_PROPS = new Item.Properties();
    private static final Item.Properties VEGETABLE_PROPS = new Item.Properties().food(VEGETABLE_FOOD);
    private static final Item.Properties FRUIT_PROPS = new Item.Properties().food(FRUIT_FOOD);
    private static final Item.Properties LEAFY_PROPS = new Item.Properties().food(LEAFY_FOOD);
    private static final Item.Properties NUT_PROPS = new Item.Properties().food(NUT_FOOD);
    private static final Item.Properties BEAN_PROPS = new Item.Properties().food(BEAN_FOOD);
    private static final Item.Properties MUSHROOM_PROPS = new Item.Properties().food(MUSHROOM_FOOD);
    private static final Item.Properties CONDIMENT_PROPS = new Item.Properties().food(CONDIMENT_FOOD);
    private static final Item.Properties INGREDIENT_PROPS = new Item.Properties().food(INGREDIENT_FOOD);
    private static final Item.Properties OIL_PROPS = new Item.Properties().food(OIL_FOOD);
    private static final Item.Properties PASTE_PROPS = new Item.Properties().food(PASTE_FOOD);
    private static final Item.Properties SPECIALTY_PROPS = new Item.Properties().food(SPECIALTY_FOOD);
    private static final Item.Properties HIGH_NUTRITION_PROPS = new Item.Properties().food(HIGH_NUTRITION_FOOD);

    public static final Item BOBIXUAN_CORN_SEEDS = register("bobixuan_corn_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_CORN_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SOYBEAN_SEEDS = register("bobixuan_soybean_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_SOYBEAN_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CHILI_PEPPER_SEEDS = register("bobixuan_chili_pepper_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_CHILI_PEPPER_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GARLIC_SEEDS = register("bobixuan_garlic_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_GARLIC_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GINGER_SEEDS = register("bobixuan_ginger_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_GINGER_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CUCUMBER_SEEDS = register("bobixuan_cucumber_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_CUCUMBER_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_EGGPLANT_SEEDS = register("bobixuan_eggplant_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_EGGPLANT_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_RADISH_SEEDS = register("bobixuan_radish_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_RADISH_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GREEN_ONION_SEEDS = register("bobixuan_green_onion_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_GREEN_ONION_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CILANTRO_SEEDS = register("bobixuan_cilantro_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_CILANTRO_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_LETTUCE_SEEDS = register("bobixuan_lettuce_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_LETTUCE_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CELERY_SEEDS = register("bobixuan_celery_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_CELERY_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BELL_PEPPER_SEEDS = register("bobixuan_bell_pepper_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_BELL_PEPPER_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_PEANUT_SEEDS = register("bobixuan_peanut_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_PEANUT_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SESAME_SEEDS = register("bobixuan_sesame_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_SESAME_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_MUNG_BEAN_SEEDS = register("bobixuan_mung_bean_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_MUNG_BEAN_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_STRAWBERRY_SEEDS = register("bobixuan_strawberry_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_STRAWBERRY_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BLUEBERRY_SEEDS = register("bobixuan_blueberry_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_BLUEBERRY_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_PINEAPPLE_SEEDS = register("bobixuan_pineapple_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_PINEAPPLE_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GRAPE_SEEDS = register("bobixuan_grape_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_GRAPE_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_LEMON_SEEDS = register("bobixuan_lemon_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_LEMON_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SWEET_POTATO_SEEDS = register("bobixuan_sweet_potato_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_SWEET_POTATO_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_TARO_SEEDS = register("bobixuan_taro_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_TARO_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BAMBOO_SHOOT_SEEDS = register("bobixuan_bamboo_shoot_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_BAMBOO_SHOOT_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_MINT_SEEDS = register("bobixuan_mint_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_MINT_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_ROSEMARY_SEEDS = register("bobixuan_rosemary_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_ROSEMARY_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_OYSTER_MUSHROOM_SEEDS = register("bobixuan_oyster_mushroom_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_OYSTER_MUSHROOM_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SPINACH_SEEDS = register("bobixuan_spinach_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_SPINACH_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_MUSTARD_GREEN_SEEDS = register("bobixuan_mustard_green_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_MUSTARD_GREEN_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SEAWEED_SEEDS = register("bobixuan_seaweed_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_SEAWEED_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BOK_CHOY_SEEDS = register("bobixuan_bok_choy_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_BOK_CHOY_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SHIITAKE_SEEDS = register("bobixuan_shiitake_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_SHIITAKE_CROP, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CHINESE_YAM_SEEDS = register("bobixuan_chinese_yam_seeds",
            new ItemNameBlockItem(ModBlocks.BOBIXUAN_CHINESE_YAM_CROP, DEFAULT_PROPS));

    public static final Item BOBIXUAN_CORN = register("bobixuan_corn",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_SOYBEAN = register("bobixuan_soybean",
            new Item(BEAN_PROPS));
    public static final Item BOBIXUAN_CHILI_PEPPER = register("bobixuan_chili_pepper",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_GARLIC = register("bobixuan_garlic",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_GINGER = register("bobixuan_ginger",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_CUCUMBER = register("bobixuan_cucumber",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_EGGPLANT = register("bobixuan_eggplant",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_RADISH = register("bobixuan_radish",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_GREEN_ONION = register("bobixuan_green_onion",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_CILANTRO = register("bobixuan_cilantro",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_LETTUCE = register("bobixuan_lettuce",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_CELERY = register("bobixuan_celery",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_BELL_PEPPER = register("bobixuan_bell_pepper",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_PEANUT = register("bobixuan_peanut",
            new Item(NUT_PROPS));
    public static final Item BOBIXUAN_SESAME = register("bobixuan_sesame",
            new Item(BEAN_PROPS));
    public static final Item BOBIXUAN_MUNG_BEAN = register("bobixuan_mung_bean",
            new Item(BEAN_PROPS));
    public static final Item BOBIXUAN_STRAWBERRY = register("bobixuan_strawberry",
            new Item(FRUIT_PROPS));
    public static final Item BOBIXUAN_BLUEBERRY = register("bobixuan_blueberry",
            new Item(FRUIT_PROPS));
    public static final Item BOBIXUAN_PINEAPPLE = register("bobixuan_pineapple",
            new Item(FRUIT_PROPS));
    public static final Item BOBIXUAN_GRAPE = register("bobixuan_grape",
            new Item(FRUIT_PROPS));
    public static final Item BOBIXUAN_LEMON = register("bobixuan_lemon",
            new Item(FRUIT_PROPS));
    public static final Item BOBIXUAN_SWEET_POTATO = register("bobixuan_sweet_potato",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_TARO = register("bobixuan_taro",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_BAMBOO_SHOOT = register("bobixuan_bamboo_shoot",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_MINT = register("bobixuan_mint",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_ROSEMARY = register("bobixuan_rosemary",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_OYSTER_MUSHROOM = register("bobixuan_oyster_mushroom",
            new Item(MUSHROOM_PROPS));
    public static final Item BOBIXUAN_SPINACH = register("bobixuan_spinach",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_MUSTARD_GREEN = register("bobixuan_mustard_green",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_SEAWEED = register("bobixuan_seaweed",
            new Item(VEGETABLE_PROPS));
    public static final Item BOBIXUAN_BOK_CHOY = register("bobixuan_bok_choy",
            new Item(LEAFY_PROPS));
    public static final Item BOBIXUAN_SHIITAKE = register("bobixuan_shiitake",
            new Item(MUSHROOM_PROPS));
    public static final Item BOBIXUAN_CHINESE_YAM = register("bobixuan_chinese_yam",
            new Item(VEGETABLE_PROPS));

    public static final Item BOBIXUAN_CORN_CRATE = register("bobixuan_corn_crate",
            new BlockItem(ModBlocks.BOBIXUAN_CORN_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CHILI_PEPPER_CRATE = register("bobixuan_chili_pepper_crate",
            new BlockItem(ModBlocks.BOBIXUAN_CHILI_PEPPER_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GARLIC_CRATE = register("bobixuan_garlic_crate",
            new BlockItem(ModBlocks.BOBIXUAN_GARLIC_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GINGER_CRATE = register("bobixuan_ginger_crate",
            new BlockItem(ModBlocks.BOBIXUAN_GINGER_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CUCUMBER_CRATE = register("bobixuan_cucumber_crate",
            new BlockItem(ModBlocks.BOBIXUAN_CUCUMBER_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_EGGPLANT_CRATE = register("bobixuan_eggplant_crate",
            new BlockItem(ModBlocks.BOBIXUAN_EGGPLANT_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_RADISH_CRATE = register("bobixuan_radish_crate",
            new BlockItem(ModBlocks.BOBIXUAN_RADISH_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CELERY_CRATE = register("bobixuan_celery_crate",
            new BlockItem(ModBlocks.BOBIXUAN_CELERY_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BELL_PEPPER_CRATE = register("bobixuan_bell_pepper_crate",
            new BlockItem(ModBlocks.BOBIXUAN_BELL_PEPPER_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_STRAWBERRY_CRATE = register("bobixuan_strawberry_crate",
            new BlockItem(ModBlocks.BOBIXUAN_STRAWBERRY_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BLUEBERRY_CRATE = register("bobixuan_blueberry_crate",
            new BlockItem(ModBlocks.BOBIXUAN_BLUEBERRY_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_PINEAPPLE_CRATE = register("bobixuan_pineapple_crate",
            new BlockItem(ModBlocks.BOBIXUAN_PINEAPPLE_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GRAPE_CRATE = register("bobixuan_grape_crate",
            new BlockItem(ModBlocks.BOBIXUAN_GRAPE_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_LEMON_CRATE = register("bobixuan_lemon_crate",
            new BlockItem(ModBlocks.BOBIXUAN_LEMON_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SWEET_POTATO_CRATE = register("bobixuan_sweet_potato_crate",
            new BlockItem(ModBlocks.BOBIXUAN_SWEET_POTATO_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_TARO_CRATE = register("bobixuan_taro_crate",
            new BlockItem(ModBlocks.BOBIXUAN_TARO_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BAMBOO_SHOOT_CRATE = register("bobixuan_bamboo_shoot_crate",
            new BlockItem(ModBlocks.BOBIXUAN_BAMBOO_SHOOT_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_OYSTER_MUSHROOM_CRATE = register("bobixuan_oyster_mushroom_crate",
            new BlockItem(ModBlocks.BOBIXUAN_OYSTER_MUSHROOM_CRATE, DEFAULT_PROPS));

    public static final Item BOBIXUAN_SOYBEAN_BAG = register("bobixuan_soybean_bag",
            new BlockItem(ModBlocks.BOBIXUAN_SOYBEAN_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_GREEN_ONION_BAG = register("bobixuan_green_onion_bag",
            new BlockItem(ModBlocks.BOBIXUAN_GREEN_ONION_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CILANTRO_BAG = register("bobixuan_cilantro_bag",
            new BlockItem(ModBlocks.BOBIXUAN_CILANTRO_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_LETTUCE_BAG = register("bobixuan_lettuce_bag",
            new BlockItem(ModBlocks.BOBIXUAN_LETTUCE_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_PEANUT_BAG = register("bobixuan_peanut_bag",
            new BlockItem(ModBlocks.BOBIXUAN_PEANUT_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SESAME_BAG = register("bobixuan_sesame_bag",
            new BlockItem(ModBlocks.BOBIXUAN_SESAME_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_MUNG_BEAN_BAG = register("bobixuan_mung_bean_bag",
            new BlockItem(ModBlocks.BOBIXUAN_MUNG_BEAN_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_MINT_BAG = register("bobixuan_mint_bag",
            new BlockItem(ModBlocks.BOBIXUAN_MINT_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_ROSEMARY_BAG = register("bobixuan_rosemary_bag",
            new BlockItem(ModBlocks.BOBIXUAN_ROSEMARY_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SPINACH_BAG = register("bobixuan_spinach_bag",
            new BlockItem(ModBlocks.BOBIXUAN_SPINACH_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_MUSTARD_GREEN_BAG = register("bobixuan_mustard_green_bag",
            new BlockItem(ModBlocks.BOBIXUAN_MUSTARD_GREEN_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SEAWEED_BAG = register("bobixuan_seaweed_bag",
            new BlockItem(ModBlocks.BOBIXUAN_SEAWEED_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_BOK_CHOY_BAG = register("bobixuan_bok_choy_bag",
            new BlockItem(ModBlocks.BOBIXUAN_BOK_CHOY_BAG, DEFAULT_PROPS));
    public static final Item BOBIXUAN_SHIITAKE_CRATE = register("bobixuan_shiitake_crate",
            new BlockItem(ModBlocks.BOBIXUAN_SHIITAKE_CRATE, DEFAULT_PROPS));
    public static final Item BOBIXUAN_CHINESE_YAM_CRATE = register("bobixuan_chinese_yam_crate",
            new BlockItem(ModBlocks.BOBIXUAN_CHINESE_YAM_CRATE, DEFAULT_PROPS));

    // --- Processed Ingredients (12) ---
    public static final Item BOBIXUAN_CORNMEAL = register("bobixuan_cornmeal",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_TOFU = register("bobixuan_tofu",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_CHILI_POWDER = register("bobixuan_chili_powder",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_GARLIC_POWDER = register("bobixuan_garlic_powder",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_SOY_SAUCE = register("bobixuan_soy_sauce",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_SESAME_OIL = register("bobixuan_sesame_oil",
            new Item(OIL_PROPS));
    public static final Item BOBIXUAN_PEANUT_OIL = register("bobixuan_peanut_oil",
            new Item(OIL_PROPS));
    public static final Item BOBIXUAN_VINEGAR = register("bobixuan_vinegar",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_PEANUT_BUTTER = register("bobixuan_peanut_butter",
            new Item(PASTE_PROPS));
    public static final Item BOBIXUAN_SESAME_PASTE = register("bobixuan_sesame_paste",
            new Item(PASTE_PROPS));
    public static final Item BOBIXUAN_PICKLED_CUCUMBER = register("bobixuan_pickled_cucumber",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_MUNG_BEAN_SPROUTS = register("bobixuan_mung_bean_sprouts",
            new Item(INGREDIENT_PROPS));

    // --- Sauces (5) ---
    public static final Item BOBIXUAN_CHILI_SAUCE = register("bobixuan_chili_sauce",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_GARLIC_SAUCE = register("bobixuan_garlic_sauce",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_SWEET_SOUR_SAUCE = register("bobixuan_sweet_sour_sauce",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_FERMENTED_BEAN_PASTE = register("bobixuan_fermented_bean_paste",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_GINGER_SOY_MARINADE = register("bobixuan_ginger_soy_marinade",
            new Item(CONDIMENT_PROPS));

    // --- Special Items (10) ---
    public static final Item BOBIXUAN_DUMPLING_WRAPPER = register("bobixuan_dumpling_wrapper",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_RAW_NOODLES = register("bobixuan_raw_noodles",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_TOFU_SKIN = register("bobixuan_tofu_skin",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_RICE_CAKE = register("bobixuan_rice_cake",
            new Item(PASTE_PROPS));
    public static final Item BOBIXUAN_ZONGZI = register("bobixuan_zongzi",
            new Item(HIGH_NUTRITION_PROPS));
    public static final Item BOBIXUAN_MOONCAKE = register("bobixuan_mooncake",
            new Item(SPECIALTY_PROPS));
    public static final Item BOBIXUAN_SPRING_ROLL_WRAPPER = register("bobixuan_spring_roll_wrapper",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_ROCK_SUGAR = register("bobixuan_rock_sugar",
            new Item(INGREDIENT_PROPS));
    public static final Item BOBIXUAN_FERMENTED_TOFU = register("bobixuan_fermented_tofu",
            new Item(CONDIMENT_PROPS));
    public static final Item BOBIXUAN_CENTURY_EGG = register("bobixuan_century_egg",
            new Item(INGREDIENT_PROPS));

    // --- Dishes (20) ---

    public static final FoodProperties GRILLED_CORN_FOOD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.6f).build();
    public static final FoodProperties MAPO_TOFU_FOOD = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.8f).build();
    public static final FoodProperties KUNG_PAO_CHICKEN_FOOD = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.8f).build();
    public static final FoodProperties SWEET_SOUR_PORK_FOOD = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.8f).build();
    public static final FoodProperties BRAISED_EGGPLANT_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties CUCUMBER_SALAD_FOOD = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.6f).build();
    public static final FoodProperties FRIED_RICE_FOOD = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.7f).build();
    public static final FoodProperties DUMPLINGS_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties SPRING_ROLLS_FOOD = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.6f).build();
    public static final FoodProperties HAND_PULLED_NOODLES_FOOD = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.7f).build();
    public static final FoodProperties STIR_FRIED_NOODLES_FOOD = new FoodProperties.Builder()
            .nutrition(9).saturationMod(0.7f).build();
    public static final FoodProperties EGG_FRIED_RICE_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties SCALLION_PANCAKE_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties GARLIC_GREENS_FOOD = new FoodProperties.Builder()
            .nutrition(7).saturationMod(0.6f).build();
    public static final FoodProperties BRAISED_RADISH_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties SPICY_PEANUTS_FOOD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.5f).build();
    public static final FoodProperties EDAMAME_FOOD = new FoodProperties.Builder()
            .nutrition(5).saturationMod(0.4f).build();
    public static final FoodProperties POTSTICKERS_FOOD = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    public static final FoodProperties ZHAJIANG_NOODLES_FOOD = new FoodProperties.Builder()
            .nutrition(11).saturationMod(0.8f).build();
    public static final FoodProperties SWEET_POTATO_BALLS_FOOD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.5f).build();

    // --- Drinks (8) ---

    public static final FoodProperties SOY_MILK_FOOD = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.4f).build();
    public static final FoodProperties LEMONADE_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.3f).build();
    public static final FoodProperties GRAPE_JUICE_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.4f).build();
    public static final FoodProperties MINT_TEA_FOOD = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.5f).build();
    public static final FoodProperties GINGER_TEA_FOOD = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.5f).build();
    public static final FoodProperties STRAWBERRY_SMOOTHIE_FOOD = new FoodProperties.Builder()
            .nutrition(5).saturationMod(0.6f).build();
    public static final FoodProperties BLUEBERRY_JUICE_FOOD = new FoodProperties.Builder()
            .nutrition(2).saturationMod(0.4f).build();
    public static final FoodProperties TARO_BUBBLE_TEA_FOOD = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.7f).build();

    public static final Item BOBIXUAN_GRILLED_CORN = register("bobixuan_grilled_corn",
            new Item(new Item.Properties().food(GRILLED_CORN_FOOD)));
    public static final Item BOBIXUAN_MAPO_TOFU = register("bobixuan_mapo_tofu",
            new Item(new Item.Properties().food(MAPO_TOFU_FOOD)));
    public static final Item BOBIXUAN_KUNG_PAO_CHICKEN = register("bobixuan_kung_pao_chicken",
            new Item(new Item.Properties().food(KUNG_PAO_CHICKEN_FOOD)));
    public static final Item BOBIXUAN_SWEET_SOUR_PORK = register("bobixuan_sweet_sour_pork",
            new Item(new Item.Properties().food(SWEET_SOUR_PORK_FOOD)));
    public static final Item BOBIXUAN_BRAISED_EGGPLANT = register("bobixuan_braised_eggplant",
            new Item(new Item.Properties().food(BRAISED_EGGPLANT_FOOD)));
    public static final Item BOBIXUAN_CUCUMBER_SALAD = register("bobixuan_cucumber_salad",
            new Item(new Item.Properties().food(CUCUMBER_SALAD_FOOD)));
    public static final Item BOBIXUAN_FRIED_RICE = register("bobixuan_fried_rice",
            new Item(new Item.Properties().food(FRIED_RICE_FOOD)));
    public static final Item BOBIXUAN_DUMPLINGS = register("bobixuan_dumplings",
            new Item(new Item.Properties().food(DUMPLINGS_FOOD)));
    public static final Item BOBIXUAN_SPRING_ROLLS = register("bobixuan_spring_rolls",
            new Item(new Item.Properties().food(SPRING_ROLLS_FOOD)));
    public static final Item BOBIXUAN_HAND_PULLED_NOODLES = register("bobixuan_hand_pulled_noodles",
            new Item(new Item.Properties().food(HAND_PULLED_NOODLES_FOOD)));
    public static final Item BOBIXUAN_STIR_FRIED_NOODLES = register("bobixuan_stir_fried_noodles",
            new Item(new Item.Properties().food(STIR_FRIED_NOODLES_FOOD)));
    public static final Item BOBIXUAN_EGG_FRIED_RICE = register("bobixuan_egg_fried_rice",
            new Item(new Item.Properties().food(EGG_FRIED_RICE_FOOD)));
    public static final Item BOBIXUAN_SCALLION_PANCAKE = register("bobixuan_scallion_pancake",
            new Item(new Item.Properties().food(SCALLION_PANCAKE_FOOD)));
    public static final Item BOBIXUAN_GARLIC_GREENS = register("bobixuan_garlic_greens",
            new Item(new Item.Properties().food(GARLIC_GREENS_FOOD)));
    public static final Item BOBIXUAN_BRAISED_RADISH = register("bobixuan_braised_radish",
            new Item(new Item.Properties().food(BRAISED_RADISH_FOOD)));
    public static final Item BOBIXUAN_SPICY_PEANUTS = register("bobixuan_spicy_peanuts",
            new Item(new Item.Properties().food(SPICY_PEANUTS_FOOD)));
    public static final Item BOBIXUAN_EDAMAME = register("bobixuan_edamame",
            new Item(new Item.Properties().food(EDAMAME_FOOD)));
    public static final Item BOBIXUAN_POTSTICKERS = register("bobixuan_potstickers",
            new Item(new Item.Properties().food(POTSTICKERS_FOOD)));
    public static final Item BOBIXUAN_ZHAJIANG_NOODLES = register("bobixuan_zhajiang_noodles",
            new Item(new Item.Properties().food(ZHAJIANG_NOODLES_FOOD)));
    public static final Item BOBIXUAN_SWEET_POTATO_BALLS = register("bobixuan_sweet_potato_balls",
            new Item(new Item.Properties().food(SWEET_POTATO_BALLS_FOOD)));

    public static final Item BOBIXUAN_SOY_MILK = register("bobixuan_soy_milk",
            new Item(new Item.Properties().food(SOY_MILK_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_LEMONADE = register("bobixuan_lemonade",
            new Item(new Item.Properties().food(LEMONADE_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_GRAPE_JUICE = register("bobixuan_grape_juice",
            new Item(new Item.Properties().food(GRAPE_JUICE_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_MINT_TEA = register("bobixuan_mint_tea",
            new Item(new Item.Properties().food(MINT_TEA_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_GINGER_TEA = register("bobixuan_ginger_tea",
            new Item(new Item.Properties().food(GINGER_TEA_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_STRAWBERRY_SMOOTHIE = register("bobixuan_strawberry_smoothie",
            new Item(new Item.Properties().food(STRAWBERRY_SMOOTHIE_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_BLUEBERRY_JUICE = register("bobixuan_blueberry_juice",
            new Item(new Item.Properties().food(BLUEBERRY_JUICE_FOOD).stacksTo(16)));
    public static final Item BOBIXUAN_TARO_BUBBLE_TEA = register("bobixuan_taro_bubble_tea",
            new Item(new Item.Properties().food(TARO_BUBBLE_TEA_FOOD).stacksTo(16)));

    public static final Item BOBIXUAN_HOT_POT = register("bobixuan_hot_pot",
            new BlockItem(ModBlocks.BOBIXUAN_HOT_POT, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_PEKING_DUCK = register("bobixuan_peking_duck",
            new BlockItem(ModBlocks.BOBIXUAN_PEKING_DUCK, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_DUMPLING_FEAST = register("bobixuan_dumpling_feast",
            new BlockItem(ModBlocks.BOBIXUAN_DUMPLING_FEAST, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_BIG_PLATE_CHICKEN = register("bobixuan_big_plate_chicken",
            new BlockItem(ModBlocks.BOBIXUAN_BIG_PLATE_CHICKEN, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_NEW_YEAR_FEAST = register("bobixuan_new_year_feast",
            new BlockItem(ModBlocks.BOBIXUAN_NEW_YEAR_FEAST, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_BBQ_PLATTER = register("bobixuan_bbq_platter",
            new BlockItem(ModBlocks.BOBIXUAN_BBQ_PLATTER, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_FRUIT_PLATTER = register("bobixuan_fruit_platter",
            new BlockItem(ModBlocks.BOBIXUAN_FRUIT_PLATTER, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_NOODLE_FEAST = register("bobixuan_noodle_feast",
            new BlockItem(ModBlocks.BOBIXUAN_NOODLE_FEAST, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_STONE_POT_RICE = register("bobixuan_stone_pot_rice",
            new BlockItem(ModBlocks.BOBIXUAN_STONE_POT_RICE, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_ROAST_SUCKLING_PIG = register("bobixuan_roast_suckling_pig",
            new BlockItem(ModBlocks.BOBIXUAN_ROAST_SUCKLING_PIG, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_DIM_SUM_BASKET = register("bobixuan_dim_sum_basket",
            new BlockItem(ModBlocks.BOBIXUAN_DIM_SUM_BASKET, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_SEAFOOD_PLATTER = register("bobixuan_seafood_platter",
            new BlockItem(ModBlocks.BOBIXUAN_SEAFOOD_PLATTER, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_CANDY_BOX = register("bobixuan_candy_box",
            new BlockItem(ModBlocks.BOBIXUAN_CANDY_BOX, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_CHEESE_PLATTER = register("bobixuan_cheese_platter",
            new BlockItem(ModBlocks.BOBIXUAN_CHEESE_PLATTER, new Item.Properties().stacksTo(1)));
    public static final Item BOBIXUAN_HOT_SOUR_SOUP_POT = register("bobixuan_hot_sour_soup_pot",
            new BlockItem(ModBlocks.BOBIXUAN_HOT_SOUR_SOUP_POT, new Item.Properties().stacksTo(1)));

        public static void init() {
        }

        public static List<Item> getProduceStorageItems() {
                return List.of(
                                BOBIXUAN_CORN_CRATE,
                                BOBIXUAN_CHILI_PEPPER_CRATE,
                                BOBIXUAN_GARLIC_CRATE,
                                BOBIXUAN_GINGER_CRATE,
                                BOBIXUAN_CUCUMBER_CRATE,
                                BOBIXUAN_EGGPLANT_CRATE,
                                BOBIXUAN_RADISH_CRATE,
                                BOBIXUAN_CELERY_CRATE,
                                BOBIXUAN_BELL_PEPPER_CRATE,
                                BOBIXUAN_STRAWBERRY_CRATE,
                                BOBIXUAN_BLUEBERRY_CRATE,
                                BOBIXUAN_PINEAPPLE_CRATE,
                                BOBIXUAN_GRAPE_CRATE,
                                BOBIXUAN_LEMON_CRATE,
                                BOBIXUAN_SWEET_POTATO_CRATE,
                                BOBIXUAN_TARO_CRATE,
                                BOBIXUAN_BAMBOO_SHOOT_CRATE,
                                BOBIXUAN_OYSTER_MUSHROOM_CRATE,
                                BOBIXUAN_SOYBEAN_BAG,
                                BOBIXUAN_GREEN_ONION_BAG,
                                BOBIXUAN_CILANTRO_BAG,
                                BOBIXUAN_LETTUCE_BAG,
                                BOBIXUAN_PEANUT_BAG,
                                BOBIXUAN_SESAME_BAG,
                                BOBIXUAN_MUNG_BEAN_BAG,
                                BOBIXUAN_MINT_BAG,
                                BOBIXUAN_ROSEMARY_BAG,
                                BOBIXUAN_SPINACH_BAG,
                                BOBIXUAN_MUSTARD_GREEN_BAG,
                                BOBIXUAN_SEAWEED_BAG,
                                BOBIXUAN_BOK_CHOY_BAG,
                                BOBIXUAN_SHIITAKE_CRATE,
                                BOBIXUAN_CHINESE_YAM_CRATE
                );
        }

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, name), item);
    }
}
