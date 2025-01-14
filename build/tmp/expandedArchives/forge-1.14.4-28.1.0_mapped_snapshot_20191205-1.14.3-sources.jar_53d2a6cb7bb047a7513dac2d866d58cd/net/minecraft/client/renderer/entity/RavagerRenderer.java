package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.model.RavagerModel;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RavagerRenderer extends MobRenderer<RavagerEntity, RavagerModel> {
   private static final ResourceLocation field_217778_a = new ResourceLocation("textures/entity/illager/ravager.png");

   public RavagerRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new RavagerModel(), 1.1F);
   }

   protected ResourceLocation getEntityTexture(RavagerEntity entity) {
      return field_217778_a;
   }
}