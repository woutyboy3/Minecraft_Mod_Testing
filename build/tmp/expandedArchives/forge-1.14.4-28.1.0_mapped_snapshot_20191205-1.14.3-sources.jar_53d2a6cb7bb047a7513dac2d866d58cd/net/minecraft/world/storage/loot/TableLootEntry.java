package net.minecraft.world.storage.loot;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;

public class TableLootEntry extends StandaloneLootEntry {
   private final ResourceLocation table;

   private TableLootEntry(ResourceLocation tableIn, int weightIn, int qualityIn, ILootCondition[] conditionsIn, ILootFunction[] functionsIn) {
      super(weightIn, qualityIn, conditionsIn, functionsIn);
      this.table = tableIn;
   }

   public void func_216154_a(Consumer<ItemStack> p_216154_1_, LootContext p_216154_2_) {
      LootTable loottable = p_216154_2_.getLootTableManager().getLootTableFromLocation(this.table);
      loottable.recursiveGenerate(p_216154_2_, p_216154_1_);
   }

   public void func_216142_a(ValidationResults p_216142_1_, Function<ResourceLocation, LootTable> p_216142_2_, Set<ResourceLocation> p_216142_3_, LootParameterSet p_216142_4_) {
      if (p_216142_3_.contains(this.table)) {
         p_216142_1_.addProblem("Table " + this.table + " is recursively called");
      } else {
         super.func_216142_a(p_216142_1_, p_216142_2_, p_216142_3_, p_216142_4_);
         LootTable loottable = p_216142_2_.apply(this.table);
         if (loottable == null) {
            p_216142_1_.addProblem("Unknown loot table called " + this.table);
         } else {
            Set<ResourceLocation> set = ImmutableSet.<ResourceLocation>builder().addAll(p_216142_3_).add(this.table).build();
            loottable.func_216117_a(p_216142_1_.descend("->{" + this.table + "}"), p_216142_2_, set, p_216142_4_);
         }

      }
   }

   public static StandaloneLootEntry.Builder<?> builder(ResourceLocation tableIn) {
      return builder((p_216173_1_, p_216173_2_, p_216173_3_, p_216173_4_) -> {
         return new TableLootEntry(tableIn, p_216173_1_, p_216173_2_, p_216173_3_, p_216173_4_);
      });
   }

   public static class Serializer extends StandaloneLootEntry.Serializer<TableLootEntry> {
      public Serializer() {
         super(new ResourceLocation("loot_table"), TableLootEntry.class);
      }

      public void serialize(JsonObject json, TableLootEntry entryIn, JsonSerializationContext context) {
         super.serialize(json, entryIn, context);
         json.addProperty("name", entryIn.table.toString());
      }

      protected TableLootEntry func_212829_b_(JsonObject p_212829_1_, JsonDeserializationContext p_212829_2_, int p_212829_3_, int p_212829_4_, ILootCondition[] p_212829_5_, ILootFunction[] p_212829_6_) {
         ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(p_212829_1_, "name"));
         return new TableLootEntry(resourcelocation, p_212829_3_, p_212829_4_, p_212829_5_, p_212829_6_);
      }
   }
}