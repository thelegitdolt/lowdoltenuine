package com.dolthhaven.lowdoltenuine.other;

import com.dolthhaven.lowdoltenuine.integration.CavernsChasmsIntegration;
import com.dolthhaven.lowdoltenuine.integration.QuarkIntegration;
import com.dolthhaven.lowdoltenuine.integration.doublewhammy.CavernsJNEInt;
import com.dolthhaven.lowdoltenuine.integration.doublewhammy.CavernsOreganizedInt;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LDTNCommonSetup {
    public static final Map<Item, Pair<Supplier<Boolean>, BlockItem>> ITEM_PLACE_MAP = new HashMap<>();

    public static void registerData() {
        if (Consts.QUARK.loaded()) {
            QuarkIntegration.registerPipes();
        }
        registerBlockPlacing();
        dispensers();
        if (Consts.CAVERNS_AND_CHASMS.loaded()) {
            CavernsChasmsIntegration.registerGoldenBuckets();
            if (Consts.OREGANIZED.loaded()) {
                CavernsOreganizedInt.registerCauldrons();
            }
            if (Consts.JNE.loaded()) {
                CavernsJNEInt.registerCauldrons();
            }
        }
    }

    public static void registerBlockPlacing() {
        putIfNotNull(ITEM_PLACE_MAP, Items.BONE, LDTNConfig.COMMON.shouldPlaceBonePilesWithNormalBones,
                (BlockItem) RegUtil.item(Consts.BONE_PIKE));
    }

    public static void dispensers() {
        if (Consts.CAVERNS_AND_CHASMS.loaded()) {
            CavernsChasmsIntegration.registerDispensers();
        }
    }

    private static <A, B, C> void putIfNotNull(Map<A, Pair<B, C>> map, A key, B val, C thing) {
        if (key != null && val != null && thing != null) map.put(key, Pair.of(val, thing));
    }
}
