package com.dolthhaven.lowdoltenuine.core.mixin;

import com.teamabnormals.caverns_and_chasms.core.CavernsAndChasms;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {CavernsAndChasms.class, UpgradeAquatic.class})
public class Debug {
    @Inject(method = "dataSetup", at = @At("HEAD"), cancellable = true)
    private void sex(GatherDataEvent event, CallbackInfo ci) {
        if (DatagenModLoader.isRunningDataGen())
            ci.cancel();
    }
}
