package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNBlockTags extends BlockTagsProvider {
    public LDTNBlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(DATE_CRATE.get());

        this.tag(Tags.Blocks.STORAGE_BLOCKS).add(STURDY_DEEPSLATE.get(), DATE_CRATE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(STURDY_DEEPSLATE.get(), ZINC_BRICK_SLAB.get(), ZINC_BRICKS.get(), ZINC_BRICK_WALL.get(),
                CHISELED_ZINC_BRICKS.get(), ZINC_BRICK_STAIRS.get(), HEART_CRYSTAL_LAMP.get(),
                WHITE_ENCASED_PIPE.get(), BROWN_ENCASED_PIPE.get(), GRAY_ENCASED_PIPE.get(), LIGHT_GRAY_ENCASED_PIPE.get(), RED_ENCASED_PIPE.get(), ORANGE_ENCASED_PIPE.get(),  YELLOW_ENCASED_PIPE.get(), LIME_ENCASED_PIPE.get(), GREEN_ENCASED_PIPE.get(), BLUE_ENCASED_PIPE.get(), LIGHT_BLUE_ENCASED_PIPE.get(), CYAN_ENCASED_PIPE.get(), PURPLE_ENCASED_PIPE.get(), MAGENTA_ENCASED_PIPE.get(), PINK_ENCASED_PIPE.get(), BLACK_ENCASED_PIPE.get(), ROSE_ENCASED_PIPE.get(), MAROON_ENCASED_PIPE.get(), GINGER_ENCASED_PIPE.get(), TAN_ENCASED_PIPE.get(), BEIGE_ENCASED_PIPE.get(), CORAL_ENCASED_PIPE.get(), OLIVE_ENCASED_PIPE.get(), FOREST_ENCASED_PIPE.get(), VERDANT_ENCASED_PIPE.get(), AMBER_ENCASED_PIPE.get(), TEAL_ENCASED_PIPE.get(), MINT_ENCASED_PIPE.get(), AQUA_ENCASED_PIPE.get(), SLATE_ENCASED_PIPE.get(), NAVY_ENCASED_PIPE.get(), INDIGO_ENCASED_PIPE.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ARID_RAKED_SAND.get(), RED_ARID_RAKED_SAND.get(), ASHEN_RAKED_SAND.get());
        this.tag(LDTNTags.Blocks.RAKED_SANDS).add(ARID_RAKED_SAND.get(), RED_ARID_RAKED_SAND.get(), ASHEN_RAKED_SAND.get())
                .addOptional(Consts.MOWZIES_MOBS.rl("raked_sand"))
                .addOptional(Consts.MOWZIES_MOBS.rl("red_raked_sand"));

        this.tag(BlockTags.SAND).addTag(LDTNTags.Blocks.RAKED_SANDS);
        this.tag(Tags.Blocks.SANDS_COLORLESS).add(ARID_RAKED_SAND.get());
        this.tag(Tags.Blocks.SANDS_RED).add(RED_ARID_RAKED_SAND.get());

        this.tag(LDTNTags.Blocks.ENCASED_PIPE_BLOCKS).add(WHITE_ENCASED_PIPE.get(), BROWN_ENCASED_PIPE.get(), GRAY_ENCASED_PIPE.get(), LIGHT_GRAY_ENCASED_PIPE.get(), RED_ENCASED_PIPE.get(), ORANGE_ENCASED_PIPE.get(),
                YELLOW_ENCASED_PIPE.get(), LIME_ENCASED_PIPE.get(), GREEN_ENCASED_PIPE.get(), BLUE_ENCASED_PIPE.get(), LIGHT_BLUE_ENCASED_PIPE.get(), CYAN_ENCASED_PIPE.get(), PURPLE_ENCASED_PIPE.get(),
                MAGENTA_ENCASED_PIPE.get(), PINK_ENCASED_PIPE.get(), BLACK_ENCASED_PIPE.get(), ROSE_ENCASED_PIPE.get(), MAROON_ENCASED_PIPE.get(), GINGER_ENCASED_PIPE.get(), TAN_ENCASED_PIPE.get(),
                BEIGE_ENCASED_PIPE.get(), CORAL_ENCASED_PIPE.get(), OLIVE_ENCASED_PIPE.get(), FOREST_ENCASED_PIPE.get(), VERDANT_ENCASED_PIPE.get(), AMBER_ENCASED_PIPE.get(),
                TEAL_ENCASED_PIPE.get(), MINT_ENCASED_PIPE.get(), AQUA_ENCASED_PIPE.get(), SLATE_ENCASED_PIPE.get(), NAVY_ENCASED_PIPE.get(), INDIGO_ENCASED_PIPE.get());

        this.tag(BlockTags.FLOWER_POTS).add(POTTED_BEACHGRASS.get(), POTTED_MYCELIUM_SPROUTS.get(), POTTED_TALL_BEACHGRASS.get(), POTTED_ARID_SPROUTS.get());

        this.tag(LDTNTags.Blocks.QUARK_PIPES).addTags(LDTNTags.Blocks.ENCASED_PIPE_BLOCKS);
    }
}
