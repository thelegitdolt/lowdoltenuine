package com.dolthhaven.lowdoltenuine.integration.doublewhammy;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNItems;
import com.dolthhaven.lowdoltenuine.integration.CavernsChasmsIntegration;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.caverns_and_chasms.core.registry.CCItems;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.world.MeltingCauldronInteractions;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;

public class CavernsOreganizedInt {
    public static void registerCauldrons() {
        DataUtil.addDefaultCauldronInteraction(LDTNItems.GOLDEN_MOLTEN_LEAD_BUCKET.get(), CavernsChasmsIntegration.fillCauldron(PlumbumBlocks.MOLTEN_LEAD_CAULDRON.get(), state -> true, SoundEvents.BUCKET_EMPTY_LAVA, SoundEvents.BUCKET_FILL_LAVA));
        put(CCItems.GOLDEN_BUCKET.get(), CavernsChasmsIntegration.emptyCauldron(LDTNItems.GOLDEN_MOLTEN_LEAD_BUCKET.get(), a -> true, SoundEvents.BUCKET_FILL_LAVA));
        put(LDTNItems.GOLDEN_MOLTEN_LEAD_BUCKET.get(), CavernsChasmsIntegration.emptyCauldronWithHalfFilledBucket(state -> true, SoundEvents.BUCKET_FILL_LAVA));
    }

    private static void put(Item item, CauldronInteraction interaction) {
        MeltingCauldronInteractions.MOLTEN_LEAD.map().put(item, interaction);
    }
}
