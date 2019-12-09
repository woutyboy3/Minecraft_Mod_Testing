package net.minecraft.world.gen.feature.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.EmptyJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.FeatureJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.template.AlwaysTrueRuleTest;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RandomBlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleEntry;
import net.minecraft.world.gen.feature.template.RuleStructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;

public class DesertVillagePools {
   public static void init() {
   }

   static {
      ImmutableList<StructureProcessor> immutablelist = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SMOOTH_SANDSTONE, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CUT_SANDSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.TERRACOTTA, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SMOOTH_SANDSTONE_STAIRS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SMOOTH_SANDSTONE_SLAB, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()))));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/town_centers"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/town_centers/desert_meeting_point_1"), 98), new Pair<>(new SingleJigsawPiece("village/desert/town_centers/desert_meeting_point_2"), 98), new Pair<>(new SingleJigsawPiece("village/desert/town_centers/desert_meeting_point_3"), 49), new Pair<>(new SingleJigsawPiece("village/desert/zombie/town_centers/desert_meeting_point_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/town_centers/desert_meeting_point_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/town_centers/desert_meeting_point_3", immutablelist), 1)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/streets"), new ResourceLocation("village/desert/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/streets/corner_01"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/corner_02"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/straight_01"), 4), new Pair<>(new SingleJigsawPiece("village/desert/streets/straight_02"), 4), new Pair<>(new SingleJigsawPiece("village/desert/streets/straight_03"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/crossroad_01"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/crossroad_02"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/crossroad_03"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/square_01"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/square_02"), 3), new Pair<>(new SingleJigsawPiece("village/desert/streets/turn_01"), 3)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/zombie/streets"), new ResourceLocation("village/desert/zombie/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/corner_01"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/corner_02"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/straight_01"), 4), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/straight_02"), 4), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/straight_03"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/crossroad_01"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/crossroad_02"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/crossroad_03"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/square_01"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/square_02"), 3), new Pair<>(new SingleJigsawPiece("village/desert/zombie/streets/turn_01"), 3)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      ImmutableList<StructureProcessor> immutablelist1 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()), new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()))));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/houses"), new ResourceLocation("village/desert/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_2"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_3"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_4"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_5"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_6"), 1), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_7"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_small_house_8"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_medium_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_medium_house_2"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_butcher_shop_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_tool_smith_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_fletcher_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_shepherd_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_armorer_1"), 1), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_fisher_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_tannery_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_cartographer_house_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_library_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_mason_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_weaponsmith_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_temple_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_temple_2"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_large_farm_1", immutablelist1), 11), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_farm_1", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_farm_2", immutablelist1), 4), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_animal_pen_1"), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_animal_pen_2"), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 5)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/zombie/houses"), new ResourceLocation("village/desert/zombie/terminators"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_3", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_4", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_5", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_6", immutablelist), 1), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_7", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_small_house_8", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_medium_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/zombie/houses/desert_medium_house_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_butcher_shop_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_tool_smith_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_fletcher_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_shepherd_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_armorer_1", immutablelist), 1), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_fisher_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_tannery_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_cartographer_house_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_library_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_mason_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_weaponsmith_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_temple_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_temple_2", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_large_farm_1", immutablelist), 7), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_farm_1", immutablelist), 4), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_farm_2", immutablelist), 4), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_animal_pen_1", immutablelist), 2), new Pair<>(new SingleJigsawPiece("village/desert/houses/desert_animal_pen_2", immutablelist), 2), Pair.of(EmptyJigsawPiece.INSTANCE, 5)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/terminators"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/terminators/terminator_01"), 1), new Pair<>(new SingleJigsawPiece("village/desert/terminators/terminator_02"), 1)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/zombie/terminators"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/terminators/terminator_01"), 1), new Pair<>(new SingleJigsawPiece("village/desert/zombie/terminators/terminator_02"), 1)), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/decor"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/desert_lamp_1"), 10), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.CACTUS, IFeatureConfig.NO_FEATURE_CONFIG)), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.HAY_PILE, IFeatureConfig.NO_FEATURE_CONFIG)), 4), Pair.of(EmptyJigsawPiece.INSTANCE, 10)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/zombie/decor"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/desert_lamp_1", immutablelist), 10), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.CACTUS, IFeatureConfig.NO_FEATURE_CONFIG)), 4), new Pair<>(new FeatureJigsawPiece(new ConfiguredFeature<>(Feature.HAY_PILE, IFeatureConfig.NO_FEATURE_CONFIG)), 4), Pair.of(EmptyJigsawPiece.INSTANCE, 10)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/villagers"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/villagers/nitwit"), 1), new Pair<>(new SingleJigsawPiece("village/desert/villagers/baby"), 1), new Pair<>(new SingleJigsawPiece("village/desert/villagers/unemployed"), 10)), JigsawPattern.PlacementBehaviour.RIGID));
      JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("village/desert/zombie/villagers"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece("village/desert/zombie/villagers/nitwit"), 1), new Pair<>(new SingleJigsawPiece("village/desert/zombie/villagers/unemployed"), 10)), JigsawPattern.PlacementBehaviour.RIGID));
   }
}