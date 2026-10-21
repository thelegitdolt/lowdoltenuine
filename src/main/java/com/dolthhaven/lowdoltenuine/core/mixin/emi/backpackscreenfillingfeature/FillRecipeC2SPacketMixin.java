package com.dolthhaven.lowdoltenuine.core.mixin.emi.backpackscreenfillingfeature;

import dev.emi.emi.network.FillRecipeC2SPacket;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.violetmoon.quark.addons.oddities.inventory.slot.CachedItemHandlerSlot;

// Fixes backpacks etc
@Pseudo
@Mixin(FillRecipeC2SPacket.class)
public class FillRecipeC2SPacketMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    private void hi(Player player, CallbackInfo ci) {
        CachedItemHandlerSlot.cache(player.containerMenu);
    }

    @Inject(method = "apply", at = @At("TAIL"))
    private void bye(Player player, CallbackInfo ci) {
        CachedItemHandlerSlot.applyCache(player.containerMenu);
    }
}
