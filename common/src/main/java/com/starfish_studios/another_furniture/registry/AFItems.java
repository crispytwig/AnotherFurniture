package com.starfish_studios.another_furniture.registry;

import com.google.common.base.Function;
import com.starfish_studios.another_furniture.item.HammerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AFItems {

    public static final Item FURNITURE_HAMMER = registerItem("furniture_hammer", (p) -> new HammerItem(p), new Item.Properties().durability(892));

    public static void init() {}

    private static Item registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        return AFRegistry.registerItem(name, itemFactory, properties);
    }
}
