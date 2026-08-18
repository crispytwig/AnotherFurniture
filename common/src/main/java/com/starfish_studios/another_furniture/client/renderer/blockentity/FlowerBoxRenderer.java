package com.starfish_studios.another_furniture.client.renderer.blockentity;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.starfish_studios.another_furniture.block.FlowerBoxBlock;
import com.starfish_studios.another_furniture.block.entity.FlowerBoxBlockEntity;
import com.starfish_studios.another_furniture.client.renderer.blockentity.state.FlowerBoxEntityRenderState;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Display.BlockDisplay.BlockRenderState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

@Environment(value= EnvType.CLIENT)
public class FlowerBoxRenderer implements BlockEntityRenderer<FlowerBoxBlockEntity, FlowerBoxEntityRenderState> {
    private final BlockModelResolver blockModelResolver;
    public FlowerBoxRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver = context.blockModelResolver();
    }
    
    @Override
    public FlowerBoxEntityRenderState createRenderState() {
        return new FlowerBoxEntityRenderState();
    }

    @Override
    public void extractRenderState(FlowerBoxBlockEntity blockEntity, FlowerBoxEntityRenderState state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        state.setFacing(blockEntity.getBlockState().getValue(FlowerBoxBlock.FACING));

        NonNullList<NonNullList<BlockModelRenderState>> items = NonNullList.withSize(2, NonNullList.create());
        for (int i = 0; i < 2; i++) {
            ItemStack stack = blockEntity.getItems().get(i);
            if (!stack.isEmpty()) {
                if (stack.isEmpty()) continue;
            
                Item item = stack.getItem();
                Block block = ((BlockItem) item).getBlock();
                NonNullList<BlockModelRenderState> itemStates = NonNullList.create();
                if (block instanceof DoublePlantBlock) {
                    BlockState lower = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
                    BlockState upper = block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
                    BlockModelRenderState lowerState = new BlockModelRenderState();
                    BlockModelRenderState upperState = new BlockModelRenderState();
                    blockModelResolver.update(lowerState, lower, BlockDisplayContext.create());
                    blockModelResolver.update(upperState, upper, BlockDisplayContext.create());
                    itemStates.add(lowerState);
                    itemStates.add(upperState);
                } else {
                    BlockState blockState = block.defaultBlockState();
                    BlockModelRenderState renderState = new BlockModelRenderState();
                    blockModelResolver.update(renderState, blockState, BlockDisplayContext.create());
                    itemStates.add(renderState);
                }
                items.set(i, itemStates);
            }
        }

        state.setItems(items);
        state.setAttached(blockEntity.getBlockState().getValue(FlowerBoxBlock.ATTACHED));
    }

    @Override
    public void submit(FlowerBoxEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        Direction facing = state.getFacing().getOpposite();
        float rotation = -facing.toYRot();

        poseStack.pushPose();

        poseStack.scale(0.7f, 0.7f, 0.7f);
        poseStack.translate(0f, 0.4f, 0f);

        for (int i = 0; i < 2; i++) {
            NonNullList<BlockModelRenderState> itemStates = state.getItems().get(i);
            if (itemStates.isEmpty()) continue;

            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // rotate based on direction
            poseStack.translate(0.6f - 0.8 * i, 0.001f * i, 0.2f); // position each flower at left and right
            poseStack.translate(0f, 0.001f * i, 0.001f * i); // prevent z-clipping

            switch (state.getFacing()) { // correct position based on direction
                case EAST -> poseStack.translate(0f, 0f, -1.4f);
                case WEST -> poseStack.translate(-1.4f, 0f, 0);
                case SOUTH -> poseStack.translate(-1.4f, 0f, -1.4f);
            }

            if (state.isAttached()) { // correct position when attached
                poseStack.translate(0f, 0.9f, 0.36f);
            }

            for (BlockModelRenderState itemState : itemStates) {
                itemState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                poseStack.translate(0f, 1.0f, 0f); // move up for the next part of the item (for double plants)
            }
            poseStack.popPose();
        }

        poseStack.popPose();
    }
}
