package com.dolthhaven.lowdoltenuine.core.mixin.heartcrystals;

import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(HeartCrystalBlock.class)
public class HeartCrystalBlockMixin extends Block {
    @Override
    public Item asItem() {
        return Consts.HEART_CRYSTAL_ITEM.item();
    }

    public HeartCrystalBlockMixin(Properties properties) {
        super(properties);
    }
}
