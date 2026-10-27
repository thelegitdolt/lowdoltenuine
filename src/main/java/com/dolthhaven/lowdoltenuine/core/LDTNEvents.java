package com.dolthhaven.lowdoltenuine.core;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class LDTNEvents {
    @SubscribeEvent
    public static void hello(PlayerInteractEvent.RightClickBlock event) {}

    @SubscribeEvent
    public static void watchMobKillTrigger(LivingDeathEvent event) {
        Entity victim = event.getEntity();
        Entity killer = event.getSource().getEntity();
        if (killer == null) return;

        if (killer instanceof TamableAnimal tamableAnimal && tamableAnimal.getOwner() instanceof ServerPlayer player) {
            LDTNAdvancementTriggers.WATCH_MOB_DIE.get().trigger(player, killer, victim);
            return;
        }

        if (killer.level().getNearestPlayer(killer, 50) instanceof ServerPlayer player) {
            LDTNAdvancementTriggers.WATCH_MOB_DIE.get().trigger(player, killer, victim);
        }
    }
}
