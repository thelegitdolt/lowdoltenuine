package com.dolthhaven.lowdoltenuine.core.mixin.quark;

import com.dolthhaven.lowdoltenuine.integration.DyeDepotIntegration;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.violetmoon.quark.addons.oddities.block.be.PipeBlockEntity;
import org.violetmoon.quark.addons.oddities.block.pipe.BasePipeBlock;
import org.violetmoon.quark.addons.oddities.module.PipesModule;
import org.violetmoon.zeta.util.MiscUtil;
import vectorwing.farmersdelight.common.utility.ItemUtils;

@Pseudo
@Mixin(BasePipeBlock.class)
public abstract class BasePipeBlockMixin {

    @Inject(method = "useItemOn", at = @At(value = "HEAD"), cancellable = true)
    private void sex(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<ItemInteractionResult> cir) {
        if (PipesModule.enableEncasedPipes && stack.is(ItemTags.PICKAXES)) {
            Item glass;
            if (state.is(PipesModule.encasedPipe)) glass = Items.GLASS;
            else {
                ResourceLocation dye = DyeDepotIntegration.getDyeFromName(RegUtil.blockId(state.getBlock()).getPath().replace("_encased_pipe", ""));
                if (dye == null) return;
                glass = RegUtil.item(dye.withSuffix("_stained_glass"));
            }
            if (glass != null) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(handIn));
                BlockState newState = getTargetState(level, pos);

                Direction direction = player.getDirection().getOpposite();
                ItemUtils.spawnItemEntity(level, new ItemStack(glass), (double)pos.getX() + (double)0.5F, (double)pos.getY() + 0.3, (double)pos.getZ() + (double)0.5F, (double)direction.getStepX() * 0.15, 0.05, (double)direction.getStepZ() * 0.15);

                level.setBlock(pos, newState, 3);
                level.updateNeighborsAt(pos, PipesModule.encasedPipe);
                level.playSound(player, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS);
                cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }

    @Unique
    private BlockState getTargetState(LevelAccessor level, BlockPos pos) {
        BlockState newState = PipesModule.pipe.defaultBlockState();

        for(Direction facing : Direction.values()) {
            PipeBlockEntity.ConnectionType type = PipeBlockEntity.computeConnectionTo(level, pos, facing);
            newState = newState.setValue(MiscUtil.directionProperty(facing), ((BasePipeBlock)PipesModule.pipe).allowsFullConnection(type));
        }

        return newState;
    }

}

