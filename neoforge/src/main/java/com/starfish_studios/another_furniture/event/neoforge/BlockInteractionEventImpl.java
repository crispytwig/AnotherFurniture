package com.starfish_studios.another_furniture.event.neoforge;

import com.starfish_studios.another_furniture.event.BlockInteractionEvent;
import com.starfish_studios.another_furniture.AnotherFurniture;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;


@EventBusSubscriber(modid = AnotherFurniture.MOD_ID)
public class BlockInteractionEventImpl {
    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.RightClickBlock event) {
        var result = BlockInteractionEvent.use(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
        if (result.consumesAction()) {
            event.setCancellationResult(result);
            event.setCanceled(true);
        }
    }
}
