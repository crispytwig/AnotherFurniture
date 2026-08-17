package com.starfish_studios.another_furniture.block;

import com.mojang.serialization.MapCodec;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.util.block.BlockPart;
import com.starfish_studios.another_furniture.util.block.ShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SmallShelfBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<SmallShelfBlock> CODEC = simpleCodec(SmallShelfBlock::new);
    public MapCodec<SmallShelfBlock> codec() {
        return CODEC;
    }

    public static final EnumProperty<HorizontalConnectionType> TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final EnumProperty<SlabType> HALF = ModBlockStateProperties.HALF;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape TOP_AABB_NORTH = Block.box(0, 14, 8, 16, 16, 16);
    protected static final VoxelShape BOTTOM_AABB_NORTH = Block.box(0, 6, 8, 16, 8, 16);
    protected static final VoxelShape DOUBLE_AABB_NORTH = Shapes.or(TOP_AABB_NORTH, BOTTOM_AABB_NORTH);

    protected static final VoxelShape TOP_AABB_EAST = ShapeUtil.rotateShape(TOP_AABB_NORTH, Direction.EAST);
    protected static final VoxelShape BOTTOM_AABB_EAST = ShapeUtil.rotateShape(BOTTOM_AABB_NORTH, Direction.EAST);
    protected static final VoxelShape DOUBLE_AABB_EAST = ShapeUtil.rotateShape(DOUBLE_AABB_NORTH, Direction.EAST);

    protected static final VoxelShape TOP_AABB_SOUTH = ShapeUtil.rotateShape(TOP_AABB_NORTH, Direction.SOUTH);
    protected static final VoxelShape BOTTOM_AABB_SOUTH = ShapeUtil.rotateShape(BOTTOM_AABB_NORTH, Direction.SOUTH);
    protected static final VoxelShape DOUBLE_AABB_SOUTH = ShapeUtil.rotateShape(DOUBLE_AABB_NORTH, Direction.SOUTH);

    protected static final VoxelShape TOP_AABB_WEST = ShapeUtil.rotateShape(TOP_AABB_NORTH, Direction.WEST);
    protected static final VoxelShape BOTTOM_AABB_WEST = ShapeUtil.rotateShape(BOTTOM_AABB_NORTH, Direction.WEST);
    protected static final VoxelShape DOUBLE_AABB_WEST = ShapeUtil.rotateShape(DOUBLE_AABB_NORTH, Direction.WEST);

//    protected static final VoxelShape NL = Block.box(12, 6, 6, 16, 14, 16);
//    protected static final VoxelShape NR = Block.box(0, 6, 6, 4, 14, 16);
//
//    protected static final VoxelShape T_NL = Shapes.or(TOP, NL);
//    protected static final VoxelShape T_NR = Shapes.or(TOP, NR);
//    protected static final VoxelShape T_NLR = Shapes.or(TOP, NL, NR);
//    protected static final VoxelShape T_EL = ShapeUtil.rotateShape(T_NL, Direction.EAST);
//    protected static final VoxelShape T_ER = ShapeUtil.rotateShape(T_NR, Direction.EAST);
//    protected static final VoxelShape T_ELR = ShapeUtil.rotateShape(T_NLR, Direction.EAST);
//    protected static final VoxelShape T_SL = ShapeUtil.rotateShape(T_NL, Direction.SOUTH);
//    protected static final VoxelShape T_SR = ShapeUtil.rotateShape(T_NR, Direction.SOUTH);
//    protected static final VoxelShape T_SLR = ShapeUtil.rotateShape(T_NLR, Direction.SOUTH);
//    protected static final VoxelShape T_WL = ShapeUtil.rotateShape(T_NL, Direction.WEST);
//    protected static final VoxelShape T_WR = ShapeUtil.rotateShape(T_NLR, Direction.WEST);
//    protected static final VoxelShape T_WLR = ShapeUtil.rotateShape(T_NLR, Direction.WEST);

    public SmallShelfBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TYPE, HorizontalConnectionType.SINGLE)
                .setValue(HALF, SlabType.BOTTOM)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        ItemStack itemStack = useContext.getItemInHand();
        SlabType slabType = state.getValue(HALF);
        if (slabType == SlabType.DOUBLE || !itemStack.is(this.asItem())) return false;

        if (!useContext.replacingClickedOnBlock()) return true;

        boolean bl = useContext.getClickLocation().y - (double)useContext.getClickedPos().getY() > 0.5;
        Direction direction = useContext.getClickedFace();
        if (slabType == SlabType.BOTTOM) return direction == Direction.UP || bl && direction.getAxis().isHorizontal();

        return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        //if (hit.getDirection() != Direction.UP) return InteractionResult.PASS;
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof ShelfBlockEntity shelfBE)) return ItemInteractionResult.FAIL;

        Direction facing = state.getValue(FACING);
        int slot = BlockPart.get2D(pos, hitResult.getLocation(), Direction.UP, facing.getClockWise(), 2, 2);

        // Place
        if (!stack.isEmpty()) {//todo switch to #Block.useWithoutItem
            if (!level.isClientSide && shelfBE.placeItem(player.getAbilities().instabuild ? stack.copy() : stack, slot)) {
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                return ItemInteractionResult.SUCCESS;
            }
            // Avoids client trying to place actual block on top
            return ItemInteractionResult.CONSUME;
        }

        // Remove
        //todo switch to #Block.useWithoutItem
        if (shelfBE.removeItem(slot, player, level)) return ItemInteractionResult.SUCCESS;

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock())) return;

        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ShelfBlockEntity shelfBE) Containers.dropContents(level, pos, shelfBE.getItems());
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        HorizontalConnectionType type = state.getValue(TYPE);
        SlabType half = state.getValue(HALF);



        if (direction == Direction.NORTH) {
            return switch (half) {
                case TOP -> TOP_AABB_NORTH;
                case DOUBLE -> DOUBLE_AABB_NORTH;
                default -> BOTTOM_AABB_NORTH;
            };
        } else if (direction == Direction.EAST) {
            return switch (half) {
                case TOP -> TOP_AABB_EAST;
                case DOUBLE -> DOUBLE_AABB_EAST;
                default -> BOTTOM_AABB_EAST;
            };
        } else if (direction == Direction.SOUTH) {
            return switch (half) {
                case TOP -> TOP_AABB_SOUTH;
                case DOUBLE -> DOUBLE_AABB_SOUTH;
                default -> BOTTOM_AABB_SOUTH;
            };
        } else {
            return switch (half) {
                case TOP -> TOP_AABB_WEST;
                case DOUBLE -> DOUBLE_AABB_WEST;
                default -> BOTTOM_AABB_WEST;
            };
        }
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        BlockState stateExisting = context.getLevel().getBlockState(clickedPos);

        if (stateExisting.is(this)) return stateExisting.setValue(HALF, SlabType.DOUBLE);

        boolean waterlogged = context.getLevel().getFluidState(clickedPos).getType() == Fluids.WATER;
        BlockState state = this.defaultBlockState()
                .setValue(HALF, SlabType.BOTTOM)
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
        Direction direction = context.getClickedFace();
        return direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)clickedPos.getY() > 0.5)) ? state : state.setValue(HALF, SlabType.TOP);

    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        BlockState above = level.getBlockState(currentPos.above());
        if (direction == Direction.UP && (above.isFaceSturdy(level, currentPos, Direction.DOWN) && !above.getVisualShape(level, currentPos.above(), CollisionContext.empty()).isEmpty())) {
            if (level.getBlockEntity(currentPos) instanceof ShelfBlockEntity shelfBE) shelfBE.removeAllItems();
        }

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        Direction dirL = facing.getClockWise();
        Direction dirR = facing.getCounterClockWise();
        if (direction != dirL && direction != dirR) return state;

        BlockState stateL = level.getBlockState(currentPos.relative(dirL));
        BlockState stateR = level.getBlockState(currentPos.relative(dirR));
        boolean sideL = stateL.getBlock() instanceof SmallShelfBlock && stateL.getValue(BlockStateProperties.HORIZONTAL_FACING) == facing;
        boolean sideR = stateR.getBlock() instanceof SmallShelfBlock && stateR.getValue(BlockStateProperties.HORIZONTAL_FACING) == facing;
        HorizontalConnectionType type = sideL && sideR ?
                HorizontalConnectionType.MIDDLE
                : (sideR ? HorizontalConnectionType.LEFT
                : (sideL ? HorizontalConnectionType.RIGHT
                : HorizontalConnectionType.SINGLE));
        return state.setValue(TYPE, type);
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShelfBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, HALF, WATERLOGGED);
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

