package com.dolthhaven.lowdoltenuine.core.datapacks.criteriatriggers;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.dolthhaven.lowdoltenuine.other.LDTNTrackedData;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Optional;

public class ThunderdomeTrigger extends SimpleCriterionTrigger<ThunderdomeTrigger.TriggerInstance> {
    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, int thunderdomeSore) {
        LootContext playerContext = EntityPredicate.createContext(player, player);
        this.trigger(player, instance -> instance.matches(playerContext, thunderdomeSore));
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, int mimeThunderdomed) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<ThunderdomeTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(ThunderdomeTrigger.TriggerInstance::player),
                        Codec.INT.fieldOf("mimes_thunderdomed").forGetter(ThunderdomeTrigger.TriggerInstance::mimeThunderdomed)
                ).apply(instance, ThunderdomeTrigger.TriggerInstance::new)
        );

        public static Criterion<TriggerInstance> domes(int mimeScore) {
            return LDTNAdvancementTriggers.THUNDERDOME.get().createCriterion(new TriggerInstance(Optional.empty(), mimeScore));
        }

        public boolean matches(LootContext context, int mimeScore) {
            return mimeScore >= mimeThunderdomed && this.player.map(pred -> pred.matches(context)).orElse(true) ;
        }
    }

    public static class ThunderdomeUtil {
        public static int incrementScore(Player player) {
            int score = getScore(player) + 1;
            setScore(player, score);
            return score;
        }

        private static int getScore(Player player) {
            return ((IDataManager) player).getValue(LDTNTrackedData.THUNDERDOME_SCORE);
        }

        private static void setScore(Player player, int num) {
            ((IDataManager) player).setValue(LDTNTrackedData.THUNDERDOME_SCORE, num);
        }

        public static void reset(Player player) {
            setScore(player, 0);
        }
    }
}