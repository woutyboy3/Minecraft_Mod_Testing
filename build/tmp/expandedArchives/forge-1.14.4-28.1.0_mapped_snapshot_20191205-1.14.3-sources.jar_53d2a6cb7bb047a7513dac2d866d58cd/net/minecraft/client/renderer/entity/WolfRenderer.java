package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfRenderer extends MobRenderer<WolfEntity, WolfModel<WolfEntity>> {
   private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf.png");
   private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   private static final ResourceLocation ANGRY_WOLF_TEXTURES = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

   public WolfRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new WolfModel<>(), 0.5F);
      this.addLayer(new WolfCollarLayer(this));
   }

   protected float handleRotationFloat(WolfEntity livingBase, float partialTicks) {
      return livingBase.getTailRotation();
   }

   public void doRender(WolfEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.isWolfWet()) {
         float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
         GlStateManager.color3f(f, f, f);
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(WolfEntity entity) {
      if (entity.isTamed()) {
         return TAMED_WOLF_TEXTURES;
      } else {
         return entity.isAngry() ? ANGRY_WOLF_TEXTURES : WOLF_TEXTURES;
      }
   }
}