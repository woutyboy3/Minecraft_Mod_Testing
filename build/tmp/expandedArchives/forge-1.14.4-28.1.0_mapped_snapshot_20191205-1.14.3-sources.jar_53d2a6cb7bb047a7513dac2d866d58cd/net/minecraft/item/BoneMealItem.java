package net.minecraft.item;

import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralWallFanBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BoneMealItem extends Item {
   public BoneMealItem(Item.Properties p_i50055_1_) {
      super(p_i50055_1_);
   }

   /**
    * Called when this item is used when targetting a Block
    */
   public ActionResultType onItemUse(ItemUseContext context) {
      World world = context.getWorld();
      BlockPos blockpos = context.getPos();
      BlockPos blockpos1 = blockpos.offset(context.getFace());
      if (applyBonemeal(context.getItem(), world, blockpos, context.getPlayer())) {
         if (!world.isRemote) {
            world.playEvent(2005, blockpos, 0);
         }

         return ActionResultType.SUCCESS;
      } else {
         BlockState blockstate = world.getBlockState(blockpos);
         boolean flag = blockstate.func_224755_d(world, blockpos, context.getFace());
         if (flag && growSeagrass(context.getItem(), world, blockpos1, context.getFace())) {
            if (!world.isRemote) {
               world.playEvent(2005, blockpos1, 0);
            }

            return ActionResultType.SUCCESS;
         } else {
            return ActionResultType.PASS;
         }
      }
   }

   @Deprecated //Forge: Use Player/Hand version
   public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos) {
      if (worldIn instanceof net.minecraft.world.server.ServerWorld)
         return applyBonemeal(stack, worldIn, pos, net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.server.ServerWorld)worldIn));
      return false;
   }

   public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos, net.minecraft.entity.player.PlayerEntity player) {
      BlockState blockstate = worldIn.getBlockState(pos);
      int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, blockstate, stack);
      if (hook != 0) return hook > 0;
      if (blockstate.getBlock() instanceof IGrowable) {
         IGrowable igrowable = (IGrowable)blockstate.getBlock();
         if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
            if (!worldIn.isRemote) {
               if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
                  igrowable.grow(worldIn, worldIn.rand, pos, blockstate);
               }

               stack.shrink(1);
            }

            return true;
         }
      }

      return false;
   }

   public static boolean growSeagrass(ItemStack stack, World worldIn, BlockPos pos, @Nullable Direction side) {
      if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getFluidState(pos).getLevel() == 8) {
         if (!worldIn.isRemote) {
            label79:
            for(int i = 0; i < 128; ++i) {
               BlockPos blockpos = pos;
               Biome biome = worldIn.getBiome(pos);
               BlockState blockstate = Blocks.SEAGRASS.getDefaultState();

               for(int j = 0; j < i / 16; ++j) {
                  blockpos = blockpos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                  biome = worldIn.getBiome(blockpos);
                  if (worldIn.getBlockState(blockpos).func_224756_o(worldIn, blockpos)) {
                     continue label79;
                  }
               }

               if (biome == Biomes.WARM_OCEAN || biome == Biomes.DEEP_WARM_OCEAN) {
                  if (i == 0 && side != null && side.getAxis().isHorizontal()) {
                     blockstate = BlockTags.WALL_CORALS.getRandomElement(worldIn.rand).getDefaultState().with(DeadCoralWallFanBlock.FACING, side);
                  } else if (random.nextInt(4) == 0) {
                     blockstate = BlockTags.UNDERWATER_BONEMEALS.getRandomElement(random).getDefaultState();
                  }
               }

               if (blockstate.getBlock().isIn(BlockTags.WALL_CORALS)) {
                  for(int k = 0; !blockstate.isValidPosition(worldIn, blockpos) && k < 4; ++k) {
                     blockstate = blockstate.with(DeadCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.random(random));
                  }
               }

               if (blockstate.isValidPosition(worldIn, blockpos)) {
                  BlockState blockstate1 = worldIn.getBlockState(blockpos);
                  if (blockstate1.getBlock() == Blocks.WATER && worldIn.getFluidState(blockpos).getLevel() == 8) {
                     worldIn.setBlockState(blockpos, blockstate, 3);
                  } else if (blockstate1.getBlock() == Blocks.SEAGRASS && random.nextInt(10) == 0) {
                     ((IGrowable)Blocks.SEAGRASS).grow(worldIn, random, blockpos, blockstate1);
                  }
               }
            }

            stack.shrink(1);
         }

         return true;
      } else {
         return false;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static void spawnBonemealParticles(IWorld worldIn, BlockPos posIn, int data) {
      if (data == 0) {
         data = 15;
      }

      BlockState blockstate = worldIn.getBlockState(posIn);
      if (!blockstate.isAir(worldIn, posIn)) {
         for(int i = 0; i < data; ++i) {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)((float)posIn.getX() + random.nextFloat()), (double)posIn.getY() + (double)random.nextFloat() * blockstate.getShape(worldIn, posIn).getEnd(Direction.Axis.Y), (double)((float)posIn.getZ() + random.nextFloat()), d0, d1, d2);
         }

      }
   }
}