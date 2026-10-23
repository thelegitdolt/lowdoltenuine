package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.common.block.BlockWithOneString;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.violetmoon.quark.addons.oddities.block.pipe.EncasedPipeBlock;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.LDTNBlockProps.*;
import static net.minecraft.world.level.material.MapColor.GLOW_LICHEN;

public class LDTNBlocks {
    public static final BlockSubRegistryHelper BLOCKS = Lowdoltenuine.REGISTRY_HELPER.getBlockSubHelper();


    public static final DeferredBlock<Block> DATE_CRATE = BLOCKS.createBlock("date_crate", () -> new BlueprintDirectionalBlock(DATE_CRATE_PROP));
    public static final DeferredBlock<Block> HEART_CRYSTAL_LAMP = BLOCKS.createBlock("heart_crystal_lamp", () -> new Block(CC_LAMP));
    public static final DeferredBlock<Block> STURDY_DEEPSLATE = BLOCKS.createBlock("sturdy_deepslate", () -> new Block(STURDY_DEEPSLATE_PROPS));

    public static final DeferredBlock<Block> ZINC_BRICKS = BLOCKS.createBlock("zinc_bricks", () -> new Block(ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> ZINC_BRICK_STAIRS = BLOCKS.createBlock("zinc_brick_stairs", () -> new StairBlock(ZINC_BRICKS.get().defaultBlockState(), ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> ZINC_BRICK_SLAB = BLOCKS.createBlock("zinc_brick_slab", () -> new SlabBlock(ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> ZINC_BRICK_WALL = BLOCKS.createBlock("zinc_brick_wall", () -> new WallBlock(ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> CHISELED_ZINC_BRICKS = BLOCKS.createBlock("chiseled_zinc_bricks", () -> new Block(ZINC_PLATED_BRICKS));

    public static final DeferredBlock<Block> POTTED_ARID_SPROUTS = customPot("arid_sprouts", Consts.ARID_SPROUTS);
    public static final DeferredBlock<Block> POTTED_BEACHGRASS = customPot("beachgrass", Consts.BEACHGRASS);
    public static final DeferredBlock<Block> POTTED_TALL_BEACHGRASS = customPot("tall_beachgrass", Consts.TALL_BEACHGRASS);
    public static final DeferredBlock<Block> POTTED_MYCELIUM_SPROUTS = customPot("mycelium_sprouts", Consts.MYCELIUM_SPROUTS);

    
    public static final DeferredBlock<Block> WHITE_ENCASED_PIPE = BLOCKS.createBlock("white_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> BROWN_ENCASED_PIPE = BLOCKS.createBlock("brown_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> GRAY_ENCASED_PIPE = BLOCKS.createBlock("gray_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> LIGHT_GRAY_ENCASED_PIPE = BLOCKS.createBlock("light_gray_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> RED_ENCASED_PIPE = BLOCKS.createBlock("red_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> ORANGE_ENCASED_PIPE = BLOCKS.createBlock("orange_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> YELLOW_ENCASED_PIPE = BLOCKS.createBlock("yellow_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> LIME_ENCASED_PIPE = BLOCKS.createBlock("lime_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> GREEN_ENCASED_PIPE = BLOCKS.createBlock("green_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> BLUE_ENCASED_PIPE = BLOCKS.createBlock("blue_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> LIGHT_BLUE_ENCASED_PIPE = BLOCKS.createBlock("light_blue_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> CYAN_ENCASED_PIPE = BLOCKS.createBlock("cyan_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> PURPLE_ENCASED_PIPE = BLOCKS.createBlock("purple_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> MAGENTA_ENCASED_PIPE = BLOCKS.createBlock("magenta_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> PINK_ENCASED_PIPE = BLOCKS.createBlock("pink_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> BLACK_ENCASED_PIPE = BLOCKS.createBlock("black_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> ROSE_ENCASED_PIPE = BLOCKS.createBlock("rose_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> MAROON_ENCASED_PIPE = BLOCKS.createBlock("maroon_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> GINGER_ENCASED_PIPE = BLOCKS.createBlock("ginger_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> TAN_ENCASED_PIPE = BLOCKS.createBlock("tan_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> BEIGE_ENCASED_PIPE = BLOCKS.createBlock("beige_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> CORAL_ENCASED_PIPE = BLOCKS.createBlock("coral_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> OLIVE_ENCASED_PIPE = BLOCKS.createBlock("olive_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> FOREST_ENCASED_PIPE = BLOCKS.createBlock("forest_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> VERDANT_ENCASED_PIPE = BLOCKS.createBlock("verdant_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> AMBER_ENCASED_PIPE = BLOCKS.createBlock("amber_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> TEAL_ENCASED_PIPE = BLOCKS.createBlock("teal_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> MINT_ENCASED_PIPE = BLOCKS.createBlock("mint_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> AQUA_ENCASED_PIPE = BLOCKS.createBlock("aqua_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> SLATE_ENCASED_PIPE = BLOCKS.createBlock("slate_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> NAVY_ENCASED_PIPE = BLOCKS.createBlock("navy_encased_pipe", () -> new EncasedPipeBlock(null));
    public static final DeferredBlock<Block> INDIGO_ENCASED_PIPE = BLOCKS.createBlock("indigo_encased_pipe", () -> new EncasedPipeBlock(null));

    private static DeferredBlock<Block> customPot(String name, ResourceLocation resource) {
        if (ModList.get().isLoaded(resource.getNamespace())){
            return BLOCKS.createBlockNoItem("potted_" + name, () -> new FlowerPotBlock(RegUtil.block(resource), PropertyUtil.flowerPot()));
        }
        return BLOCKS.createBlockNoItem("potted_" + name, () -> new BlockWithOneString(resource.toString(), PropertyUtil.flowerPot()));
    }

    public static class LDTNBlockProps {
        public static final BlockBehaviour.Properties STURDY_DEEPSLATE_PROPS = BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(4.5F, 9.0F).pushReaction(PushReaction.IGNORE);
        public static final BlockBehaviour.Properties CC_LAMP = BlockBehaviour.Properties.of().lightLevel((state) -> 15).strength(0.3F).isValidSpawn((a, b, c, d) -> true).sound(SoundType.AMETHYST);
        public static final BlockBehaviour.Properties DATE_CRATE_PROP = BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_BROWN);

        public static final BlockBehaviour.Properties ZINC_PLATED_BRICKS = BlockBehaviour.Properties.of().mapColor(GLOW_LICHEN)
                .strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM);
    }
}
