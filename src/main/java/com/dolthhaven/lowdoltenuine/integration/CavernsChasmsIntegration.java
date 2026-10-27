package com.dolthhaven.lowdoltenuine.integration;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNItems;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.teamabnormals.caverns_and_chasms.common.dispenser.FilledGoldenBucketDispenseBehavior;
import com.teamabnormals.caverns_and_chasms.common.item.GoldenBucketItem;
import com.teamabnormals.caverns_and_chasms.core.other.CCCauldronInteractions;
import com.teamabnormals.caverns_and_chasms.core.registry.CCItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class CavernsChasmsIntegration {
    public static Supplier<Item> item(ResourceLocation fluidloc) {
        return () -> {
            Fluid fluid = BuiltInRegistries.FLUID.get(fluidloc);
            return new GoldenBucketItem(fluid, new Item.Properties().stacksTo(1).craftRemainder(CCItems.GOLDEN_BUCKET.get()));
        };
    }

    public static void goldBucketItemProp(Item item) {
        ItemProperties.register(item, Consts.CAVERNS_AND_CHASMS.rl("level"), (stack, level, entity, hash) -> GoldenBucketItem.getFluidLevel(stack));
    }

    public static void registerGoldenBuckets() {
        putIfNotNull(Consts.ECTOPLASM.loc(), LDTNItems.GOLDEN_ECTOPLASM_BUCKET);
        putIfNotNull(Consts.MOLTEN_LEAD.loc(), LDTNItems.GOLDEN_MOLTEN_LEAD_BUCKET);
    }

    public static void registerDispensers() {
        DispenserBlock.registerBehavior(LDTNItems.GOLDEN_ECTOPLASM_BUCKET, new FilledGoldenBucketDispenseBehavior());
        DispenserBlock.registerBehavior(LDTNItems.GOLDEN_MOLTEN_LEAD_BUCKET, new FilledGoldenBucketDispenseBehavior());
    }

    private static void putIfNotNull(ResourceLocation location, DeferredItem<Item> bucket) {
        if (ModList.get().isLoaded(location.getNamespace())) {
            Fluid fluid = BuiltInRegistries.FLUID.get(location);
            GoldenBucketItem.GOLDEN_BUCKET_BEHAVIORS.put(state -> state.getFluidState().is(fluid), bucket);
        }
    }

    public static CauldronInteraction fillCauldron(Block block, Predicate<BlockState> canAct, SoundEvent emptySound, SoundEvent fillSound) {
        return (BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) -> {
            return CCCauldronInteractions.emptyBucket(level, pos, player, hand, stack,
                    block.defaultBlockState(),
                    canAct,
                    emptySound, fillSound);
        };
    }

    public static CauldronInteraction emptyCauldron(Item resultItem, Predicate<BlockState> canAct, SoundEvent fillSound) {
        return (BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) -> {
            return CCCauldronInteractions.fillBucket(state, level, pos, player, hand, stack,
                    resultItem.getDefaultInstance(),
                    canAct, fillSound);
        };
    }

    public static CauldronInteraction emptyCauldronWithHalfFilledBucket(Predicate<BlockState> canAct, SoundEvent fillSound) {
        return (BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) -> {
            return CCCauldronInteractions.fillFilledBucket(state, level, pos, player, hand, stack, canAct, fillSound);
        };
    }
}
