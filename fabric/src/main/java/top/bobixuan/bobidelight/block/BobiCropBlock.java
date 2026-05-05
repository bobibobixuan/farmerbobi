package top.bobixuan.bobidelight.block;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class BobiCropBlock extends CropBlock {
    private final Supplier<? extends ItemLike> seedSupplier;

    public BobiCropBlock(Supplier<? extends ItemLike> seedSupplier) {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY));
        this.seedSupplier = seedSupplier;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return seedSupplier.get();
    }
}
