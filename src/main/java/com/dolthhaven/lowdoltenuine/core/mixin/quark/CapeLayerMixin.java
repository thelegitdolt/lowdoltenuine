package com.dolthhaven.lowdoltenuine.core.mixin.quark;

import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void sex(PoseStack p_116615_, MultiBufferSource p_116616_, int p_116617_, AbstractClientPlayer player, float p_116619_, float p_116620_, float p_116621_, float p_116622_, float p_116623_, float p_116624_, CallbackInfo ci) {
        if (player.getItemBySlot(EquipmentSlot.CHEST).is(LDTNTags.HIDES_CAPES)) ci.cancel();
    }
}
