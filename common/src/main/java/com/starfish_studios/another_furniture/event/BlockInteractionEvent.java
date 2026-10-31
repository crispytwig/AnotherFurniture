package com.starfish_studios.another_furniture.event;

import com.starfish_studios.another_furniture.block.ShutterBlock;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BlockInteractionEvent {
    public static InteractionResult use(Player player, Level level, InteractionHand hand, BlockHitResult hit) {
        BlockState state = level.getBlockState(hit.getBlockPos());

        if (state.is(AFBlockTags.CAN_USE_SHUTTERS_THROUGH) && hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty())
            return tryUseShutter(player, level, hand, hit);

        return InteractionResult.PASS;

    }

    public static InteractionResult tryUseShutter(Player player, Level level, InteractionHand hand, BlockHitResult hit) {

        BlockPos frontPos = hit.getBlockPos().relative(hit.getDirection().getOpposite());
        BlockState frontState = level.getBlockState(frontPos);
        if (frontState.getBlock() instanceof ShutterBlock shutterBlock) {
            player.swing(hand, SwingAnimation.DEFAULT, true);
            return shutterBlock.toggleShutters(frontState, level, frontPos, player);
        }


        return InteractionResult.PASS;
    }
}
