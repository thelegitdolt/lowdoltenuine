package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
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

        this.builder(LDTNTags.Datamaps.COMPOSTABLE_ENTITIES)
                .add(Consts.BANANA_PEEL.loc(), new Compostable(0.5f), false, Consts.NEAPOLITAN.requiresLoaded())
                .add(Consts.MONSTER_YAM.loc(), new Compostable(1.0f), false, Consts.DUNGEONS_DELIGHT.requiresLoaded());

        this.builder(LDTNTags.Datamaps.RAKE_MAP)
                .conditions(Consts.MOWZIES_MOBS.requiresLoaded())
                .add(RegUtil.blockId(Blocks.SAND), RegUtil.blockHolder(Consts.RAKED_SAND.loc()), false)
                .add(RegUtil.blockId(Blocks.RED_SAND), RegUtil.blockHolder(Consts.RED_RAKED_SAND.loc()), false)
                .add(Consts.ARID_SAND.loc(), LDTNBlocks.ARID_RAKED_SAND, false, Consts.ATMOSPHERIC.requiresLoaded())
                .add(Consts.RED_ARID_SAND.loc(), LDTNBlocks.RED_ARID_RAKED_SAND, false, Consts.ATMOSPHERIC.requiresLoaded())
                .add(Consts.BB_ASHEN_SAND.loc(), LDTNBlocks.ASHEN_RAKED_SAND, false, Consts.BLASTED_BARRENS.requiresLoaded());
    }
}
