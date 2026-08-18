package com.starfish_studios.another_furniture.client.renderer.entity;

import com.starfish_studios.another_furniture.client.renderer.entity.state.SeatEntityRenderState;
import com.starfish_studios.another_furniture.entity.SeatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

@Environment(value= EnvType.CLIENT)
public class SeatRenderer extends EntityRenderer<SeatEntity, SeatEntityRenderState> {
    public SeatRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }
    
    @Override
    public SeatEntityRenderState createRenderState() {
        return new SeatEntityRenderState();
    }
}