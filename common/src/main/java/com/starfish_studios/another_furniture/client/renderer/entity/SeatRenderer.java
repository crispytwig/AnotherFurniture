package com.starfish_studios.another_furniture.client.renderer.entity;

import com.starfish_studios.another_furniture.entity.SeatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

@Environment(value= EnvType.CLIENT)
public class SeatRenderer extends EntityRenderer<SeatEntity, EntityRenderState> {
    public SeatRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }
    
    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}