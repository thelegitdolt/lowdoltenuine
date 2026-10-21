package com.dolthhaven.lowdoltenuine.core.mixin.emi.backpackscreenfillingfeature;

import com.dolthhaven.lowdoltenuine.integration.emi.BackpackScreenHandler;
import dev.emi.emi.api.recipe.handler.EmiRecipeHandler;
import dev.emi.emi.registry.EmiRecipeFiller;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.violetmoon.quark.addons.oddities.inventory.BackpackMenu;

import java.util.List;

@Pseudo
@Mixin(EmiRecipeFiller.class)
public class EmiRecipeFillerMixin {
    @Unique
    private static final BackpackScreenHandler HANDLER = new BackpackScreenHandler();

    @Inject(method = "getAllHandlers", at = @At(value = "HEAD"), cancellable = true)
    private static <T extends AbstractContainerMenu> void LowDoltenuine$RegisterBackpackHandler(AbstractContainerScreen<T> screen, CallbackInfoReturnable<List<EmiRecipeHandler<? extends AbstractContainerMenu>>> cir) {
        if (screen != null && screen.getMenu() instanceof BackpackMenu) {
            cir.setReturnValue(List.of(HANDLER));
        }
    }
}
