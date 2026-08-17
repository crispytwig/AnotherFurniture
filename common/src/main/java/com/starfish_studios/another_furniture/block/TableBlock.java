package com.starfish_studios.another_furniture.block;

import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TableBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LEG1 = ModBlockStateProperties.LEG_1;
    public static final BooleanProperty LEG2 = ModBlockStateProperties.LEG_2;
    public static final BooleanProperty LEG3 = ModBlockStateProperties.LEG_3;
    public static final BooleanProperty LEG4 = ModBlockStateProperties.LEG_4;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    //exists solely to update corner blocks
    public static final BooleanProperty UPDATE = ModBlockStateProperties.UPDATE;

    protected static final VoxelShape TOP = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEG_1 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14.0D, 2.0D);
    protected static final VoxelShape LEG_2 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_4 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14.0D, 2.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            TOP,
            Shapes.or(TOP, LEG_1),
            Shapes.or(TOP, LEG_2),
            Shapes.or(TOP, LEG_1, LEG_2),
            Shapes.or(TOP, LEG_3),
            Shapes.or(TOP, LEG_1, LEG_3),
            Shapes.or(TOP, LEG_2, LEG_3),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_3),
            Shapes.or(TOP, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_4),
            Shapes.or(TOP, LEG_2, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_4),
            Shapes.or(TOP, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_2, LEG_3, LEG_4),
            Shapes.or(TOP, LEG_1, LEG_2, LEG_3, LEG_4)
    };

    public TableBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LEG1, true)
                .setValue(LEG2, true)
                .setValue(LEG3, true)
                .setValue(LEG4, true)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int shape = 0;
        if (state.getValue(LEG1)) shape += 1;
        if (state.getValue(LEG2)) shape += 2;
        if (state.getValue(LEG3)) shape += 4;
        if (state.getValue(LEG4)) shape += 8;
        return SHAPES[shape];
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        BlockState state = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, waterlogged);
        return getConnections(state, context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos currentPos, final Direction direction, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return getConnections(state, level, currentPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEG1, LEG2, LEG3, LEG4, UPDATE, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        boolean leg1 = state.getValue(LEG1);
        boolean leg2 = state.getValue(LEG2);
        boolean leg3 = state.getValue(LEG3);
        boolean leg4 = state.getValue(LEG4);
        return switch(rotation) {
            case NONE -> state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
            case CLOCKWISE_90 -> state
                    .setValue(FACING, rotation.rotate(state.getValue(FACING)))
                    .setValue(LEG1, leg4)
                    .setValue(LEG2, leg1)
                    .setValue(LEG3, leg2)
                    .setValue(LEG4, leg3);
            case CLOCKWISE_180 -> state
                    .setValue(FACING, rotation.rotate(state.getValue(FACING)))
                    .setValue(LEG1, leg3)
                    .setValue(LEG2, leg4)
                    .setValue(LEG3, leg1).setValue(LEG4, leg2);
            case COUNTERCLOCKWISE_90 -> state
                    .setValue(FACING, rotation.rotate(state.getValue(FACING)))
                    .setValue(LEG1, leg2)
                    .setValue(LEG2, leg3)
                    .setValue(LEG3, leg4)
                    .setValue(LEG4, leg1);
        };
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    public BlockState getConnections(BlockState state, BlockGetter level, BlockPos pos) {
        boolean n = validConnection(level.getBlockState(pos.north()));
        boolean e = validConnection(level.getBlockState(pos.east()));
        boolean s = validConnection(level.getBlockState(pos.south()));
        boolean w = validConnection(level.getBlockState(pos.west()));
        boolean leg1 = (!n && !e) || (n && e && !(validConnection(level.getBlockState(pos.north().east()))));
        boolean leg2 = (!e && !s) || (e && s && !(validConnection(level.getBlockState(pos.south().east()))));
        boolean leg3 = (!s && !w) || (s && w && !(validConnection(level.getBlockState(pos.south().west()))));
        boolean leg4 = (!n && !w) || (n && w && !(validConnection(level.getBlockState(pos.north().west()))));
        boolean update = ((n ? 1 : 0) + (e ? 1 : 0) + (s ? 1 : 0) + (w ? 1 : 0)) % 2 == 0;
        return state.setValue(LEG1, leg1).setValue(LEG2, leg2).setValue(LEG3, leg3).setValue(LEG4, leg4).setValue(UPDATE, update);
    }

    public boolean validConnection(BlockState state) {
        return state.is(AFBlockTags.TABLES_CONNECTABLE);
    }
}
