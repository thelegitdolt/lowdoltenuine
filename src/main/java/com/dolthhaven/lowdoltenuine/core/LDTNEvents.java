package com.dolthhaven.lowdoltenuine.core;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class LDTNEvents {
    @SubscribeEvent
    public static void watchMobKillTrigger(LivingDeathEvent event) {
        Entity victim = event.getEntity();
        Entity killer = event.getSource().getEntity();
        BlockPos pos = new BlockPos((int) victim.getX(), (int) victim.getY(), (int) victim.getZ());
        DamageSource source = event.getSource();
        if (killer instanceof LivingEntity livingKiller) {
            if (livingKiller instanceof TamableAnimal tamableAnimal && tamableAnimal.getOwner() instanceof ServerPlayer player) {
                LDTNAdvancementTriggers.WATCH_MOB_DIE.get().trigger(player, killer, victim, source);
            }

            if (killer.level().getNearbyPlayers(TargetingConditions.forNonCombat(), livingKiller, new AABB(pos).inflate(25, 25, 25)) instanceof ServerPlayer player) {
                LDTNAdvancementTriggers.WATCH_MOB_DIE.get().trigger(player, killer, victim, source);
            }
        }
    }
}
