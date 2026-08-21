package com.starfish_studios.another_furniture.registry.neoforge;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.mixin.neoforge.FireBlockAccessor;
import com.starfish_studios.another_furniture.registry.AFRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class AFRegistryImpl {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AnotherFurniture.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(AnotherFurniture.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, AnotherFurniture.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, AnotherFurniture.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AnotherFurniture.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AnotherFurniture.MOD_ID);

    private static final HashMap<String, List<Supplier<? extends ItemLike>>> ITEMS_TAB_MAP = new HashMap<>();

    public static <T extends Block> Supplier<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        var key = ResourceKey.create(Registries.BLOCK, AnotherFurniture.res(name));
        return BLOCKS.register(name, () -> factory.apply(properties.setId(key)));
    }

    public static <T extends Item> Supplier<T> registerItem(String name, Function<Item.Properties, T> factory, Item.Properties properties, String tab_id) {
        var key = ResourceKey.create(Registries.ITEM, AnotherFurniture.res(name));
        Supplier<T> registered = ITEMS.register(name, () -> factory.apply(properties.setId(key)));
        if (tab_id != null) {
            ITEMS_TAB_MAP.computeIfAbsent(tab_id, id -> new ArrayList<>()).add(registered);
        }
        return registered;
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        return SOUND_EVENTS.register(name, soundEvent);
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, AnotherFurniture.res(name));
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(key));
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> registerBlockEntityType(String name, Supplier<T> blockEntity) {
        return BLOCK_ENTITY_TYPES.register(name, blockEntity);
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(AFRegistry.BlockEntitySupplier<T> blockEntity, Block... validBlocks) {
        return new BlockEntityType<>(blockEntity::create, validBlocks);
    }

    public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        EntityRenderers.register(type.get(), renderProvider);
    }

    public static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T, S> renderProvider) {
        BlockEntityRenderers.register(type.get(), renderProvider);
    }

    public static <T extends Block> void setFlammable(Block fireBlock, Supplier<T> block, int encouragement, int flammability) {
        ((FireBlockAccessor) fireBlock).invokeSetFlammable(block.get(), encouragement, flammability);
    }

    public static boolean isModLoaded(String mod) {
        return ModList.get().isLoaded(mod);
    }

    public static boolean isFakePlayer(Player player) {
        return player instanceof FakePlayer;
    }

    public static Collection<ItemStack> getAllModItems() {
        List<ItemStack> itemList = new ArrayList<>();
        for (List<Supplier<? extends ItemLike>> items : ITEMS_TAB_MAP.values()) {
            for (Supplier<? extends ItemLike> item : items) {
                itemList.add(new ItemStack(item.get()));
            }
        }
        return itemList;
    }
}
