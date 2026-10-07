package com.starfish_studios.another_furniture.block.entity;

import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ServiceBellBlockEntity extends BlockEntity {
    public int ticks;
    public boolean pressed;

    public ServiceBellBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AFBlockEntityTypes.SERVICE_BELL.get(), blockPos, blockState);
    }

    public void onHit() {
        if (this.pressed) {
            this.ticks = 0;
        } else {
            this.pressed = true;
        }
        if (this.level != null) {
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, 0);
        }
    }

    @Override
    public boolean triggerEvent(int id, int data) {
        if (id == 1) {
            this.ticks = 0;
            this.pressed = true;
            return true;
        }
        return super.triggerEvent(id, data);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, ServiceBellBlockEntity blockEntity) {
        if (blockEntity.pressed) {
            ++blockEntity.ticks;
        }

        if (blockEntity.ticks >= 5) {
            blockEntity.pressed = false;
            blockEntity.ticks = 0;
        }
    }


}
