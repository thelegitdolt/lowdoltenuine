package com.dolthhaven.lowdoltenuine.core.mixin.oreganized;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.electrum.accessor.IMotionHolder;
import galena.oreganized.electrum.world.item.SpeedometerItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(SpeedometerItem.class)
public class SpeedometerMixin {
    @WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V"))
    private void detectJohnSpeed(ItemCooldowns instance, Item item, int ticks, Operation<Void> original, @Local(argsOnly = true) Player player) {
        original.call(instance, item, ticks);
        Entity entity = player.getRootVehicle();

        if (player instanceof ServerPlayer serverPlayer) {
            if (entity instanceof IMotionHolder motionHolder) {
                float speed = (float) motionHolder.oreganised$getMotion();
                LDTNAdvancementTriggers.SPEEDOMETER_SPEED.get().trigger(serverPlayer, speed);
            }

        }
    }
}
