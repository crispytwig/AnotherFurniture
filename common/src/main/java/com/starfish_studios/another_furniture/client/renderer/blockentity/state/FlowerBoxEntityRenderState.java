package com.starfish_studios.another_furniture.client.renderer.blockentity.state;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class FlowerBoxEntityRenderState extends BlockEntityRenderState {
    private Direction facing;
    private NonNullList<NonNullList<BlockModelRenderState>> items;
    private boolean attached;

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public NonNullList<NonNullList<BlockModelRenderState>> getItems() {
        return items;
    }

    public void setItems(NonNullList<NonNullList<BlockModelRenderState>> items) {
        this.items = items;
    }

    public boolean isAttached() {
        return attached;
    }

    public void setAttached(boolean attached) {
        this.attached = attached;
    }
}
