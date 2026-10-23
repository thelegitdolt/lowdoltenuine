package com.dolthhaven.lowdoltenuine.core.data.client;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlockFamilies;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNBlockStateModel extends BlueprintBlockStateProvider {
    public LDTNBlockStateModel(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        block(STURDY_DEEPSLATE);
        block(HEART_CRYSTAL_LAMP);
        directionalBlockSharedSide(DATE_CRATE, DATE_CRATE);
        forDefaultBlockFamily(LDTNBlockFamilies.ZINC_BRICKS_FAMILY);
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
}
