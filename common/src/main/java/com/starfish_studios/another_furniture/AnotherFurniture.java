package com.starfish_studios.another_furniture;

import com.starfish_studios.another_furniture.registry.*;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherFurniture {
    public static final String MOD_ID = "another_furniture";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        AFBlocks.init();
        AFItems.init();
        AFSoundEvents.init();
        AFEntityTypes.init();
        AFBlockEntityTypes.init();

        AFItemTags.init();
        AFBlockTags.init();
    }

    public static Identifier res(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}