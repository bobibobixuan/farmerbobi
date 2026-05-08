package top.bobixuan.bobidelight.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import top.bobixuan.bobidelight.item.ModItems;

public class ModWorldGen {
    public static void init() {
        registerWildCrop("corn", BiomeTags.IS_SAVANNA, BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("soybean", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("chili_pepper", BiomeTags.IS_SAVANNA, BiomeTags.IS_BADLANDS);
        registerWildCrop("garlic", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("ginger", BiomeTags.IS_JUNGLE);
        registerWildCrop("cucumber", BiomeTags.IS_FOREST, BiomeTags.IS_RIVER, BiomeTags.IS_HILL);
        registerWildCrop("eggplant", BiomeTags.IS_SAVANNA, BiomeTags.IS_FOREST);
        registerWildCrop("radish", BiomeTags.IS_TAIGA, BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("green_onion", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("cilantro", BiomeTags.IS_FOREST, BiomeTags.IS_RIVER, BiomeTags.IS_HILL);
        registerWildCrop("lettuce", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("celery", BiomeTags.IS_RIVER);
        registerWildCrop("bell_pepper", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("peanut", BiomeTags.IS_SAVANNA, BiomeTags.IS_BADLANDS);
        registerWildCrop("sesame", BiomeTags.IS_SAVANNA, BiomeTags.IS_BADLANDS);
        registerWildCrop("mung_bean", BiomeTags.IS_SAVANNA, BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("strawberry", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("blueberry", BiomeTags.IS_TAIGA);
        registerWildCrop("pineapple", BiomeTags.IS_JUNGLE);
        registerWildCrop("grape", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("lemon", BiomeTags.IS_SAVANNA, BiomeTags.IS_JUNGLE);
        registerWildCrop("sweet_potato", BiomeTags.IS_SAVANNA, BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("taro", BiomeTags.IS_JUNGLE, BiomeTags.IS_RIVER);
        registerWildCrop("bamboo_shoot", BiomeTags.IS_JUNGLE);
        registerWildCrop("mint", BiomeTags.IS_FOREST, BiomeTags.IS_RIVER, BiomeTags.IS_HILL);
        registerWildCrop("rosemary", BiomeTags.IS_SAVANNA, BiomeTags.IS_FOREST);
        registerWildCrop("oyster_mushroom", BiomeTags.IS_FOREST);
        registerWildCrop("spinach", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("mustard_green", BiomeTags.IS_TAIGA, BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("seaweed", BiomeTags.IS_OCEAN, BiomeTags.IS_BEACH);
        registerWildCrop("bok_choy", BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
        registerWildCrop("shiitake", BiomeTags.IS_FOREST);
        registerWildCrop("chinese_yam", BiomeTags.IS_SAVANNA, BiomeTags.IS_FOREST, BiomeTags.IS_HILL);
    }

    @SafeVarargs
    private static void registerWildCrop(String name, TagKey<Biome>... biomeTags) {
        ResourceKey<PlacedFeature> featureKey = ResourceKey.create(
                Registries.PLACED_FEATURE,
                new ResourceLocation(ModItems.MOD_ID, "bobixuan_wild_" + name + "_placed")
        );

        for (TagKey<Biome> tag : biomeTags) {
            BiomeModifications.addFeature(
                    BiomeSelectors.tag(tag),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    featureKey
            );
        }
    }
}
