package com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers;

import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.EntityIDCondition;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.conditions.AndCondition;

import java.util.List;
import java.util.Optional;

public class WatchMobDieTrigger extends SimpleCriterionTrigger<WatchMobDieTrigger.TriggerInstance> {
    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, Entity killer, Entity victim) {
        LootContext victimContext = EntityPredicate.createContext(player, victim);
        LootContext killerContext = EntityPredicate.createContext(player, killer);
        LootContext playerContext = EntityPredicate.createContext(player, player);
        this.trigger(player, instance -> instance.matches(playerContext, killerContext, victimContext));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ContextAwarePredicate> killer, Optional<ContextAwarePredicate> victim) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<WatchMobDieTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(WatchMobDieTrigger.TriggerInstance::player),
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("killer").forGetter(WatchMobDieTrigger.TriggerInstance::killer),
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("victim").forGetter(WatchMobDieTrigger.TriggerInstance::victim)
                ).apply(instance, WatchMobDieTrigger.TriggerInstance::new)
        );

        public static Criterion<TriggerInstance> withType(ResourceLocation location, EntityType<?> killerType) {
            return LDTNAdvancementTriggers.WATCH_MOB_DIE.get().createCriterion(new TriggerInstance(Optional.empty(),
                    Optional.of(typeMatches(location)), Optional.of(typeMatches(killerType))));
        }

        public static Criterion<TriggerInstance> withType(TagKey<EntityType<?>> victimLoc, ResourceLocation killerLoc, EntityPredicate additionalKillerReq) {
            return LDTNAdvancementTriggers.WATCH_MOB_DIE.get().createCriterion(new TriggerInstance(Optional.empty(),
                    Optional.of(typeMatches(victimLoc)), Optional.of(ContextAwarePredicate.create(
                            AllOfCondition.allOf(List.of(
                                    LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, additionalKillerReq).build(),
                                    new EntityIDCondition(killerLoc)))
            ))));
        }

        public static Criterion<TriggerInstance> withType(ResourceLocation victimLoc, ResourceLocation killerLoc) {
            return LDTNAdvancementTriggers.WATCH_MOB_DIE.get().createCriterion(new TriggerInstance(Optional.empty(),
                    Optional.of(typeMatches(victimLoc)), Optional.of(typeMatches(killerLoc))));
        }

        public static Criterion<TriggerInstance> withType(EntityType<?> victimType, EntityType<?> killerType) {
            return LDTNAdvancementTriggers.WATCH_MOB_DIE.get().createCriterion(new TriggerInstance(Optional.empty(),
                    Optional.of(typeMatches(victimType)), Optional.of(typeMatches(killerType))));
        }

        public static Criterion<TriggerInstance> withType(TagKey<EntityType<?>> victimType, EntityType<?> killerType) {
            return LDTNAdvancementTriggers.WATCH_MOB_DIE.get().createCriterion(new TriggerInstance(Optional.empty(),
                    Optional.of(typeMatches(victimType)), Optional.of(typeMatches(killerType))));
        }

        public static Criterion<TriggerInstance> withType(EntityType<?> victimType, TagKey<EntityType<?>> killerType) {
            return LDTNAdvancementTriggers.WATCH_MOB_DIE.get().createCriterion(new TriggerInstance(Optional.empty(),
                    Optional.of(typeMatches(victimType)), Optional.of(typeMatches(killerType))));
        }

        private static ContextAwarePredicate typeMatches(TagKey<EntityType<?>> entityType) {
            return ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                    EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entityType)).build()).build());
        }

        private static ContextAwarePredicate typeMatches(ResourceLocation location) {
            return ContextAwarePredicate.create(new EntityIDCondition(location));
        }

        private static ContextAwarePredicate typeMatches(EntityType<?> entityType) {
            return ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                    EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entityType)).build()).build());
        }

        public boolean matches(LootContext player, LootContext killer, LootContext victim) {
            return this.killer.map(pred -> pred.matches(killer)).orElse(true) && this.victim.map(pred -> pred.matches(victim)).orElse(true) && this.player.map(pred -> pred.matches(player)).orElse(true);
        }
    }
}
