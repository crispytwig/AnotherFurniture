package com.starfish_studios.another_furniture.block.properties;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.*;

public class ModBlockStateProperties {
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE = EnumProperty.create("horizontal", HorizontalConnectionType.class);
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE_1 = EnumProperty.create("horizontal_1", HorizontalConnectionType.class);
    public static final EnumProperty<HorizontalConnectionType> HORIZONTAL_CONNECTION_TYPE_2 = EnumProperty.create("horizontal_2", HorizontalConnectionType.class);
    public static final EnumProperty<VerticalConnectionType> VERTICAL_CONNECTION_TYPE = EnumProperty.create("vertical", VerticalConnectionType.class);
    public static final EnumProperty<SofaType> SOFA_TYPE = EnumProperty.create("type", SofaType.class);
    public static final EnumProperty<VerticalConnectionType> VERTICAL_CONNECTION_NO_SINGLE_TYPE = EnumProperty.create("type", VerticalConnectionType.class, (type) -> type != VerticalConnectionType.SINGLE);
    public static final EnumProperty<SlabType> HALF = EnumProperty.create("half", SlabType.class);
    public static final EnumProperty<Direction> FACING_EXCEPT_DOWN = EnumProperty.create("facing", Direction.class, (direction) -> direction != Direction.DOWN);
    public static final EnumProperty<Direction> VERTICAL_CONNECTION_TYPE_UP_DOWN = EnumProperty.create("vertical", Direction.class, (direction) -> direction.getAxis().isVertical());
    public static final IntegerProperty LEVEL_1_3 = IntegerProperty.create("level", 1, 3);
    public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 1, 11);
    public static final BooleanProperty BACK = BooleanProperty.create("back");
    public static final BooleanProperty BASE = BooleanProperty.create("base");
    public static final BooleanProperty LOW = BooleanProperty.create("low");
    public static final BooleanProperty TUCKED = BooleanProperty.create("tucked");
    public static final BooleanProperty LEG_1 = BooleanProperty.create("leg_1");
    public static final BooleanProperty LEG_2 = BooleanProperty.create("leg_2");
    public static final BooleanProperty LEG_3 = BooleanProperty.create("leg_3");
    public static final BooleanProperty LEG_4 = BooleanProperty.create("leg_4");
    public static final BooleanProperty UPDATE = BooleanProperty.create("update");
    public static final BooleanProperty ON = BooleanProperty.create("on");
}