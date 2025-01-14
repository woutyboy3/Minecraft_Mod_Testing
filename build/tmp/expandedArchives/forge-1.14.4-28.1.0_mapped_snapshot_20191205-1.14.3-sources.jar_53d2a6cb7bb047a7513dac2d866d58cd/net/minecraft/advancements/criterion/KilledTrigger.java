package net.minecraft.advancements.criterion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class KilledTrigger implements ICriterionTrigger<KilledTrigger.Instance> {
   private final Map<PlayerAdvancements, KilledTrigger.Listeners> listeners = Maps.newHashMap();
   private final ResourceLocation id;

   public KilledTrigger(ResourceLocation id) {
      this.id = id;
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<KilledTrigger.Instance> listener) {
      KilledTrigger.Listeners killedtrigger$listeners = this.listeners.get(playerAdvancementsIn);
      if (killedtrigger$listeners == null) {
         killedtrigger$listeners = new KilledTrigger.Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, killedtrigger$listeners);
      }

      killedtrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<KilledTrigger.Instance> listener) {
      KilledTrigger.Listeners killedtrigger$listeners = this.listeners.get(playerAdvancementsIn);
      if (killedtrigger$listeners != null) {
         killedtrigger$listeners.remove(listener);
         if (killedtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public KilledTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new KilledTrigger.Instance(this.id, EntityPredicate.deserialize(json.get("entity")), DamageSourcePredicate.deserialize(json.get("killing_blow")));
   }

   public void trigger(ServerPlayerEntity player, Entity entity, DamageSource source) {
      KilledTrigger.Listeners killedtrigger$listeners = this.listeners.get(player.getAdvancements());
      if (killedtrigger$listeners != null) {
         killedtrigger$listeners.trigger(player, entity, source);
      }

   }

   public static class Instance extends CriterionInstance {
      private final EntityPredicate entity;
      private final DamageSourcePredicate killingBlow;

      public Instance(ResourceLocation criterionIn, EntityPredicate entity, DamageSourcePredicate killingBlow) {
         super(criterionIn);
         this.entity = entity;
         this.killingBlow = killingBlow;
      }

      public static KilledTrigger.Instance playerKilledEntity(EntityPredicate.Builder p_203928_0_) {
         return new KilledTrigger.Instance(CriteriaTriggers.PLAYER_KILLED_ENTITY.id, p_203928_0_.build(), DamageSourcePredicate.ANY);
      }

      public static KilledTrigger.Instance playerKilledEntity() {
         return new KilledTrigger.Instance(CriteriaTriggers.PLAYER_KILLED_ENTITY.id, EntityPredicate.ANY, DamageSourcePredicate.ANY);
      }

      public static KilledTrigger.Instance playerKilledEntity(EntityPredicate.Builder p_203929_0_, DamageSourcePredicate.Builder p_203929_1_) {
         return new KilledTrigger.Instance(CriteriaTriggers.PLAYER_KILLED_ENTITY.id, p_203929_0_.build(), p_203929_1_.build());
      }

      public static KilledTrigger.Instance entityKilledPlayer() {
         return new KilledTrigger.Instance(CriteriaTriggers.ENTITY_KILLED_PLAYER.id, EntityPredicate.ANY, DamageSourcePredicate.ANY);
      }

      public boolean test(ServerPlayerEntity player, Entity entity, DamageSource source) {
         return !this.killingBlow.test(player, source) ? false : this.entity.test(player, entity);
      }

      public JsonElement serialize() {
         JsonObject jsonobject = new JsonObject();
         jsonobject.add("entity", this.entity.serialize());
         jsonobject.add("killing_blow", this.killingBlow.serialize());
         return jsonobject;
      }
   }

   static class Listeners {
      private final PlayerAdvancements playerAdvancements;
      private final Set<ICriterionTrigger.Listener<KilledTrigger.Instance>> listeners = Sets.newHashSet();

      public Listeners(PlayerAdvancements playerAdvancementsIn) {
         this.playerAdvancements = playerAdvancementsIn;
      }

      public boolean isEmpty() {
         return this.listeners.isEmpty();
      }

      public void add(ICriterionTrigger.Listener<KilledTrigger.Instance> listener) {
         this.listeners.add(listener);
      }

      public void remove(ICriterionTrigger.Listener<KilledTrigger.Instance> listener) {
         this.listeners.remove(listener);
      }

      public void trigger(ServerPlayerEntity player, Entity entity, DamageSource source) {
         List<ICriterionTrigger.Listener<KilledTrigger.Instance>> list = null;

         for(ICriterionTrigger.Listener<KilledTrigger.Instance> listener : this.listeners) {
            if (listener.getCriterionInstance().test(player, entity, source)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener);
            }
         }

         if (list != null) {
            for(ICriterionTrigger.Listener<KilledTrigger.Instance> listener1 : list) {
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }
}