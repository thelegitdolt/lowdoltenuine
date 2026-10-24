package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.integration.CavernsChasmsIntegration;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.caverns_and_chasms.common.item.GoldenBucketItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class LDTNItems {
    public static final ItemSubRegistryHelper ITEMS = Lowdoltenuine.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<BlockItem> HEART_GEODE = ITEMS.createItem("heart_geode", () -> new ItemNameBlockItem(RegUtil.blockOrSlop(Consts.HEART_CRYSTAL_ITEM), new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> GOLDEN_MOLTEN_LEAD_BUCKET = goldenBucket("molten_lead", Consts.MOLTEN_LEAD);
    public static final DeferredItem<Item> GOLDEN_ECTOPLASM_BUCKET = goldenBucket("ectoplasm", Consts.ECTOPLASM);

    private static DeferredItem<Item> goldenBucket(String name, ResourceLocation fluidLoc) {
        boolean prereqs = Consts.CAVERNS_AND_CHASMS.loaded() && ModList.get().isLoaded(fluidLoc.getNamespace());
        Supplier<Item> item = prereqs ? CavernsChasmsIntegration.item(fluidLoc) : () -> new Item(new Item.Properties().stacksTo(1));
        return ITEMS.createItem("golden_" + name + "_bucket", item);
    }

    public static void setUpTabEditors() {
        var creativeTabs = CreativeModeTabContentsPopulator.mod(Lowdoltenuine.MOD_ID)
                .tab(CreativeModeTabs.NATURAL_BLOCKS)
                    .addItemsAfter(ofMod(Items.AMETHYST_CLUSTER, Consts.HEART_CRYSTALS), HEART_GEODE)
                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                    .addItemsAfter(ofID(Consts.STURDY_STONE), STURDY_DEEPSLATE)
                    .addItemsAfter(of(Blocks.EMERALD_BLOCK), HEART_CRYSTAL_LAMP)
                .tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                    .addItemsAfter(of(Blocks.SEA_LANTERN), HEART_CRYSTAL_LAMP)
                .tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                    .addItemsAfter(ofID(Consts.GOLDEN_LAVA_BUCKET, Consts.OREGANIZED.id()), GOLDEN_MOLTEN_LEAD_BUCKET)
                    .addItemsAfter(ofID(Consts.GOLDEN_LAVA_BUCKET, Consts.JNE.id()), GOLDEN_ECTOPLASM_BUCKET)

                .predicate(LDTNItems::createPredicate)
                    .addItemsAfter(ofID(Consts.CREATE_ZINC_BLOCK, Consts.CREATE.id()), ZINC_BRICKS, ZINC_BRICK_STAIRS, ZINC_BRICK_SLAB, ZINC_BRICK_WALL, CHISELED_ZINC_BRICKS)
                .predicate(LDTNItems::spawnPredicate)
                    .addItemsAfter(ofID(Consts.SPAWN_DATES, Consts.SPAWN.id()), DATE_CRATE)
                .predicate(LDTNItems::mowziesPredicate)
                        .addItemsAfter(ofID(Consts.RED_RAKED_SAND, Consts.ATMOSPHERIC.id()),
                                ARID_RAKED_SAND, RED_ARID_RAKED_SAND);

        creativeTabs.tab(CreativeModeTabs.REDSTONE_BLOCKS)
                .addItems(WHITE_ENCASED_PIPE, BROWN_ENCASED_PIPE, GRAY_ENCASED_PIPE, LIGHT_GRAY_ENCASED_PIPE, RED_ENCASED_PIPE, ORANGE_ENCASED_PIPE,
                        YELLOW_ENCASED_PIPE, LIME_ENCASED_PIPE, GREEN_ENCASED_PIPE, BLUE_ENCASED_PIPE, LIGHT_BLUE_ENCASED_PIPE, CYAN_ENCASED_PIPE, PURPLE_ENCASED_PIPE,
                        MAGENTA_ENCASED_PIPE, PINK_ENCASED_PIPE, BLACK_ENCASED_PIPE);

        if (Consts.DYE_DEPOT.loaded()) {
            creativeTabs.addItems(ROSE_ENCASED_PIPE, MAROON_ENCASED_PIPE, GINGER_ENCASED_PIPE, TAN_ENCASED_PIPE,
                    BEIGE_ENCASED_PIPE, CORAL_ENCASED_PIPE, OLIVE_ENCASED_PIPE, FOREST_ENCASED_PIPE, VERDANT_ENCASED_PIPE, AMBER_ENCASED_PIPE,
                    TEAL_ENCASED_PIPE, MINT_ENCASED_PIPE, AQUA_ENCASED_PIPE, SLATE_ENCASED_PIPE, NAVY_ENCASED_PIPE, INDIGO_ENCASED_PIPE);
        }
    }

    public static boolean createPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTabKey().location().equals(Consts.CREATE_BUILDING_TAB);
    }

    public static boolean mowziesPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTabKey().location().equals(Consts.MOWZIES_MOBS_TAB);
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
