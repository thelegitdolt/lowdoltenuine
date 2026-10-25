package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.common.block.BlockWithOneString;
import com.dolthhaven.lowdoltenuine.integration.MowziesIntegration;
import com.dolthhaven.lowdoltenuine.integration.QuarkIntegration;
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
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.LDTNBlockProps.*;
import static net.minecraft.world.level.material.MapColor.GLOW_LICHEN;

public class LDTNBlocks {
    public static final BlockSubRegistryHelper BLOCKS = Lowdoltenuine.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> DATE_CRATE = BLOCKS.createBlock("date_crate", () -> new BlueprintDirectionalBlock(DATE_CRATE_PROP));
    public static final DeferredBlock<Block> HEART_CRYSTAL_LAMP = BLOCKS.createBlock("heart_crystal_lamp", () -> new Block(CC_LAMP));
    public static final DeferredBlock<Block> STURDY_DEEPSLATE = BLOCKS.createBlock("sturdy_deepslate", () -> new Block(STURDY_DEEPSLATE_PROPS));
    public static final DeferredBlock<Block> BAMBOO_BUNDLE = BLOCKS.createBlock("bamboo_bundle", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_BAMBOO_BLOCK)));



    public static final DeferredBlock<Block> ZINC_BRICKS = BLOCKS.createBlock("zinc_bricks", () -> new Block(ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> ZINC_BRICK_STAIRS = BLOCKS.createBlock("zinc_brick_stairs", () -> new StairBlock(ZINC_BRICKS.get().defaultBlockState(), ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> ZINC_BRICK_SLAB = BLOCKS.createBlock("zinc_brick_slab", () -> new SlabBlock(ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> ZINC_BRICK_WALL = BLOCKS.createBlock("zinc_brick_wall", () -> new WallBlock(ZINC_PLATED_BRICKS));
    public static final DeferredBlock<Block> CHISELED_ZINC_BRICKS = BLOCKS.createBlock("chiseled_zinc_bricks", () -> new Block(ZINC_PLATED_BRICKS));

    public static final DeferredBlock<Block> POTTED_ARID_SPROUTS = customPot("arid_sprouts", Consts.ARID_SPROUTS);
    public static final DeferredBlock<Block> POTTED_BEACHGRASS = customPot("beachgrass", Consts.BEACHGRASS);
    public static final DeferredBlock<Block> POTTED_TALL_BEACHGRASS = customPot("tall_beachgrass", Consts.TALL_BEACHGRASS);
    public static final DeferredBlock<Block> POTTED_MYCELIUM_SPROUTS = customPot("mycelium_sprouts", Consts.MYCELIUM_SPROUTS);

    public static final DeferredBlock<Block> ARID_RAKED_SAND = getRakedSand("arid_raked_sand", Consts.ARID_SAND, 0xDBD3A0);
    public static final DeferredBlock<Block> RED_ARID_RAKED_SAND = getRakedSand("red_arid_raked_sand", Consts.RED_ARID_SAND, 0xF7D3A0);
    public static final DeferredBlock<Block> ASHEN_RAKED_SAND = getRakedSand("ashen_raked_sand", Consts.BB_ASHEN_SAND, 0x565E5D);

    public static final DeferredBlock<Block> WHITE_ENCASED_PIPE = encasedPipe("white");
    public static final DeferredBlock<Block> BROWN_ENCASED_PIPE = encasedPipe("brown");
    public static final DeferredBlock<Block> GRAY_ENCASED_PIPE = encasedPipe("gray");
    public static final DeferredBlock<Block> LIGHT_GRAY_ENCASED_PIPE = encasedPipe("light_gray");
    public static final DeferredBlock<Block> RED_ENCASED_PIPE = encasedPipe("red");
    public static final DeferredBlock<Block> ORANGE_ENCASED_PIPE = encasedPipe("orange");
    public static final DeferredBlock<Block> YELLOW_ENCASED_PIPE = encasedPipe("yellow");
    public static final DeferredBlock<Block> LIME_ENCASED_PIPE = encasedPipe("lime");
    public static final DeferredBlock<Block> GREEN_ENCASED_PIPE = encasedPipe("green");
    public static final DeferredBlock<Block> BLUE_ENCASED_PIPE = encasedPipe("blue");
    public static final DeferredBlock<Block> LIGHT_BLUE_ENCASED_PIPE = encasedPipe("light_blue");
    public static final DeferredBlock<Block> CYAN_ENCASED_PIPE = encasedPipe("cyan");
    public static final DeferredBlock<Block> PURPLE_ENCASED_PIPE = encasedPipe("purple");
    public static final DeferredBlock<Block> MAGENTA_ENCASED_PIPE = encasedPipe("magenta");
    public static final DeferredBlock<Block> PINK_ENCASED_PIPE = encasedPipe("pink");
    public static final DeferredBlock<Block> BLACK_ENCASED_PIPE = encasedPipe("black");
    public static final DeferredBlock<Block> ROSE_ENCASED_PIPE = encasedPipe("rose");
    public static final DeferredBlock<Block> MAROON_ENCASED_PIPE = encasedPipe("maroon");
    public static final DeferredBlock<Block> GINGER_ENCASED_PIPE = encasedPipe("ginger");
    public static final DeferredBlock<Block> TAN_ENCASED_PIPE = encasedPipe("tan");
    public static final DeferredBlock<Block> BEIGE_ENCASED_PIPE = encasedPipe("beige");
    public static final DeferredBlock<Block> CORAL_ENCASED_PIPE = encasedPipe("coral");
    public static final DeferredBlock<Block> OLIVE_ENCASED_PIPE = encasedPipe("olive");
    public static final DeferredBlock<Block> FOREST_ENCASED_PIPE = encasedPipe("forest");
    public static final DeferredBlock<Block> VERDANT_ENCASED_PIPE = encasedPipe("verdant");
    public static final DeferredBlock<Block> AMBER_ENCASED_PIPE = encasedPipe("amber");
    public static final DeferredBlock<Block> TEAL_ENCASED_PIPE = encasedPipe("teal");
    public static final DeferredBlock<Block> MINT_ENCASED_PIPE = encasedPipe("mint");
    public static final DeferredBlock<Block> AQUA_ENCASED_PIPE = encasedPipe("aqua");
    public static final DeferredBlock<Block> SLATE_ENCASED_PIPE = encasedPipe("slate");
    public static final DeferredBlock<Block> NAVY_ENCASED_PIPE = encasedPipe("navy");
    public static final DeferredBlock<Block> INDIGO_ENCASED_PIPE = encasedPipe("indigo");

    private static DeferredBlock<Block> customPot(String name, ResourceLocation resource) {
        if (ModList.get().isLoaded(resource.getNamespace())){
            return BLOCKS.createBlockNoItem("potted_" + name, () -> new FlowerPotBlock(RegUtil.block(resource), PropertyUtil.flowerPot()));
        }
        return BLOCKS.createBlockNoItem("potted_" + name, () -> new BlockWithOneString(resource.toString(), PropertyUtil.flowerPot()));
    }
    
    private static DeferredBlock<Block> encasedPipe(String dye) {
        Supplier<Block> pipeSupplier = Consts.QUARK.loaded() ? QuarkIntegration.encasedPipe() : () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_STAINED_GLASS));
        return BLOCKS.createBlock(dye + "_encased_pipe", pipeSupplier);
    }

    private static DeferredBlock<Block> getRakedSand(String name, ResourceLocation sandLoc, int color) {
        boolean modsOK = (ModList.get().isLoaded(sandLoc.getNamespace()) && Consts.MOWZIES_MOBS.loaded()) || DatagenModLoader.isRunningDataGen();
        Supplier<Block> block = modsOK ? MowziesIntegration.rakedSand(color, sandLoc) : () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND));
        return BLOCKS.createBlock(name, block);
    }

    public static class LDTNBlockProps {
        public static final BlockBehaviour.Properties STURDY_DEEPSLATE_PROPS = BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(4.5F, 9.0F).pushReaction(PushReaction.IGNORE);
        public static final BlockBehaviour.Properties CC_LAMP = BlockBehaviour.Properties.of().lightLevel((state) -> 15).strength(0.3F).isValidSpawn((a, b, c, d) -> true).sound(SoundType.AMETHYST);
        public static final BlockBehaviour.Properties DATE_CRATE_PROP = BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_BROWN);

        public static final BlockBehaviour.Properties ZINC_PLATED_BRICKS = BlockBehaviour.Properties.of().mapColor(GLOW_LICHEN)
                .strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM);
    }
}
