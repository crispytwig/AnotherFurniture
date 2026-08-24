package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.block.ShelfBlock;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;
import java.util.function.Supplier;

public class AFBlocks {

    public static class Properties {
        public static BlockBehaviour.Properties oak_wood = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties spruce_wood = BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties birch_wood = BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties jungle_wood = BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties acacia_wood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties dark_oak_wood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties mangrove_wood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava();
        public static BlockBehaviour.Properties crimson_wood = BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD);
        public static BlockBehaviour.Properties warped_wood = BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD);
        public static BlockBehaviour.Properties bamboo_wood = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.BAMBOO_WOOD).ignitedByLava();
        public static BlockBehaviour.Properties cherry_wood = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava();

        public static BlockBehaviour.Properties weak_wood = oak_wood.strength(1.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava().mapColor(MapColor.WOOD);
        
        public static BlockBehaviour.Properties curtain = Block.Properties.of().strength(0.1F).noOcclusion().sound(SoundType.WOOL).mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY);
        public static BlockBehaviour.Properties service_bell = Block.Properties.of().strength(2.0F, 3.0F).sound(SoundType.METAL).mapColor(MapColor.METAL);
        public static BlockBehaviour.Properties lamp = weak_wood.lightLevel((blockState) -> blockState.hasProperty(BlockStateProperties.LIT) && blockState.getValue(BlockStateProperties.LIT) ? (blockState.getValue(ModBlockStateProperties.LEVEL_1_3) * 5) : 0);
    }

    // Chair
    public static final Supplier<Block> OAK_CHAIR = registerBlock("oak_chair", (properties) -> new ChairBlock(1, properties), Properties.oak_wood);
    public static final Supplier<Block> SPRUCE_CHAIR = registerBlock("spruce_chair", (properties) -> new ChairBlock(2, properties), Properties.spruce_wood);
    public static final Supplier<Block> BIRCH_CHAIR = registerBlock("birch_chair", (properties) -> new ChairBlock(3, properties), Properties.birch_wood);
    public static final Supplier<Block> JUNGLE_CHAIR = registerBlock("jungle_chair", (properties) -> new ChairBlock(4, properties), Properties.jungle_wood);
    public static final Supplier<Block> ACACIA_CHAIR = registerBlock("acacia_chair", (properties) -> new ChairBlock(5, properties), Properties.acacia_wood);
    public static final Supplier<Block> DARK_OAK_CHAIR = registerBlock("dark_oak_chair", (properties) -> new ChairBlock(6, properties), Properties.dark_oak_wood);
    public static final Supplier<Block> MANGROVE_CHAIR = registerBlock("mangrove_chair", (properties) -> new ChairBlock(7, properties), Properties.mangrove_wood);
    public static final Supplier<Block> CHERRY_CHAIR = registerBlock("cherry_chair", (properties) -> new ChairBlock(8, properties), Properties.cherry_wood);
    public static final Supplier<Block> BAMBOO_CHAIR = registerBlock("bamboo_chair", (properties) -> new ChairBlock(9, properties), Properties.bamboo_wood);
    public static final Supplier<Block> CRIMSON_CHAIR = registerBlock("crimson_chair", (properties) -> new ChairBlock(10, properties), Properties.crimson_wood);
    public static final Supplier<Block> WARPED_CHAIR = registerBlock("warped_chair", (properties) -> new ChairBlock(11, properties), Properties.warped_wood);

    // Shelf
    public static final Supplier<Block> OAK_SHELF = registerBlock("oak_shelf", (properties) -> new ShelfBlock(properties), Properties.oak_wood);
    public static final Supplier<Block> SPRUCE_SHELF = registerBlock("spruce_shelf", (properties) -> new ShelfBlock(properties), Properties.spruce_wood);
    public static final Supplier<Block> BIRCH_SHELF = registerBlock("birch_shelf", (properties) -> new ShelfBlock(properties), Properties.birch_wood);
    public static final Supplier<Block> JUNGLE_SHELF = registerBlock("jungle_shelf", (properties) -> new ShelfBlock(properties), Properties.jungle_wood);
    public static final Supplier<Block> ACACIA_SHELF = registerBlock("acacia_shelf", (properties) -> new ShelfBlock(properties), Properties.acacia_wood);
    public static final Supplier<Block> DARK_OAK_SHELF = registerBlock("dark_oak_shelf", (properties) -> new ShelfBlock(properties), Properties.dark_oak_wood);
    public static final Supplier<Block> MANGROVE_SHELF = registerBlock("mangrove_shelf", (properties) -> new ShelfBlock(properties), Properties.mangrove_wood);
    public static final Supplier<Block> CHERRY_SHELF = registerBlock("cherry_shelf", (properties) -> new ShelfBlock(properties), Properties.cherry_wood);
    public static final Supplier<Block> BAMBOO_SHELF = registerBlock("bamboo_shelf", (properties) -> new ShelfBlock(properties), Properties.bamboo_wood);
    public static final Supplier<Block> CRIMSON_SHELF = registerBlock("crimson_shelf", (properties) -> new ShelfBlock(properties), Properties.crimson_wood);
    public static final Supplier<Block> WARPED_SHELF = registerBlock("warped_shelf", (properties) -> new ShelfBlock(properties), Properties.warped_wood);

    // Small Shelf
//    public static final Supplier<Block> OAK_SMALL_SHELF = registerBlock("oak_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> SPRUCE_SMALL_SHELF = registerBlock("spruce_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> BIRCH_SMALL_SHELF = registerBlock("birch_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> JUNGLE_SMALL_SHELF = registerBlock("jungle_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> ACACIA_SMALL_SHELF = registerBlock("acacia_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> DARK_OAK_SMALL_SHELF = registerBlock("dark_oak_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> MANGROVE_SMALL_SHELF = registerBlock("mangrove_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Supplier<Block> CHERRY_SMALL_SHELF = registerBlock("cherry_small_shelf", () -> new SmallShelfBlock(Properties.cherry_wood));
//    public static final Supplier<Block> BAMBOO_SMALL_SHELF = registerBlock("bamboo_small_shelf", () -> new SmallShelfBlock(Properties.bamboo_wood));
//    public static final Supplier<Block> CRIMSON_SMALL_SHELF = registerBlock("crimson_small_shelf", () -> new SmallShelfBlock(Properties.nether_wood));
//    public static final Supplier<Block> WARPED_SMALL_SHELF = registerBlock("warped_small_shelf", () -> new SmallShelfBlock(Properties.nether_wood));

    // Table
    public static final Supplier<Block> OAK_TABLE = registerBlock("oak_table", (properties) -> new TableBlock(properties), Properties.oak_wood);
    public static final Supplier<Block> SPRUCE_TABLE = registerBlock("spruce_table", (properties) -> new TableBlock(properties), Properties.spruce_wood);
    public static final Supplier<Block> BIRCH_TABLE = registerBlock("birch_table", (properties) -> new TableBlock(properties), Properties.birch_wood);
    public static final Supplier<Block> JUNGLE_TABLE = registerBlock("jungle_table", (properties) -> new TableBlock(properties), Properties.jungle_wood);
    public static final Supplier<Block> ACACIA_TABLE = registerBlock("acacia_table", (properties) -> new TableBlock(properties), Properties.acacia_wood);
    public static final Supplier<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table", (properties) -> new TableBlock(properties), Properties.dark_oak_wood);
    public static final Supplier<Block> MANGROVE_TABLE = registerBlock("mangrove_table", (properties) -> new TableBlock(properties), Properties.mangrove_wood);
    public static final Supplier<Block> CHERRY_TABLE = registerBlock("cherry_table", (properties) -> new TableBlock(properties), Properties.cherry_wood);
    public static final Supplier<Block> BAMBOO_TABLE = registerBlock("bamboo_table", (properties) -> new TableBlock(properties), Properties.bamboo_wood);
    public static final Supplier<Block> CRIMSON_TABLE = registerBlock("crimson_table", (properties) -> new TableBlock(properties), Properties.crimson_wood);
    public static final Supplier<Block> WARPED_TABLE = registerBlock("warped_table", (properties) -> new TableBlock(properties), Properties.warped_wood);

    // Stool
    public static final Supplier<Block> WHITE_STOOL = registerBlock("white_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> ORANGE_STOOL = registerBlock("orange_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> MAGENTA_STOOL = registerBlock("magenta_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_BLUE_STOOL = registerBlock("light_blue_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> YELLOW_STOOL = registerBlock("yellow_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIME_STOOL = registerBlock("lime_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> PINK_STOOL = registerBlock("pink_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> GRAY_STOOL = registerBlock("gray_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_GRAY_STOOL = registerBlock("light_gray_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> CYAN_STOOL = registerBlock("cyan_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> PURPLE_STOOL = registerBlock("purple_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BLUE_STOOL = registerBlock("blue_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BROWN_STOOL = registerBlock("brown_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> GREEN_STOOL = registerBlock("green_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> RED_STOOL = registerBlock("red_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BLACK_STOOL = registerBlock("black_stool", (properties) -> new StoolBlock(properties), Properties.weak_wood);
    // Shutter
    public static final Supplier<Block> OAK_SHUTTER = registerBlock("oak_shutter", (properties) -> new ShutterBlock(1, properties), Properties.oak_wood.noOcclusion());
    public static final Supplier<Block> SPRUCE_SHUTTER = registerBlock("spruce_shutter", (properties) -> new ShutterBlock(2, properties), Properties.spruce_wood.noOcclusion());
    public static final Supplier<Block> BIRCH_SHUTTER = registerBlock("birch_shutter", (properties) -> new ShutterBlock(3, properties), Properties.birch_wood.noOcclusion());
    public static final Supplier<Block> JUNGLE_SHUTTER = registerBlock("jungle_shutter", (properties) -> new ShutterBlock(4, properties), Properties.jungle_wood.noOcclusion());
    public static final Supplier<Block> ACACIA_SHUTTER = registerBlock("acacia_shutter", (properties) -> new ShutterBlock(5, properties), Properties.acacia_wood.noOcclusion());
    public static final Supplier<Block> DARK_OAK_SHUTTER = registerBlock("dark_oak_shutter", (properties) -> new ShutterBlock(6, properties), Properties.dark_oak_wood.noOcclusion());
    public static final Supplier<Block> MANGROVE_SHUTTER = registerBlock("mangrove_shutter", (properties) -> new ShutterBlock(7, properties), Properties.mangrove_wood.noOcclusion());
    public static final Supplier<Block> CHERRY_SHUTTER = registerBlock("cherry_shutter", (properties) -> new ShutterBlock(8, properties), Properties.cherry_wood.noOcclusion());
    public static final Supplier<Block> BAMBOO_SHUTTER = registerBlock("bamboo_shutter", (properties) -> new ShutterBlock(9, properties), Properties.bamboo_wood.noOcclusion());
    public static final Supplier<Block> CRIMSON_SHUTTER = registerBlock("crimson_shutter", (properties) -> new ShutterBlock(10, properties), Properties.crimson_wood.noOcclusion());
    public static final Supplier<Block> WARPED_SHUTTER = registerBlock("warped_shutter", (properties) -> new ShutterBlock(11, properties), Properties.warped_wood.noOcclusion());

    // Planter Box
    public static final Supplier<Block> OAK_FLOWER_BOX = registerBlock("oak_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.oak_wood);
    public static final Supplier<Block> SPRUCE_FLOWER_BOX = registerBlock("spruce_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.spruce_wood);
    public static final Supplier<Block> BIRCH_FLOWER_BOX = registerBlock("birch_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.birch_wood);
    public static final Supplier<Block> JUNGLE_FLOWER_BOX = registerBlock("jungle_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.jungle_wood);
    public static final Supplier<Block> ACACIA_FLOWER_BOX = registerBlock("acacia_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.acacia_wood);
    public static final Supplier<Block> DARK_OAK_FLOWER_BOX = registerBlock("dark_oak_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.dark_oak_wood);
    public static final Supplier<Block> MANGROVE_FLOWER_BOX = registerBlock("mangrove_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.mangrove_wood);
    public static final Supplier<Block> CHERRY_FLOWER_BOX = registerBlock("cherry_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.cherry_wood);
    public static final Supplier<Block> BAMBOO_FLOWER_BOX = registerBlock("bamboo_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.bamboo_wood);
    public static final Supplier<Block> CRIMSON_FLOWER_BOX = registerBlock("crimson_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.crimson_wood);
    public static final Supplier<Block> WARPED_FLOWER_BOX = registerBlock("warped_flower_box", (properties) -> new FlowerBoxBlock(properties), Properties.warped_wood);

    // Curtain
    public static final Supplier<Block> WHITE_CURTAIN = registerBlock("white_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> ORANGE_CURTAIN = registerBlock("orange_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> MAGENTA_CURTAIN = registerBlock("magenta_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> LIGHT_BLUE_CURTAIN = registerBlock("light_blue_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> YELLOW_CURTAIN = registerBlock("yellow_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> LIME_CURTAIN = registerBlock("lime_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> PINK_CURTAIN = registerBlock("pink_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> GRAY_CURTAIN = registerBlock("gray_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> LIGHT_GRAY_CURTAIN = registerBlock("light_gray_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> CYAN_CURTAIN = registerBlock("cyan_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> PURPLE_CURTAIN = registerBlock("purple_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> BLUE_CURTAIN = registerBlock("blue_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> BROWN_CURTAIN = registerBlock("brown_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> GREEN_CURTAIN = registerBlock("green_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> RED_CURTAIN = registerBlock("red_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);
    public static final Supplier<Block> BLACK_CURTAIN = registerBlock("black_curtain", (properties) -> new CurtainBlock(properties), Properties.curtain);

    // Bell
    public static final Supplier<Block> SERVICE_BELL = registerBlock("service_bell", (properties) -> new ServiceBellBlock(properties), Properties.service_bell);

    // Bench
    public static final Supplier<Block> OAK_BENCH = registerBlock("oak_bench", (properties) -> new BenchBlock(properties), Properties.oak_wood);
    public static final Supplier<Block> SPRUCE_BENCH = registerBlock("spruce_bench", (properties) -> new BenchBlock(properties), Properties.spruce_wood);
    public static final Supplier<Block> BIRCH_BENCH = registerBlock("birch_bench", (properties) -> new BenchBlock(properties), Properties.birch_wood);
    public static final Supplier<Block> JUNGLE_BENCH = registerBlock("jungle_bench", (properties) -> new BenchBlock(properties), Properties.jungle_wood);
    public static final Supplier<Block> ACACIA_BENCH = registerBlock("acacia_bench", (properties) -> new BenchBlock(properties), Properties.acacia_wood);
    public static final Supplier<Block> DARK_OAK_BENCH = registerBlock("dark_oak_bench", (properties) -> new BenchBlock(properties), Properties.dark_oak_wood);
    public static final Supplier<Block> MANGROVE_BENCH = registerBlock("mangrove_bench", (properties) -> new BenchBlock(properties), Properties.mangrove_wood);
    public static final Supplier<Block> CHERRY_BENCH = registerBlock("cherry_bench", (properties) -> new BenchBlock(properties), Properties.cherry_wood);
    public static final Supplier<Block> BAMBOO_BENCH = registerBlock("bamboo_bench", (properties) -> new BenchBlock(properties), Properties.bamboo_wood);
    public static final Supplier<Block> CRIMSON_BENCH = registerBlock("crimson_bench", (properties) -> new BenchBlock(properties), Properties.crimson_wood);
    public static final Supplier<Block> WARPED_BENCH = registerBlock("warped_bench", (properties) -> new BenchBlock(properties), Properties.warped_wood);

    // Drawer
    public static final Supplier<Block> OAK_DRAWER = registerBlock("oak_drawer", (properties) -> new DrawerBlock(properties), Properties.oak_wood);
    public static final Supplier<Block> SPRUCE_DRAWER = registerBlock("spruce_drawer", (properties) -> new DrawerBlock(properties), Properties.spruce_wood);
    public static final Supplier<Block> BIRCH_DRAWER = registerBlock("birch_drawer", (properties) -> new DrawerBlock(properties), Properties.birch_wood);
    public static final Supplier<Block> JUNGLE_DRAWER = registerBlock("jungle_drawer", (properties) -> new DrawerBlock(properties), Properties.jungle_wood);
    public static final Supplier<Block> ACACIA_DRAWER = registerBlock("acacia_drawer", (properties) -> new DrawerBlock(properties), Properties.acacia_wood);
    public static final Supplier<Block> DARK_OAK_DRAWER = registerBlock("dark_oak_drawer", (properties) -> new DrawerBlock(properties), Properties.dark_oak_wood);
    public static final Supplier<Block> MANGROVE_DRAWER = registerBlock("mangrove_drawer", (properties) -> new DrawerBlock(properties), Properties.mangrove_wood);
    public static final Supplier<Block> CHERRY_DRAWER = registerBlock("cherry_drawer", (properties) -> new DrawerBlock(properties), Properties.cherry_wood);
    public static final Supplier<Block> BAMBOO_DRAWER = registerBlock("bamboo_drawer", (properties) -> new DrawerBlock(properties), Properties.bamboo_wood);
    public static final Supplier<Block> CRIMSON_DRAWER = registerBlock("crimson_drawer", (properties) -> new DrawerBlock(properties), Properties.crimson_wood);
    public static final Supplier<Block> WARPED_DRAWER = registerBlock("warped_drawer", (properties) -> new DrawerBlock(properties), Properties.warped_wood);

    // Lamp
    public static final Supplier<Block> WHITE_LAMP = registerBlock("white_lamp", (properties) -> new LampBlock(DyeColor.WHITE, properties), Properties.lamp);
    public static final Supplier<Block> ORANGE_LAMP = registerBlock("orange_lamp", (properties) -> new LampBlock(DyeColor.ORANGE, properties), Properties.lamp);
    public static final Supplier<Block> MAGENTA_LAMP = registerBlock("magenta_lamp", (properties) -> new LampBlock(DyeColor.MAGENTA, properties), Properties.lamp);
    public static final Supplier<Block> LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp", (properties) -> new LampBlock(DyeColor.LIGHT_BLUE, properties), Properties.lamp);
    public static final Supplier<Block> YELLOW_LAMP = registerBlock("yellow_lamp", (properties) -> new LampBlock(DyeColor.YELLOW, properties), Properties.lamp);
    public static final Supplier<Block> LIME_LAMP = registerBlock("lime_lamp", (properties) -> new LampBlock(DyeColor.LIME, properties), Properties.lamp);
    public static final Supplier<Block> PINK_LAMP = registerBlock("pink_lamp", (properties) -> new LampBlock(DyeColor.PINK, properties), Properties.lamp);
    public static final Supplier<Block> GRAY_LAMP = registerBlock("gray_lamp", (properties) -> new LampBlock(DyeColor.GRAY, properties), Properties.lamp);
    public static final Supplier<Block> LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp", (properties) -> new LampBlock(DyeColor.LIGHT_GRAY, properties), Properties.lamp);
    public static final Supplier<Block> CYAN_LAMP = registerBlock("cyan_lamp", (properties) -> new LampBlock(DyeColor.CYAN, properties), Properties.lamp);
    public static final Supplier<Block> PURPLE_LAMP = registerBlock("purple_lamp", (properties) -> new LampBlock(DyeColor.PURPLE, properties), Properties.lamp);
    public static final Supplier<Block> BLUE_LAMP = registerBlock("blue_lamp", (properties) -> new LampBlock(DyeColor.BLUE, properties), Properties.lamp);
    public static final Supplier<Block> BROWN_LAMP = registerBlock("brown_lamp", (properties) -> new LampBlock(DyeColor.BROWN, properties), Properties.lamp);
    public static final Supplier<Block> GREEN_LAMP = registerBlock("green_lamp", (properties) -> new LampBlock(DyeColor.GREEN, properties), Properties.lamp);
    public static final Supplier<Block> RED_LAMP = registerBlock("red_lamp", (properties) -> new LampBlock(DyeColor.RED, properties), Properties.lamp);
    public static final Supplier<Block> BLACK_LAMP = registerBlock("black_lamp", (properties) -> new LampBlock(DyeColor.BLACK, properties), Properties.lamp);

    // Lamp Connector
    public static final Supplier<Block> WHITE_LAMP_CONNECTOR = registerBlockOnly("white_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.WHITE, properties), Properties.weak_wood);
    public static final Supplier<Block> ORANGE_LAMP_CONNECTOR = registerBlockOnly("orange_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.ORANGE, properties), Properties.weak_wood);
    public static final Supplier<Block> MAGENTA_LAMP_CONNECTOR = registerBlockOnly("magenta_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.MAGENTA, properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_BLUE_LAMP_CONNECTOR = registerBlockOnly("light_blue_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.LIGHT_BLUE, properties), Properties.weak_wood);
    public static final Supplier<Block> YELLOW_LAMP_CONNECTOR = registerBlockOnly("yellow_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.YELLOW, properties), Properties.weak_wood);
    public static final Supplier<Block> LIME_LAMP_CONNECTOR = registerBlockOnly("lime_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.LIME, properties), Properties.weak_wood);
    public static final Supplier<Block> PINK_LAMP_CONNECTOR = registerBlockOnly("pink_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.PINK, properties), Properties.weak_wood);
    public static final Supplier<Block> GRAY_LAMP_CONNECTOR = registerBlockOnly("gray_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.GRAY, properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_GRAY_LAMP_CONNECTOR = registerBlockOnly("light_gray_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.LIGHT_GRAY, properties), Properties.weak_wood);
    public static final Supplier<Block> CYAN_LAMP_CONNECTOR = registerBlockOnly("cyan_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.CYAN, properties), Properties.weak_wood);
    public static final Supplier<Block> PURPLE_LAMP_CONNECTOR = registerBlockOnly("purple_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.PURPLE, properties), Properties.weak_wood);
    public static final Supplier<Block> BLUE_LAMP_CONNECTOR = registerBlockOnly("blue_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.BLUE, properties), Properties.weak_wood);
    public static final Supplier<Block> BROWN_LAMP_CONNECTOR = registerBlockOnly("brown_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.BROWN, properties), Properties.weak_wood);
    public static final Supplier<Block> GREEN_LAMP_CONNECTOR = registerBlockOnly("green_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.GREEN, properties), Properties.weak_wood);
    public static final Supplier<Block> RED_LAMP_CONNECTOR = registerBlockOnly("red_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.RED, properties), Properties.weak_wood);
    public static final Supplier<Block> BLACK_LAMP_CONNECTOR = registerBlockOnly("black_lamp_connector", (properties) -> new LampConnectorBlock(DyeColor.BLACK, properties), Properties.weak_wood);

    // Sofa
    public static final Supplier<Block> WHITE_SOFA = registerBlock("white_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> ORANGE_SOFA = registerBlock("orange_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> MAGENTA_SOFA = registerBlock("magenta_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_BLUE_SOFA = registerBlock("light_blue_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> YELLOW_SOFA = registerBlock("yellow_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIME_SOFA = registerBlock("lime_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> PINK_SOFA = registerBlock("pink_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> GRAY_SOFA = registerBlock("gray_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_GRAY_SOFA = registerBlock("light_gray_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> CYAN_SOFA = registerBlock("cyan_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> PURPLE_SOFA = registerBlock("purple_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BLUE_SOFA = registerBlock("blue_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BROWN_SOFA = registerBlock("brown_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> GREEN_SOFA = registerBlock("green_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> RED_SOFA = registerBlock("red_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BLACK_SOFA = registerBlock("black_sofa", (properties) -> new SofaBlock(properties), Properties.weak_wood);

    // Tall Stool
    public static final Supplier<Block> WHITE_TALL_STOOL = registerBlock("white_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> ORANGE_TALL_STOOL = registerBlock("orange_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> MAGENTA_TALL_STOOL = registerBlock("magenta_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_BLUE_TALL_STOOL = registerBlock("light_blue_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> YELLOW_TALL_STOOL = registerBlock("yellow_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIME_TALL_STOOL = registerBlock("lime_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> PINK_TALL_STOOL = registerBlock("pink_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> GRAY_TALL_STOOL = registerBlock("gray_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> LIGHT_GRAY_TALL_STOOL = registerBlock("light_gray_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> CYAN_TALL_STOOL = registerBlock("cyan_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> PURPLE_TALL_STOOL = registerBlock("purple_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BLUE_TALL_STOOL = registerBlock("blue_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BROWN_TALL_STOOL = registerBlock("brown_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> GREEN_TALL_STOOL = registerBlock("green_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> RED_TALL_STOOL = registerBlock("red_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);
    public static final Supplier<Block> BLACK_TALL_STOOL = registerBlock("black_tall_stool", (properties) -> new TallStoolBlock(properties), Properties.weak_wood);

//    // Grandfather Clock
//    public static final Supplier<Block> OAK_GRANDFATHER_CLOCK = registerBlock("oak_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> SPRUCE_GRANDFATHER_CLOCK = registerBlock("spruce_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> BIRCH_GRANDFATHER_CLOCK = registerBlock("birch_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> JUNGLE_GRANDFATHER_CLOCK = registerBlock("jungle_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> ACACIA_GRANDFATHER_CLOCK = registerBlock("acacia_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> DARK_OAK_GRANDFATHER_CLOCK = registerBlock("dark_oak_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> MANGROVE_GRANDFATHER_CLOCK = registerBlock("mangrove_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Supplier<Block> CRIMSON_GRANDFATHER_CLOCK = registerBlock("crimson_grandfather_clock", () -> new GrandfatherClockBlock(Properties.nether_wood));
//    public static final Supplier<Block> WARPED_GRANDFATHER_CLOCK = registerBlock("warped_grandfather_clock", () -> new GrandfatherClockBlock(Properties.nether_wood));
//    public static final Supplier<Block> BAMBOO_GRANDFATHER_CLOCK = registerBlock("bamboo_grandfather_clock", () -> new GrandfatherClockBlock(Properties.bamboo_wood));
//    public static final Supplier<Block> CHERRY_GRANDFATHER_CLOCK = registerBlock("cherry_grandfather_clock", () -> new GrandfatherClockBlock(Properties.cherry_wood));

    public static <T extends Block> Supplier<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
        Supplier<T> supplier = AFRegistry.registerBlock(name, block, properties);
        AFRegistry.registerItem(name, (itemProperties) -> new BlockItem(supplier.get(), itemProperties), new Item.Properties().useBlockDescriptionPrefix(), "tab");
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockHidden(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
        Supplier<T> supplier = AFRegistry.registerBlock(name, block, properties);
        AFRegistry.registerItem(name, (itemProperties) -> new BlockItem(supplier.get(), itemProperties), new Item.Properties().useBlockDescriptionPrefix(), null);
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockOnly(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
        return AFRegistry.registerBlock(name, block, properties);
    }

    public static void registerFlammables() {
        AFRegistry.setFlammable(OAK_CHAIR, 5, 20);
        AFRegistry.setFlammable(SPRUCE_CHAIR, 5, 20);
        AFRegistry.setFlammable(BIRCH_CHAIR, 5, 20);
        AFRegistry.setFlammable(JUNGLE_CHAIR, 5, 20);
        AFRegistry.setFlammable(ACACIA_CHAIR, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_CHAIR, 5, 20);
        AFRegistry.setFlammable(MANGROVE_CHAIR, 5, 20);
        AFRegistry.setFlammable(CHERRY_CHAIR, 5, 20);
        AFRegistry.setFlammable(BAMBOO_CHAIR, 5, 20);

        AFRegistry.setFlammable(OAK_SHELF, 5, 20);
        AFRegistry.setFlammable(SPRUCE_SHELF, 5, 20);
        AFRegistry.setFlammable(BIRCH_SHELF, 5, 20);
        AFRegistry.setFlammable(JUNGLE_SHELF, 5, 20);
        AFRegistry.setFlammable(ACACIA_SHELF, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_SHELF, 5, 20);
        AFRegistry.setFlammable(MANGROVE_SHELF, 5, 20);
        AFRegistry.setFlammable(CHERRY_SHELF, 5, 20);
        AFRegistry.setFlammable(BAMBOO_SHELF, 5, 20);

        AFRegistry.setFlammable(OAK_TABLE, 5, 20);
        AFRegistry.setFlammable(SPRUCE_TABLE, 5, 20);
        AFRegistry.setFlammable(BIRCH_TABLE, 5, 20);
        AFRegistry.setFlammable(JUNGLE_TABLE, 5, 20);
        AFRegistry.setFlammable(ACACIA_TABLE, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_TABLE, 5, 20);
        AFRegistry.setFlammable(MANGROVE_TABLE, 5, 20);
        AFRegistry.setFlammable(CHERRY_TABLE, 5, 20);
        AFRegistry.setFlammable(BAMBOO_TABLE, 5, 20);

        AFRegistry.setFlammable(OAK_SHUTTER, 5, 20);
        AFRegistry.setFlammable(SPRUCE_SHUTTER, 5, 20);
        AFRegistry.setFlammable(BIRCH_SHUTTER, 5, 20);
        AFRegistry.setFlammable(JUNGLE_SHUTTER, 5, 20);
        AFRegistry.setFlammable(ACACIA_SHUTTER, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_SHUTTER, 5, 20);
        AFRegistry.setFlammable(MANGROVE_SHUTTER, 5, 20);
        AFRegistry.setFlammable(CHERRY_SHUTTER, 5, 20);
        AFRegistry.setFlammable(BAMBOO_SHUTTER, 5, 20);

        AFRegistry.setFlammable(OAK_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(SPRUCE_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(BIRCH_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(JUNGLE_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(ACACIA_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(MANGROVE_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(CHERRY_FLOWER_BOX, 5, 20);
        AFRegistry.setFlammable(BAMBOO_FLOWER_BOX, 5, 20);

        AFRegistry.setFlammable(WHITE_STOOL, 5, 20);
        AFRegistry.setFlammable(ORANGE_STOOL, 5, 20);
        AFRegistry.setFlammable(MAGENTA_STOOL, 5, 20);
        AFRegistry.setFlammable(LIGHT_BLUE_STOOL, 5, 20);
        AFRegistry.setFlammable(YELLOW_STOOL, 5, 20);
        AFRegistry.setFlammable(LIME_STOOL, 5, 20);
        AFRegistry.setFlammable(PINK_STOOL, 5, 20);
        AFRegistry.setFlammable(GRAY_STOOL, 5, 20);
        AFRegistry.setFlammable(LIGHT_GRAY_STOOL, 5, 20);
        AFRegistry.setFlammable(CYAN_STOOL, 5, 20);
        AFRegistry.setFlammable(PURPLE_STOOL, 5, 20);
        AFRegistry.setFlammable(BLUE_STOOL, 5, 20);
        AFRegistry.setFlammable(BROWN_STOOL, 5, 20);
        AFRegistry.setFlammable(GREEN_STOOL, 5, 20);
        AFRegistry.setFlammable(RED_STOOL, 5, 20);
        AFRegistry.setFlammable(BLACK_STOOL, 5, 20);

        AFRegistry.setFlammable(WHITE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(ORANGE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(MAGENTA_CURTAIN, 5, 20);
        AFRegistry.setFlammable(LIGHT_BLUE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(YELLOW_CURTAIN, 5, 20);
        AFRegistry.setFlammable(LIME_CURTAIN, 5, 20);
        AFRegistry.setFlammable(PINK_CURTAIN, 5, 20);
        AFRegistry.setFlammable(GRAY_CURTAIN, 5, 20);
        AFRegistry.setFlammable(LIGHT_GRAY_CURTAIN, 5, 20);
        AFRegistry.setFlammable(CYAN_CURTAIN, 5, 20);
        AFRegistry.setFlammable(PURPLE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(BLUE_CURTAIN, 5, 20);
        AFRegistry.setFlammable(BROWN_CURTAIN, 5, 20);
        AFRegistry.setFlammable(GREEN_CURTAIN, 5, 20);
        AFRegistry.setFlammable(RED_CURTAIN, 5, 20);
        AFRegistry.setFlammable(BLACK_CURTAIN, 5, 20);

        AFRegistry.setFlammable(OAK_BENCH, 5, 20);
        AFRegistry.setFlammable(SPRUCE_BENCH, 5, 20);
        AFRegistry.setFlammable(BIRCH_BENCH, 5, 20);
        AFRegistry.setFlammable(JUNGLE_BENCH, 5, 20);
        AFRegistry.setFlammable(ACACIA_BENCH, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_BENCH, 5, 20);
        AFRegistry.setFlammable(MANGROVE_BENCH, 5, 20);
        AFRegistry.setFlammable(CHERRY_BENCH, 5, 20);
        AFRegistry.setFlammable(BAMBOO_BENCH, 5, 20);

        AFRegistry.setFlammable(OAK_DRAWER, 5, 20);
        AFRegistry.setFlammable(SPRUCE_DRAWER, 5, 20);
        AFRegistry.setFlammable(BIRCH_DRAWER, 5, 20);
        AFRegistry.setFlammable(JUNGLE_DRAWER, 5, 20);
        AFRegistry.setFlammable(ACACIA_DRAWER, 5, 20);
        AFRegistry.setFlammable(DARK_OAK_DRAWER, 5, 20);
        AFRegistry.setFlammable(MANGROVE_DRAWER, 5, 20);
        AFRegistry.setFlammable(CHERRY_DRAWER, 5, 20);
        AFRegistry.setFlammable(BAMBOO_DRAWER, 5, 20);

        AFRegistry.setFlammable(WHITE_LAMP, 5, 20);
        AFRegistry.setFlammable(ORANGE_LAMP, 5, 20);
        AFRegistry.setFlammable(MAGENTA_LAMP, 5, 20);
        AFRegistry.setFlammable(LIGHT_BLUE_LAMP, 5, 20);
        AFRegistry.setFlammable(YELLOW_LAMP, 5, 20);
        AFRegistry.setFlammable(LIME_LAMP, 5, 20);
        AFRegistry.setFlammable(PINK_LAMP, 5, 20);
        AFRegistry.setFlammable(GRAY_LAMP, 5, 20);
        AFRegistry.setFlammable(LIGHT_GRAY_LAMP, 5, 20);
        AFRegistry.setFlammable(CYAN_LAMP, 5, 20);
        AFRegistry.setFlammable(PURPLE_LAMP, 5, 20);
        AFRegistry.setFlammable(BLUE_LAMP, 5, 20);
        AFRegistry.setFlammable(BROWN_LAMP, 5, 20);
        AFRegistry.setFlammable(GREEN_LAMP, 5, 20);
        AFRegistry.setFlammable(RED_LAMP, 5, 20);
        AFRegistry.setFlammable(BLACK_LAMP, 5, 20);

        AFRegistry.setFlammable(WHITE_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(ORANGE_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(MAGENTA_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(LIGHT_BLUE_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(YELLOW_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(LIME_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(PINK_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(GRAY_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(LIGHT_GRAY_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(CYAN_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(PURPLE_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(BLUE_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(BROWN_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(GREEN_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(RED_LAMP_CONNECTOR, 5, 20);
        AFRegistry.setFlammable(BLACK_LAMP_CONNECTOR, 5, 20);

        AFRegistry.setFlammable(WHITE_SOFA, 5, 20);
        AFRegistry.setFlammable(ORANGE_SOFA, 5, 20);
        AFRegistry.setFlammable(MAGENTA_SOFA, 5, 20);
        AFRegistry.setFlammable(LIGHT_BLUE_SOFA, 5, 20);
        AFRegistry.setFlammable(YELLOW_SOFA, 5, 20);
        AFRegistry.setFlammable(LIME_SOFA, 5, 20);
        AFRegistry.setFlammable(PINK_SOFA, 5, 20);
        AFRegistry.setFlammable(GRAY_SOFA, 5, 20);
        AFRegistry.setFlammable(LIGHT_GRAY_SOFA, 5, 20);
        AFRegistry.setFlammable(CYAN_SOFA, 5, 20);
        AFRegistry.setFlammable(PURPLE_SOFA, 5, 20);
        AFRegistry.setFlammable(BLUE_SOFA, 5, 20);
        AFRegistry.setFlammable(BROWN_SOFA, 5, 20);
        AFRegistry.setFlammable(GREEN_SOFA, 5, 20);
        AFRegistry.setFlammable(RED_SOFA, 5, 20);
        AFRegistry.setFlammable(BLACK_SOFA, 5, 20);

        AFRegistry.setFlammable(WHITE_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(ORANGE_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(MAGENTA_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(LIGHT_BLUE_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(YELLOW_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(LIME_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(PINK_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(GRAY_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(LIGHT_GRAY_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(CYAN_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(PURPLE_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(BLUE_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(BROWN_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(GREEN_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(RED_TALL_STOOL, 5, 20);
        AFRegistry.setFlammable(BLACK_TALL_STOOL, 5, 20);
    }

    public static void init() {}
}