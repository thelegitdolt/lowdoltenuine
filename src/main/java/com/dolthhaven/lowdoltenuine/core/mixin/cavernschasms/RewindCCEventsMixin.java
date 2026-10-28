package com.dolthhaven.lowdoltenuine.core.mixin.cavernschasms;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.teamabnormals.caverns_and_chasms.core.other.CCEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CCEvents.class)
public class RewindCCEventsMixin {
    @Inject(method = "rewindTeleport", at = @At("HEAD"), remap = false)
    private static void sexers(LivingEntity entity, CallbackInfo ci, @Share("arg") LocalRef<Vec3> argRef) {
        argRef.set(entity.position());
    }

    @Inject(method = "rewindTeleport", at = @At(value = "TAIL"), remap = false)
    private static void sex(LivingEntity entity, CallbackInfo ci, @Share("arg") LocalRef<Vec3> argRef) {
        if (entity instanceof ServerPlayer player) {
            LDTNAdvancementTriggers.REWIND_TELEPORT.get().trigger(player, (int) argRef.get().distanceTo(player.position()));
        }
    }
}
