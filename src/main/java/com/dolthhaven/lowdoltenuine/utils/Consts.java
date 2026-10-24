package com.dolthhaven.lowdoltenuine.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class Consts {
    public static final ModId ATMOSPHERIC = new ModId("atmospheric");
    public static final ModId BLASTED_BARRENS = new ModId("blasted_barrens");
    public static final ModId CAVERNS_AND_CHASMS = new ModId("caverns_and_chasms");
    public static final ModId CREATE = new ModId("create");
    public static final ModId DUNGEONS_DELIGHT = new ModId("dungeonsdelight");
    public static final ModId DYE_DEPOT = new ModId("dye_depot");
    public static final ModId ENVIRONMENTAL = new ModId("environmental");
    public static final ModId HEART_CRYSTALS = new ModId("heart_crystals");
    public static final ModId JNE = new ModId("netherexp");
    public static final ModId MINECRAFT = new ModId("minecraft");
    public static final ModId MOWZIES_MOBS = new ModId("mowziesmobs");
    public static final ModId OREGANIZED = new ModId("oreganized");
    public static final ModId QUARK = new ModId("quark");
    public static final ModId NEAPOLITAN = new ModId("neapolitan");
    public static final ModId SPAWN = new ModId("spawn");
    public static final ModId UPGRADE_AQUATIC = new ModId("upgrade_aquatic");
    public static final ModId WOODWORKS = new ModId("woodworks");



    public static final ResourceLocation HEART_CRYSTAL_ITEM = HEART_CRYSTALS.rl("heart_crystal");
    public static final ResourceLocation BANANA_PEEL = NEAPOLITAN.rl("banana_peel");
    public static final ResourceLocation BB_ASHEN_SAND = BLASTED_BARRENS.rl("ashen_sand");

    public static final ResourceLocation MONSTER_YAM = DUNGEONS_DELIGHT.rl("monster_yam");
    public static final ResourceLocation STURDY_STONE = QUARK.rl("sturdy_stone");

    public static final ResourceLocation ECTOPLASM = JNE.rl("ectoplasm");
    public static final ResourceLocation ECTOPLASM_BUCKET = JNE.rl("ectoplasm_bucket");
    public static final ResourceLocation BONE_PIKE = JNE.rl("bone_pike");
    public static final ResourceLocation MOLTEN_LEAD = OREGANIZED.rl("molten_lead");
    public static final ResourceLocation MOLTEN_LEAD_BUCKET = OREGANIZED.rl("molten_lead_bucket");
    public static final ResourceLocation GOLDEN_LAVA_BUCKET = CAVERNS_AND_CHASMS.rl("golden_lava_bucket");

    public static final ResourceLocation SPAWN_DATES = SPAWN.rl("dates");

    public static final ResourceLocation MOWZIES_MOBS_TAB = MOWZIES_MOBS.rl("mowziesmobs_tab");


    public static final ResourceLocation ZINC_INGOT = CREATE.rl("zinc_ingot");
    public static final ResourceLocation CREATE_ZINC_BLOCK = CREATE.rl("zinc_block");
    public static final ResourceLocation CREATE_BUILDING_TAB = CREATE.rl("palettes");

    public static final ResourceLocation RAKED_SAND = MOWZIES_MOBS.rl("raked_sand");
    public static final ResourceLocation RED_RAKED_SAND = MOWZIES_MOBS.rl("red_raked_sand");

    public static final ResourceLocation SPAWN_ITEM_TAB = SPAWN.rl("item_group");
    public static final ResourceLocation ARID_SAND = ATMOSPHERIC.rl("arid_sand");
    public static final ResourceLocation RED_ARID_SAND = ATMOSPHERIC.rl("red_arid_sand");
    public static final ResourceLocation ARID_SPROUTS = ATMOSPHERIC.rl("arid_sprouts");
    public static final ResourceLocation BEACHGRASS = UPGRADE_AQUATIC.rl("beachgrass");
    public static final ResourceLocation TALL_BEACHGRASS = UPGRADE_AQUATIC.rl("tall_beachgrass");
    public static final ResourceLocation MYCELIUM_SPROUTS = ENVIRONMENTAL.rl("mycelium_sprouts");

    public record ModId(String id) {
        public boolean loaded() {
            return ModList.get().isLoaded(id);
        }

        public void ifLoaded(Runnable runnable) {
            if (loaded()) runnable.run();
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
