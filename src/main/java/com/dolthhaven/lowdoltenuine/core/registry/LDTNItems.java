package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.function.Predicate;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class LDTNItems {
    public static final ItemSubRegistryHelper ITEMS = Lowdoltenuine.REGISTRY_HELPER.getItemSubHelper();

    public static void setUpTabEditors() {
        CreativeModeTabContentsPopulator.mod(Lowdoltenuine.MOD_ID)
                .tab(CreativeModeTabs.NATURAL_BLOCKS)
                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                .addItemsAfter(ofID(Consts.STURDY_STONE), STURDY_DEEPSLATE)
                .predicate(LDTNItems::createPredicate)
                .addItemsAfter(ofID(Consts.CREATE_ZINC_BLOCK, Consts.CREATE.id()), ZINC_BRICKS, ZINC_BRICK_STAIRS, ZINC_BRICK_SLAB, ZINC_BRICK_WALL, CHISELED_ZINC_BRICKS);
    }

    public static boolean createPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTabKey().location().equals(Consts.CREATE_BUILDING_TAB);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(RegUtil.item(location)).test(stack));
    }
}
