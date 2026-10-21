package com.dolthhaven.lowdoltenuine.core.data.tag;

import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class LDTNDataMaps extends DataMapProvider {
    public LDTNDataMaps(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
    }
}
