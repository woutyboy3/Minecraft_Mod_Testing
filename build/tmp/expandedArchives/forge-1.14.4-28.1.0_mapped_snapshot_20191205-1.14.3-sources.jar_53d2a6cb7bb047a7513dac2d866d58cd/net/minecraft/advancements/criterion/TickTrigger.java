package net.minecraft.advancements.criterion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class TickTrigger implements ICriterionTrigger<TickTrigger.Instance> {
   public static final ResourceLocation ID = new ResourceLocation("tick");
   private final Map<PlayerAdvancements, TickTrigger.Listeners> listeners = Maps.newHashMap();

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TickTrigger.Instance> listener) {
      TickTrigger.Listeners ticktrigger$listeners = this.listeners.get(playerAdvancementsIn);
      if (ticktrigger$listeners == null) {
         ticktrigger$listeners = new TickTrigger.Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, ticktrigger$listeners);
      }

      ticktrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TickTrigger.Instance> listener) {
      TickTrigger.Listeners ticktrigger$listeners = this.listeners.get(playerAdvancementsIn);
      if (ticktrigger$listeners != null) {
         ticktrigger$listeners.remove(listener);
         if (ticktrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public TickTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new TickTrigger.Instance();
   }

   public void trigger(ServerPlayerEntity player) {
      TickTrigger.Listeners ticktrigger$listeners = this.listeners.get(player.getAdvancements());
      if (ticktrigger$listeners != null) {
         ticktrigger$listeners.trigger();
      }

   }

   public static class Instance extends CriterionInstance {
      public Instance() {
         super(TickTrigger.ID);
      }
   }

   static class Listeners {
      private final PlayerAdvancements playerAdvancements;
      private final Set<ICriterionTrigger.Listener<TickTrigger.Instance>> listeners = Sets.newHashSet();

      public Listeners(PlayerAdvancements playerAdvancementsIn) {
         this.playerAdvancements = playerAdvancementsIn;
      }

      public boolean isEmpty() {
         return this.listeners.isEmpty();
      }

      public void add(ICriterionTrigger.Listener<TickTrigger.Instance> listener) {
         this.listeners.add(listener);
      }

      public void remove(ICriterionTrigger.Listener<TickTrigger.Instance> listener) {
         this.listeners.remove(listener);
      }

      public void trigger() {
         for(ICriterionTrigger.Listener<TickTrigger.Instance> listener : Lists.newArrayList(this.listeners)) {
            listener.grantCriterion(this.playerAdvancements);
         }

      }
   }
}