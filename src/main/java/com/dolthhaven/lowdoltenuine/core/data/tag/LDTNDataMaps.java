package com.dolthhaven.lowdoltenuine.core.data.tag;

import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.DATE_CRATE;

public class LDTNDataMaps extends DataMapProvider {
    public LDTNDataMaps(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(DATE_CRATE.getId(), new Compostable(1f), false);
    }
}
