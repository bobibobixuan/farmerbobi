package top.bobixuan.bobidelight.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import top.bobixuan.bobidelight.item.ModItems;

import java.util.List;

public class ModBlocks {
    public static final String MOD_ID = "bobidelight";

    public static final Block BOBIXUAN_WILD_CORN = register("bobixuan_wild_corn", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_SOYBEAN = register("bobixuan_wild_soybean", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_CHILI_PEPPER = register("bobixuan_wild_chili_pepper", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_GARLIC = register("bobixuan_wild_garlic", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_GINGER = register("bobixuan_wild_ginger", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_CUCUMBER = register("bobixuan_wild_cucumber", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_EGGPLANT = register("bobixuan_wild_eggplant", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_RADISH = register("bobixuan_wild_radish", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_GREEN_ONION = register("bobixuan_wild_green_onion", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_CILANTRO = register("bobixuan_wild_cilantro", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_LETTUCE = register("bobixuan_wild_lettuce", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_CELERY = register("bobixuan_wild_celery", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_BELL_PEPPER = register("bobixuan_wild_bell_pepper", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_PEANUT = register("bobixuan_wild_peanut", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_SESAME = register("bobixuan_wild_sesame", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_MUNG_BEAN = register("bobixuan_wild_mung_bean", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_STRAWBERRY = register("bobixuan_wild_strawberry", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_BLUEBERRY = register("bobixuan_wild_blueberry", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_PINEAPPLE = register("bobixuan_wild_pineapple", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_GRAPE = register("bobixuan_wild_grape", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_LEMON = register("bobixuan_wild_lemon", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_SWEET_POTATO = register("bobixuan_wild_sweet_potato", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_TARO = register("bobixuan_wild_taro", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_BAMBOO_SHOOT = register("bobixuan_wild_bamboo_shoot", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_MINT = register("bobixuan_wild_mint", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_ROSEMARY = register("bobixuan_wild_rosemary", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_OYSTER_MUSHROOM = register("bobixuan_wild_oyster_mushroom", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_SPINACH = register("bobixuan_wild_spinach", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_MUSTARD_GREEN = register("bobixuan_wild_mustard_green", new WildCropBlock());
    public static final Block BOBIXUAN_WILD_SEAWEED = register("bobixuan_wild_seaweed", new WildCropBlock());
        public static final Block BOBIXUAN_WILD_BOK_CHOY = register("bobixuan_wild_bok_choy", new WildCropBlock());
        public static final Block BOBIXUAN_WILD_SHIITAKE = register("bobixuan_wild_shiitake", new WildCropBlock());
        public static final Block BOBIXUAN_WILD_CHINESE_YAM = register("bobixuan_wild_chinese_yam", new WildCropBlock());

    public static final Block BOBIXUAN_CORN_CROP = register("bobixuan_corn_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_CORN_SEEDS));
    public static final Block BOBIXUAN_SOYBEAN_CROP = register("bobixuan_soybean_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_SOYBEAN_SEEDS));
    public static final Block BOBIXUAN_CHILI_PEPPER_CROP = register("bobixuan_chili_pepper_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_CHILI_PEPPER_SEEDS));
    public static final Block BOBIXUAN_GARLIC_CROP = register("bobixuan_garlic_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_GARLIC_SEEDS));
    public static final Block BOBIXUAN_GINGER_CROP = register("bobixuan_ginger_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_GINGER_SEEDS));
    public static final Block BOBIXUAN_CUCUMBER_CROP = register("bobixuan_cucumber_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_CUCUMBER_SEEDS));
    public static final Block BOBIXUAN_EGGPLANT_CROP = register("bobixuan_eggplant_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_EGGPLANT_SEEDS));
    public static final Block BOBIXUAN_RADISH_CROP = register("bobixuan_radish_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_RADISH_SEEDS));
    public static final Block BOBIXUAN_GREEN_ONION_CROP = register("bobixuan_green_onion_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_GREEN_ONION_SEEDS));
    public static final Block BOBIXUAN_CILANTRO_CROP = register("bobixuan_cilantro_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_CILANTRO_SEEDS));
    public static final Block BOBIXUAN_LETTUCE_CROP = register("bobixuan_lettuce_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_LETTUCE_SEEDS));
    public static final Block BOBIXUAN_CELERY_CROP = register("bobixuan_celery_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_CELERY_SEEDS));
    public static final Block BOBIXUAN_BELL_PEPPER_CROP = register("bobixuan_bell_pepper_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_BELL_PEPPER_SEEDS));
    public static final Block BOBIXUAN_PEANUT_CROP = register("bobixuan_peanut_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_PEANUT_SEEDS));
    public static final Block BOBIXUAN_SESAME_CROP = register("bobixuan_sesame_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_SESAME_SEEDS));
    public static final Block BOBIXUAN_MUNG_BEAN_CROP = register("bobixuan_mung_bean_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_MUNG_BEAN_SEEDS));
    public static final Block BOBIXUAN_STRAWBERRY_CROP = register("bobixuan_strawberry_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_STRAWBERRY_SEEDS));
    public static final Block BOBIXUAN_BLUEBERRY_CROP = register("bobixuan_blueberry_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_BLUEBERRY_SEEDS));
    public static final Block BOBIXUAN_PINEAPPLE_CROP = register("bobixuan_pineapple_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_PINEAPPLE_SEEDS));
    public static final Block BOBIXUAN_GRAPE_CROP = register("bobixuan_grape_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_GRAPE_SEEDS));
    public static final Block BOBIXUAN_LEMON_CROP = register("bobixuan_lemon_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_LEMON_SEEDS));
    public static final Block BOBIXUAN_SWEET_POTATO_CROP = register("bobixuan_sweet_potato_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_SWEET_POTATO_SEEDS));
    public static final Block BOBIXUAN_TARO_CROP = register("bobixuan_taro_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_TARO_SEEDS));
    public static final Block BOBIXUAN_BAMBOO_SHOOT_CROP = register("bobixuan_bamboo_shoot_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_BAMBOO_SHOOT_SEEDS));
    public static final Block BOBIXUAN_MINT_CROP = register("bobixuan_mint_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_MINT_SEEDS));
    public static final Block BOBIXUAN_ROSEMARY_CROP = register("bobixuan_rosemary_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_ROSEMARY_SEEDS));
    public static final Block BOBIXUAN_OYSTER_MUSHROOM_CROP = register("bobixuan_oyster_mushroom_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_OYSTER_MUSHROOM_SEEDS));
    public static final Block BOBIXUAN_SPINACH_CROP = register("bobixuan_spinach_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_SPINACH_SEEDS));
    public static final Block BOBIXUAN_MUSTARD_GREEN_CROP = register("bobixuan_mustard_green_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_MUSTARD_GREEN_SEEDS));
    public static final Block BOBIXUAN_SEAWEED_CROP = register("bobixuan_seaweed_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_SEAWEED_SEEDS));
    public static final Block BOBIXUAN_BOK_CHOY_CROP = register("bobixuan_bok_choy_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_BOK_CHOY_SEEDS));
    public static final Block BOBIXUAN_SHIITAKE_CROP = register("bobixuan_shiitake_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_SHIITAKE_SEEDS));
    public static final Block BOBIXUAN_CHINESE_YAM_CROP = register("bobixuan_chinese_yam_crop",
            new BobiCropBlock(() -> ModItems.BOBIXUAN_CHINESE_YAM_SEEDS));

    public static final Block BOBIXUAN_CORN_CRATE = register("bobixuan_corn_crate", crateBlock());
    public static final Block BOBIXUAN_CHILI_PEPPER_CRATE = register("bobixuan_chili_pepper_crate", crateBlock());
    public static final Block BOBIXUAN_GARLIC_CRATE = register("bobixuan_garlic_crate", crateBlock());
    public static final Block BOBIXUAN_GINGER_CRATE = register("bobixuan_ginger_crate", crateBlock());
    public static final Block BOBIXUAN_CUCUMBER_CRATE = register("bobixuan_cucumber_crate", crateBlock());
    public static final Block BOBIXUAN_EGGPLANT_CRATE = register("bobixuan_eggplant_crate", crateBlock());
    public static final Block BOBIXUAN_RADISH_CRATE = register("bobixuan_radish_crate", crateBlock());
    public static final Block BOBIXUAN_CELERY_CRATE = register("bobixuan_celery_crate", crateBlock());
    public static final Block BOBIXUAN_BELL_PEPPER_CRATE = register("bobixuan_bell_pepper_crate", crateBlock());
    public static final Block BOBIXUAN_STRAWBERRY_CRATE = register("bobixuan_strawberry_crate", crateBlock());
    public static final Block BOBIXUAN_BLUEBERRY_CRATE = register("bobixuan_blueberry_crate", crateBlock());
    public static final Block BOBIXUAN_PINEAPPLE_CRATE = register("bobixuan_pineapple_crate", crateBlock());
    public static final Block BOBIXUAN_GRAPE_CRATE = register("bobixuan_grape_crate", crateBlock());
    public static final Block BOBIXUAN_LEMON_CRATE = register("bobixuan_lemon_crate", crateBlock());
    public static final Block BOBIXUAN_SWEET_POTATO_CRATE = register("bobixuan_sweet_potato_crate", crateBlock());
    public static final Block BOBIXUAN_TARO_CRATE = register("bobixuan_taro_crate", crateBlock());
    public static final Block BOBIXUAN_BAMBOO_SHOOT_CRATE = register("bobixuan_bamboo_shoot_crate", crateBlock());
    public static final Block BOBIXUAN_OYSTER_MUSHROOM_CRATE = register("bobixuan_oyster_mushroom_crate", crateBlock());

    public static final Block BOBIXUAN_SOYBEAN_BAG = register("bobixuan_soybean_bag", bagBlock());
    public static final Block BOBIXUAN_GREEN_ONION_BAG = register("bobixuan_green_onion_bag", bagBlock());
    public static final Block BOBIXUAN_CILANTRO_BAG = register("bobixuan_cilantro_bag", bagBlock());
    public static final Block BOBIXUAN_LETTUCE_BAG = register("bobixuan_lettuce_bag", bagBlock());
    public static final Block BOBIXUAN_PEANUT_BAG = register("bobixuan_peanut_bag", bagBlock());
    public static final Block BOBIXUAN_SESAME_BAG = register("bobixuan_sesame_bag", bagBlock());
    public static final Block BOBIXUAN_MUNG_BEAN_BAG = register("bobixuan_mung_bean_bag", bagBlock());
    public static final Block BOBIXUAN_MINT_BAG = register("bobixuan_mint_bag", bagBlock());
    public static final Block BOBIXUAN_ROSEMARY_BAG = register("bobixuan_rosemary_bag", bagBlock());
    public static final Block BOBIXUAN_SPINACH_BAG = register("bobixuan_spinach_bag", bagBlock());
    public static final Block BOBIXUAN_MUSTARD_GREEN_BAG = register("bobixuan_mustard_green_bag", bagBlock());
    public static final Block BOBIXUAN_SEAWEED_BAG = register("bobixuan_seaweed_bag", bagBlock());
        public static final Block BOBIXUAN_BOK_CHOY_BAG = register("bobixuan_bok_choy_bag", bagBlock());
        public static final Block BOBIXUAN_SHIITAKE_CRATE = register("bobixuan_shiitake_crate", crateBlock());
        public static final Block BOBIXUAN_CHINESE_YAM_CRATE = register("bobixuan_chinese_yam_crate", crateBlock());

    private static final FoodProperties HOT_POT_FOOD_SERVING = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.6f).build();
    private static final FoodProperties FEAST_FOOD_SERVING = new FoodProperties.Builder()
            .nutrition(8).saturationMod(0.7f).build();
    private static final FoodProperties HEARTY_FOOD_SERVING = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.8f).build();
    private static final FoodProperties LIGHT_FOOD_SERVING = new FoodProperties.Builder()
            .nutrition(6).saturationMod(0.5f).build();
    private static final FoodProperties ROAST_FOOD_SERVING = new FoodProperties.Builder()
            .nutrition(12).saturationMod(0.9f).build();

    public static final Block BOBIXUAN_HOT_POT = register("bobixuan_hot_pot",
            new FeastBlock(HOT_POT_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_PEKING_DUCK = register("bobixuan_peking_duck",
            new FeastBlock(FEAST_FOOD_SERVING, null));
    public static final Block BOBIXUAN_DUMPLING_FEAST = register("bobixuan_dumpling_feast",
            new FeastBlock(LIGHT_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_BIG_PLATE_CHICKEN = register("bobixuan_big_plate_chicken",
            new FeastBlock(HEARTY_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_NEW_YEAR_FEAST = register("bobixuan_new_year_feast",
            new FeastBlock(HEARTY_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_BBQ_PLATTER = register("bobixuan_bbq_platter",
            new FeastBlock(FEAST_FOOD_SERVING, null));
    public static final Block BOBIXUAN_FRUIT_PLATTER = register("bobixuan_fruit_platter",
            new FeastBlock(LIGHT_FOOD_SERVING, null));
    public static final Block BOBIXUAN_NOODLE_FEAST = register("bobixuan_noodle_feast",
            new FeastBlock(HEARTY_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_STONE_POT_RICE = register("bobixuan_stone_pot_rice",
            new FeastBlock(FEAST_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_ROAST_SUCKLING_PIG = register("bobixuan_roast_suckling_pig",
            new FeastBlock(ROAST_FOOD_SERVING, null));
    public static final Block BOBIXUAN_DIM_SUM_BASKET = register("bobixuan_dim_sum_basket",
            new FeastBlock(LIGHT_FOOD_SERVING, null));
    public static final Block BOBIXUAN_SEAFOOD_PLATTER = register("bobixuan_seafood_platter",
            new FeastBlock(FEAST_FOOD_SERVING, () -> Items.BOWL));
    public static final Block BOBIXUAN_CANDY_BOX = register("bobixuan_candy_box",
            new FeastBlock(LIGHT_FOOD_SERVING, null));
    public static final Block BOBIXUAN_CHEESE_PLATTER = register("bobixuan_cheese_platter",
            new FeastBlock(FEAST_FOOD_SERVING, null));
    public static final Block BOBIXUAN_HOT_SOUR_SOUP_POT = register("bobixuan_hot_sour_soup_pot",
            new FeastBlock(HOT_POT_FOOD_SERVING, () -> Items.BOWL));

        public static List<Block> getCutoutBlocks() {
                return BuiltInRegistries.BLOCK.stream()
                                .filter(block -> MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()))
                                .filter(BushBlock.class::isInstance)
                                .toList();
        }

    private static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, name), block);
    }

        private static Block crateBlock() {
                return new Block(BlockBehaviour.Properties.copy(Blocks.BARREL));
        }

        private static Block bagBlock() {
                return new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK));
        }

    public static void init() {
    }
}
