package com.starfish_studios.another_furniture.registry.fabric;

import com.google.common.base.Function;
import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.registry.AFRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class AFRegistryImpl {

    public static Block registerBlockItem(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = registerBlock(id.block(), blockFactory, properties);

        registerItem(id.item(), (p) -> new BlockItem(block, p), new Item.Properties().useBlockDescriptionPrefix());

		return block;
    }

    public static Block registerBlock(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        return Registry.register(BuiltInRegistries.BLOCK, key, blockFactory.apply(properties.setId(key)));
    }

    public static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        var key = ResourceKey.create(Registries.BLOCK, AnotherFurniture.res(name));
        return registerBlock(key, blockFactory, properties);
    }

    public static Item registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        var item = Registry.register(BuiltInRegistries.ITEM, key, itemFactory.apply(properties.setId(key)));
        itemList.add(item);
        return item;
    }

    public static Item registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        var key = ResourceKey.create(Registries.ITEM, AnotherFurniture.res(name));
        return registerItem(key, itemFactory, properties);
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        var registry = Registry.register(BuiltInRegistries.SOUND_EVENT, AnotherFurniture.res(name), soundEvent.get());
        return () -> registry;
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, AnotherFurniture.res(name));
        var registry = Registry.register(BuiltInRegistries.ENTITY_TYPE, key, EntityType.Builder.<T>of(factory, category).sized(width, height).build(key));
        return () -> registry;
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> registerBlockEntityType(String name, Supplier<T> blockEntity) {
        var registry = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, AnotherFurniture.res(name), blockEntity.get());
        return () -> registry;
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(AFRegistry.BlockEntitySupplier<T> blockEntity, Block... validBlocks) {
        return FabricBlockEntityTypeBuilder.<T>create(blockEntity::create, validBlocks).build();
    }

    public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        EntityRenderers.register(type.get(), renderProvider);
    }

    public static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T, S> renderProvider) {
        BlockEntityRenderers.register(type.get(), renderProvider);
    }

    public static <T extends Block> void setFlammable(Block fireBlock, Supplier<T> block, int encouragement, int flammability) {
        FlammableBlockRegistry.getInstance(fireBlock).add(block.get(), encouragement, flammability);
    }

    public static void setFlammable(Block fireBlock, Block block, int encouragement, int flammability) {
        FlammableBlockRegistry.getInstance(fireBlock).add(block, encouragement, flammability);
    }

    public static boolean isModLoaded(String mod) {
        return FabricLoader.getInstance().isModLoaded(mod);
    }

    public static boolean isFakePlayer(Player player) {
        return player instanceof ServerPlayer && player.getClass() != ServerPlayer.class;
    }

    // TODO this is a really unclean implementation
    static List<Item> itemList = new ArrayList<>();
    public static Collection<Item> getAllModItems() {
        return itemList;
    }
}
