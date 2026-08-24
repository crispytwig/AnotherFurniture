
package com.starfish_studios.another_furniture.client.renderer.blockentity;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.entity.ServiceBellBlockEntity;
import com.starfish_studios.another_furniture.client.renderer.blockentity.state.ServiceBellRenderState;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.phys.Vec3;

@Environment(value= EnvType.CLIENT)
public class ServiceBellButtonRenderer implements BlockEntityRenderer<ServiceBellBlockEntity, ServiceBellRenderState> {
    public static final SpriteId BELL_TEXTURE = new SpriteId(TextureAtlas.LOCATION_BLOCKS, AnotherFurniture.res("block/service_bell"));

    private final SpriteGetter sprites;
    private final ModelPart button;

    public ServiceBellButtonRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.button = createBodyLayer().bakeRoot().getChild("button");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("button", CubeListBuilder.create().texOffs(19, 11).addBox(-9.0F, -7.0F, 7.0F, 2.0F, 1.0F, 2.0F), PartPose.ZERO);
        partdefinition.getChild("button").addOrReplaceChild("button_shaft", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -6.0F, 7.5F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public ServiceBellRenderState createRenderState() {
        return new ServiceBellRenderState();
    }

    @Override
    public void extractRenderState(ServiceBellBlockEntity blockEntity, ServiceBellRenderState state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        state.isPressed = blockEntity.pressed;
        state.pressProgress = blockEntity.ticks;
    }

    @Override
    public void submit(ServiceBellRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        float f3 = 0f;
        if (state.isPressed) f3 = -(state.pressProgress / 25.0f);

        poseStack.pushPose();

        poseStack.translate(0, f3, 0);
        poseStack.scale(-1f, -1f, 1f);

        submitNodeCollector.submitModelPart(
                this.button,
                poseStack,
                BELL_TEXTURE.renderType(RenderTypes::entitySolid),
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                this.sprites.get(BELL_TEXTURE),
                -1,
                state.breakProgress
        );

        poseStack.popPose();
    }
}
