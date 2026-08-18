package com.starfish_studios.another_furniture.registry;

import com.google.common.base.Function;
import com.mojang.datafixers.util.Function10;
import com.mojang.datafixers.util.Function3;
import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.block.ShelfBlock;
import com.starfish_studios.another_furniture.block.properties.ModBlockStateProperties;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiFunction;
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
    public static final Block OAK_CHAIR = registerBlockItem("oak_chair", (p) -> new ChairBlock(1, p), Properties.oak_wood);
    public static final Block SPRUCE_CHAIR = registerBlockItem("spruce_chair", (p) -> new ChairBlock(2, p), Properties.spruce_wood);
    public static final Block BIRCH_CHAIR = registerBlockItem("birch_chair", (p) -> new ChairBlock(3, p), Properties.birch_wood);
    public static final Block JUNGLE_CHAIR = registerBlockItem("jungle_chair", (p) -> new ChairBlock(4, p), Properties.jungle_wood);
    public static final Block ACACIA_CHAIR = registerBlockItem("acacia_chair", (p) -> new ChairBlock(5, p), Properties.acacia_wood);
    public static final Block DARK_OAK_CHAIR = registerBlockItem("dark_oak_chair", (p) -> new ChairBlock(6, p), Properties.dark_oak_wood);
    public static final Block MANGROVE_CHAIR = registerBlockItem("mangrove_chair", (p) -> new ChairBlock(7, p), Properties.mangrove_wood);
    public static final Block CHERRY_CHAIR = registerBlockItem("cherry_chair", (p) -> new ChairBlock(8, p), Properties.cherry_wood);
    public static final Block BAMBOO_CHAIR = registerBlockItem("bamboo_chair", (p) -> new ChairBlock(9, p), Properties.bamboo_wood);
    public static final Block CRIMSON_CHAIR = registerBlockItem("crimson_chair", (p) -> new ChairBlock(10, p), Properties.crimson_wood);
    public static final Block WARPED_CHAIR = registerBlockItem("warped_chair", (p) -> new ChairBlock(11, p), Properties.warped_wood);

    // Shelf
    public static final Block OAK_SHELF = registerBlockItem("oak_shelf", (p) -> new ShelfBlock(p), Properties.oak_wood);
    public static final Block SPRUCE_SHELF = registerBlockItem("spruce_shelf", (p) -> new ShelfBlock(p), Properties.spruce_wood);
    public static final Block BIRCH_SHELF = registerBlockItem("birch_shelf", (p) -> new ShelfBlock(p), Properties.birch_wood);
    public static final Block JUNGLE_SHELF = registerBlockItem("jungle_shelf", (p) -> new ShelfBlock(p), Properties.jungle_wood);
    public static final Block ACACIA_SHELF = registerBlockItem("acacia_shelf", (p) -> new ShelfBlock(p), Properties.acacia_wood);
    public static final Block DARK_OAK_SHELF = registerBlockItem("dark_oak_shelf", (p) -> new ShelfBlock(p), Properties.dark_oak_wood);
    public static final Block MANGROVE_SHELF = registerBlockItem("mangrove_shelf", (p) -> new ShelfBlock(p), Properties.mangrove_wood);
    public static final Block CHERRY_SHELF = registerBlockItem("cherry_shelf", (p) -> new ShelfBlock(p), Properties.cherry_wood);
    public static final Block BAMBOO_SHELF = registerBlockItem("bamboo_shelf", (p) -> new ShelfBlock(p), Properties.bamboo_wood);
    public static final Block CRIMSON_SHELF = registerBlockItem("crimson_shelf", (p) -> new ShelfBlock(p), Properties.crimson_wood);
    public static final Block WARPED_SHELF = registerBlockItem("warped_shelf", (p) -> new ShelfBlock(p), Properties.warped_wood);

    // Small Shelf
//    public static final Block OAK_SMALL_SHELF = registerBlock("oak_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block SPRUCE_SMALL_SHELF = registerBlock("spruce_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block BIRCH_SMALL_SHELF = registerBlock("birch_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block JUNGLE_SMALL_SHELF = registerBlock("jungle_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block ACACIA_SMALL_SHELF = registerBlock("acacia_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block DARK_OAK_SMALL_SHELF = registerBlock("dark_oak_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block MANGROVE_SMALL_SHELF = registerBlock("mangrove_small_shelf", () -> new SmallShelfBlock(Properties.wood));
//    public static final Block CHERRY_SMALL_SHELF = registerBlock("cherry_small_shelf", () -> new SmallShelfBlock(Properties.cherry_wood));
//    public static final Block BAMBOO_SMALL_SHELF = registerBlock("bamboo_small_shelf", () -> new SmallShelfBlock(Properties.bamboo_wood));
//    public static final Block CRIMSON_SMALL_SHELF = registerBlock("crimson_small_shelf", () -> new SmallShelfBlock(Properties.nether_wood));
//    public static final Block WARPED_SMALL_SHELF = registerBlock("warped_small_shelf", () -> new SmallShelfBlock(Properties.nether_wood));

    // Table
    public static final Block OAK_TABLE = registerBlockItem("oak_table", (p) -> new TableBlock(p), Properties.oak_wood);
    public static final Block SPRUCE_TABLE = registerBlockItem("spruce_table", (p) -> new TableBlock(p), Properties.spruce_wood);
    public static final Block BIRCH_TABLE = registerBlockItem("birch_table", (p) -> new TableBlock(p), Properties.birch_wood);
    public static final Block JUNGLE_TABLE = registerBlockItem("jungle_table", (p) -> new TableBlock(p), Properties.jungle_wood);
    public static final Block ACACIA_TABLE = registerBlockItem("acacia_table", (p) -> new TableBlock(p), Properties.acacia_wood);
    public static final Block DARK_OAK_TABLE = registerBlockItem("dark_oak_table", (p) -> new TableBlock(p), Properties.dark_oak_wood);
    public static final Block MANGROVE_TABLE = registerBlockItem("mangrove_table", (p) -> new TableBlock(p), Properties.mangrove_wood);
    public static final Block CHERRY_TABLE = registerBlockItem("cherry_table", (p) -> new TableBlock(p), Properties.cherry_wood);
    public static final Block BAMBOO_TABLE = registerBlockItem("bamboo_table", (p) -> new TableBlock(p), Properties.bamboo_wood);
    public static final Block CRIMSON_TABLE = registerBlockItem("crimson_table", (p) -> new TableBlock(p), Properties.crimson_wood);
    public static final Block WARPED_TABLE = registerBlockItem("warped_table", (p) -> new TableBlock(p), Properties.warped_wood);

    // Stool
    public static final Block WHITE_STOOL = registerBlockItem("white_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block ORANGE_STOOL = registerBlockItem("orange_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block MAGENTA_STOOL = registerBlockItem("magenta_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block LIGHT_BLUE_STOOL = registerBlockItem("light_blue_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block YELLOW_STOOL = registerBlockItem("yellow_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block LIME_STOOL = registerBlockItem("lime_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block PINK_STOOL = registerBlockItem("pink_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block GRAY_STOOL = registerBlockItem("gray_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block LIGHT_GRAY_STOOL = registerBlockItem("light_gray_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block CYAN_STOOL = registerBlockItem("cyan_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block PURPLE_STOOL = registerBlockItem("purple_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block BLUE_STOOL = registerBlockItem("blue_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block BROWN_STOOL = registerBlockItem("brown_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block GREEN_STOOL = registerBlockItem("green_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block RED_STOOL = registerBlockItem("red_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    public static final Block BLACK_STOOL = registerBlockItem("black_stool", (p) -> new StoolBlock(p), Properties.weak_wood);
    // Shutter
    public static final Block OAK_SHUTTER = registerBlockItem("oak_shutter", (p) -> new ShutterBlock(1, p), Properties.oak_wood.noOcclusion());
    public static final Block SPRUCE_SHUTTER = registerBlockItem("spruce_shutter", (p) -> new ShutterBlock(2, p), Properties.spruce_wood.noOcclusion());
    public static final Block BIRCH_SHUTTER = registerBlockItem("birch_shutter", (p) -> new ShutterBlock(3, p), Properties.birch_wood.noOcclusion());
    public static final Block JUNGLE_SHUTTER = registerBlockItem("jungle_shutter", (p) -> new ShutterBlock(4, p), Properties.jungle_wood.noOcclusion());
    public static final Block ACACIA_SHUTTER = registerBlockItem("acacia_shutter", (p) -> new ShutterBlock(5, p), Properties.acacia_wood.noOcclusion());
    public static final Block DARK_OAK_SHUTTER = registerBlockItem("dark_oak_shutter", (p) -> new ShutterBlock(6, p), Properties.dark_oak_wood.noOcclusion());
    public static final Block MANGROVE_SHUTTER = registerBlockItem("mangrove_shutter", (p) -> new ShutterBlock(7, p), Properties.mangrove_wood.noOcclusion());
    public static final Block CHERRY_SHUTTER = registerBlockItem("cherry_shutter", (p) -> new ShutterBlock(8, p), Properties.cherry_wood.noOcclusion());
    public static final Block BAMBOO_SHUTTER = registerBlockItem("bamboo_shutter", (p) -> new ShutterBlock(9, p), Properties.bamboo_wood.noOcclusion());
    public static final Block CRIMSON_SHUTTER = registerBlockItem("crimson_shutter", (p) -> new ShutterBlock(10, p), Properties.crimson_wood.noOcclusion());
    public static final Block WARPED_SHUTTER = registerBlockItem("warped_shutter", (p) -> new ShutterBlock(11, p), Properties.warped_wood.noOcclusion());

    // Planter Box
    public static final Block OAK_FLOWER_BOX = registerBlockItem("oak_flower_box", (p) -> new FlowerBoxBlock(p), Properties.oak_wood);
    public static final Block SPRUCE_FLOWER_BOX = registerBlockItem("spruce_flower_box", (p) -> new FlowerBoxBlock(p), Properties.spruce_wood);
    public static final Block BIRCH_FLOWER_BOX = registerBlockItem("birch_flower_box", (p) -> new FlowerBoxBlock(p), Properties.birch_wood);
    public static final Block JUNGLE_FLOWER_BOX = registerBlockItem("jungle_flower_box", (p) -> new FlowerBoxBlock(p), Properties.jungle_wood);
    public static final Block ACACIA_FLOWER_BOX = registerBlockItem("acacia_flower_box", (p) -> new FlowerBoxBlock(p), Properties.acacia_wood);
    public static final Block DARK_OAK_FLOWER_BOX = registerBlockItem("dark_oak_flower_box", (p) -> new FlowerBoxBlock(p), Properties.dark_oak_wood);
    public static final Block MANGROVE_FLOWER_BOX = registerBlockItem("mangrove_flower_box", (p) -> new FlowerBoxBlock(p), Properties.mangrove_wood);
    public static final Block CHERRY_FLOWER_BOX = registerBlockItem("cherry_flower_box", (p) -> new FlowerBoxBlock(p), Properties.cherry_wood);
    public static final Block BAMBOO_FLOWER_BOX = registerBlockItem("bamboo_flower_box", (p) -> new FlowerBoxBlock(p), Properties.bamboo_wood);
    public static final Block CRIMSON_FLOWER_BOX = registerBlockItem("crimson_flower_box", (p) -> new FlowerBoxBlock(p), Properties.crimson_wood);
    public static final Block WARPED_FLOWER_BOX = registerBlockItem("warped_flower_box", (p) -> new FlowerBoxBlock(p), Properties.warped_wood);

    // Curtain
    public static final Block WHITE_CURTAIN = registerBlockItem("white_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block ORANGE_CURTAIN = registerBlockItem("orange_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block MAGENTA_CURTAIN = registerBlockItem("magenta_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block LIGHT_BLUE_CURTAIN = registerBlockItem("light_blue_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block YELLOW_CURTAIN = registerBlockItem("yellow_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block LIME_CURTAIN = registerBlockItem("lime_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block PINK_CURTAIN = registerBlockItem("pink_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block GRAY_CURTAIN = registerBlockItem("gray_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block LIGHT_GRAY_CURTAIN = registerBlockItem("light_gray_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block CYAN_CURTAIN = registerBlockItem("cyan_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block PURPLE_CURTAIN = registerBlockItem("purple_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block BLUE_CURTAIN = registerBlockItem("blue_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block BROWN_CURTAIN = registerBlockItem("brown_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block GREEN_CURTAIN = registerBlockItem("green_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block RED_CURTAIN = registerBlockItem("red_curtain", (p) -> new CurtainBlock(p), Properties.curtain);
    public static final Block BLACK_CURTAIN = registerBlockItem("black_curtain", (p) -> new CurtainBlock(p), Properties.curtain);

    // Bell
    public static final Block SERVICE_BELL = registerBlockItem("service_bell", (p) -> new ServiceBellBlock(p), Properties.service_bell);

    // Bench
    public static final Block OAK_BENCH = registerBlockItem("oak_bench", (p) -> new BenchBlock(p), Properties.oak_wood);
    public static final Block SPRUCE_BENCH = registerBlockItem("spruce_bench", (p) -> new BenchBlock(p), Properties.spruce_wood);
    public static final Block BIRCH_BENCH = registerBlockItem("birch_bench", (p) -> new BenchBlock(p), Properties.birch_wood);
    public static final Block JUNGLE_BENCH = registerBlockItem("jungle_bench", (p) -> new BenchBlock(p), Properties.jungle_wood);
    public static final Block ACACIA_BENCH = registerBlockItem("acacia_bench", (p) -> new BenchBlock(p), Properties.acacia_wood);
    public static final Block DARK_OAK_BENCH = registerBlockItem("dark_oak_bench", (p) -> new BenchBlock(p), Properties.dark_oak_wood);
    public static final Block MANGROVE_BENCH = registerBlockItem("mangrove_bench", (p) -> new BenchBlock(p), Properties.mangrove_wood);
    public static final Block CHERRY_BENCH = registerBlockItem("cherry_bench", (p) -> new BenchBlock(p), Properties.cherry_wood);
    public static final Block BAMBOO_BENCH = registerBlockItem("bamboo_bench", (p) -> new BenchBlock(p), Properties.bamboo_wood);
    public static final Block CRIMSON_BENCH = registerBlockItem("crimson_bench", (p) -> new BenchBlock(p), Properties.crimson_wood);
    public static final Block WARPED_BENCH = registerBlockItem("warped_bench", (p) -> new BenchBlock(p), Properties.warped_wood);

    // Drawer
    public static final Block OAK_DRAWER = registerBlockItem("oak_drawer", (p) -> new DrawerBlock(p), Properties.oak_wood);
    public static final Block SPRUCE_DRAWER = registerBlockItem("spruce_drawer", (p) -> new DrawerBlock(p), Properties.spruce_wood);
    public static final Block BIRCH_DRAWER = registerBlockItem("birch_drawer", (p) -> new DrawerBlock(p), Properties.birch_wood);
    public static final Block JUNGLE_DRAWER = registerBlockItem("jungle_drawer", (p) -> new DrawerBlock(p), Properties.jungle_wood);
    public static final Block ACACIA_DRAWER = registerBlockItem("acacia_drawer", (p) -> new DrawerBlock(p), Properties.acacia_wood);
    public static final Block DARK_OAK_DRAWER = registerBlockItem("dark_oak_drawer", (p) -> new DrawerBlock(p), Properties.dark_oak_wood);
    public static final Block MANGROVE_DRAWER = registerBlockItem("mangrove_drawer", (p) -> new DrawerBlock(p), Properties.mangrove_wood);
    public static final Block CHERRY_DRAWER = registerBlockItem("cherry_drawer", (p) -> new DrawerBlock(p), Properties.cherry_wood);
    public static final Block BAMBOO_DRAWER = registerBlockItem("bamboo_drawer", (p) -> new DrawerBlock(p), Properties.bamboo_wood);
    public static final Block CRIMSON_DRAWER = registerBlockItem("crimson_drawer", (p) -> new DrawerBlock(p), Properties.crimson_wood);
    public static final Block WARPED_DRAWER = registerBlockItem("warped_drawer", (p) -> new DrawerBlock(p), Properties.warped_wood);

    // Lamp
    public static final Block WHITE_LAMP = registerBlockItem("white_lamp", (p) -> new LampBlock(DyeColor.WHITE, p), Properties.lamp);
    public static final Block ORANGE_LAMP = registerBlockItem("orange_lamp", (p) -> new LampBlock(DyeColor.ORANGE, p), Properties.lamp);
    public static final Block MAGENTA_LAMP = registerBlockItem("magenta_lamp", (p) -> new LampBlock(DyeColor.MAGENTA, p), Properties.lamp);
    public static final Block LIGHT_BLUE_LAMP = registerBlockItem("light_blue_lamp", (p) -> new LampBlock(DyeColor.LIGHT_BLUE, p), Properties.lamp);
    public static final Block YELLOW_LAMP = registerBlockItem("yellow_lamp", (p) -> new LampBlock(DyeColor.YELLOW, p), Properties.lamp);
    public static final Block LIME_LAMP = registerBlockItem("lime_lamp", (p) -> new LampBlock(DyeColor.LIME, p), Properties.lamp);
    public static final Block PINK_LAMP = registerBlockItem("pink_lamp", (p) -> new LampBlock(DyeColor.PINK, p), Properties.lamp);
    public static final Block GRAY_LAMP = registerBlockItem("gray_lamp", (p) -> new LampBlock(DyeColor.GRAY, p), Properties.lamp);
    public static final Block LIGHT_GRAY_LAMP = registerBlockItem("light_gray_lamp", (p) -> new LampBlock(DyeColor.LIGHT_GRAY, p), Properties.lamp);
    public static final Block CYAN_LAMP = registerBlockItem("cyan_lamp", (p) -> new LampBlock(DyeColor.CYAN, p), Properties.lamp);
    public static final Block PURPLE_LAMP = registerBlockItem("purple_lamp", (p) -> new LampBlock(DyeColor.PURPLE, p), Properties.lamp);
    public static final Block BLUE_LAMP = registerBlockItem("blue_lamp", (p) -> new LampBlock(DyeColor.BLUE, p), Properties.lamp);
    public static final Block BROWN_LAMP = registerBlockItem("brown_lamp", (p) -> new LampBlock(DyeColor.BROWN, p), Properties.lamp);
    public static final Block GREEN_LAMP = registerBlockItem("green_lamp", (p) -> new LampBlock(DyeColor.GREEN, p), Properties.lamp);
    public static final Block RED_LAMP = registerBlockItem("red_lamp", (p) -> new LampBlock(DyeColor.RED, p), Properties.lamp);
    public static final Block BLACK_LAMP = registerBlockItem("black_lamp", (p) -> new LampBlock(DyeColor.BLACK, p), Properties.lamp);

    // Lamp Connector
    public static final Block WHITE_LAMP_CONNECTOR = registerBlockOnly("white_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.WHITE, p), Properties.weak_wood);
    public static final Block ORANGE_LAMP_CONNECTOR = registerBlockOnly("orange_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.ORANGE, p), Properties.weak_wood);
    public static final Block MAGENTA_LAMP_CONNECTOR = registerBlockOnly("magenta_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.MAGENTA, p), Properties.weak_wood);
    public static final Block LIGHT_BLUE_LAMP_CONNECTOR = registerBlockOnly("light_blue_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.LIGHT_BLUE, p), Properties.weak_wood);
    public static final Block YELLOW_LAMP_CONNECTOR = registerBlockOnly("yellow_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.YELLOW, p), Properties.weak_wood);
    public static final Block LIME_LAMP_CONNECTOR = registerBlockOnly("lime_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.LIME, p), Properties.weak_wood);
    public static final Block PINK_LAMP_CONNECTOR = registerBlockOnly("pink_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.PINK, p), Properties.weak_wood);
    public static final Block GRAY_LAMP_CONNECTOR = registerBlockOnly("gray_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.GRAY, p), Properties.weak_wood);
    public static final Block LIGHT_GRAY_LAMP_CONNECTOR = registerBlockOnly("light_gray_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.LIGHT_GRAY, p), Properties.weak_wood);
    public static final Block CYAN_LAMP_CONNECTOR = registerBlockOnly("cyan_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.CYAN, p), Properties.weak_wood);
    public static final Block PURPLE_LAMP_CONNECTOR = registerBlockOnly("purple_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.PURPLE, p), Properties.weak_wood);
    public static final Block BLUE_LAMP_CONNECTOR = registerBlockOnly("blue_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.BLUE, p), Properties.weak_wood);
    public static final Block BROWN_LAMP_CONNECTOR = registerBlockOnly("brown_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.BROWN, p), Properties.weak_wood);
    public static final Block GREEN_LAMP_CONNECTOR = registerBlockOnly("green_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.GREEN, p), Properties.weak_wood);
    public static final Block RED_LAMP_CONNECTOR = registerBlockOnly("red_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.RED, p), Properties.weak_wood);
    public static final Block BLACK_LAMP_CONNECTOR = registerBlockOnly("black_lamp_connector", (p) -> new LampConnectorBlock(DyeColor.BLACK, p), Properties.weak_wood);

    // Sofa
    public static final Block WHITE_SOFA = registerBlockItem("white_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block ORANGE_SOFA = registerBlockItem("orange_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block MAGENTA_SOFA = registerBlockItem("magenta_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block LIGHT_BLUE_SOFA = registerBlockItem("light_blue_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block YELLOW_SOFA = registerBlockItem("yellow_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block LIME_SOFA = registerBlockItem("lime_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block PINK_SOFA = registerBlockItem("pink_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block GRAY_SOFA = registerBlockItem("gray_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block LIGHT_GRAY_SOFA = registerBlockItem("light_gray_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block CYAN_SOFA = registerBlockItem("cyan_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block PURPLE_SOFA = registerBlockItem("purple_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block BLUE_SOFA = registerBlockItem("blue_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block BROWN_SOFA = registerBlockItem("brown_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block GREEN_SOFA = registerBlockItem("green_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block RED_SOFA = registerBlockItem("red_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);
    public static final Block BLACK_SOFA = registerBlockItem("black_sofa", (p) -> new SofaBlock(p), Properties.weak_wood);

    // Tall Stool
    public static final Block WHITE_TALL_STOOL = registerBlockItem("white_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block ORANGE_TALL_STOOL = registerBlockItem("orange_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block MAGENTA_TALL_STOOL = registerBlockItem("magenta_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block LIGHT_BLUE_TALL_STOOL = registerBlockItem("light_blue_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block YELLOW_TALL_STOOL = registerBlockItem("yellow_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block LIME_TALL_STOOL = registerBlockItem("lime_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block PINK_TALL_STOOL = registerBlockItem("pink_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block GRAY_TALL_STOOL = registerBlockItem("gray_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block LIGHT_GRAY_TALL_STOOL = registerBlockItem("light_gray_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block CYAN_TALL_STOOL = registerBlockItem("cyan_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block PURPLE_TALL_STOOL = registerBlockItem("purple_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block BLUE_TALL_STOOL = registerBlockItem("blue_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block BROWN_TALL_STOOL = registerBlockItem("brown_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block GREEN_TALL_STOOL = registerBlockItem("green_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block RED_TALL_STOOL = registerBlockItem("red_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);
    public static final Block BLACK_TALL_STOOL = registerBlockItem("black_tall_stool", (p) -> new TallStoolBlock(p), Properties.weak_wood);

//    // Grandfather Clock
//    public static final Block OAK_GRANDFATHER_CLOCK = registerBlock("oak_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block SPRUCE_GRANDFATHER_CLOCK = registerBlock("spruce_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block BIRCH_GRANDFATHER_CLOCK = registerBlock("birch_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block JUNGLE_GRANDFATHER_CLOCK = registerBlock("jungle_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block ACACIA_GRANDFATHER_CLOCK = registerBlock("acacia_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block DARK_OAK_GRANDFATHER_CLOCK = registerBlock("dark_oak_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block MANGROVE_GRANDFATHER_CLOCK = registerBlock("mangrove_grandfather_clock", () -> new GrandfatherClockBlock(Properties.wood));
//    public static final Block CRIMSON_GRANDFATHER_CLOCK = registerBlock("crimson_grandfather_clock", () -> new GrandfatherClockBlock(Properties.nether_wood));
//    public static final Block WARPED_GRANDFATHER_CLOCK = registerBlock("warped_grandfather_clock", () -> new GrandfatherClockBlock(Properties.nether_wood));
//    public static final Block BAMBOO_GRANDFATHER_CLOCK = registerBlock("bamboo_grandfather_clock", () -> new GrandfatherClockBlock(Properties.bamboo_wood));
//    public static final Block CHERRY_GRANDFATHER_CLOCK = registerBlock("cherry_grandfather_clock", () -> new GrandfatherClockBlock(Properties.cherry_wood));


    private static Block registerBlockItem(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        BlockItemId id = BlockItemId.create(AnotherFurniture.res(name), AnotherFurniture.res(name));
        return AFRegistry.registerBlockItem(id, blockFactory, properties);
    }

    public static Block registerBlockOnly(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        return AFRegistry.registerBlock(name, blockFactory, properties);
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
