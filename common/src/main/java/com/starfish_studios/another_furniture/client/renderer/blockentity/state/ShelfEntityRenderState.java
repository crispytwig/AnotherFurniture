package com.starfish_studios.another_furniture.client.renderer.blockentity.state;

import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class ShelfEntityRenderState extends BlockEntityRenderState {
    private Direction facing;
    private NonNullList<ItemStackRenderState> items;
    private final int[] renderCounts = new int[4];

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public NonNullList<ItemStackRenderState> getItems() {
        return items;
    }

    public void setItems(NonNullList<ItemStackRenderState> items) {
        this.items = items;
    }

    public int getRenderCount(int slot) {
        return this.renderCounts[slot];
    }

    public void setRenderCount(int slot, int count) {
        this.renderCounts[slot] = count;
    }
}
