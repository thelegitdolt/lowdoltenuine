package com.dolthhaven.lowdoltenuine.core.registry;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.RewindDistanceTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.ThunderdomeTrigger;
import com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers.WatchMobDieTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LDTNAdvancementTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Lowdoltenuine.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, RewindDistanceTrigger> REWIND_TELEPORT = TRIGGERS.register("rewind_teleport", RewindDistanceTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, WatchMobDieTrigger> WATCH_MOB_DIE = TRIGGERS.register("watch_mob_die", WatchMobDieTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, ThunderdomeTrigger> THUNDERDOME_TRIGGER = TRIGGERS.register("thunderdome", ThunderdomeTrigger::new);
}
