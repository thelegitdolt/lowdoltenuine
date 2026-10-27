package com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions;


import com.dolthhaven.lowdoltenuine.core.registry.LDTNLootItemConditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record EntityIDCondition(ResourceLocation id) implements LootItemCondition {
    public static final MapCodec<EntityIDCondition> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(EntityIDCondition::id)
    ).apply(inst, EntityIDCondition::new));

    @Override
    public LootItemConditionType getType() {
        return LDTNLootItemConditions.MATCHES_ID.get();
    }

    @Override
    public boolean test(LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity != null) {
            return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).equals(id);
        }
        return false;
    }
}
