package top.bobixuan.bobidelight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import top.bobixuan.bobidelight.block.ModBlocks;

public class BobiDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModBlocks.getCutoutBlocks().forEach(block ->
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout())
        );
    }
}