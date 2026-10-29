package com.dolthhaven.lowdoltenuine.core.mixin.heartcrystals;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.other.LDTNConfig;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import java.util.List;

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

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        if (LDTNConfig.COMMON.heartGeodeBlock.get()) {
            ResourceKey<LootTable> heartGeodeTable = ResourceKey.create(Registries.LOOT_TABLE, Lowdoltenuine.rl("blocks/heart_geode"));
            LootParams lootparams = params.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
            ServerLevel serverlevel = lootparams.getLevel();
            LootTable loottable = serverlevel.getServer().reloadableRegistries().getLootTable(heartGeodeTable);
            return loottable.getRandomItems(lootparams);
        }
        return super.getDrops(state, params);
    }
}
