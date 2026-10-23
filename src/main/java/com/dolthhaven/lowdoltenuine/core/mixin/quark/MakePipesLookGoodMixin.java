package com.dolthhaven.lowdoltenuine.core.mixin.quark;

import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HalfTransparentBlock.class)
public class MakePipesLookGoodMixin {
    @Inject(method = "skipRendering", at = @At("HEAD"), cancellable = true)
    private void skipRendering(BlockState state, BlockState other, Direction direction, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if(state.is(Blocks.GLASS) && other.is(LDTNTags.Blocks.ENCASED_PIPE_BLOCKS)) {
            callbackInfoReturnable.setReturnValue(true);
            callbackInfoReturnable.cancel();
        }
    }
}
