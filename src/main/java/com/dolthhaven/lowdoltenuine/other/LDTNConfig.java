package com.dolthhaven.lowdoltenuine.other;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class LDTNConfig {
    public static class Common {
        public final ModConfigSpec.ConfigValue<Integer> modpackStackSize;

        // JNE
        public final ModConfigSpec.ConfigValue<Boolean> shouldPlaceBonePilesWithNormalBones;

        // mowzies
        public final ModConfigSpec.ConfigValue<Boolean> hoesRakeSand;

        //Woodworks
        public final ModConfigSpec.ConfigValue<Boolean> replaceSawmillWithStonecutter;


        // heart crystals
        public final ModConfigSpec.ConfigValue<Boolean> heartCrystalsAreDifferent;
        public final ModConfigSpec.ConfigValue<Boolean> heartLanternsDontGiveRegen;
        public final ModConfigSpec.ConfigValue<Boolean> heartGeodeBlock;

        // opposing forces
        public final ModConfigSpec.ConfigValue<Boolean> opposeForceSawBladeChopTree;


//        public final ModConfigSpec.ConfigValue<Boolean> doltChargedCreeperTweaks;
//        public final ModConfigSpec.ConfigValue<Boolean> sporeRocketSummonSporeCloud;
//        public final ModConfigSpec.ConfigValue<Integer> sporeCloudChance;
//        public final ModConfigSpec.ConfigValue<Boolean> shouldCombustionGriefBlocks;
//        public final ModConfigSpec.ConfigValue<Boolean> frogMistLikeHopeLights;



        Common(ModConfigSpec.Builder builder) {
            modpackStackSize = builder.comment("What items stack to by default in your modpack. Should be 64 unless you've changed it with a mod like Bigger Stacks.")
                    .comment("For crankbows and packing container tweaks.")
                    .define("Default Stack Size", 64);


            builder.push("Mowzies Mobs");
            builder.push("Rakes");
            hoesRakeSand = builder.comment("If hoes should also be able to rake sand. Disabled by default option for my modpack").define("Hoes Rake Sand", false);
            builder.pop();
            builder.pop();

//            builder.push("From the Ground Up / ftgu");
//            builder.push("Creepers");
//            doltChargedCreeperTweaks = builder.comment("Removes the Doomed effect, creates preparations to allow charged creepers to naturally spawn during thunderstorms. Spawns must still be manually added by the modpack dev.")
//                    .define("Based Charged Creepers", false);
//            sporeRocketSummonSporeCloud = builder.comment("If spore rockets explosions should create a Spore Cloud from Savage and Ravage").define("Spore Bomb Two", true);
//            sporeCloudChance = builder.comment("The chance, out of 100, that a spore rocket will summon a spore cloud").defineInRange("Spore rocket possibilities", 100, 0, 100);
//            builder.pop();
//            builder.pop();

            builder.push("jaden nether expansion");
            builder.push("Bone Rods");
            shouldPlaceBonePilesWithNormalBones = builder
                    .comment("If bone rods should be placed by right clicking vanilla bones, instead of having a new bone rod item")
                    .define("Bone ahh bone", true);
            builder.pop();
            builder.pop();

            builder.push("OpposingForces");
            builder.push("Sawblades");
            opposeForceSawBladeChopTree = builder.comment("If the terror sawblades from the opposing forces modification should be banned from doing the tree chop thing, which is VANILLA MINUS and should not be encouraged.")
                    .define("Vanilla Plus Modpack", false);
            builder.pop();
            builder.pop();

            builder.push("Woodworks");
            builder.push("Stonecutter");
            replaceSawmillWithStonecutter = builder.comment("If Woodworks is loaded, whether all sawmill " +
                    "recipes should becomes stonecutter recipes instead.").define("Stonecutter more like woodcutter am I right", false);
            builder.pop();
            builder.pop();


            builder.push("heart_crystals");
            builder.push("Heart Crystals");
            heartCrystalsAreDifferent = builder.comment("If heart crystals from the heart crystals mod should give the Health Boost effect and regen for awhile instead of giving you permanent hearts")
                    .define("Impermanent Hearts", false);
            heartLanternsDontGiveRegen = builder.comment("If heart lanterns should no longer be a source of the regeneration effect.")
                    .define("Heart Lanterns Don't Give Regeneration", false);
            heartGeodeBlock = builder.comment("If Heart Crystals should drop as a block when silk touched, like other ores.")
                    .define("Heart Geode", false);
            builder.pop();
            builder.pop();

//            builder.push("John Species");
//
//            builder.push("hopelight");
//            frogMistLikeHopeLights = builder.comment("If species hopelights should act like JNE frogmists (only interactable when holding another hopelight or a pickaxe)").define("Frogmist Hopelights", true);
//            builder.pop();
//
//            builder.push("Combustion Effect");
//            shouldCombustionGriefBlocks = builder.comment("If explosions caused by mobs that die with the Species Combustion effect should not any blocks")
//                    .define("Less combusty combustion", false);
//            builder.pop();
//
//            builder.pop();
        }
    }

    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(LDTNConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
