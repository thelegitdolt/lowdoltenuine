package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlockFamilies;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Arrays;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.STURDY_DEEPSLATE;
import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.ZINC_BRICKS;

public class LDTNRecipes extends BlueprintRecipeProvider {

    public LDTNRecipes(GatherDataEvent event) {
        super(Lowdoltenuine.MOD_ID, event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider holderLookup) {
        storageRecipes(output.withConditions(Consts.QUARK.requiresLoaded()), RecipeCategory.BUILDING_BLOCKS, Items.DEEPSLATE, RecipeCategory.BUILDING_BLOCKS, STURDY_DEEPSLATE);


        AndCondition createCCLoaded = and(Consts.CAVERNS_AND_CHASMS.requiresLoaded(), Consts.CREATE.requiresLoaded());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ZINC_BRICKS, 4)
                .define('z', RegUtil.item(Consts.RAW_ZINC)).define('d', Items.DEEPSLATE)
                .pattern("zd").pattern("dz").unlockedBy("has_zinc", has(RegUtil.item(Consts.RAW_ZINC)))
                .save(output.withConditions(createCCLoaded));

        generateRecipes(output.withConditions(createCCLoaded), LDTNBlockFamilies.ZINC_BRICKS_FAMILY);
        stoneCutterForBlockFamily(output.withConditions(createCCLoaded), LDTNBlockFamilies.ZINC_BRICKS_FAMILY);
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
}
