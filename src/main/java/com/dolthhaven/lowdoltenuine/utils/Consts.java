package com.dolthhaven.lowdoltenuine.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Optional;

public class Consts {
    public static final ModId CAVERNS_AND_CHASMS = new ModId("caverns_and_chasms");
    public static final ModId CREATE = new ModId("create");
    public static final ModId QUARK = new ModId("quark");

    public static final ResourceLocation STURDY_STONE = QUARK.rl("sturdy_stone");

    public static final ResourceLocation RAW_ZINC = CREATE.rl("raw_zinc");
    public static final ResourceLocation CREATE_ZINC_BLOCK = CREATE.rl("zinc_block");
    public static final ResourceLocation CREATE_BUILDING_TAB = CREATE.rl("palettes");

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
