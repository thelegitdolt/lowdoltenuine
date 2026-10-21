package com.dolthhaven.lowdoltenuine.core.data.client;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNItems;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.rosemods.heart_crystals.common.item.HeartCrystalItem;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.data.event.GatherDataEvent;


public class LDTNItemModelGen extends BlueprintItemModelProvider {
    public LDTNItemModelGen(GatherDataEvent e) {
        super(e.getGenerator().getPackOutput(), Lowdoltenuine.MOD_ID, e.getExistingFileHelper());
    }

    @Override
    protected void registerModels() {
        this.basicItemWithWeirdPath(LDTNItems.HEART_GEODE, Consts.HEART_CRYSTALS.rl("block/heart_crystal"));

    }

    private void basicItemWithWeirdPath(ItemLike itemLike, ResourceLocation location) {
        this.getBuilder(id(itemLike.asItem()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", location);
    }

    private ResourceLocation id(Item item) {
        return RegUtil.itemId(item);
    }
}
