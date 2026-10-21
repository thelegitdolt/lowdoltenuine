package com.dolthhaven.lowdoltenuine.integration.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import org.violetmoon.quark.addons.oddities.module.BackpackModule;

@EmiEntrypoint
@SuppressWarnings("unused")
public class EmiIntegration implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registry.addRecipeHandler(BackpackModule.menuType, new BackpackScreenHandler());
    }
}