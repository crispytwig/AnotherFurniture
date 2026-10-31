package com.starfish_studios.another_furniture.registry.fabric;

import com.starfish_studios.another_furniture.registry.AFFurnaceFuelRegistry;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;

public class AFFurnaceFuelRegistryImpl {
    public static void init() {
        CookingFuel fuel = AFFurnaceFuelRegistry.cookingFuel();
        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(
                AFFurnaceFuelRegistry::isFurnitureFuel,
                (builder, item) -> builder.set(DataComponents.COOKING_FUEL, fuel)));
    }
}
