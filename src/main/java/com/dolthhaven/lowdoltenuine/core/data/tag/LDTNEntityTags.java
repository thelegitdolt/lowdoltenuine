package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class LDTNEntityTags extends EntityTypeTagsProvider {
    public LDTNEntityTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    protected void addTags(HolderLookup.Provider provider) {
        tag(LDTNTags.Entity.HUMANOID_ZOMBIES)
                .add(EntityType.ZOMBIE, EntityType.HUSK, EntityType.DROWNED)
                .addOptional(Consts.CHILLED);
    }
}
