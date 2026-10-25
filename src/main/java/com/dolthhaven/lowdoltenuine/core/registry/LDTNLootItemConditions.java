package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.HealthLootCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LDTNLootItemConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, Lowdoltenuine.MOD_ID);

    public static final DeferredHolder<LootItemConditionType, ?> HEALTH = LOOT_CONDITIONS.register("health", () -> new LootItemConditionType(HealthLootCondition.CODEC));
}
