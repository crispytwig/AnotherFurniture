package com.starfish_studios.another_furniture.util.block;

import com.starfish_studios.another_furniture.registry.AFItemTags;
import com.starfish_studios.another_furniture.registry.AFSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public interface HammerableBlock {

    default boolean tryHammerBlock(Property<?> property, ItemStack stack, BlockState state, LevelAccessor level, BlockPos pos, Player player) {
        if (property == null || !state.hasProperty(property)) return false;
        if (!stack.is(AFItemTags.FURNITURE_HAMMER)) return false;
        state = state.cycle(property);
        state = updateAfterCycle(state, level, pos);

        level.setBlock(pos, state, 3);
        level.playSound(null, pos, getUseSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
        return true;
    }

    default BlockState updateAfterCycle(BlockState state, LevelAccessor level, BlockPos pos) {
        return state;
    }

//    default void cycleState(BlockState state, Property<?> property, LevelAccessor level, BlockPos pos) {
//
//        level.setBlock(pos, state.cycle(property), 3);
//    }
//
//    @UnstableApi
//    static Property<?> cycleState(Property<?> property) {
//        if (property instanceof BooleanProperty a) return cycleState(a);
//        if (property instanceof IntegerProperty a) return cycleState(a);
//        if (property instanceof EnumProperty a) return cycleState(a);
//        throw new UnsupportedOperationException("Unknown property type " + property.getName());
//    }

    default SoundEvent getUseSound() {
        return AFSoundEvents.HAMMER_USE.get();
    }



}
