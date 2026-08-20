package com.starfish_studios.another_furniture.block;

import com.mojang.serialization.MapCodec;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.util.block.BlockPart;
import com.starfish_studios.another_furniture.util.block.ShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<ShelfBlock> CODEC = simpleCodec(ShelfBlock::new);
    public MapCodec<ShelfBlock> codec() {
        return CODEC;
    }
    public static final EnumProperty<HorizontalConnectionType> TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape TOP = Block.box(0, 14, 0.0, 16, 16, 16);
    protected static final VoxelShape NL = Block.box(12, 6, 6, 16, 14, 16);
    protected static final VoxelShape NR = Block.box(0, 6, 6, 4, 14, 16);

    protected static final VoxelShape T_NL = Shapes.or(TOP, NL);
    protected static final VoxelShape T_NR = Shapes.or(TOP, NR);
    protected static final VoxelShape T_NLR = Shapes.or(TOP, NL, NR);
    protected static final VoxelShape T_EL = ShapeUtil.rotateShape(T_NL, Direction.EAST);
    protected static final VoxelShape T_ER = ShapeUtil.rotateShape(T_NR, Direction.EAST);
    protected static final VoxelShape T_ELR = ShapeUtil.rotateShape(T_NLR, Direction.EAST);
    protected static final VoxelShape T_SL = ShapeUtil.rotateShape(T_NL, Direction.SOUTH);
    protected static final VoxelShape T_SR = ShapeUtil.rotateShape(T_NR, Direction.SOUTH);
    protected static final VoxelShape T_SLR = ShapeUtil.rotateShape(T_NLR, Direction.SOUTH);
    protected static final VoxelShape T_WL = ShapeUtil.rotateShape(T_NL, Direction.WEST);
    protected static final VoxelShape T_WR = ShapeUtil.rotateShape(T_NLR, Direction.WEST);
    protected static final VoxelShape T_WLR = ShapeUtil.rotateShape(T_NLR, Direction.WEST);

    public ShelfBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (hitResult.getDirection() != Direction.UP) return InteractionResult.TRY_WITH_EMPTY_HAND;
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfBE)) return InteractionResult.FAIL;

        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get2D(pos, hitResult.getLocation(), facing.getClockWise(), facing, 2, 2);

        if (shelfBE.placeItem(player.getAbilities().instabuild ? stack.copy() : stack, slot)) {
            level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
         }
        

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (hitResult.getDirection() != Direction.UP) return InteractionResult.FAIL;
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfBE)) return InteractionResult.FAIL;

        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get2D(pos, hitResult.getLocation(), facing.getClockWise(), facing, 2, 2);

        if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            ItemStack stack = shelfBE.removeItem(slot);
            if (!stack.isEmpty()) {
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                player.setItemInHand(InteractionHand.MAIN_HAND, stack);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.CONSUME;
    }

    /*
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock())) return;

        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ShelfBlockEntity shelfBE) Containers.dropContents(level, pos, shelfBE.getItems());
        super.onRemove(state, level, pos, newState, isMoving);
    }
    */

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        HorizontalConnectionType type = state.getValue(TYPE);

        if (direction == Direction.NORTH) {
            return switch (type) {
                case SINGLE -> T_NLR;
                case LEFT -> T_NL;
                case RIGHT -> T_NR;
                default -> TOP;
            };
        } else if (direction == Direction.EAST) {
            return switch (type) {
                case SINGLE -> T_ELR;
                case LEFT -> T_EL;
                case RIGHT -> T_ER;
                default -> TOP;
            };
        } else if (direction == Direction.SOUTH) {
            return switch (type) {
                case SINGLE -> T_SLR;
                case LEFT -> T_SL;
                case RIGHT -> T_SR;
                default -> TOP;
            };
        } else {
            return switch (type) {
                case SINGLE -> T_WLR;
                case LEFT -> T_WL;
                case RIGHT -> T_WR;
                default -> TOP;
            };
        }
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos currentPos, final Direction direction, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        BlockState above = level.getBlockState(currentPos.above());
        if (direction == Direction.UP && (above.isFaceSturdy(level, currentPos, Direction.DOWN) && !above.getVisualShape(level, currentPos.above(), CollisionContext.empty()).isEmpty())) {
            if (level.getBlockEntity(currentPos) instanceof ShelfBlockEntity shelfBE) shelfBE.removeAllItems();
        }

        Direction facing = state.getValue(FACING);
        Direction dirL = facing.getClockWise();
        Direction dirR = facing.getCounterClockWise();
        if (direction != dirL && direction != dirR) return state;

        BlockState l_state = level.getBlockState(currentPos.relative(dirL));
        BlockState r_state = level.getBlockState(currentPos.relative(dirR));
        boolean l_side = l_state.getBlock() instanceof ShelfBlock && l_state.getValue(FACING) == facing;
        boolean r_side = r_state.getBlock() instanceof ShelfBlock && r_state.getValue(FACING) == facing;
        HorizontalConnectionType type = l_side && r_side ?
                HorizontalConnectionType.MIDDLE
                : (r_side ? HorizontalConnectionType.LEFT
                : (l_side ? HorizontalConnectionType.RIGHT
                : HorizontalConnectionType.SINGLE));
        return state.setValue(TYPE, type);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
	protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
		Containers.updateNeighboursAfterDestroy(state, level, pos);
	}

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
}

