package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.data.loot.FishingLootTables;
import net.minecraft.data.loot.GiftLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.ValidationResults;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootTableProvider implements IDataProvider {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
   private final DataGenerator dataGenerator;
   private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> field_218444_e = ImmutableList.of(Pair.of(FishingLootTables::new, LootParameterSets.FISHING), Pair.of(ChestLootTables::new, LootParameterSets.CHEST), Pair.of(EntityLootTables::new, LootParameterSets.ENTITY), Pair.of(BlockLootTables::new, LootParameterSets.BLOCK), Pair.of(GiftLootTables::new, LootParameterSets.GIFT));

   public LootTableProvider(DataGenerator dataGeneratorIn) {
      this.dataGenerator = dataGeneratorIn;
   }

   /**
    * Performs this provider's action.
    */
   public void act(DirectoryCache cache) {
      Path path = this.dataGenerator.getOutputFolder();
      Map<ResourceLocation, LootTable> map = Maps.newHashMap();
      this.field_218444_e.forEach((p_218438_1_) -> {
         p_218438_1_.getFirst().get().accept((p_218437_2_, p_218437_3_) -> {
            if (map.put(p_218437_2_, p_218437_3_.setParameterSet(p_218438_1_.getSecond()).build()) != null) {
               throw new IllegalStateException("Duplicate loot table " + p_218437_2_);
            }
         });
      });
      ValidationResults validationresults = new ValidationResults();

      for(ResourceLocation resourcelocation : Sets.difference(LootTables.func_215796_a(), map.keySet())) {
         validationresults.addProblem("Missing built-in table: " + resourcelocation);
      }

      map.forEach((p_218436_2_, p_218436_3_) -> {
         LootTableManager.func_215302_a(validationresults, p_218436_2_, p_218436_3_, map::get);
      });
      Multimap<String, String> multimap = validationresults.getProblems();
      if (!multimap.isEmpty()) {
         multimap.forEach((p_218435_0_, p_218435_1_) -> {
            LOGGER.warn("Found validation problem in " + p_218435_0_ + ": " + p_218435_1_);
         });
         throw new IllegalStateException("Failed to validate loot tables, see logs");
      } else {
         map.forEach((p_218440_2_, p_218440_3_) -> {
            Path path1 = getPath(path, p_218440_2_);

            try {
               IDataProvider.save(GSON, cache, LootTableManager.toJson(p_218440_3_), path1);
            } catch (IOException ioexception) {
               LOGGER.error("Couldn't save loot table {}", path1, ioexception);
            }

         });
      }
   }

   private static Path getPath(Path pathIn, ResourceLocation id) {
      return pathIn.resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
   }

   /**
    * Gets a name for this provider, to use in logging.
    */
   public String getName() {
      return "LootTables";
   }
}