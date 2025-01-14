package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DefaultRenderer extends EntityRenderer<Entity> {
   public DefaultRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.pushMatrix();
      renderOffsetAABB(entity.getBoundingBox(), x - entity.lastTickPosX, y - entity.lastTickPosY, z - entity.lastTickPosZ);
      GlStateManager.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   @Nullable
   protected ResourceLocation getEntityTexture(Entity entity) {
      return null;
   }
}