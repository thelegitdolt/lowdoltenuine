package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNBlockTags extends BlockTagsProvider {
    public LDTNBlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(STURDY_DEEPSLATE.get(), ZINC_BRICK_SLAB.get(), ZINC_BRICKS.get(), ZINC_BRICK_WALL.get(),
                CHISELED_ZINC_BRICKS.get(), ZINC_BRICK_STAIRS.get());

    }
}
