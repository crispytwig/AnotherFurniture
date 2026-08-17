package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.block.properties.VerticalConnectionType;
import com.starfish_studios.another_furniture.util.block.BlockPart;
import com.starfish_studios.another_furniture.util.block.HammerableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShutterBlock extends Block implements SimpleWaterloggedBlock, HammerableBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<VerticalConnectionType> VERTICAL = ModBlockStateProperties.VERTICAL_CONNECTION_TYPE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty VARIANT = ModBlockStateProperties.VARIANT;

    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{SOUTH, WEST, NORTH, EAST};

    public ShutterBlock(int defaultVariant, Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(VERTICAL, VerticalConnectionType.SINGLE)
                .setValue(OPEN, false)
                .setValue(VARIANT, defaultVariant)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // 3 = open + hinge left
        // 1 open + hinge right
        // 0 not open
        int shape = state.getValue(FACING).get2DDataValue() + (state.getValue(OPEN) ? (state.getValue(HINGE) == DoorHingeSide.LEFT ? 3 : 1) : 0);
        return SHAPES[shape % 4];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockState state = this.defaultBlockState().setValue(FACING, facing);
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Vec3 clickLocation = context.getClickLocation();

        state = state.setValue(HINGE, BlockPart.get1D(clickedPos, clickLocation, facing.getClockWise(), 2) == 0 ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT);
        boolean powered = level.hasNeighborSignal(clickedPos);

        return state
                .setValue(OPEN, powered)
                .setValue(POWERED, powered)
                .setValue(VERTICAL, getType(state, level.getBlockState(clickedPos.above()), level.getBlockState(clickedPos.below())))
                .setValue(WATERLOGGED, level.getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final @Nullable Orientation orientation, final boolean movedByPiston) {
        if (level.isClientSide()) return;

        boolean powered = level.hasNeighborSignal(pos);
        BlockState newState = state;
        if (powered != newState.getValue(POWERED)) {
            if (newState.getValue(OPEN) != powered) {
                newState = newState.setValue(OPEN, powered);
                toggleShutters(newState.setValue(OPEN, !powered), level, pos, null);
            }
            newState = newState.setValue(POWERED, powered);
            if (newState.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        }
        VerticalConnectionType type = getType(newState, level.getBlockState(pos.above()), level.getBlockState(pos.below()));
        newState = newState.setValue(VERTICAL, type);
        level.setBlock(pos, newState, 3);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (tryHammerBlock(VARIANT, stack, state, level, pos, player)) return InteractionResult.SUCCESS;
        return InteractionResult.PASS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return toggleShutters(state, level, pos, player);
    }

    public InteractionResult toggleShutters(BlockState state, Level level, BlockPos pos, Player player) {
        state = state.cycle(OPEN);
        level.setBlock(pos, state, 3);
        if (player == null || !player.isCrouching()) toggleShutters(state, level, pos, state.getValue(OPEN));
        level.playSound(null, pos, shutterSound(state.getValue(OPEN)), SoundSource.BLOCKS, 1.0F, 1.0F);

        if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }

//    public void toggleShuttles(BlockState state, Level level, BlockPos pos) {
//        boolean open = !state.getValue(OPEN);
//        Direction facing = state.getValue(FACING);
//        Direction relativeLeft = facing.getClockWise();
//        Direction relativeRight = facing.getCounterClockWise();
//
//        toggleVertical(state, level, pos, open);
//        toggleHorizontal(state, level, pos, open, facing, relativeLeft);
//        toggleHorizontal(state, level, pos, open, facing, relativeRight);
//    }

    public void toggleShutters(BlockState state, Level level, BlockPos pos, boolean open) {
        BlockState updateState = state;
        BlockPos updatePos = pos;
        if (state.getValue(VERTICAL) == VerticalConnectionType.MIDDLE || state.getValue(VERTICAL) == VerticalConnectionType.BOTTOM) {
            int heightUp = level.dimensionType().height() - updatePos.getY();
            for (int i = 0; i < heightUp; i++) {
                BlockState above = level.getBlockState(updatePos.above());
                if (above.is(state.getBlock()) && above.getValue(FACING) == updateState.getValue(FACING) && above.getValue(HINGE) == updateState.getValue(HINGE) && above.getValue(OPEN) != open) {
                    updateState = above;
                    updatePos = updatePos.above();
                    level.setBlock(updatePos, updateState.setValue(OPEN, open), 3);
                } else {
                    break;
                }
            }
        }
        if (state.getValue(VERTICAL) == VerticalConnectionType.MIDDLE || state.getValue(VERTICAL) == VerticalConnectionType.TOP) {
            updateState = state;
            updatePos = pos;
            int heightDown = level.dimensionType().minY() - updatePos.getY();
            heightDown = (heightDown < 0) ? -heightDown : heightDown;
            for (int i = 0; i < heightDown; i++) {
                BlockState below = level.getBlockState(updatePos.below());
                if (below.is(state.getBlock()) && below.getValue(FACING) == updateState.getValue(FACING) && below.getValue(HINGE) == updateState.getValue(HINGE) && below.getValue(OPEN) != open) {
                    updateState = below;
                    updatePos = updatePos.below();
                    level.setBlock(updatePos, updateState.setValue(OPEN, open), 3);
                } else {
                    break;
                }
            }
        }
    }

//    public void flip(BlockState state, Level level, BlockPos pos) {
//        level.scheduleTick(pos, state.getBlock(), 2);
//    }
//
//    @Override
//    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        toggleShutters();
//    }

    public static SoundEvent shutterSound(boolean open) {
        return open ? SoundEvents.WOODEN_TRAPDOOR_OPEN : SoundEvents.WOODEN_TRAPDOOR_CLOSE;
    }

    public VerticalConnectionType getType(BlockState state, BlockState above, BlockState below) {
        boolean up = isConnected(state, above);
        boolean down = isConnected(state, below);

        if (up && down) return VerticalConnectionType.MIDDLE;
        else if (up) return VerticalConnectionType.BOTTOM;
        else if (down) return VerticalConnectionType.TOP;
        return VerticalConnectionType.SINGLE;
    }

    public boolean isConnected(BlockState state, BlockState other) {
        return other.is(state.getBlock())
                //&& other.getValue(VERTICAL) == state.getValue(VERTICAL)
                && other.getValue(FACING) == state.getValue(FACING)
                && other.getValue(OPEN) == state.getValue(OPEN)
                && other.getValue(HINGE) == state.getValue(HINGE)
                && other.getValue(VARIANT).equals(state.getValue(VARIANT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, VERTICAL, OPEN, HINGE, VARIANT, POWERED, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos currentPos, final Direction direction, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, level, ticks, currentPos, direction, neighbourPos, neighbourState, random);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
