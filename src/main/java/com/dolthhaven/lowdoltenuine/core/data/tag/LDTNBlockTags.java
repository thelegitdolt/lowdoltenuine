package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class LDTNBlockTags extends BlockTagsProvider {
    public LDTNBlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    }
}
