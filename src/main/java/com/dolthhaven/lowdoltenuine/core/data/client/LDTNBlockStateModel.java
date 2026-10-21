package com.dolthhaven.lowdoltenuine.core.data.client;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.data.event.GatherDataEvent;


public class LDTNBlockStateModel extends BlueprintBlockStateProvider {
    public LDTNBlockStateModel(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Lowdoltenuine.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
    }


    private ResourceLocation id(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private void basicItemWithWeirdPath(String name, ResourceLocation location) {
        this.itemModels().getBuilder(name).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", location);
    }
}
