package com.dolthhaven.lowdoltenuine.other;

import net.minecraft.world.level.block.Block;
import org.violetmoon.quark.addons.oddities.module.PipesModule;

import java.util.HashSet;
import java.util.Set;
import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNCommonSetup {
    public static void registerData() {
        registerPipes();
    }

    private static void registerPipes() {
        Set<Block> block = new HashSet<>(PipesModule.blockEntityType.validBlocks);
        block.add(WHITE_ENCASED_PIPE.get());
        block.add(BROWN_ENCASED_PIPE.get());
        block.add(GRAY_ENCASED_PIPE.get());
        block.add(LIGHT_GRAY_ENCASED_PIPE.get());
        block.add(RED_ENCASED_PIPE.get());
        block.add(ORANGE_ENCASED_PIPE.get());
        block.add(YELLOW_ENCASED_PIPE.get());
        block.add(LIME_ENCASED_PIPE.get());
        block.add(GREEN_ENCASED_PIPE.get());
        block.add(BLUE_ENCASED_PIPE.get());
        block.add(LIGHT_BLUE_ENCASED_PIPE.get());
        block.add(CYAN_ENCASED_PIPE.get());
        block.add(PURPLE_ENCASED_PIPE.get());
        block.add(MAGENTA_ENCASED_PIPE.get());
        block.add(PINK_ENCASED_PIPE.get());
        block.add(BLACK_ENCASED_PIPE.get());
        block.add(ROSE_ENCASED_PIPE.get());
        block.add(MAROON_ENCASED_PIPE.get());
        block.add(GINGER_ENCASED_PIPE.get());
        block.add(TAN_ENCASED_PIPE.get());
        block.add(BEIGE_ENCASED_PIPE.get());
        block.add(CORAL_ENCASED_PIPE.get());
        block.add(OLIVE_ENCASED_PIPE.get());
        block.add(FOREST_ENCASED_PIPE.get());
        block.add(VERDANT_ENCASED_PIPE.get());
        block.add(AMBER_ENCASED_PIPE.get());
        block.add(TEAL_ENCASED_PIPE.get());
        block.add(MINT_ENCASED_PIPE.get());
        block.add(AQUA_ENCASED_PIPE.get());
        block.add(SLATE_ENCASED_PIPE.get());
        block.add(NAVY_ENCASED_PIPE.get());
        block.add(INDIGO_ENCASED_PIPE.get());
        PipesModule.blockEntityType.validBlocks = block;
    }
}
