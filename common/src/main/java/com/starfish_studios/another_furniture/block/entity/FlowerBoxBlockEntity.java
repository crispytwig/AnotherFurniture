package com.starfish_studios.another_furniture.block.entity;

import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import dev.architectury.injectables.annotations.PlatformOnly;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

public class FlowerBoxBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public FlowerBoxBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AFBlockEntityTypes.FLOWER_BOX.get(), blockPos, blockState);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public Item getItemFromSlot(int slot) {
        return this.items.get(slot).getItem();
    }

    @Override
    public void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        this.items.clear();
        ContainerHelper.loadAllItems(input, this.items);
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items, true);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(final HolderLookup.Provider registries) {
        CompoundTag compoundtag = new CompoundTag();
        //ContainerHelper.saveAllItems(registries, this.items, true);
        return compoundtag;
    }

    public boolean placeFlower(ItemStack stack, int slot) {

        ItemStack itemstack = this.items.get(slot);
        if (itemstack.isEmpty()) {
            Block block = ((BlockItem)stack.getItem()).getBlock();
            this.getLevel().playSound(null, this.getBlockPos(), block.defaultBlockState().getSoundType().getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            this.items.set(slot, stack.split(1));
            this.markUpdated();
            return true;
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    public void removeAllItems() {
        boolean update = false;
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).isEmpty()) continue;
            double posX = worldPosition.getX() + 0.5;
            double posY = worldPosition.getY() + 0.5;
            double posZ = worldPosition.getZ() + 0.5;

            ItemEntity entity = new ItemEntity(this.level, posX, posY + 0.1, posZ, this.items.get(i).copy());
            this.level.addFreshEntity(entity);
            this.items.set(i, ItemStack.EMPTY);
            update = true;

        }
        if (update) {
            this.markUpdated();
        }
    }

    @PlatformOnly(PlatformOnly.FORGE)
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition.offset(0, 1, 0));
    }
}
