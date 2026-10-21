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

        public ItemResource itemResource(String path) {
            return new ItemResource(rl(path));
        }

        public BlockResource blockResource(String path) {
            return new BlockResource(rl(path));
        }
    }

    public record ItemResource(ResourceLocation loc) {
        public Item lookup() {
            return RegUtil.item(loc);
        }

        public Optional<Item> safeLookup() {
            return Optional.ofNullable(lookup());
        }

        public Block getBlock() {
            return RegUtil.block(loc);
        }

        public Optional<Block> safeGetBlock() {
            return Optional.ofNullable(getBlock());
        }

        public void registerDummy(RegisterEvent event) {
            event.register(Registries.ITEM, loc, () -> new Item(new Item.Properties()));
        }
    }

    public record BlockResource(ResourceLocation loc) {
        public Block lookup() {
            return RegUtil.block(loc);
        }

        public Optional<Block> safeLookup() {
            return Optional.ofNullable(lookup());
        }

        public Item getItem() {
            return RegUtil.item(loc);
        }

        public Optional<Item> safeGetItem() {
            return Optional.ofNullable(getItem());
        }
    }
}
