package com.dolthhaven.lowdoltenuine.core.data.client;

import com.bobmowzie.mowziesmobs.server.block.RakedSandBlock;
import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlockFamilies;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNBlockStateModel extends BlueprintBlockStateProvider {
    public LDTNBlockStateModel(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        block(STURDY_DEEPSLATE);
        block(HEART_CRYSTAL_LAMP);

        logBlock(BAMBOO_BUNDLE);
        directionalBlockSharedSide(DATE_CRATE, DATE_CRATE);
        forDefaultBlockFamily(LDTNBlockFamilies.ZINC_BRICKS_FAMILY);

        customPot(POTTED_ARID_SPROUTS, POTTED_MYCELIUM_SPROUTS, POTTED_BEACHGRASS, POTTED_TALL_BEACHGRASS);

        rakedSand(ASHEN_RAKED_SAND, Consts.BB_ASHEN_SAND.loc());
        rakedSand(ARID_RAKED_SAND, Consts.ARID_SAND.loc());
        rakedSand(RED_ARID_RAKED_SAND, Consts.RED_ARID_SAND.loc());
    }

    private void rakedSand(DeferredBlock<? extends Block> sand, ResourceLocation nonRaked) {
        String cubeTop = "block/cube_top";
        this.getVariantBuilder(sand.get()).forAllStates(state -> {
            Function<String, ConfiguredModel.Builder<?>> sandModelFunction = sandType -> {
                String name = name(sand.get()) + (sandType.isEmpty() ? "" : "_" + sandType.replace("_", ""));
                return ConfiguredModel.builder().modelFile(this.models().withExistingParent("block/" + name, cubeTop)
                        .texture("side", blockTexture(nonRaked))
                        .texture("top", id(sand.get()).withPath(str -> "block/%s%s".formatted(name(sand.get()),
                                sandType.isEmpty() ? "" :  "_" + sandType.replace("_", "")))));
            };

            Function<String, Integer> getFacingValue = str -> switch (str) {
                case "north_east" ->  270;
                case "north_west" ->  180;
                case "south_east" ->  0;
                case "south_west" ->  90;
                default -> throw new IllegalStateException("Unexpected value: " + str);
            };

            String shape = state.getValue(RakedSandBlock.SHAPE).getName();
            String[] shapeName = shape.split("_");

            if (Objects.equals(shapeName[0], "north") && Objects.equals(shapeName[1], "south")) {
                return sandModelFunction.apply("").build();
            }
            else if (Objects.equals(shapeName[0], "east") && Objects.equals(shapeName[1], "west")) {
                return sandModelFunction.apply("").rotationY(90).build();
            }
            else {
                int rotationAmount = getFacingValue.apply(shape);
                return sandModelFunction.apply(shape).rotationY(rotationAmount).build();
            }
        });

        this.blockItem(sand.get());
    }

    private void forDefaultBlockFamily(BlockFamily family) {
        Block base = family.getBaseBlock();
        Block slab = family.get(BlockFamily.Variant.SLAB);
        Block stairs = family.get(BlockFamily.Variant.STAIRS);
        Block chiseled = family.get(BlockFamily.Variant.CHISELED);
        Block wall = family.get(BlockFamily.Variant.WALL);

        block(base);
        block(chiseled);
        wallBlock(base, wall);
        stairsBlock(base, stairs);
        slabBlock(base, slab);
    }

    private ResourceLocation id(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private void basicItemWithWeirdPath(String name, ResourceLocation location) {
        this.itemModels().getBuilder(name).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", location);
    }

    @SafeVarargs
    private void customPot(DeferredBlock<? extends Block>... pots) {
        for (DeferredBlock<? extends Block> pot : pots) {
            ResourceLocation potTexture = Lowdoltenuine.rl("block/" + name(pot.get()));
            this.simpleBlock(pot.get(), models()
                    .singleTexture(name(pot.get()), ResourceLocation.withDefaultNamespace("block/flower_pot_cross"), "plant", potTexture).renderType("cutout"));
        }
    }

    private ResourceLocation blockTexture(ResourceLocation block) {
        return block.withPrefix("block/");
    }
}
