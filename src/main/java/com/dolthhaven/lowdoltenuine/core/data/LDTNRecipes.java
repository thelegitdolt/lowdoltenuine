package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlockFamilies;
import com.dolthhaven.lowdoltenuine.integration.DyeDepotIntegration;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.violetmoon.quark.addons.oddities.module.PipesModule;

import java.util.Arrays;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNRecipes extends BlueprintRecipeProvider {

    public LDTNRecipes(GatherDataEvent event) {
        super(Lowdoltenuine.MOD_ID, event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider holderLookup) {
        storageRecipes(output.withConditions(Consts.QUARK.requiresLoaded()), RecipeCategory.BUILDING_BLOCKS, Items.DEEPSLATE, RecipeCategory.BUILDING_BLOCKS, STURDY_DEEPSLATE);
        storageRecipes(output.withConditions(Consts.SPAWN.requiresLoaded()), RecipeCategory.FOOD, Consts.SPAWN_DATES.item(), RecipeCategory.DECORATIONS, DATE_CRATE);

        storageRecipes(output.withConditions(Consts.QUARK.requiresLoaded()), RecipeCategory.MISC, Items.BAMBOO, RecipeCategory.BUILDING_BLOCKS, BAMBOO_BUNDLE);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_BLOCK).requires(Items.BAMBOO, 8)
                .unlockedBy("has_bamboo", has(Items.BAMBOO)).save(output, ResourceLocation.withDefaultNamespace("bamboo_block"));


        AndCondition createCCLoaded = and(Consts.CAVERNS_AND_CHASMS.requiresLoaded(), Consts.CREATE.requiresLoaded());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZINC_BRICKS, 4)
                .define('z', Consts.ZINC_INGOT.item()).define('d', Items.DEEPSLATE)
                .pattern("zd").pattern("dz").unlockedBy("has_zinc", has(Consts.ZINC_INGOT.item()))
                .save(output.withConditions(createCCLoaded));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HEART_CRYSTAL_LAMP.get())
                .define('h', HCBlocks.HEART_CRYSTAL_SHARD.get())
                .define('g', Blocks.GLOWSTONE)
                .pattern(" h ").pattern("hgh").pattern(" h ")
                .unlockedBy("has_heart_crystal", has(HCBlocks.HEART_CRYSTAL_SHARD.get())).save(output.withConditions(Consts.HEART_CRYSTALS.requiresLoaded()));

        generateRecipes(output.withConditions(createCCLoaded), LDTNBlockFamilies.ZINC_BRICKS_FAMILY);
        stoneCutterForBlockFamily(output.withConditions(createCCLoaded), LDTNBlockFamilies.ZINC_BRICKS_FAMILY);
        pipes(output);
    }

    protected static void twoByTwoCustomLoc(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, ItemLike unpacked, String savePath) {
        ShapedRecipeBuilder.shaped(category, packed, 1).define('#', unpacked).pattern("##").pattern("##").unlockedBy(getHasName(unpacked), has(unpacked)).save(recipeOutput, Lowdoltenuine.rl(savePath));
    }

    protected AndCondition and(ICondition... conditions) {
        return new AndCondition(Arrays.asList(conditions));
    }

    protected void stoneCutterForBlockFamily(RecipeOutput output, BlockFamily family) {
        Block base = family.getBaseBlock();
        Block slab = family.get(BlockFamily.Variant.SLAB);
        Block stairs = family.get(BlockFamily.Variant.STAIRS);
        Block chiseled = family.get(BlockFamily.Variant.CHISELED);
        Block wall = family.get(BlockFamily.Variant.WALL);

        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, slab, base, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, stairs, base, 1);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, chiseled, base, 1);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, wall, base, 1);
    }

    private void pipes(RecipeOutput output) {
        for (ResourceLocation dye : DyeDepotIntegration.DYES.get()) {
            Block pipe = RegUtil.block(Lowdoltenuine.rl(dye.getPath() + "_encased_pipe"));
            Block glass = RegUtil.block(dye.withSuffix("_stained_glass"));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, pipe)
                    .requires(PipesModule.pipe).requires(glass)
                    .unlockedBy("has_pipe", has(PipesModule.pipe))
                    .save(output);
        }
    }
}
