package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class LDTNTags {
    public static final TagKey<Item> HIDES_CAPES = itemTag("hides_capes");

    private static TagKey<Item> itemTag(String path) {
        return externalItemTag(Lowdoltenuine.MOD_ID, path);
    }

    private static TagKey<Item> externalItemTag(String modId, String path) {
        return TagUtil.itemTag(modId, path);
    }
}
