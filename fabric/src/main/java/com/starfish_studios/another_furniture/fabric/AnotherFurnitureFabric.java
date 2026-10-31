package com.starfish_studios.another_furniture.fabric;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.event.BlockInteractionEvent;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.fabric.AFFurnaceFuelRegistryImpl;
import com.starfish_studios.another_furniture.registry.fabric.AFTabsImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class AnotherFurnitureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AnotherFurniture.init();
        AFBlocks.registerFlammables();
        AFFurnaceFuelRegistryImpl.init();
        AFTabsImpl.register();
        UseBlockCallback.EVENT.register(BlockInteractionEvent::use);
    }

}
