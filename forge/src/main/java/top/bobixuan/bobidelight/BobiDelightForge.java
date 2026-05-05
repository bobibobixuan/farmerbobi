package top.bobixuan.bobidelight;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.bobixuan.bobidelight.block.ModBlocks;
import top.bobixuan.bobidelight.item.ModItems;

@Mod(ModItems.MOD_ID)
public class BobiDelightForge {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModItems.MOD_ID);

    public static final CreativeModeTab CREATIVE_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ModItems.BOBIXUAN_PIZZA))
            .title(Component.translatable("itemGroup.bobidelight.bobixuan"))
            .displayItems((params, output) -> {
                output.accept(ModItems.BOBIXUAN_PIZZA);
                output.accept(ModItems.BOBIXUAN_HAMBURGER);
                output.accept(ModItems.BOBIXUAN_ICE_CREAM);
                output.accept(ModItems.BOBIXUAN_CHICKEN_WINGS);
                output.accept(ModItems.BOBIXUAN_FRIES);

                output.accept(ModItems.BOBIXUAN_CORN_SEEDS);
                output.accept(ModItems.BOBIXUAN_SOYBEAN_SEEDS);
                output.accept(ModItems.BOBIXUAN_CHILI_PEPPER_SEEDS);
                output.accept(ModItems.BOBIXUAN_GARLIC_SEEDS);
                output.accept(ModItems.BOBIXUAN_GINGER_SEEDS);
                output.accept(ModItems.BOBIXUAN_CUCUMBER_SEEDS);
                output.accept(ModItems.BOBIXUAN_EGGPLANT_SEEDS);
                output.accept(ModItems.BOBIXUAN_RADISH_SEEDS);
                output.accept(ModItems.BOBIXUAN_GREEN_ONION_SEEDS);
                output.accept(ModItems.BOBIXUAN_CILANTRO_SEEDS);
                output.accept(ModItems.BOBIXUAN_LETTUCE_SEEDS);
                output.accept(ModItems.BOBIXUAN_CELERY_SEEDS);
                output.accept(ModItems.BOBIXUAN_BELL_PEPPER_SEEDS);
                output.accept(ModItems.BOBIXUAN_PEANUT_SEEDS);
                output.accept(ModItems.BOBIXUAN_SESAME_SEEDS);
                output.accept(ModItems.BOBIXUAN_MUNG_BEAN_SEEDS);
                output.accept(ModItems.BOBIXUAN_STRAWBERRY_SEEDS);
                output.accept(ModItems.BOBIXUAN_BLUEBERRY_SEEDS);
                output.accept(ModItems.BOBIXUAN_PINEAPPLE_SEEDS);
                output.accept(ModItems.BOBIXUAN_GRAPE_SEEDS);
                output.accept(ModItems.BOBIXUAN_LEMON_SEEDS);
                output.accept(ModItems.BOBIXUAN_SWEET_POTATO_SEEDS);
                output.accept(ModItems.BOBIXUAN_TARO_SEEDS);
                output.accept(ModItems.BOBIXUAN_BAMBOO_SHOOT_SEEDS);
                output.accept(ModItems.BOBIXUAN_MINT_SEEDS);
                output.accept(ModItems.BOBIXUAN_ROSEMARY_SEEDS);
                output.accept(ModItems.BOBIXUAN_OYSTER_MUSHROOM_SEEDS);
                output.accept(ModItems.BOBIXUAN_SPINACH_SEEDS);
                output.accept(ModItems.BOBIXUAN_MUSTARD_GREEN_SEEDS);
                output.accept(ModItems.BOBIXUAN_SEAWEED_SEEDS);
                output.accept(ModItems.BOBIXUAN_BOK_CHOY_SEEDS);
                output.accept(ModItems.BOBIXUAN_SHIITAKE_SEEDS);
                output.accept(ModItems.BOBIXUAN_CHINESE_YAM_SEEDS);

                output.accept(ModItems.BOBIXUAN_CORN);
                output.accept(ModItems.BOBIXUAN_SOYBEAN);
                output.accept(ModItems.BOBIXUAN_CHILI_PEPPER);
                output.accept(ModItems.BOBIXUAN_GARLIC);
                output.accept(ModItems.BOBIXUAN_GINGER);
                output.accept(ModItems.BOBIXUAN_CUCUMBER);
                output.accept(ModItems.BOBIXUAN_EGGPLANT);
                output.accept(ModItems.BOBIXUAN_RADISH);
                output.accept(ModItems.BOBIXUAN_GREEN_ONION);
                output.accept(ModItems.BOBIXUAN_CILANTRO);
                output.accept(ModItems.BOBIXUAN_LETTUCE);
                output.accept(ModItems.BOBIXUAN_CELERY);
                output.accept(ModItems.BOBIXUAN_BELL_PEPPER);
                output.accept(ModItems.BOBIXUAN_PEANUT);
                output.accept(ModItems.BOBIXUAN_SESAME);
                output.accept(ModItems.BOBIXUAN_MUNG_BEAN);
                output.accept(ModItems.BOBIXUAN_STRAWBERRY);
                output.accept(ModItems.BOBIXUAN_BLUEBERRY);
                output.accept(ModItems.BOBIXUAN_PINEAPPLE);
                output.accept(ModItems.BOBIXUAN_GRAPE);
                output.accept(ModItems.BOBIXUAN_LEMON);
                output.accept(ModItems.BOBIXUAN_SWEET_POTATO);
                output.accept(ModItems.BOBIXUAN_TARO);
                output.accept(ModItems.BOBIXUAN_BAMBOO_SHOOT);
                output.accept(ModItems.BOBIXUAN_MINT);
                output.accept(ModItems.BOBIXUAN_ROSEMARY);
                output.accept(ModItems.BOBIXUAN_OYSTER_MUSHROOM);
                output.accept(ModItems.BOBIXUAN_SPINACH);
                output.accept(ModItems.BOBIXUAN_MUSTARD_GREEN);
                output.accept(ModItems.BOBIXUAN_SEAWEED);
                output.accept(ModItems.BOBIXUAN_BOK_CHOY);
                output.accept(ModItems.BOBIXUAN_SHIITAKE);
                output.accept(ModItems.BOBIXUAN_CHINESE_YAM);

                ModItems.getProduceStorageItems().forEach(item -> output.accept(item));

                output.accept(ModItems.BOBIXUAN_CORNMEAL);
                output.accept(ModItems.BOBIXUAN_TOFU);
                output.accept(ModItems.BOBIXUAN_CHILI_POWDER);
                output.accept(ModItems.BOBIXUAN_GARLIC_POWDER);
                output.accept(ModItems.BOBIXUAN_SOY_SAUCE);
                output.accept(ModItems.BOBIXUAN_SESAME_OIL);
                output.accept(ModItems.BOBIXUAN_PEANUT_OIL);
                output.accept(ModItems.BOBIXUAN_VINEGAR);
                output.accept(ModItems.BOBIXUAN_PEANUT_BUTTER);
                output.accept(ModItems.BOBIXUAN_SESAME_PASTE);
                output.accept(ModItems.BOBIXUAN_PICKLED_CUCUMBER);
                output.accept(ModItems.BOBIXUAN_MUNG_BEAN_SPROUTS);

                output.accept(ModItems.BOBIXUAN_CHILI_SAUCE);
                output.accept(ModItems.BOBIXUAN_GARLIC_SAUCE);
                output.accept(ModItems.BOBIXUAN_SWEET_SOUR_SAUCE);
                output.accept(ModItems.BOBIXUAN_FERMENTED_BEAN_PASTE);
                output.accept(ModItems.BOBIXUAN_GINGER_SOY_MARINADE);

                output.accept(ModItems.BOBIXUAN_DUMPLING_WRAPPER);
                output.accept(ModItems.BOBIXUAN_RAW_NOODLES);
                output.accept(ModItems.BOBIXUAN_TOFU_SKIN);
                output.accept(ModItems.BOBIXUAN_RICE_CAKE);
                output.accept(ModItems.BOBIXUAN_ZONGZI);
                output.accept(ModItems.BOBIXUAN_MOONCAKE);
                output.accept(ModItems.BOBIXUAN_SPRING_ROLL_WRAPPER);
                output.accept(ModItems.BOBIXUAN_ROCK_SUGAR);
                output.accept(ModItems.BOBIXUAN_FERMENTED_TOFU);
                output.accept(ModItems.BOBIXUAN_CENTURY_EGG);

                output.accept(ModItems.BOBIXUAN_GRILLED_CORN);
                output.accept(ModItems.BOBIXUAN_MAPO_TOFU);
                output.accept(ModItems.BOBIXUAN_KUNG_PAO_CHICKEN);
                output.accept(ModItems.BOBIXUAN_SWEET_SOUR_PORK);
                output.accept(ModItems.BOBIXUAN_BRAISED_EGGPLANT);
                output.accept(ModItems.BOBIXUAN_CUCUMBER_SALAD);
                output.accept(ModItems.BOBIXUAN_FRIED_RICE);
                output.accept(ModItems.BOBIXUAN_DUMPLINGS);
                output.accept(ModItems.BOBIXUAN_SPRING_ROLLS);
                output.accept(ModItems.BOBIXUAN_HAND_PULLED_NOODLES);
                output.accept(ModItems.BOBIXUAN_STIR_FRIED_NOODLES);
                output.accept(ModItems.BOBIXUAN_EGG_FRIED_RICE);
                output.accept(ModItems.BOBIXUAN_SCALLION_PANCAKE);
                output.accept(ModItems.BOBIXUAN_GARLIC_GREENS);
                output.accept(ModItems.BOBIXUAN_BRAISED_RADISH);
                output.accept(ModItems.BOBIXUAN_SPICY_PEANUTS);
                output.accept(ModItems.BOBIXUAN_EDAMAME);
                output.accept(ModItems.BOBIXUAN_POTSTICKERS);
                output.accept(ModItems.BOBIXUAN_ZHAJIANG_NOODLES);
                output.accept(ModItems.BOBIXUAN_SWEET_POTATO_BALLS);

                output.accept(ModItems.BOBIXUAN_SOY_MILK);
                output.accept(ModItems.BOBIXUAN_LEMONADE);
                output.accept(ModItems.BOBIXUAN_GRAPE_JUICE);
                output.accept(ModItems.BOBIXUAN_MINT_TEA);
                output.accept(ModItems.BOBIXUAN_GINGER_TEA);
                output.accept(ModItems.BOBIXUAN_STRAWBERRY_SMOOTHIE);
                output.accept(ModItems.BOBIXUAN_BLUEBERRY_JUICE);
                output.accept(ModItems.BOBIXUAN_TARO_BUBBLE_TEA);

                output.accept(ModItems.BOBIXUAN_HOT_POT);
                output.accept(ModItems.BOBIXUAN_PEKING_DUCK);
                output.accept(ModItems.BOBIXUAN_DUMPLING_FEAST);
                output.accept(ModItems.BOBIXUAN_BIG_PLATE_CHICKEN);
                output.accept(ModItems.BOBIXUAN_NEW_YEAR_FEAST);
                output.accept(ModItems.BOBIXUAN_BBQ_PLATTER);
                output.accept(ModItems.BOBIXUAN_FRUIT_PLATTER);
                output.accept(ModItems.BOBIXUAN_NOODLE_FEAST);
                output.accept(ModItems.BOBIXUAN_STONE_POT_RICE);
                output.accept(ModItems.BOBIXUAN_ROAST_SUCKLING_PIG);
                output.accept(ModItems.BOBIXUAN_DIM_SUM_BASKET);
                output.accept(ModItems.BOBIXUAN_SEAFOOD_PLATTER);
                output.accept(ModItems.BOBIXUAN_CANDY_BOX);
                output.accept(ModItems.BOBIXUAN_CHEESE_PLATTER);
                output.accept(ModItems.BOBIXUAN_HOT_SOUR_SOUP_POT);
            })
            .build();

    public BobiDelightForge() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onRegister);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::onClientSetup);
        }

        ModItems.BOBIXUAN_PIZZA.getClass();
        ModBlocks.init();

        LOGGER.info("Registered BobiDelight content (Forge)");
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
            event.register(
                    Registries.CREATIVE_MODE_TAB,
                    new ResourceLocation(ModItems.MOD_ID, "bobixuan_group"),
                    () -> CREATIVE_TAB
            );
        }
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> ModBlocks.getCutoutBlocks().forEach(block ->
                ItemBlockRenderTypes.setRenderLayer(block, renderType -> renderType == RenderType.cutout())
        ));
    }
}
