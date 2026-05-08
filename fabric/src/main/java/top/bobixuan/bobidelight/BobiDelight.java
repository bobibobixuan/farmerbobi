package top.bobixuan.bobidelight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.bobixuan.bobidelight.block.ModBlocks;
import top.bobixuan.bobidelight.item.ModItems;
import top.bobixuan.bobidelight.worldgen.ModWorldGen;

public class BobiDelight implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModItems.MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.init();
        ModBlocks.init();
        ModWorldGen.init();

        Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(ModItems.MOD_ID, "bobixuan_group"),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModItems.BOBIXUAN_PIZZA))
                .title(Component.translatable("itemGroup.bobidelight.bobixuan"))
                .displayItems((context, entries) -> {
                    entries.accept(ModItems.BOBIXUAN_PIZZA);
                    entries.accept(ModItems.BOBIXUAN_HAMBURGER);
                    entries.accept(ModItems.BOBIXUAN_ICE_CREAM);
                    entries.accept(ModItems.BOBIXUAN_CHICKEN_WINGS);
                    entries.accept(ModItems.BOBIXUAN_FRIES);

                    entries.accept(ModItems.BOBIXUAN_CORN_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_SOYBEAN_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_CHILI_PEPPER_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_GARLIC_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_GINGER_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_CUCUMBER_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_EGGPLANT_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_RADISH_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_GREEN_ONION_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_CILANTRO_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_LETTUCE_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_CELERY_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_BELL_PEPPER_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_PEANUT_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_SESAME_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_MUNG_BEAN_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_STRAWBERRY_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_BLUEBERRY_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_PINEAPPLE_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_GRAPE_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_LEMON_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_SWEET_POTATO_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_TARO_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_BAMBOO_SHOOT_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_MINT_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_ROSEMARY_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_OYSTER_MUSHROOM_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_SPINACH_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_MUSTARD_GREEN_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_SEAWEED_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_BOK_CHOY_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_SHIITAKE_SEEDS);
                    entries.accept(ModItems.BOBIXUAN_CHINESE_YAM_SEEDS);

                    entries.accept(ModItems.BOBIXUAN_CORN);
                    entries.accept(ModItems.BOBIXUAN_SOYBEAN);
                    entries.accept(ModItems.BOBIXUAN_CHILI_PEPPER);
                    entries.accept(ModItems.BOBIXUAN_GARLIC);
                    entries.accept(ModItems.BOBIXUAN_GINGER);
                    entries.accept(ModItems.BOBIXUAN_CUCUMBER);
                    entries.accept(ModItems.BOBIXUAN_EGGPLANT);
                    entries.accept(ModItems.BOBIXUAN_RADISH);
                    entries.accept(ModItems.BOBIXUAN_GREEN_ONION);
                    entries.accept(ModItems.BOBIXUAN_CILANTRO);
                    entries.accept(ModItems.BOBIXUAN_LETTUCE);
                    entries.accept(ModItems.BOBIXUAN_CELERY);
                    entries.accept(ModItems.BOBIXUAN_BELL_PEPPER);
                    entries.accept(ModItems.BOBIXUAN_PEANUT);
                    entries.accept(ModItems.BOBIXUAN_SESAME);
                    entries.accept(ModItems.BOBIXUAN_MUNG_BEAN);
                    entries.accept(ModItems.BOBIXUAN_STRAWBERRY);
                    entries.accept(ModItems.BOBIXUAN_BLUEBERRY);
                    entries.accept(ModItems.BOBIXUAN_PINEAPPLE);
                    entries.accept(ModItems.BOBIXUAN_GRAPE);
                    entries.accept(ModItems.BOBIXUAN_LEMON);
                    entries.accept(ModItems.BOBIXUAN_SWEET_POTATO);
                    entries.accept(ModItems.BOBIXUAN_TARO);
                    entries.accept(ModItems.BOBIXUAN_BAMBOO_SHOOT);
                    entries.accept(ModItems.BOBIXUAN_MINT);
                    entries.accept(ModItems.BOBIXUAN_ROSEMARY);
                    entries.accept(ModItems.BOBIXUAN_OYSTER_MUSHROOM);
                    entries.accept(ModItems.BOBIXUAN_SPINACH);
                    entries.accept(ModItems.BOBIXUAN_MUSTARD_GREEN);
                    entries.accept(ModItems.BOBIXUAN_SEAWEED);
                    entries.accept(ModItems.BOBIXUAN_BOK_CHOY);
                    entries.accept(ModItems.BOBIXUAN_SHIITAKE);
                    entries.accept(ModItems.BOBIXUAN_CHINESE_YAM);

                    ModItems.getProduceStorageItems().forEach(item -> entries.accept(item));

                    entries.accept(ModItems.BOBIXUAN_CORNMEAL);
                    entries.accept(ModItems.BOBIXUAN_TOFU);
                    entries.accept(ModItems.BOBIXUAN_CHILI_POWDER);
                    entries.accept(ModItems.BOBIXUAN_GARLIC_POWDER);
                    entries.accept(ModItems.BOBIXUAN_SOY_SAUCE);
                    entries.accept(ModItems.BOBIXUAN_SESAME_OIL);
                    entries.accept(ModItems.BOBIXUAN_PEANUT_OIL);
                    entries.accept(ModItems.BOBIXUAN_VINEGAR);
                    entries.accept(ModItems.BOBIXUAN_PEANUT_BUTTER);
                    entries.accept(ModItems.BOBIXUAN_SESAME_PASTE);
                    entries.accept(ModItems.BOBIXUAN_PICKLED_CUCUMBER);
                    entries.accept(ModItems.BOBIXUAN_MUNG_BEAN_SPROUTS);

                    entries.accept(ModItems.BOBIXUAN_CHILI_SAUCE);
                    entries.accept(ModItems.BOBIXUAN_GARLIC_SAUCE);
                    entries.accept(ModItems.BOBIXUAN_SWEET_SOUR_SAUCE);
                    entries.accept(ModItems.BOBIXUAN_FERMENTED_BEAN_PASTE);
                    entries.accept(ModItems.BOBIXUAN_GINGER_SOY_MARINADE);

                    entries.accept(ModItems.BOBIXUAN_DUMPLING_WRAPPER);
                    entries.accept(ModItems.BOBIXUAN_RAW_NOODLES);
                    entries.accept(ModItems.BOBIXUAN_TOFU_SKIN);
                    entries.accept(ModItems.BOBIXUAN_RICE_CAKE);
                    entries.accept(ModItems.BOBIXUAN_ZONGZI);
                    entries.accept(ModItems.BOBIXUAN_MOONCAKE);
                    entries.accept(ModItems.BOBIXUAN_SPRING_ROLL_WRAPPER);
                    entries.accept(ModItems.BOBIXUAN_ROCK_SUGAR);
                    entries.accept(ModItems.BOBIXUAN_FERMENTED_TOFU);
                    entries.accept(ModItems.BOBIXUAN_CENTURY_EGG);

                    entries.accept(ModItems.BOBIXUAN_GRILLED_CORN);
                    entries.accept(ModItems.BOBIXUAN_MAPO_TOFU);
                    entries.accept(ModItems.BOBIXUAN_KUNG_PAO_CHICKEN);
                    entries.accept(ModItems.BOBIXUAN_SWEET_SOUR_PORK);
                    entries.accept(ModItems.BOBIXUAN_BRAISED_EGGPLANT);
                    entries.accept(ModItems.BOBIXUAN_CUCUMBER_SALAD);
                    entries.accept(ModItems.BOBIXUAN_FRIED_RICE);
                    entries.accept(ModItems.BOBIXUAN_DUMPLINGS);
                    entries.accept(ModItems.BOBIXUAN_SPRING_ROLLS);
                    entries.accept(ModItems.BOBIXUAN_HAND_PULLED_NOODLES);
                    entries.accept(ModItems.BOBIXUAN_STIR_FRIED_NOODLES);
                    entries.accept(ModItems.BOBIXUAN_EGG_FRIED_RICE);
                    entries.accept(ModItems.BOBIXUAN_SCALLION_PANCAKE);
                    entries.accept(ModItems.BOBIXUAN_GARLIC_GREENS);
                    entries.accept(ModItems.BOBIXUAN_BRAISED_RADISH);
                    entries.accept(ModItems.BOBIXUAN_SPICY_PEANUTS);
                    entries.accept(ModItems.BOBIXUAN_EDAMAME);
                    entries.accept(ModItems.BOBIXUAN_POTSTICKERS);
                    entries.accept(ModItems.BOBIXUAN_ZHAJIANG_NOODLES);
                    entries.accept(ModItems.BOBIXUAN_SWEET_POTATO_BALLS);

                    entries.accept(ModItems.BOBIXUAN_SOY_MILK);
                    entries.accept(ModItems.BOBIXUAN_LEMONADE);
                    entries.accept(ModItems.BOBIXUAN_GRAPE_JUICE);
                    entries.accept(ModItems.BOBIXUAN_MINT_TEA);
                    entries.accept(ModItems.BOBIXUAN_GINGER_TEA);
                    entries.accept(ModItems.BOBIXUAN_STRAWBERRY_SMOOTHIE);
                    entries.accept(ModItems.BOBIXUAN_BLUEBERRY_JUICE);
                    entries.accept(ModItems.BOBIXUAN_TARO_BUBBLE_TEA);

                    entries.accept(ModItems.BOBIXUAN_HOT_POT);
                    entries.accept(ModItems.BOBIXUAN_PEKING_DUCK);
                    entries.accept(ModItems.BOBIXUAN_DUMPLING_FEAST);
                    entries.accept(ModItems.BOBIXUAN_BIG_PLATE_CHICKEN);
                    entries.accept(ModItems.BOBIXUAN_NEW_YEAR_FEAST);
                    entries.accept(ModItems.BOBIXUAN_BBQ_PLATTER);
                    entries.accept(ModItems.BOBIXUAN_FRUIT_PLATTER);
                    entries.accept(ModItems.BOBIXUAN_NOODLE_FEAST);
                    entries.accept(ModItems.BOBIXUAN_STONE_POT_RICE);
                    entries.accept(ModItems.BOBIXUAN_ROAST_SUCKLING_PIG);
                    entries.accept(ModItems.BOBIXUAN_DIM_SUM_BASKET);
                    entries.accept(ModItems.BOBIXUAN_SEAFOOD_PLATTER);
                    entries.accept(ModItems.BOBIXUAN_CANDY_BOX);
                    entries.accept(ModItems.BOBIXUAN_CHEESE_PLATTER);
                    entries.accept(ModItems.BOBIXUAN_HOT_SOUR_SOUP_POT);
                        })
                        .build()
        );

        LOGGER.info("Registered BobiDelight content (Fabric)");
    }
}
