package com.starfish_studios.another_furniture.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import com.google.common.base.Function;

import java.util.Collection;
import java.util.function.Supplier;

public class AFRegistry {

    @ExpectPlatform
    public static Block registerBlockItem(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Block registerBlock(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> registerBlockEntityType(String name, Supplier<T> blockEntity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BlockEntitySupplier<T> blockEntity, Block... validBlocks) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface BlockEntitySupplier<T extends BlockEntity> {
        @NotNull T create(BlockPos var1, BlockState var2);
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderers(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererProvider<T, S> renderProvider) {
        throw new AssertionError();
    }

    public static <T extends Block> void setFlammable(Supplier<T> block, int encouragement, int flammability) {
        setFlammable(Blocks.FIRE, block, encouragement, flammability);
    }

    public static void setFlammable(Block block, int encouragement, int flammability) {
        setFlammable(Blocks.FIRE, block, encouragement, flammability);
    }

    @ExpectPlatform
    public static <T extends Block> void setFlammable(Block fireBlock, Supplier<T> block, int encouragement, int flammability) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setFlammable(Block fireBlock, Block block, int encouragement, int flammability) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String mod) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isFakePlayer(Player player) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<Item> getAllModItems() {
        throw new AssertionError();
    }
}
