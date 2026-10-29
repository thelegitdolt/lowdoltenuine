package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.EntityIDCondition;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.HealthLootCondition;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.IsChargedCreeperCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LDTNLootItemConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, Lowdoltenuine.MOD_ID);

    public static final DeferredHolder<LootItemConditionType, ?> HEALTH = LOOT_CONDITIONS.register("health", () -> new LootItemConditionType(HealthLootCondition.CODEC));
    public static final DeferredHolder<LootItemConditionType, ?> MATCHES_ID = LOOT_CONDITIONS.register("matches_id", () -> new LootItemConditionType(EntityIDCondition.CODEC));
    public static final DeferredHolder<LootItemConditionType, ?> IS_CHARGED_CREEPER = LOOT_CONDITIONS.register("is_charged_creeper", () -> new LootItemConditionType(IsChargedCreeperCondition.CODEC));
}
