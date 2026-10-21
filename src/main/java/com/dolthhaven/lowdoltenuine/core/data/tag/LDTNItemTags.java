package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class LDTNItemTags extends ItemTagsProvider {
    public LDTNItemTags(GatherDataEvent event, BlockTagsProvider provider) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), provider.contentsGetter(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    }
}
