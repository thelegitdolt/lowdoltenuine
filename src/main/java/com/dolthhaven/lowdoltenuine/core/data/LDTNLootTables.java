package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.common.block.BlockWithOneString;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlockFamilies;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.google.common.collect.ImmutableList;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.loot.CanItemPerformAbility;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;
import static com.dolthhaven.lowdoltenuine.core.registry.LDTNItems.HEART_GEODE;
import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.LootTable.lootTable;
import static net.minecraft.world.level.storage.loot.entries.AlternativesEntry.alternatives;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;


public class LDTNLootTables extends LootTableProvider{
    private static final Set<Block> MODIFY_WHITELIST = Set.of(HCBlocks.HEART_CRYSTAL.get());

    protected static final LootItemCondition.Builder IS_SHEARS = CanItemPerformAbility.canItemPerformAbility(ItemAbilities.SHEARS_HARVEST);

    static List<Block> BLOCK_BLACKLIST = ImmutableList.of();

    public LDTNLootTables(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
        ), event.getLookupProvider());
    }

    @Override
    protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
    }

    public static class BlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());


        protected BlockLoot(HolderLookup.Provider registries) {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate() {
            this.dropSelf(DATE_CRATE.get());
            this.dropSelf(HEART_CRYSTAL_LAMP.get());
            this.add(HCBlocks.HEART_CRYSTAL.get(), lootTable().withPool(
                    lootPool().add(alternatives(lootTableItem(HEART_GEODE.get())
                            .when(hasSilkTouch())
                            .otherwise(lootTableItem(HCBlocks.HEART_CRYSTAL.asItem()))))));

            this.dropSelf(STURDY_DEEPSLATE.get());
            this.forStoneFamily(LDTNBlockFamilies.ZINC_BRICKS_FAMILY);

            this.dropReallyWeirdPottedContents(POTTED_TALL_BEACHGRASS);
            this.dropReallyWeirdPottedContents(POTTED_BEACHGRASS);
            this.dropReallyWeirdPottedContents(POTTED_ARID_SPROUTS);
            this.dropReallyWeirdPottedContents(POTTED_MYCELIUM_SPROUTS);

            this.pipes();
        }

        private void dropReallyWeirdPottedContents(DeferredBlock<Block> block) {
            Lowdoltenuine.LOGGER.info("I'm REALLY sorry tslat");
            if (block.get() instanceof BlockWithOneString stringBlock) {
                Item item = RegUtil.item(ResourceLocation.parse(stringBlock.getTheString()));
                this.add(block.get(), lootTable().withPool(this.applyExplosionCondition(Blocks.FLOWER_POT, lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Blocks.FLOWER_POT)))).withPool(this.applyExplosionCondition(item, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item)))));
            } else if (block.get() instanceof FlowerPotBlock) {
                dropPottedContents(block.get());
            } else throw new IllegalArgumentException("this is too weird even for this method");
        }

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> {
                return Lowdoltenuine.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()) || MODIFY_WHITELIST.contains(block);
            }).filter(block -> !BLOCK_BLACKLIST.contains(block)).collect(Collectors.toSet());
        }

        private static <V extends Comparable<V>> LootItemCondition.Builder stateCond(Supplier<? extends Block> block, Property<V> property, V v) {
            return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, v.toString()));
        }

        protected void forStoneFamily(BlockFamily family) {
            Block base = family.getBaseBlock();
            Block slab = family.get(BlockFamily.Variant.SLAB);
            Block stairs = family.get(BlockFamily.Variant.STAIRS);
            Block chiseled = family.get(BlockFamily.Variant.CHISELED);
            Block wall = family.get(BlockFamily.Variant.WALL);

            this.dropSelf(base);
            this.dropSelf(wall);
            this.dropSelf(chiseled);
            this.dropSelf(stairs);
            this.add(slab, this::createSlabItemTable);
        }

        private void pipes() {
            this.dropSelf(WHITE_ENCASED_PIPE.get());
            this.dropSelf(BROWN_ENCASED_PIPE.get());
            this.dropSelf(GRAY_ENCASED_PIPE.get());
            this.dropSelf(LIGHT_GRAY_ENCASED_PIPE.get());
            this.dropSelf(RED_ENCASED_PIPE.get());
            this.dropSelf(ORANGE_ENCASED_PIPE.get());
            this.dropSelf(YELLOW_ENCASED_PIPE.get());
            this.dropSelf(LIME_ENCASED_PIPE.get());
            this.dropSelf(GREEN_ENCASED_PIPE.get());
            this.dropSelf(BLUE_ENCASED_PIPE.get());
            this.dropSelf(LIGHT_BLUE_ENCASED_PIPE.get());
            this.dropSelf(CYAN_ENCASED_PIPE.get());
            this.dropSelf(PURPLE_ENCASED_PIPE.get());
            this.dropSelf(MAGENTA_ENCASED_PIPE.get());
            this.dropSelf(PINK_ENCASED_PIPE.get());
            this.dropSelf(BLACK_ENCASED_PIPE.get());
            this.dropSelf(ROSE_ENCASED_PIPE.get());
            this.dropSelf(MAROON_ENCASED_PIPE.get());
            this.dropSelf(GINGER_ENCASED_PIPE.get());
            this.dropSelf(TAN_ENCASED_PIPE.get());
            this.dropSelf(BEIGE_ENCASED_PIPE.get());
            this.dropSelf(CORAL_ENCASED_PIPE.get());
            this.dropSelf(OLIVE_ENCASED_PIPE.get());
            this.dropSelf(FOREST_ENCASED_PIPE.get());
            this.dropSelf(VERDANT_ENCASED_PIPE.get());
            this.dropSelf(AMBER_ENCASED_PIPE.get());
            this.dropSelf(TEAL_ENCASED_PIPE.get());
            this.dropSelf(MINT_ENCASED_PIPE.get());
            this.dropSelf(AQUA_ENCASED_PIPE.get());
            this.dropSelf(SLATE_ENCASED_PIPE.get());
            this.dropSelf(NAVY_ENCASED_PIPE.get());
            this.dropSelf(INDIGO_ENCASED_PIPE.get());
        }
    }
}
