package com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNLootItemConditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record IsChargedCreeperCondition(boolean isCharged) implements LootItemCondition {
    public static MapCodec<IsChargedCreeperCondition> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.BOOL.fieldOf("charged").forGetter(IsChargedCreeperCondition::isCharged)
    ).apply(inst, IsChargedCreeperCondition::new));

    @Override
    public LootItemConditionType getType() {
        return LDTNLootItemConditions.IS_CHARGED_CREEPER.get();
    }

    @Override
    public boolean test(LootContext context) {
        return context.getParam(LootContextParams.THIS_ENTITY) instanceof Creeper creeper && creeper.isPowered();
    }
}
