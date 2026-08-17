package com.starfish_studios.another_furniture.block;

//import com.starfish_studios.another_furniture.block.entity.GrandfatherClockBlockEntity;
//import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
//import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
//import com.starfish_studios.another_furniture.block.properties.VerticalConnectionType;
//import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
//import com.starfish_studios.another_furniture.util.block.ShapeUtil;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.context.BlockPlaceContext;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.LevelReader;
//import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.entity.HopperBlockEntity;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.BlockStateProperties;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraft.world.level.block.state.properties.EnumProperty;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.Fluids;
//import net.minecraft.world.level.pathfinder.PathComputationType;
//import net.minecraft.world.phys.shapes.CollisionContext;
//import net.minecraft.world.phys.shapes.Shapes;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import org.jetbrains.annotations.Nullable;
//
//public class GrandfatherClockBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
//
//    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
//    public static final EnumProperty<VerticalConnectionType> TYPE = ModBlockStateProperties.VERTICAL_CONNECTION_NO_SINGLE_TYPE;
//
//    protected static final VoxelShape AABB_BOTTOM_NORTH = Shapes.or(Block.box(0, 0, 2, 16, 12, 14), Block.box(2, 12, 4, 14, 16, 12));
//    protected static final VoxelShape AABB_BOTTOM_EAST = ShapeUtil.rotateShape(AABB_BOTTOM_NORTH, Direction.EAST);
//    protected static final VoxelShape AABB_BOTTOM_SOUTH = ShapeUtil.rotateShape(AABB_BOTTOM_NORTH, Direction.SOUTH);
//    protected static final VoxelShape AABB_BOTTOM_WEST = ShapeUtil.rotateShape(AABB_BOTTOM_NORTH, Direction.WEST);
//
//    protected static final VoxelShape AABB_MIDDLE_NORTH = Shapes.or(Block.box(2, 0, 4, 14, 13, 12), Block.box(0, 13, 2, 16, 16, 14));
//    protected static final VoxelShape AABB_MIDDLE_EAST = ShapeUtil.rotateShape(AABB_MIDDLE_NORTH, Direction.EAST);
//    protected static final VoxelShape AABB_MIDDLE_SOUTH = ShapeUtil.rotateShape(AABB_MIDDLE_NORTH, Direction.SOUTH);
//    protected static final VoxelShape AABB_MIDDLE_WEST = ShapeUtil.rotateShape(AABB_MIDDLE_NORTH, Direction.WEST);
//
//    protected static final VoxelShape AABB_TOP_NORTH = Block.box(2, 0, 4, 14, 12, 12);
//    protected static final VoxelShape AABB_TOP_EAST = ShapeUtil.rotateShape(AABB_TOP_NORTH, Direction.EAST);
//    protected static final VoxelShape AABB_TOP_SOUTH = ShapeUtil.rotateShape(AABB_TOP_NORTH, Direction.SOUTH);
//    protected static final VoxelShape AABB_TOP_WEST = ShapeUtil.rotateShape(AABB_TOP_NORTH, Direction.WEST);
//
//    public GrandfatherClockBlock(BlockBehaviour.Properties properties) {
//        super(properties);
//        registerDefaultState(this.stateDefinition.any()
//                .setValue(WATERLOGGED, false)
//                .setValue(FACING, Direction.NORTH)
//                .setValue(TYPE, VerticalConnectionType.BOTTOM));
//    }
//
//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext context) {
//        Level level = context.getLevel();
//        BlockPos above_1 = context.getClickedPos().above();
//        BlockPos above_2 = above_1.above();
//        if (level.isOutsideBuildHeight(above_1.getY()) || level.isOutsideBuildHeight(above_2.getY())) return null;
//        if (!(level.getBlockState(above_1).canBeReplaced(context) && level.getBlockState(above_2).canBeReplaced(context))) return null;
//        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
//    }
//
//    @Override
//    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//        super.setPlacedBy(level, pos, state, placer, stack);
//        if (level.isClientSide) return;
//
//        BlockPos middlePos = pos.above();
//        level.setBlock(middlePos, state.setValue(TYPE, VerticalConnectionType.MIDDLE), 3);
//        BlockPos topPos = middlePos.above();
//        level.setBlock(topPos, state.setValue(TYPE, VerticalConnectionType.TOP), 3);
//        level.blockUpdated(pos, Blocks.AIR);
//        state.updateNeighbourShapes(level, pos, 3);
//    }
//
//    @Override
//    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
//        if (direction.getAxis().isHorizontal()) return state;
//
//        VerticalConnectionType type = state.getValue(TYPE);
//        Direction facing = state.getValue(FACING);
//
//        BlockState below = level.getBlockState(currentPos.below());
//        BlockState above = level.getBlockState(currentPos.above());
//        boolean belowIsClock = below.is(this) && below.getValue(FACING) == facing;
//        boolean aboveIsClock = above.is(this) && above.getValue(FACING) == facing;
//
//        if (type == VerticalConnectionType.BOTTOM && aboveIsClock && above.getValue(TYPE) == VerticalConnectionType.MIDDLE) return state;
//        if (type == VerticalConnectionType.MIDDLE &&
//                aboveIsClock && above.getValue(TYPE) == VerticalConnectionType.TOP &&
//                belowIsClock && below.getValue(TYPE) == VerticalConnectionType.BOTTOM) return state;
//        if (type == VerticalConnectionType.TOP && belowIsClock && below.getValue(TYPE) == VerticalConnectionType.MIDDLE) return state;
//
//        return Blocks.AIR.defaultBlockState();
//    }
//
//    @Override
//    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        Direction facing = state.getValue(FACING);
//        VerticalConnectionType type = state.getValue(TYPE);
//        if (type == VerticalConnectionType.BOTTOM) {
//            return switch (facing) {
//                default -> AABB_BOTTOM_NORTH;
//                case EAST -> AABB_BOTTOM_EAST;
//                case SOUTH -> AABB_BOTTOM_SOUTH;
//                case WEST -> AABB_BOTTOM_WEST;
//            };
//        } else if (type == VerticalConnectionType.MIDDLE) {
//            return switch (facing) {
//                default -> AABB_MIDDLE_NORTH;
//                case EAST -> AABB_MIDDLE_EAST;
//                case SOUTH -> AABB_MIDDLE_SOUTH;
//                case WEST -> AABB_MIDDLE_WEST;
//            };
//        }
//        return switch (facing) {
//            default -> AABB_TOP_NORTH;
//            case EAST -> AABB_TOP_EAST;
//            case SOUTH -> AABB_TOP_SOUTH;
//            case WEST -> AABB_TOP_WEST;
//        };
//    }
//
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return state.getValue(TYPE) == VerticalConnectionType.TOP ? new GrandfatherClockBlockEntity(pos, state) : null;
//    }
//
//    @Override
//    public RenderShape getRenderShape(BlockState state) {
//        return RenderShape.MODEL;
//    }
//
//    @Nullable
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
//        return createTickerHelper(blockEntityType, AFBlockEntityTypes.GRANDFATHER_CLOCK.get(), GrandfatherClockBlockEntity::tick);
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(FACING, TYPE, WATERLOGGED);
//    }
//
//    @Override
//    public FluidState getFluidState(BlockState state) {
//        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//    }
//
//    public BlockState rotate(BlockState state, Rotation rotation) {
//        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
//    }
//
//    public BlockState mirror(BlockState state, Mirror mirror) {
//        return state.rotate(mirror.getRotation(state.getValue(FACING)));
//    }
//
//    @Override
//    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
//        return false;
//    }
//}
