package com.dolthhaven.lowdoltenuine.core.mixin;

import com.teamabnormals.caverns_and_chasms.core.CavernsAndChasms;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CavernsAndChasms.class)
public class Debug {
    @Inject(method = "dataSetup", at = @At("HEAD"), cancellable = true)
    private void sex(GatherDataEvent event, CallbackInfo ci) {
        if (DatagenModLoader.isRunningDataGen())
            ci.cancel();
    }
}
