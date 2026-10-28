package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNTags;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.RewindDistanceTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.SpeedometerTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.ThunderdomeTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.WatchMobDieTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.EntityIDCondition;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.HealthLootCondition;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.IsChargedCreeperCondition;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.caverns_and_chasms.core.registry.CCEntityTypes;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import javax.naming.Context;
import javax.swing.text.html.Option;
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
        Item heartCrystal = RegUtil.item(Consts.HEART_CRYSTAL_ITEM.loc());
        goalAdvancement("use_heart_crystal_at_half_heart", "adventure", Consts.MINECRAFT.rl("adventure/root"), () -> heartCrystal)
                .addCriterion("use_the_heart", CriteriaTriggers.USING_ITEM.createCriterion(
                        new UsingItemTrigger.TriggerInstance(pred(
                                new HealthLootCondition(MinMaxBounds.Doubles.atMost(2))),
                                Optional.of(ItemPredicate.Builder.item().of(heartCrystal).build()))
                )).save(consumer, adventure("use_heart_crystal_at_half_heart"));
        challenge("rewind_great_distances", "nether", Consts.MINECRAFT.rl("nether/brew_potion"), CCItems.BEJEWELED_PEARL)
                .addCriterion("apply_quotient_topology", RewindDistanceTrigger.TriggerInstance.withDistance(1500))
                .save(consumer, nether("rewind_great_distances"));

        goalAdvancement("what_a_hullabaloo", "husbandry", Consts.NEAPOLITAN.rl("husbandry/chimpanzee_attack"), Consts.BANANARROW::item)
                .addCriterion("hully_bloo", WatchMobDieTrigger.TriggerInstance.withType(Consts.CHIMPANZEE.loc(), EntityType.RAVAGER))
                .save(consumer, husbandry("what_a_hullabaloo"));
        goalAdvancement("pvz", "mowzies", Consts.MOWZIES_MOBS.rl("kill_foliaath"), () -> Items.FLOWER_POT)
                .addCriterion("pvz", WatchMobDieTrigger.TriggerInstance.withType(LDTNTags.Entity.HUMANOID_ZOMBIES, Consts.FOLIAATH.loc(), EntityPredicate.Builder.entity().located(LocationPredicate.Builder.atYLocation(MinMaxBounds.Doubles.atLeast(300))).build()))
                .save(consumer, other("pvz"));
        goalAdvancement("kill_charged_creeper_with_quake", "other", Consts.SPECIES.rl("species/v3/kill_ten_mobs_with_quake"), Consts.KINETIC_CORE::item)
                .addCriterion("quake_kill_chainsaw_kill", WatchMobDieTrigger.TriggerInstance.emptyVictim(new IsChargedCreeperCondition(true), Consts.QUAKE.loc()))
                .save(consumer, other("kill_charged_creeper_with_quake"));
        goalAdvancement("thunderdome", "adventure", Consts.MINECRAFT.rl("adventure/kill_a_mob"), Consts.MIME_HEAD::item)
                .addCriterion("thunderdome", ThunderdomeTrigger.TriggerInstance.domes(3))
                .save(consumer, adventure("thunderdome"));

        goalAdvancement("slay_bewereager_with_silver", "other", Consts.SPECIES.rl("species/v3/v3"), Consts.CRANKBOW::item)
                .addCriterion("large_arrow_kill", CriteriaTriggers.PLAYER_KILLED_ENTITY.createCriterion(new KilledTrigger.TriggerInstance(
                            pred(reqHoldingItem(Consts.CRANKBOW.item())),
                            pred(new EntityIDCondition(Consts.BEWEREAGER.loc())),
                            Optional.of(DamageSourcePredicate.Builder.damageType().direct(EntityPredicate.Builder.entity().of(CCEntityTypes.LARGE_ARROW.get())).build())
                        )))
                .save(consumer, other("slay_bewereager_with_silver"));


        goalAdvancement("evaporate_nucleeper", "other", Consts.ALEXS_CAVES.rl("alexscaves/tame_tremorzilla"), Consts.MUSIC_DISC_FUSION::item)
                .addCriterion("nucleeper_gets_evapped", WatchMobDieTrigger.TriggerInstance.specifyVictim(Consts.NUCLEEPER.loc(),
                                DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(LDTNTags.DamageTypes.TREMORZILLA_BEAM))).build(),
                                Consts.TREMORZILLA.loc()))
                .save(consumer, other("evaporate_nucleeper"));

        goalAdvancement("kill_stickbug_with_lightning", "adventure", Consts.MINECRAFT.rl("adventure/throw_trident"), Consts.SUSPICIOUS_STICK::item)
                .addCriterion("kill_bug", CriteriaTriggers.CHANNELED_LIGHTNING.createCriterion(new ChanneledLightningTrigger.TriggerInstance(Optional.empty(), List.of(ContextAwarePredicate.create(new EntityIDCondition(Consts.STICKBUG.loc()))))))
                .save(consumer, adventure("kill_stickbug_with_lightning"));
        goalAdvancement("speed_demon", "adventure", Consts.MINECRAFT.rl("adventure/sleep_in_bed"), Consts.SPEEDOMETER::item)
                .addCriterion("be_fast", SpeedometerTrigger.withDistance(0.67))
                .save(consumer, adventure("speed_demon"));
        challenge("nether_thrasher", "nether", Consts.MINECRAFT.rl("nether/root"), Consts.THRASHER_TOOTH::item)
                .addCriterion("thrasher_in_nether_whatit_boutta_do", CriteriaTriggers.PLAYER_HURT_ENTITY.createCriterion(new PlayerHurtEntityTrigger.TriggerInstance(Optional.empty(), Optional.empty(),
                        pred(new EntityIDCondition(Consts.GREAT_THRASHER.loc()), entityHasCond(EntityPredicate.Builder.entity().located(LocationPredicate.Builder.inDimension(Level.NETHER)))))))
                .save(consumer, nether("nether_thrasher"));
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

    private static LootItemCondition entityHasCond(EntityPredicate.Builder predicate) {
        return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, predicate).build();
    }

    private static Advancement.Builder challenge(String name, String category, ResourceLocation parent, Supplier<Item> icon) {
        return createAdvancement(name, category, parent, icon, AdvancementType.CHALLENGE, true, true, false, 100);
    }

    private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, Supplier<Item> icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden, int xpLevel) {
        AdvancementRewards rewards = xpLevel != 0 ? AdvancementRewards.Builder.experience(xpLevel).build() : AdvancementRewards.EMPTY;
        return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon.get(),
                Lowdoltenuine.WRAPPED_ID.translatable("advancements.%s." + category + "." + name + ".title"),
                Lowdoltenuine.WRAPPED_ID.translatable("advancements.%s." + category + "." + name + ".description"),
                null, frame, showToast, announceToChat, hidden).rewards(rewards);
    }

    private static Optional<ContextAwarePredicate> pred(LootItemCondition... conditions) {
        return Optional.of(ContextAwarePredicate.create(conditions));
    }

    private static LootItemCondition reqHoldingItem(Item item) {
        return new AnyOfCondition.Builder(
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().equipment(
                                new EntityEquipmentPredicate.Builder().mainhand(ItemPredicate.Builder.item().of(item)))
                                .build()),
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().equipment(
                                        new EntityEquipmentPredicate.Builder().offhand(ItemPredicate.Builder.item().of(item)))
                                .build())
        ).build();

    }
}