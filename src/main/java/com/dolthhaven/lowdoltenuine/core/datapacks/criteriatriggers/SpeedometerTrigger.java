package com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SpeedometerTrigger extends SimpleCriterionTrigger<SpeedometerTrigger.TriggerInstance> {
    public void trigger(ServerPlayer player, float speed) {
        this.trigger(player, (instance) -> instance.matches(speed));
    }

    public static Criterion<TriggerInstance> withDistance(double min) {
        return LDTNAdvancementTriggers.SPEEDOMETER_SPEED.get().createCriterion(new TriggerInstance(Optional.empty(), MinMaxBounds.Doubles.atLeast(min)));
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, MinMaxBounds.Doubles velocity) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                        MinMaxBounds.Doubles.CODEC.fieldOf("speed").forGetter(TriggerInstance::velocity)
                ).apply(instance, TriggerInstance::new)
        );

        public boolean matches(double distance) {
            return this.velocity.matches(distance);
        }
    }
}
