package com.dolthhaven.lowdoltenuine.core.mixin.jne;

import com.dolthhaven.lowdoltenuine.other.LDTNConfig;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets="net.jadenxgamer.netherexp.core.block.BonePikeBlock")
public class BonePikeBlockMixin {
    @Definition(id = "asItem", method = "Lnet/jadenxgamer/netherexp/core/block/BonePikeBlock;asItem()Lnet/minecraft/world/item/Item;")
    @Expression("this.asItem()")
    @ModifyExpressionValue(method = "canBeReplaced", at = @At("MIXINEXTRAS:EXPRESSION"))
    private Item hi(Item original){
        return LDTNConfig.COMMON.shouldPlaceBonePilesWithNormalBones.get() ? Items.BONE : original;
    }
}
