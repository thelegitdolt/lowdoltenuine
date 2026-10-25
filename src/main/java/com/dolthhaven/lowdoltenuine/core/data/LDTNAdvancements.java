package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.RewindDistanceTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.lootconditions.HealthLootCondition;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.teamabnormals.caverns_and_chasms.core.registry.CCItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
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
        goalAdvancement("rewind_great_distances", "nether", Consts.MINECRAFT.rl("nether/brew_potion"), CCItems.BEJEWELED_PEARL)
                .addCriterion("apply_quotient_topology", RewindDistanceTrigger.TriggerInstance.withDistance(1500))
                .save(consumer, nether("rewind_great_distances"));
    }

    private static String adventure(String name) {
        return Lowdoltenuine.rl("adventure/" + name).toString();
    }

    private static String nether(String name) {
        return Lowdoltenuine.rl("nether/" + name).toString();
    }


    private static PlayerTrigger.TriggerInstance playerCondition(ContextAwarePredicate contextAwarePredicate) {
        return new PlayerTrigger.TriggerInstance(Optional.of(contextAwarePredicate));
    }

    private static LootItemCondition.Builder thisHasProps(EntityPredicate entityPredicate) {
        return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, entityPredicate);
    }

    private static Advancement.Builder goalAdvancement(String name, String category, ResourceLocation parent, Supplier<Item> icon) {
        return createAdvancement(name, category, parent, icon, AdvancementType.TASK, true, true, false);
    }

    private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, Supplier<Item> icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon.get(),
                Lowdoltenuine.WRAPPED_ID.translatable("advancements.%s." + category + "." + name + ".title"),
                Lowdoltenuine.WRAPPED_ID.translatable("advancements.%s." + category + "." + name + ".description"),
                null, frame, showToast, announceToChat, hidden);
    }

    private static EntityPredicate.Builder reqEffectAndAmplifier(Holder<MobEffect> effect, MinMaxBounds.Ints bound) {
        return EntityPredicate.Builder.entity().effects(new MobEffectsPredicate.Builder().and(effect, new MobEffectsPredicate.MobEffectInstancePredicate(bound, MinMaxBounds.Ints.ANY, Optional.empty(), Optional.empty())));
    }
}
