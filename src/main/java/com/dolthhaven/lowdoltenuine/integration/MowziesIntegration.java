package com.dolthhaven.lowdoltenuine.integration;

import com.bobmowzie.mowziesmobs.server.block.RakedSandBlock;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.data.loading.DatagenModLoader;

import java.util.function.Supplier;

public class MowziesIntegration {
    public static Supplier<Block> rakedSand(int color, ResourceLocation sandLoc) {
        Block sandBlock = !DatagenModLoader.isRunningDataGen() ? RegUtil.block(sandLoc) : Blocks.SAND;
        return () -> new RakedSandBlock(new ColorRGBA(color), BlockBehaviour.Properties.ofFullCopy(sandBlock), sandBlock.defaultBlockState());
    }
}
