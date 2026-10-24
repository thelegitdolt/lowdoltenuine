package com.dolthhaven.lowdoltenuine.integration.doublewhammy;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNItems;
import com.dolthhaven.lowdoltenuine.integration.CavernsChasmsIntegration;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.caverns_and_chasms.core.registry.CCItems;
import net.jadenxgamer.netherexp.core.misc.JNECauldronInteractions;
import net.jadenxgamer.netherexp.registry.JNEBlocks;
import net.jadenxgamer.netherexp.registry.JNESoundEvents;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;

public class CavernsJNEInt {
    public static void registerCauldrons() {
        DataUtil.addDefaultCauldronInteraction(LDTNItems.GOLDEN_ECTOPLASM_BUCKET.get(),
                CavernsChasmsIntegration.fillCauldron(JNEBlocks.ECTOPLASM_CAULDRON.get(), state -> true, JNESoundEvents.BUCKET_EMPTY_ECTOPLASM.get(), JNESoundEvents.BUCKET_FILL_ECTOPLASM.get()));

        put(CCItems.GOLDEN_BUCKET.get(), CavernsChasmsIntegration.emptyCauldron(LDTNItems.GOLDEN_ECTOPLASM_BUCKET.get(), a -> true, JNESoundEvents.BUCKET_FILL_ECTOPLASM.get()));
        put(LDTNItems.GOLDEN_ECTOPLASM_BUCKET.get(), CavernsChasmsIntegration.emptyCauldronWithHalfFilledBucket(state -> true, JNESoundEvents.BUCKET_FILL_ECTOPLASM.get()));
    }

    private static void put(Item item, CauldronInteraction interaction) {
        JNECauldronInteractions.ECTOPLASM.map().put(item, interaction);
    }
}
