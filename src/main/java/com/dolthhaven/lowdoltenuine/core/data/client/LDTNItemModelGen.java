package com.dolthhaven.lowdoltenuine.core.data.client;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;


public class LDTNItemModelGen extends BlueprintItemModelProvider {
    public LDTNItemModelGen(GatherDataEvent e) {
        super(e.getGenerator().getPackOutput(), Lowdoltenuine.MOD_ID, e.getExistingFileHelper());
    }

    @Override
    protected void registerModels() {
    }
}
