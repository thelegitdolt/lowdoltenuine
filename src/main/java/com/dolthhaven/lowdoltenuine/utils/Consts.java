package com.dolthhaven.lowdoltenuine.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class Consts {
    public static final ModId CAVERNS_AND_CHASMS = new ModId("caverns_and_chasms");
    public static final ModId CREATE = new ModId("create");
    public static final ModId QUARK = new ModId("quark");
    public static final ModId SPAWN = new ModId("spawn");



    public static final ResourceLocation STURDY_STONE = QUARK.rl("sturdy_stone");
    public static final ResourceLocation SPAWN_DATES = SPAWN.rl("dates");

    public static final ResourceLocation ZINC_INGOT = CREATE.rl("zinc_ingot");
    public static final ResourceLocation CREATE_ZINC_BLOCK = CREATE.rl("zinc_block");
    public static final ResourceLocation CREATE_BUILDING_TAB = CREATE.rl("palettes");

    public static final ResourceLocation SPAWN_ITEM_TAB = SPAWN.rl("item_group");

    public record ModId(String id) {
        public boolean loaded() {
            return ModList.get().isLoaded(id);
        }

        public ResourceLocation rl(String path) {
            return ResourceLocation.fromNamespaceAndPath(id, path);
        }

        public MutableComponent translatable(String path) {
            return Component.translatable(path.formatted(id));
        }

        public ModLoadedCondition requiresLoaded() {
            return new ModLoadedCondition(id);
        }
    }
}
