package com.dolthhaven.lowdoltenuine.common.block;

import net.minecraft.world.level.block.Block;

// a debug block, meant as a placeholder when certain mods aren't loaded.
// holds a string as information to be used in other code
// currently used for flower pots. For potted beachgrass when upgrade aquatic isn't loaded
// pulls from the string can still create a loot table after registering dummy items.
public class BlockWithOneString extends Block {
    private final String theString;

    public BlockWithOneString(String string, Properties properties) {
        super(properties);
        this.theString = string;
    }

    public String getTheString() {
        return theString;
    }
}
