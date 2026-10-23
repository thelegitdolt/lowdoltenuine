package com.dolthhaven.lowdoltenuine.core.mixin.cavernschasms;

import com.dolthhaven.lowdoltenuine.integration.DungeonsDelightIntegration;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.teamabnormals.caverns_and_chasms.common.item.TetherPotionItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(TetherPotionItem.class)
public class TetherPotionMixin {
    @Inject(method = "updateTetherPotionEffects", at = @At("HEAD"), remap = false)
    private static void sex(LivingEntity entity, ItemStack stack, boolean infiniteDuration, CallbackInfo ci) {
        if (infiniteDuration || Consts.DUNGEONS_DELIGHT.loaded()) return;

        for (MobEffectInstance instance : stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
            Holder<MobEffect> effect = DungeonsDelightIntegration.getMonsterEffect(instance.getEffect());
            if (effect == null) continue;

            entity.removeEffectNoUpdate(effect);
            MobEffectInstance newInstance = new MobEffectInstance(effect, TetherPotionItem.getTetherPotionDuration(instance.getDuration()), instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon());
            entity.addEffect(newInstance);
        }
    }
}
