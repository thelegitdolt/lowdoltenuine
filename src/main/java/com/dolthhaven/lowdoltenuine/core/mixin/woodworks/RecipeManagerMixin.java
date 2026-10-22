package com.dolthhaven.lowdoltenuine.core.mixin.woodworks;

import com.dolthhaven.lowdoltenuine.other.LDTNConfig;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @ModifyExpressionValue(method = "lambda$apply$0", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/conditions/WithConditions;carrier()Ljava/lang/Object;"))
    private static <A> A doNiceThings(A original) {
        if (!Consts.WOODWORKS.loaded() || LDTNConfig.COMMON.replaceSawmillWithStonecutter.get()) return original;

        if (original instanceof Recipe<?> recipe) {
            boolean isSawingRecipe = recipe.getType().toString().equals(Consts.WOODWORKS.rl("sawing").toString());
            if (recipe instanceof SingleItemRecipe saw && isSawingRecipe) {
                return (A) new StonecutterRecipe(saw.getGroup(), saw.getIngredients().get(0), saw.getResultItem(null));
            }
        }
        return original;
    }
}