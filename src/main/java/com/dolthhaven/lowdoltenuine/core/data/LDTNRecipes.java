package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class LDTNRecipes extends BlueprintRecipeProvider {

    public LDTNRecipes(GatherDataEvent event) {
        super(Lowdoltenuine.MOD_ID, event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider holderLookup) {

    }
    protected static void twoByTwoCustomLoc(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, ItemLike unpacked, String savePath) {
        ShapedRecipeBuilder.shaped(category, packed, 1).define('#', unpacked).pattern("##").pattern("##").unlockedBy(getHasName(unpacked), has(unpacked)).save(recipeOutput, Lowdoltenuine.rl(savePath));
    }
}
