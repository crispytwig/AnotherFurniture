package com.starfish_studios.another_furniture.block.entity;

import com.starfish_studios.another_furniture.registry.AFBlockEntityTypes;
import dev.architectury.injectables.annotations.PlatformOnly;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ListBackedContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

public class ShelfBlockEntity extends BlockEntity implements ListBackedContainer {
    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public ShelfBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AFBlockEntityTypes.SHELF.get(), blockPos, blockState);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        this.items.clear();
        ContainerHelper.loadAllItems(input, this.items);
    }
    
    @Override
	public int getMaxStackSize() {
		return 64;
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

    public boolean placeItem(ItemStack stack, int position) {
        if (this.canPlaceItem(position, stack) && !stack.isEmpty()) {
            this.setItem(position, stack.split(stack.getCount()));
            return true;
        }
        return false;
    }

    public boolean removeItem(int index, Player player, Level level) {
        ItemStack stack = this.removeItem(index, this.getItem(index).getCount());
        if (stack.isEmpty()) return false;
        return true;
    }
    
    public ItemStack removeItem(int index) {
        return this.removeItem(index, this.getItem(index).getCount());
    }

    @Override
    public boolean canPlaceItem(final int slot, final ItemStack itemStack) {
        return (this.getItem(slot).isEmpty() || (this.getItem(slot).getCount() < this.getMaxStackSize(itemStack) && ItemStack.isSameItem(itemStack, this.getItem(slot))));
    }

    @Override
	public void setItem(int slot, ItemStack itemStack) {
        ListBackedContainer.super.setItem(slot, itemStack);
        this.markUpdated();
    }

    @Override
	public ItemStack removeItem(int slot, int count) {
        ItemStack stack = ListBackedContainer.super.removeItem(slot, count);
        this.markUpdated();
        return stack;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        ProblemReporter.Collector reporter = new ProblemReporter.Collector();
        TagValueOutput output = TagValueOutput.createWithContext(reporter, registries);
        ContainerHelper.saveAllItems(output, this.items, true);
        return output.buildResult();
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
            double posX = worldPosition.getX() + 0.3 + 0.4 * (i % 2);
            double posY = worldPosition.getY() + 1.0;
            double posZ = worldPosition.getZ() + 0.3 + 0.4 * (i / 2);

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

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
	protected void applyImplicitComponents(DataComponentGetter components) {
		super.applyImplicitComponents(components);
		components.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.items);
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder components) {
		super.collectImplicitComponents(components);
		components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.items));
	}

	@Override
	public void removeComponentsFromTag(ValueOutput output) {
		output.discard("Items");
	}
}