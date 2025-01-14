package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.model.OcelotModel;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OcelotRenderer extends MobRenderer<OcelotEntity, OcelotModel<OcelotEntity>> {
   private static final ResourceLocation OCELOT_TEXTURES = new ResourceLocation("textures/entity/cat/ocelot.png");

   public OcelotRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new OcelotModel<>(0.0F), 0.4F);
   }

   protected ResourceLocation getEntityTexture(OcelotEntity entity) {
      return OCELOT_TEXTURES;
   }
}