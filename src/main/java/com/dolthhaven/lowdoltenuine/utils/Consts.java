package com.dolthhaven.lowdoltenuine.utils;

import com.dolthhaven.lowdoltenuine.core.data.DummyItemsRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
    public static final ModId SPECIES = new ModId("species");
    public static final ModId UPGRADE_AQUATIC = new ModId("upgrade_aquatic");
    public static final ModId WINDSWEPT = new ModId("windswept");
    public static final ModId WOODWORKS = new ModId("woodworks");



    public static final ModResource HEART_CRYSTAL_ITEM = HEART_CRYSTALS.resource("heart_crystal");
    public static final ModResource BANANA_PEEL = NEAPOLITAN.resource("banana_peel");
    public static final ModResource BB_ASHEN_SAND = BLASTED_BARRENS.registrableResource("ashen_sand");

    public static final ModResource BANANARROW = NEAPOLITAN.registrableResource("bananarrow");
    public static final ModResource CHIMPANZEE = NEAPOLITAN.resource("chimpanzee");

    public static final ModResource SUSPICIOUS_STICK = SPAWN.registrableResource("suspicious_stick");
    public static final ModResource STICKBUG = SPAWN.resource("stickbug");
    public static final ModResource KINETIC_CORE = SPECIES.registrableResource("kinetic_core");
    public static final ModResource QUAKE = SPECIES.resource("quake");

    public static final ModResource MONSTER_YAM = DUNGEONS_DELIGHT.resource("monster_yam");
    public static final ModResource STURDY_STONE = QUARK.resource("sturdy_stone");

    public static final ModResource ECTOPLASM = JNE.resource("ectoplasm");
    public static final ModResource ECTOPLASM_BUCKET = JNE.resource("ectoplasm_bucket");
    public static final ModResource BONE_PIKE = JNE.resource("bone_pike");
    public static final ModResource MOLTEN_LEAD = OREGANIZED.resource("molten_lead");
    public static final ModResource MOLTEN_LEAD_BUCKET = OREGANIZED.resource("molten_lead_bucket");
    public static final ModResource GOLDEN_LAVA_BUCKET = CAVERNS_AND_CHASMS.resource("golden_lava_bucket");

    public static final ModResource SPAWN_DATES = SPAWN.registrableResource("dates");

    public static final ModResource MOWZIES_MOBS_TAB = MOWZIES_MOBS.resource("mowziesmobs_tab");
    public static final ModResource FOLIAATH = MOWZIES_MOBS.resource("foliaath");

    public static final ModResource CHILLED = WINDSWEPT.resource("chilled");
    public static final ModResource ZINC_INGOT = CREATE.registrableResource("zinc_ingot");
    public static final ModResource CREATE_ZINC_BLOCK = CREATE.resource("zinc_block");
    public static final ModResource CREATE_BUILDING_TAB = CREATE.resource("palettes");

    public static final ModResource RAKED_SAND = MOWZIES_MOBS.resource("raked_sand");
    public static final ModResource RED_RAKED_SAND = MOWZIES_MOBS.resource("red_raked_sand");

    public static final ModResource SPAWN_ITEM_TAB = SPAWN.resource("item_group");
    public static final ModResource ARID_SAND = ATMOSPHERIC.registrableResource("arid_sand");
    public static final ModResource RED_ARID_SAND = ATMOSPHERIC.registrableResource("red_arid_sand");
    public static final ModResource ARID_SPROUTS = ATMOSPHERIC.registrableResource("arid_sprouts");
    public static final ModResource BEACHGRASS = UPGRADE_AQUATIC.registrableResource("beachgrass");
    public static final ModResource TALL_BEACHGRASS = UPGRADE_AQUATIC.registrableResource("tall_beachgrass");
    public static final ModResource MYCELIUM_SPROUTS = ENVIRONMENTAL.registrableResource("mycelium_sprouts");

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

        public ModResource resource(String path) {
            return new ModResource(rl(path));
        }

        public ModResource registrableResource(String path) {
            ModResource resource = resource(path);
            DummyItemsRegistry.DUMMY_ITEMS.add(resource);
            return resource;
        }
    }

    public record ModResource(ResourceLocation loc) {
        public boolean exists() {
            return ModList.get().isLoaded(loc.getNamespace());
        }

        public Item item() {
            return RegUtil.item(loc);
        }

        public Block block() {
            return RegUtil.block(loc);
        }

        public Holder<Item> itemHolder() {
            return RegUtil.itemHolder(loc);
        }

        public Holder<Block> blockHolder() {
            return RegUtil.blockHolder(loc);
        }

        public <V> V as(Registry<V> register) {
            return register.get(loc);
        }
    }
}
