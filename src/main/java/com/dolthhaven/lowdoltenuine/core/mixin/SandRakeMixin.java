package com.dolthhaven.lowdoltenuine.core.mixin;

import com.bobmowzie.mowziesmobs.server.block.RakedSandBlock;
import com.bobmowzie.mowziesmobs.server.item.ItemSandRake;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemSandRake.class)
public class SandRakeMixin {
    @ModifyVariable(method = "useOn", at = @At(value = "STORE"), name = "origBlock")
    private RakedSandBlock sex(RakedSandBlock origBlock, @Local(name = "blockstate") BlockState state) {
        Holder<Block> maybeRake = state.getBlockHolder().getData(LDTNTags.Datamaps.RAKE_MAP);
        if (maybeRake == null) return origBlock;
        return (RakedSandBlock) maybeRake.value();
    }
}
