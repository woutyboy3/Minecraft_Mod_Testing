package net.minecraft.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import java.util.function.Function;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.IWorld;

public class MelonBlockPileFeature extends BlockPileFeature {
   public MelonBlockPileFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51480_1_) {
      super(p_i51480_1_);
   }

   protected BlockState getRandomBlock(IWorld worldIn) {
      return Blocks.MELON.getDefaultState();
   }
}