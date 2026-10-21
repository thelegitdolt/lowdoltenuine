package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

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

    public static class LDTNBlockProps {
        public static final BlockBehaviour.Properties STURDY_DEEPSLATE_PROPS = BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(4.5F, 9.0F).pushReaction(PushReaction.IGNORE);
        public static final BlockBehaviour.Properties CC_LAMP = BlockBehaviour.Properties.of().lightLevel((state) -> 15).strength(0.3F).isValidSpawn((a, b, c, d) -> true).sound(SoundType.AMETHYST);
        public static final BlockBehaviour.Properties DATE_CRATE_PROP = BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(1.5f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_BROWN);

        public static final BlockBehaviour.Properties ZINC_PLATED_BRICKS = BlockBehaviour.Properties.of().mapColor(GLOW_LICHEN)
                .strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM);
    }
}
