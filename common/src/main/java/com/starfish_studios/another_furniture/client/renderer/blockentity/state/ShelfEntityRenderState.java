package com.starfish_studios.another_furniture.client.renderer.blockentity.state;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Display.ItemDisplay.ItemRenderState;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class ShelfEntityRenderState extends BlockEntityRenderState {
    private Direction facing;
    private NonNullList<ItemStackRenderState> items;

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
}
