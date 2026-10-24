package com.dolthhaven.lowdoltenuine.integration;

import com.bobmowzie.mowziesmobs.server.block.RakedSandBlock;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.data.loading.DatagenModLoader;

import java.util.function.Supplier;

public class MowziesIntegration {
    public static Supplier<Block> rakedSand(int color, ResourceLocation sandLoc) {
        ResourceLocation newSandLoc = DatagenModLoader.isRunningDataGen() ? Consts.MINECRAFT.rl("sand") : sandLoc;
        return () -> {
            Block sandBlock = RegUtil.block(newSandLoc);
            return new RakedSandBlock(new ColorRGBA(color), BlockBehaviour.Properties.ofFullCopy(sandBlock), sandBlock.defaultBlockState());
        };
    }
}
