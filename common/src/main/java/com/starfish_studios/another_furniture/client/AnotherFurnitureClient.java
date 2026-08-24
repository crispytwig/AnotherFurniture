package com.starfish_studios.another_furniture.client;

import com.starfish_studios.another_furniture.client.renderer.blockentity.*;
import com.starfish_studios.another_furniture.client.renderer.entity.SeatRenderer;
import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import com.starfish_studios.another_furniture.registry.AFEntityTypes;
import com.starfish_studios.another_furniture.registry.AFRegistry;

public class AnotherFurnitureClient {
    //public static final ModelResourceLocation GREEN_WALLPAPER = new ModelResourceLocation(AnotherFurniture.MOD_ID + ":green_wallpaper");
    //public static final ModelResourceLocation RED_STRIPES_WALLPAPER = new ModelResourceLocation(AnotherFurniture.MOD_ID + ":red_stripes_wallpaper");

    public static void init() {
        AFRegistry.registerEntityRenderers(AFEntityTypes.SEAT, SeatRenderer::new);

        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SHELF, ShelfRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.SERVICE_BELL, ServiceBellButtonRenderer::new);
        AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.FLOWER_BOX, FlowerBoxRenderer::new);
        //AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.GRANDFATHER_CLOCK, GrandfatherClockRenderer::new);
        //AFRegistry.registerBlockEntityRenderer(AFBlockEntityTypes.TOMBSTONE, TombstoneRenderer::new);

    }

}
