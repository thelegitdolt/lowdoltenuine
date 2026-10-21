package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
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
