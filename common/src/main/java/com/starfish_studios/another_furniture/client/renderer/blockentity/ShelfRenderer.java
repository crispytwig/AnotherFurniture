
package com.starfish_studios.another_furniture.client.renderer.blockentity;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.starfish_studios.another_furniture.block.FlowerBoxBlock;
import com.starfish_studios.another_furniture.block.ShelfBlock;
import com.starfish_studios.another_furniture.block.entity.FlowerBoxBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.client.renderer.blockentity.state.FlowerBoxEntityRenderState;
import com.starfish_studios.another_furniture.client.renderer.blockentity.state.ShelfEntityRenderState;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

@Environment(value= EnvType.CLIENT)
public class ShelfRenderer implements BlockEntityRenderer<ShelfBlockEntity, ShelfEntityRenderState> {
    private final ItemModelResolver itemModelResolver;
    public ShelfRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }
    
    @Override
    public ShelfEntityRenderState createRenderState() {
        return new ShelfEntityRenderState();
    }

    @Override
    public void extractRenderState(ShelfBlockEntity blockEntity, ShelfEntityRenderState state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        state.setFacing(blockEntity.getBlockState().getValue(ShelfBlock.FACING));

        NonNullList<ItemStackRenderState> items = NonNullList.withSize(4, new ItemStackRenderState());
        for (int i = 0; i < 4; i++) {
            ItemStack stack = blockEntity.getItems().get(i);
            if (!stack.isEmpty()) {
                if (stack.isEmpty()) continue;
                ItemStackRenderState renderState = new ItemStackRenderState();
                itemModelResolver.updateForTopItem(renderState, stack, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
                items.set(i, renderState);
            }
        }
        state.setItems(items);
    }

    @Override
    public void submit(ShelfEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        Direction direction = state.getFacing();
        float rotation = -direction.toYRot() + 180f;

        poseStack.pushPose();
        poseStack.translate(0.5, 1.18, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        for (int j = 0; j < state.getItems().size(); j++) {
            ItemStackRenderState renderState = state.getItems().get(j);
            if (renderState.isEmpty()) continue;

            poseStack.pushPose();
            poseStack.translate(0.15 - 0.4 * (j % 2), 0.0, -0.225 + 0.4 * (j / 2));
            poseStack.scale(0.375F, 0.375F, 0.375F);
            renderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    /*@Override
    public void render(ShelfBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = blockEntity.getBlockState().getValue(ShelfBlock.FACING);
        float rotation = -direction.toYRot() + 180f;
        NonNullList<ItemStack> items = blockEntity.getItems();
        poseStack.pushPose();
        poseStack.translate(0.5, 1.18, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        for(int j = 0; j < items.size(); j++) {
            ItemStack stack = items.get(j);
            if (stack.isEmpty()) continue;

            int renderCount = getAmount(stack.getCount());
            for (int i = 0; i < renderCount; ++i) {
                float fx = (-0.10375f * (float)(i - 1) * 0.5f) % 0.09f;
                float fy = (-0.04375f * (float)(i - 1) * 0.5f) % 0.09f;
                float fz = (-0.05375f * (float)(i - 1) * 0.5f) % 0.09f;

                poseStack.pushPose();

                poseStack.translate(0.15 - 0.4 * (j % 2), 0.0, -0.225 + 0.4 * (j / 2));
                poseStack.translate(fx, fy, fz);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, bufferSource, blockEntity.getLevel(), 0);

                poseStack.popPose();
            }

        }
        poseStack.popPose();
    } */

    public int getAmount(int count) {
        if (count > 48) return 5;
        if (count > 32) return 4;
        if (count > 16) return 3;
        if (count > 1) return 2;
        return 1;
    }
}
