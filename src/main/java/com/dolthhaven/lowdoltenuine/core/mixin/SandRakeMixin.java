package com.dolthhaven.lowdoltenuine.core.mixin;

import com.bobmowzie.mowziesmobs.server.item.ItemSandRake;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ItemSandRake.class)
public class SandRakeMixin {
    @ModifyArgs(method = "useOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private void sex(Args args, @Local(argsOnly = true) UseOnContext context, @Local(name = "blockPlaceContext") BlockPlaceContext placeContext) {
        var maybeRake = context.getLevel().getBlockState(context.getClickedPos()).getBlockHolder().getData(LDTNTags.Datamaps.RAKE_MAP);
        if (maybeRake == null) return;
        args.set(1, maybeRake.value().getStateForPlacement(placeContext));
    }
}
