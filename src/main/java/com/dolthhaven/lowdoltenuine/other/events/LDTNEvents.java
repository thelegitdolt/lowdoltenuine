package com.dolthhaven.lowdoltenuine.other.events;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.ThunderdomeTrigger;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.List;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class LDTNEvents {
    @SubscribeEvent
    public static void THUNDERDOME(LivingDeathEvent event) {
        if (!Consts.CAVERNS_AND_CHASMS.loaded()) return;
        Entity entity = event.getEntity();
        if (event.getSource().getEntity() instanceof Player player && entity instanceof LivingEntity living) {
            boolean hasMimed = false;
            if (Consts.MIME.is(entity)){
                for (ItemStack stack : living.getArmorSlots()) {
                    if (!stack.isEmpty()) {
                        hasMimed = true;
                        break;
                    }
                }
            }

            if (hasMimed) {
                int score = ThunderdomeTrigger.ThunderdomeUtil.incrementScore(player);
                if (player instanceof ServerPlayer serverPlayer)
                    LDTNAdvancementTriggers.THUNDERDOME.get().trigger(serverPlayer, score);
            } else {
                ThunderdomeTrigger.ThunderdomeUtil.reset(player);
            }
        }
    }

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

            List<Player> nearbyPlayers = killer.level().getNearbyPlayers(TargetingConditions.forNonCombat(), livingKiller, new AABB(pos).inflate(30, 25, 30));

            nearbyPlayers.forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer)
                    LDTNAdvancementTriggers.WATCH_MOB_DIE.get().trigger(serverPlayer, killer, victim, source);
            });
        }
    }
}
