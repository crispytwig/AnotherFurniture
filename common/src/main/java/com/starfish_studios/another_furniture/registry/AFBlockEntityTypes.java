package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AFBlockEntityTypes {
    public static final Supplier<BlockEntityType<ShelfBlockEntity>> SHELF = AFRegistry.registerBlockEntityType("shelf",
            () -> AFRegistry.createBlockEntityType(ShelfBlockEntity::new,
                    AFBlocks.OAK_SHELF.get(),
                    AFBlocks.SPRUCE_SHELF.get(),
                    AFBlocks.BIRCH_SHELF.get(),
                    AFBlocks.JUNGLE_SHELF.get(),
                    AFBlocks.ACACIA_SHELF.get(),
                    AFBlocks.DARK_OAK_SHELF.get(),
                    AFBlocks.MANGROVE_SHELF.get(),
                    AFBlocks.CRIMSON_SHELF.get(),
                    AFBlocks.WARPED_SHELF.get(),
                    AFBlocks.BAMBOO_SHELF.get(),
                    AFBlocks.CHERRY_SHELF.get()
            ));

//    public static final Supplier<BlockEntityType<SmallShelfBlockEntity>> SMALL_SHELF = AFRegistry.registerBlockEntityType("small_shelf",
//            () -> AFRegistry.createBlockEntityType(SmallShelfBlockEntity::new,
//                    AFBlocks.OAK_SMALL_SHELF.get(),
//                    AFBlocks.SPRUCE_SMALL_SHELF.get(),
//                    AFBlocks.BIRCH_SMALL_SHELF.get(),
//                    AFBlocks.JUNGLE_SMALL_SHELF.get(),
//                    AFBlocks.ACACIA_SMALL_SHELF.get(),
//                    AFBlocks.DARK_OAK_SMALL_SHELF.get(),
//                    AFBlocks.MANGROVE_SMALL_SHELF.get(),
//                    AFBlocks.CRIMSON_SMALL_SHELF.get(),
//                    AFBlocks.WARPED_SMALL_SHELF.get(),
//                    AFBlocks.BAMBOO_SMALL_SHELF.get(),
//                    AFBlocks.CHERRY_SMALL_SHELF.get()
//            ));

    public static final Supplier<BlockEntityType<ServiceBellBlockEntity>> SERVICE_BELL = AFRegistry.registerBlockEntityType("service_bell",
            () -> AFRegistry.createBlockEntityType(ServiceBellBlockEntity::new, AFBlocks.SERVICE_BELL.get()));

    public static final Supplier<BlockEntityType<FlowerBoxBlockEntity>> FLOWER_BOX = AFRegistry.registerBlockEntityType("planter_box",
            () -> AFRegistry.createBlockEntityType(FlowerBoxBlockEntity::new,
                    AFBlocks.OAK_FLOWER_BOX.get(),
                    AFBlocks.SPRUCE_FLOWER_BOX.get(),
                    AFBlocks.BIRCH_FLOWER_BOX.get(),
                    AFBlocks.JUNGLE_FLOWER_BOX.get(),
                    AFBlocks.ACACIA_FLOWER_BOX.get(),
                    AFBlocks.DARK_OAK_FLOWER_BOX.get(),
                    AFBlocks.MANGROVE_FLOWER_BOX.get(),
                    AFBlocks.CRIMSON_FLOWER_BOX.get(),
                    AFBlocks.WARPED_FLOWER_BOX.get(),
                    AFBlocks.BAMBOO_FLOWER_BOX.get(),
                    AFBlocks.CHERRY_FLOWER_BOX.get()
            ));

    public static final Supplier<BlockEntityType<DrawerBlockEntity>> DRAWER = AFRegistry.registerBlockEntityType("drawer",
            () -> AFRegistry.createBlockEntityType(DrawerBlockEntity::new,
                    AFBlocks.OAK_DRAWER.get(),
                    AFBlocks.SPRUCE_DRAWER.get(),
                    AFBlocks.BIRCH_DRAWER.get(),
                    AFBlocks.JUNGLE_DRAWER.get(),
                    AFBlocks.ACACIA_DRAWER.get(),
                    AFBlocks.DARK_OAK_DRAWER.get(),
                    AFBlocks.MANGROVE_DRAWER.get(),
                    AFBlocks.CRIMSON_DRAWER.get(),
                    AFBlocks.WARPED_DRAWER.get(),
                    AFBlocks.BAMBOO_DRAWER.get(),
                    AFBlocks.CHERRY_DRAWER.get()
            ));
//    public static final Supplier<BlockEntityType<GrandfatherClockBlockEntity>> GRANDFATHER_CLOCK = AFRegistry.registerBlockEntityType("grandfather_clock",
//            () -> AFRegistry.createBlockEntityType(GrandfatherClockBlockEntity::new,
//                    AFBlocks.OAK_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.SPRUCE_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.BIRCH_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.JUNGLE_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.ACACIA_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.DARK_OAK_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.MANGROVE_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.CRIMSON_GRANDFATHER_CLOCK.get(),
//                    AFBlocks.WARPED_GRANDFATHER_CLOCK.get()
//            ));

//    public static final Supplier<BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = AFRegistry.registerBlockEntityType("tombstone",
//            () -> AFRegistry.createBlockEntityType(TombstoneBlockEntity::new,
//                    AFBlocks.STONE_TOMBSTONE.get()
//            ));

    public static void init() {}
}