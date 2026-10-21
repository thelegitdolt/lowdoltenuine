package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.common.block.PatchBlockItem;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Predicate;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class LDTNItems {
    public static final ItemSubRegistryHelper ITEMS = Lowdoltenuine.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<BlockItem> HEART_GEODE = ITEMS.createItem("heart_geode", () -> new PatchBlockItem(RegUtil.blockOrSlop(Consts.HEART_CRYSTAL_ITEM), new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static void setUpTabEditors() {
        CreativeModeTabContentsPopulator.mod(Lowdoltenuine.MOD_ID)
                .tab(CreativeModeTabs.NATURAL_BLOCKS)
                    .addItemsAfter(ofMod(Items.AMETHYST_CLUSTER, Consts.HEART_CRYSTALS), HEART_GEODE)
                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                    .addItemsAfter(ofID(Consts.STURDY_STONE), STURDY_DEEPSLATE)
                .predicate(LDTNItems::createPredicate)
                    .addItemsAfter(ofID(Consts.CREATE_ZINC_BLOCK, Consts.CREATE.id()), ZINC_BRICKS, ZINC_BRICK_STAIRS, ZINC_BRICK_SLAB, ZINC_BRICK_WALL, CHISELED_ZINC_BRICKS)
                .predicate(LDTNItems::spawnPredicate)
                    .addItemsAfter(ofID(Consts.SPAWN_DATES, Consts.SPAWN.id()), DATE_CRATE);
    }

    public static boolean createPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTabKey().location().equals(Consts.CREATE_BUILDING_TAB);
    }

    public static Predicate<ItemStack> ofMod(ItemLike itemLike, Consts.ModId id) {
        return (item) -> of(itemLike).test(item) && id.loaded();
    }

    public static boolean spawnPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTabKey().location().equals(Consts.SPAWN_ITEM_TAB);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(RegUtil.item(location)).test(stack));
    }
}
