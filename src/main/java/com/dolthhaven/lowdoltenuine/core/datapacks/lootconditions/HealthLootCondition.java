package com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNLootItemConditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record HealthLootCondition(MinMaxBounds.Doubles health) implements LootItemCondition {
    public static final MapCodec<HealthLootCondition> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                    MinMaxBounds.Doubles.CODEC.fieldOf("health").forGetter(HealthLootCondition::health)
            ).apply(inst, HealthLootCondition::new));

    @Override
    public LootItemConditionType getType() {
        return LDTNLootItemConditions.HEALTH.get();
    }

    @Override
    public boolean test(LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof LivingEntity living)  {
            return this.health.matches(living.getHealth());
        }
        return false;
    }
}