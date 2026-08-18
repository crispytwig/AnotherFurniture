package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AFBlockEntityTypes {
    public static final Supplier<BlockEntityType<ShelfBlockEntity>> SHELF = AFRegistry.registerBlockEntityType("shelf",
            () -> AFRegistry.createBlockEntityType(ShelfBlockEntity::new,
                    AFBlocks.OAK_SHELF,
                    AFBlocks.SPRUCE_SHELF,
                    AFBlocks.BIRCH_SHELF,
                    AFBlocks.JUNGLE_SHELF,
                    AFBlocks.ACACIA_SHELF,
                    AFBlocks.DARK_OAK_SHELF,
                    AFBlocks.MANGROVE_SHELF,
                    AFBlocks.CRIMSON_SHELF,
                    AFBlocks.WARPED_SHELF,
                    AFBlocks.BAMBOO_SHELF,
                    AFBlocks.CHERRY_SHELF
            ));

//    public static final Supplier<BlockEntityType<SmallShelfBlockEntity>> SMALL_SHELF = AFRegistry.registerBlockEntityType("small_shelf",
//            () -> AFRegistry.createBlockEntityType(SmallShelfBlockEntity::new,
//                    AFBlocks.OAK_SMALL_SHELF,
//                    AFBlocks.SPRUCE_SMALL_SHELF,
//                    AFBlocks.BIRCH_SMALL_SHELF,
//                    AFBlocks.JUNGLE_SMALL_SHELF,
//                    AFBlocks.ACACIA_SMALL_SHELF,
//                    AFBlocks.DARK_OAK_SMALL_SHELF,
//                    AFBlocks.MANGROVE_SMALL_SHELF,
//                    AFBlocks.CRIMSON_SMALL_SHELF,
//                    AFBlocks.WARPED_SMALL_SHELF,
//                    AFBlocks.BAMBOO_SMALL_SHELF,
//                    AFBlocks.CHERRY_SMALL_SHELF
//            ));

    public static final Supplier<BlockEntityType<ServiceBellBlockEntity>> SERVICE_BELL = AFRegistry.registerBlockEntityType("service_bell",
            () -> AFRegistry.createBlockEntityType(ServiceBellBlockEntity::new, AFBlocks.SERVICE_BELL));

    public static final Supplier<BlockEntityType<FlowerBoxBlockEntity>> FLOWER_BOX = AFRegistry.registerBlockEntityType("planter_box",
            () -> AFRegistry.createBlockEntityType(FlowerBoxBlockEntity::new,
                    AFBlocks.OAK_FLOWER_BOX,
                    AFBlocks.SPRUCE_FLOWER_BOX,
                    AFBlocks.BIRCH_FLOWER_BOX,
                    AFBlocks.JUNGLE_FLOWER_BOX,
                    AFBlocks.ACACIA_FLOWER_BOX,
                    AFBlocks.DARK_OAK_FLOWER_BOX,
                    AFBlocks.MANGROVE_FLOWER_BOX,
                    AFBlocks.CRIMSON_FLOWER_BOX,
                    AFBlocks.WARPED_FLOWER_BOX,
                    AFBlocks.BAMBOO_FLOWER_BOX,
                    AFBlocks.CHERRY_FLOWER_BOX
            ));

    public static final Supplier<BlockEntityType<DrawerBlockEntity>> DRAWER = AFRegistry.registerBlockEntityType("drawer",
            () -> AFRegistry.createBlockEntityType(DrawerBlockEntity::new,
                    AFBlocks.OAK_DRAWER,
                    AFBlocks.SPRUCE_DRAWER,
                    AFBlocks.BIRCH_DRAWER,
                    AFBlocks.JUNGLE_DRAWER,
                    AFBlocks.ACACIA_DRAWER,
                    AFBlocks.DARK_OAK_DRAWER,
                    AFBlocks.MANGROVE_DRAWER,
                    AFBlocks.CRIMSON_DRAWER,
                    AFBlocks.WARPED_DRAWER,
                    AFBlocks.BAMBOO_DRAWER,
                    AFBlocks.CHERRY_DRAWER
            ));
//    public static final Supplier<BlockEntityType<GrandfatherClockBlockEntity>> GRANDFATHER_CLOCK = AFRegistry.registerBlockEntityType("grandfather_clock",
//            () -> AFRegistry.createBlockEntityType(GrandfatherClockBlockEntity::new,
//                    AFBlocks.OAK_GRANDFATHER_CLOCK,
//                    AFBlocks.SPRUCE_GRANDFATHER_CLOCK,
//                    AFBlocks.BIRCH_GRANDFATHER_CLOCK,
//                    AFBlocks.JUNGLE_GRANDFATHER_CLOCK,
//                    AFBlocks.ACACIA_GRANDFATHER_CLOCK,
//                    AFBlocks.DARK_OAK_GRANDFATHER_CLOCK,
//                    AFBlocks.MANGROVE_GRANDFATHER_CLOCK,
//                    AFBlocks.CRIMSON_GRANDFATHER_CLOCK,
//                    AFBlocks.WARPED_GRANDFATHER_CLOCK
//            ));

//    public static final Supplier<BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = AFRegistry.registerBlockEntityType("tombstone",
//            () -> AFRegistry.createBlockEntityType(TombstoneBlockEntity::new,
//                    AFBlocks.STONE_TOMBSTONE
//            ));

    public static void init() {}
}
