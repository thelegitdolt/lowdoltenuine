package com.dolthhaven.lowdoltenuine.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CustomLangBlockItem extends BlockItem {
    public CustomLangBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}
