package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.violetmoon.quark.addons.oddities.module.BackpackModule;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNItems.GOLDEN_ECTOPLASM_BUCKET;
import static com.dolthhaven.lowdoltenuine.core.registry.LDTNItems.GOLDEN_MOLTEN_LEAD_BUCKET;

public class LDTNItemTags extends ItemTagsProvider {
    public LDTNItemTags(GatherDataEvent event, BlockTagsProvider provider) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), provider.contentsGetter(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(LDTNTags.Items.HIDES_CAPES).add(BackpackModule.backpack);
        this.copy(LDTNTags.Blocks.ENCASED_PIPE_BLOCKS, LDTNTags.Items.ENCASED_PIPE_BLOCKS);
        this.copy(LDTNTags.Blocks.RAKED_SANDS, LDTNTags.Items.RAKED_SANDS);

        this.tag(ItemTags.PIGLIN_LOVED).add(GOLDEN_ECTOPLASM_BUCKET.get(), GOLDEN_MOLTEN_LEAD_BUCKET.get());
        this.tag(LDTNTags.Items.BUCKETS_ECTOPLASM).add(GOLDEN_ECTOPLASM_BUCKET.get()).addOptional(Consts.ECTOPLASM_BUCKET);
        this.tag(LDTNTags.Items.BUCKETS_MOLTEN_LEAD).add(GOLDEN_MOLTEN_LEAD_BUCKET.get()).addOptional(Consts.MOLTEN_LEAD_BUCKET);
    }
}
