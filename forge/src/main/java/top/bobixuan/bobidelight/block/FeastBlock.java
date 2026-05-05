package top.bobixuan.bobidelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class FeastBlock extends Block {
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 3);
    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);

    private final FoodProperties food;
    private final Supplier<? extends Item> containerSupplier;

    public FeastBlock(FoodProperties food, Supplier<? extends Item> containerSupplier) {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .sound(SoundType.WOOD)
                .strength(0.5f)
                .pushReaction(PushReaction.DESTROY));
        this.food = food;
        this.containerSupplier = containerSupplier;
        this.registerDefaultState(this.stateDefinition.any().setValue(SERVINGS, 3));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SERVINGS);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide) {
            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
            return InteractionResult.SUCCESS;
        }

        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        }

        return this.eat(level, pos, state, player);
    }

    private InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        }

        int servings = state.getValue(SERVINGS);

        player.getFoodData().eat(food.getNutrition(), food.getSaturationModifier());
        level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1.0F, 1.0F);

        int newServings = servings - 1;
        if (newServings <= 0) {
            level.removeBlock(pos, false);
            if (containerSupplier != null) {
                Item container = containerSupplier.get();
                if (container != null) {
                    popResource((Level) level, pos, new ItemStack(container));
                }
            }
        } else {
            level.setBlock(pos, state.setValue(SERVINGS, newServings), 3);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }
}
