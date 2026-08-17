package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CurtainBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = ModBlockStateProperties.HORIZONTAL_CONNECTION_TYPE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<Direction> VERTICAL_CONNECTION_TYPE = ModBlockStateProperties.VERTICAL_CONNECTION_TYPE_UP_DOWN;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape WEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);



    public CurtainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.SINGLE)
                .setValue(VERTICAL_CONNECTION_TYPE, Direction.UP)
                .setValue(OPEN, false)
                //.setValue(POWERED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        HorizontalConnectionType type = state.getValue(HORIZONTAL_CONNECTION_TYPE);
        if (state.getValue(OPEN) && (type == HorizontalConnectionType.SINGLE || type == HorizontalConnectionType.MIDDLE)
                && state.getValue(VERTICAL_CONNECTION_TYPE) == Direction.DOWN) return Shapes.empty();
        return switch (state.getValue(FACING)) {
            default -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? Shapes.empty() : super.getCollisionShape(state, level, pos, context);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (!level.getBlockState(pos.below()).canBeReplaced(context) || level.isOutsideBuildHeight(pos.below())) return null;

        Direction facing = context.getClickedFace();

        BlockPos clickedPos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        BlockPos clickedFacingPos = clickedPos.relative(clickedFace);
        BlockState clickedFacingState = level.getBlockState(clickedFacingPos);

        // Smart facing, if right clicked on another curtain
        if (clickedFacingState.getBlock() instanceof CurtainBlock) {
            Direction clickedFacingFace = clickedFacingState.getValue(FACING);
            // Do not do smart placement if relative front of back of curtain
            if (clickedFacingFace != clickedFace && clickedFacingFace.getOpposite() != clickedFace) facing = clickedFacingFace;
        }
        if (facing.getAxis().isVertical()) facing = context.getHorizontalDirection().getOpposite();


        BlockState stateL = level.getBlockState(pos.relative(facing.getClockWise()));
        BlockState stateR = level.getBlockState(pos.relative(facing.getCounterClockWise()));
        boolean sideL = (stateL.getBlock() instanceof CurtainBlock && stateL.getValue(VERTICAL_CONNECTION_TYPE) == Direction.UP && (stateL.getValue(FACING) == facing));
        boolean sideR = (stateR.getBlock() instanceof CurtainBlock && stateR.getValue(VERTICAL_CONNECTION_TYPE) == Direction.UP && (stateR.getValue(FACING) == facing));

        boolean open = sideL && sideR ? stateL.getValue(OPEN) || stateR.getValue(OPEN) : (sideR ? stateR.getValue(OPEN) : (sideL ? stateL.getValue(OPEN) : true));

        return this.defaultBlockState().setValue(FACING, facing).setValue(OPEN, open).setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
    }

//    @Override
//    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
//        VerticalConnectionType
//        BlockState stateOpposite = level.getBlockState(pos.relative(facing_vertical.getOpposite()));
//        if (!(stateOpposite.getBlock() instanceof CurtainBlock) || stateOpposite.getValue(VERTICAL_CONNECTION_TYPE) == facing_vertical) return Blocks.AIR.defaultBlockState();
//    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide()) return;

        BlockPos blockPos = pos.below();

        level.setBlock(blockPos, state.setValue(VERTICAL_CONNECTION_TYPE, Direction.DOWN).setValue(WATERLOGGED, level.getFluidState(blockPos).getType() == Fluids.WATER), 3);
        level.updateNeighborsAt(pos, Blocks.AIR);
        state.updateNeighbourShapes(level, pos, 3);
    }

    @Override
    public BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos currentPos, final Direction direction, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        Direction facing = state.getValue(FACING);
        Direction facing_vertical = state.getValue(VERTICAL_CONNECTION_TYPE);
        if (direction.getAxis().isVertical()) {
            BlockState stateOpposite = level.getBlockState(currentPos.relative(facing_vertical.getOpposite()));
            if (!(stateOpposite.getBlock() instanceof CurtainBlock) || stateOpposite.getValue(VERTICAL_CONNECTION_TYPE) == facing_vertical) return Blocks.AIR.defaultBlockState();
        }
        if (direction != facing.getClockWise() && direction != facing.getCounterClockWise()) return state;

        BlockState stateL = level.getBlockState(currentPos.relative(facing.getClockWise()));
        BlockState stateR = level.getBlockState(currentPos.relative(facing.getCounterClockWise()));
        boolean sideL = (stateL.getBlock() instanceof CurtainBlock && stateL.getValue(VERTICAL_CONNECTION_TYPE) == facing_vertical && (stateL.getValue(FACING) == facing));
        boolean sideR = (stateR.getBlock() instanceof CurtainBlock && stateR.getValue(VERTICAL_CONNECTION_TYPE) == facing_vertical && (stateR.getValue(FACING) == facing));
        HorizontalConnectionType type = sideL && sideR ? HorizontalConnectionType.MIDDLE : (sideR ? HorizontalConnectionType.LEFT : (sideL ? HorizontalConnectionType.RIGHT : HorizontalConnectionType.SINGLE));

        return state.setValue(HORIZONTAL_CONNECTION_TYPE, type);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        if (state.getValue(VERTICAL_CONNECTION_TYPE) == Direction.UP) toggleFromTop(state, level, pos);
        else toggleFromTop(level.getBlockState(pos.above()), level, pos.above());


        level.playSound(null, pos, AFSoundEvents.CURTAIN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }

    public void toggleFromTop(BlockState state, Level level, BlockPos pos) {
        boolean open = !state.getValue(OPEN);
        Direction facing = state.getValue(FACING);
        Direction relativeLeft = facing.getClockWise();
        Direction relativeRight = facing.getCounterClockWise();

        toggleVertical(state, level, pos, open);
        toggleHorizontal(state, level, pos, open, facing, relativeLeft);
        toggleHorizontal(state, level, pos, open, facing, relativeRight);
    }

    public void toggleHorizontal(BlockState state, Level level, BlockPos pos, boolean open, Direction facing, Direction dir) {
        BlockPos facingHorizontalPos = pos.relative(dir);
        BlockState facingHorizontalState = level.getBlockState(facingHorizontalPos);

        if (facingHorizontalState.getBlock() instanceof CurtainBlock && facingHorizontalState.getValue(FACING) == facing &&
                state.getValue(VERTICAL_CONNECTION_TYPE) == facingHorizontalState.getValue(VERTICAL_CONNECTION_TYPE)) {
            toggleVertical(facingHorizontalState, level, facingHorizontalPos, open);
            toggleHorizontal(facingHorizontalState, level, facingHorizontalPos, open, facing, dir);
        }
    }

    public void toggleVertical(BlockState state, Level level, BlockPos pos, boolean open) {
        Direction facingVertical = state.getValue(VERTICAL_CONNECTION_TYPE);
        BlockPos facingVerticalPos = pos.relative(facingVertical.getOpposite());
        BlockState facingVerticalState = level.getBlockState(facingVerticalPos);
        level.setBlockAndUpdate(pos, state.setValue(OPEN, open));

        if (facingVerticalState.getBlock() instanceof CurtainBlock && facingVertical == facingVerticalState.getValue(VERTICAL_CONNECTION_TYPE).getOpposite())
            level.setBlockAndUpdate(facingVerticalPos, facingVerticalState.setValue(OPEN, open));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HORIZONTAL_CONNECTION_TYPE, OPEN, VERTICAL_CONNECTION_TYPE, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
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
