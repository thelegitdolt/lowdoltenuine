package com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class RewindDistanceTrigger extends SimpleCriterionTrigger<RewindDistanceTrigger.TriggerInstance> {
    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, int distance) {
        this.trigger(player, instance -> instance.matches(distance));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, MinMaxBounds.Ints distance) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                        MinMaxBounds.Ints.CODEC.optionalFieldOf("velocity", MinMaxBounds.Ints.ANY).forGetter(TriggerInstance::distance)
                ).apply(instance, TriggerInstance::new)
        );

        public static Criterion<TriggerInstance> withDistance(int distance) {
            return LDTNAdvancementTriggers.REWIND_TELEPORT.get().createCriterion(new TriggerInstance(Optional.empty(), MinMaxBounds.Ints.atLeast(distance)));
        }

        public boolean matches(int distance) {
            return this.distance.matches(distance);
        }
    }
}
