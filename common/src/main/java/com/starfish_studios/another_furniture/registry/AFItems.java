package com.starfish_studios.another_furniture.registry;

import com.starfish_studios.another_furniture.item.HammerItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class AFItems {

    public static final Supplier<Item> FURNITURE_HAMMER = AFRegistry.registerItem("furniture_hammer", (properties) -> new HammerItem(properties), new Item.Properties().durability(892), "tab");

    public static void init() {}
}