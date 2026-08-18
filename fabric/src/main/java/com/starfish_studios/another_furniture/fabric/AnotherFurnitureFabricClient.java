package com.starfish_studios.another_furniture.fabric;

import com.starfish_studios.another_furniture.client.AnotherFurnitureClient;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;

public class AnotherFurnitureFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AnotherFurnitureClient.init();
        //BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
        //        AFBlocks.OAK_TABLE.get(), AFBlocks.SPRUCE_TABLE.get(), AFBlocks.BIRCH_TABLE.get(), AFBlocks.JUNGLE_TABLE.get(), AFBlocks.ACACIA_TABLE.get(), AFBlocks.DARK_OAK_TABLE.get(), AFBlocks.MANGROVE_TABLE.get(), AFBlocks.CHERRY_TABLE.get(), AFBlocks.BAMBOO_TABLE.get(), AFBlocks.CRIMSON_TABLE.get(), AFBlocks.WARPED_TABLE.get(),
        //        AFBlocks.OAK_CHAIR.get(), AFBlocks.SPRUCE_CHAIR.get(), AFBlocks.BIRCH_CHAIR.get(), AFBlocks.JUNGLE_CHAIR.get(), AFBlocks.ACACIA_CHAIR.get(), AFBlocks.DARK_OAK_CHAIR.get(), AFBlocks.MANGROVE_CHAIR.get(), AFBlocks.CHERRY_CHAIR.get(), AFBlocks.BAMBOO_CHAIR.get(), AFBlocks.CRIMSON_CHAIR.get(), AFBlocks.WARPED_CHAIR.get(),
        //        AFBlocks.OAK_BENCH.get(), AFBlocks.SPRUCE_BENCH.get(), AFBlocks.BIRCH_BENCH.get(), AFBlocks.JUNGLE_BENCH.get(), AFBlocks.ACACIA_BENCH.get(), AFBlocks.DARK_OAK_BENCH.get(), AFBlocks.MANGROVE_BENCH.get(), AFBlocks.CHERRY_BENCH.get(), AFBlocks.BAMBOO_BENCH.get(), AFBlocks.CRIMSON_BENCH.get(), AFBlocks.WARPED_BENCH.get(),
        //        AFBlocks.OAK_SHUTTER.get(), AFBlocks.SPRUCE_SHUTTER.get(), AFBlocks.BIRCH_SHUTTER.get(), AFBlocks.JUNGLE_SHUTTER.get(), AFBlocks.ACACIA_SHUTTER.get(), AFBlocks.DARK_OAK_SHUTTER.get(), AFBlocks.MANGROVE_SHUTTER.get(), AFBlocks.CRIMSON_SHUTTER.get(), AFBlocks.WARPED_SHUTTER.get(),
        //        AFBlocks.WHITE_CURTAIN.get(), AFBlocks.ORANGE_CURTAIN.get(), AFBlocks.MAGENTA_CURTAIN.get(), AFBlocks.LIGHT_BLUE_CURTAIN.get(), AFBlocks.YELLOW_CURTAIN.get(), AFBlocks.LIME_CURTAIN.get(), AFBlocks.PINK_CURTAIN.get(), AFBlocks.GRAY_CURTAIN.get(), AFBlocks.LIGHT_GRAY_CURTAIN.get(), AFBlocks.CYAN_CURTAIN.get(), AFBlocks.PURPLE_CURTAIN.get(), AFBlocks.BLUE_CURTAIN.get(), AFBlocks.BROWN_CURTAIN.get(), AFBlocks.GREEN_CURTAIN.get(), AFBlocks.RED_CURTAIN.get(), AFBlocks.BLACK_CURTAIN.get(),
        //        AFBlocks.WHITE_SOFA.get(), AFBlocks.ORANGE_SOFA.get(), AFBlocks.MAGENTA_SOFA.get(), AFBlocks.LIGHT_BLUE_SOFA.get(), AFBlocks.YELLOW_SOFA.get(), AFBlocks.LIME_SOFA.get(), AFBlocks.PINK_SOFA.get(), AFBlocks.GRAY_SOFA.get(), AFBlocks.LIGHT_GRAY_SOFA.get(), AFBlocks.CYAN_SOFA.get(), AFBlocks.PURPLE_SOFA.get(), AFBlocks.BLUE_SOFA.get(), AFBlocks.BROWN_SOFA.get(), AFBlocks.GREEN_SOFA.get(), AFBlocks.RED_SOFA.get(), AFBlocks.BLACK_SOFA.get(),
        //        AFBlocks.WHITE_LAMP.get(), AFBlocks.ORANGE_LAMP.get(), AFBlocks.MAGENTA_LAMP.get(), AFBlocks.LIGHT_BLUE_LAMP.get(), AFBlocks.YELLOW_LAMP.get(), AFBlocks.LIME_LAMP.get(), AFBlocks.PINK_LAMP.get(), AFBlocks.GRAY_LAMP.get(), AFBlocks.LIGHT_GRAY_LAMP.get(), AFBlocks.CYAN_LAMP.get(), AFBlocks.PURPLE_LAMP.get(), AFBlocks.BLUE_LAMP.get(), AFBlocks.BROWN_LAMP.get(), AFBlocks.GREEN_LAMP.get(), AFBlocks.RED_LAMP.get(), AFBlocks.BLACK_LAMP.get()//,
        //        //AFBlocks.OAK_GRANDFATHER_CLOCK.get(), AFBlocks.SPRUCE_GRANDFATHER_CLOCK.get(), AFBlocks.BIRCH_GRANDFATHER_CLOCK.get(), AFBlocks.JUNGLE_GRANDFATHER_CLOCK.get(), AFBlocks.ACACIA_GRANDFATHER_CLOCK.get(), AFBlocks.DARK_OAK_GRANDFATHER_CLOCK.get(), AFBlocks.MANGROVE_GRANDFATHER_CLOCK.get(), AFBlocks.CRIMSON_GRANDFATHER_CLOCK.get(), AFBlocks.WARPED_GRANDFATHER_CLOCK.get()
        //);
        //EntityModelLayerRegistry.registerModelLayer(ServiceBellButtonRenderer.SERVICE_BELL_MODEL, ServiceBellButtonRenderer::createBodyLayer);
        //EntityModelLayerRegistry.registerModelLayer(GrandfatherClockRenderer.GRANDFATHER_CLOCK_MODEL, GrandfatherClockRenderer::createBodyLayer);
    }
}
