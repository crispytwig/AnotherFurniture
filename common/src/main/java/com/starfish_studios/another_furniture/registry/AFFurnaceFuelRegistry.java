package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Set;

public class AFFurnaceFuelRegistry {
    private static final Set<Class<? extends Block>> FUEL_BLOCKS = Set.of(
            BenchBlock.class, ChairBlock.class, CurtainBlock.class, DrawerBlock.class,
            LampBlock.class, FlowerBoxBlock.class, ShelfBlock.class, ShutterBlock.class,
            SofaBlock.class, StoolBlock.class, TableBlock.class, TallStoolBlock.class);

    public static boolean isFurnitureFuel(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(AnotherFurniture.MOD_ID)
                && item instanceof BlockItem blockItem
                && FUEL_BLOCKS.contains(blockItem.getBlock().getClass());
    }

    public static CookingFuel cookingFuel() {
        return new CookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS,
                ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER);
    }
}
