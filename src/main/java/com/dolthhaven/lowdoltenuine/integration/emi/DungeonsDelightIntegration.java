package com.dolthhaven.lowdoltenuine.integration.emi;

import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DungeonsDelightIntegration {
    public static final Supplier<Map<Holder<MobEffect>, Holder<MobEffect>>> MONSTER_MAP = Suppliers.memoize(() -> Util.make(new HashMap<>(), map -> {
        map.put(MobEffects.ABSORPTION, ddeffect("exudation"));
        map.put(MobEffects.DAMAGE_BOOST, ddeffect("decisive"));
        map.put(ModEffects.NOURISHMENT, ddeffect("voracity"));
        map.put(MobEffects.DIG_SPEED, ddeffect("burrow_gut"));
        map.put(MobEffects.JUMP, ddeffect("pouncing"));
    }));

    public static Holder<MobEffect> getMonsterEffect(Holder<MobEffect> effectHolder) {
        return MONSTER_MAP.get().get(effectHolder);
    }

    private static Holder<MobEffect> ddeffect(String path) {
        return BuiltInRegistries.MOB_EFFECT.getHolder(Consts.DUNGEONS_DELIGHT.rl(path)).orElseThrow();
    }
}
