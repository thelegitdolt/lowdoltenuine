package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.RewindDistanceTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.WatchMobDieTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.EntityIDCondition;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.HealthLootCondition;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.caverns_and_chasms.core.registry.CCItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LDTNAdvancements implements AdvancementProvider.AdvancementGenerator {
    public static AdvancementProvider create(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookup) {
        return new AdvancementProvider(event.getGenerator().getPackOutput(), lookup, event.getExistingFileHelper(), List.of(new LDTNAdvancements()));
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
        Item heartCrystal = RegUtil.item(Consts.HEART_CRYSTAL_ITEM);
        goalAdvancement("use_heart_crystal_at_half_heart", "adventure", Consts.MINECRAFT.rl("adventure/root"), () -> heartCrystal)
                .addCriterion("use_the_heart", CriteriaTriggers.USING_ITEM.createCriterion(
                        new UsingItemTrigger.TriggerInstance(Optional.of(ContextAwarePredicate.create(
                                new HealthLootCondition(MinMaxBounds.Doubles.atMost(2)))),
                                Optional.of(ItemPredicate.Builder.item().of(heartCrystal).build()))
                )).save(consumer, adventure("use_heart_crystal_at_half_heart"));
        challenge("rewind_great_distances", "nether", Consts.MINECRAFT.rl("nether/brew_potion"), CCItems.BEJEWELED_PEARL, 100)
                .addCriterion("apply_quotient_topology", RewindDistanceTrigger.TriggerInstance.withDistance(1500))
                .save(consumer, nether("rewind_great_distances"));

        goalAdvancement("what_a_hullabaloo", "husbandry", Consts.NEAPOLITAN.rl("husbandry/chimpanzee_attack"), () -> RegUtil.item(Consts.BANANARROW))
                .addCriterion("pvz", WatchMobDieTrigger.TriggerInstance.withType(Consts.CHIMPANZEE, EntityType.RAVAGER))
                .save(consumer, husbandry("what_a_hullabaloo"));
        goalAdvancement("pvz", "mowzies", Consts.MOWZIES_MOBS.rl("kill_foliaath"), () -> Items.FLOWER_POT)
                .addCriterion("pvz", WatchMobDieTrigger.TriggerInstance.withType(LDTNTags.Entity.HUMANOID_ZOMBIES, Consts.FOLIAATH, EntityPredicate.Builder.entity().located(LocationPredicate.Builder.atYLocation(MinMaxBounds.Doubles.atLeast(300))).build()))
                .save(consumer, other("/pvz"));

        goalAdvancement("kill_stickbug_with_lightning", "adventure", Consts.MINECRAFT.rl("adventure/throw_trident"), () -> RegUtil.item(Consts.SUSPICIOUS_STICK))
                .addCriterion("kill_bug", CriteriaTriggers.CHANNELED_LIGHTNING.createCriterion(new ChanneledLightningTrigger.TriggerInstance(Optional.empty(), List.of(ContextAwarePredicate.create(new EntityIDCondition(Consts.STICKBUG))))))
                .save(consumer, adventure("/kill_stickbug_with_lightning"));
    }

    private static String adventure(String name) {
        return Lowdoltenuine.rl("adventure/" + name).toString();
    }

    private static String other(String name) {
        return Lowdoltenuine.rl("other/" + name).toString();
    }

    private static String husbandry(String name) {
        return Lowdoltenuine.rl("husbandry/" + name).toString();
    }

    private static String nether(String name) {
        return Lowdoltenuine.rl("nether/" + name).toString();
    }

    private static Advancement.Builder goalAdvancement(String name, String category, ResourceLocation parent, Supplier<Item> icon) {
        return createAdvancement(name, category, parent, icon, AdvancementType.TASK, true, true, false, 0);
    }

    private static Advancement.Builder challenge(String name, String category, ResourceLocation parent, Supplier<Item> icon, int xp) {
        return createAdvancement(name, category, parent, icon, AdvancementType.CHALLENGE, true, true, false, xp);
    }

    private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, Supplier<Item> icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden, int xpLevel) {
        AdvancementRewards rewards = xpLevel != 0 ? AdvancementRewards.Builder.experience(xpLevel).build() : AdvancementRewards.EMPTY;
        return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon.get(),
                Lowdoltenuine.WRAPPED_ID.translatable("advancements.%s." + category + "." + name + ".title"),
                Lowdoltenuine.WRAPPED_ID.translatable("advancements.%s." + category + "." + name + ".description"),
                null, frame, showToast, announceToChat, hidden).rewards(rewards);
    }

    private static EntityPredicate.Builder reqEffectAndAmplifier(Holder<MobEffect> effect, MinMaxBounds.Ints bound) {
        return EntityPredicate.Builder.entity().effects(new MobEffectsPredicate.Builder().and(effect, new MobEffectsPredicate.MobEffectInstancePredicate(bound, MinMaxBounds.Ints.ANY, Optional.empty(), Optional.empty())));
    }
}
