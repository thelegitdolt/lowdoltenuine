package com.dolthhaven.lowdoltenuine.core.mixin.upgradeaquatic;

import com.dolthhaven.lowdoltenuine.core.registry.LDTNAdvancementTriggers;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Pseudo
@Mixin(Thrasher.class)
public abstract class ThrasherMixin extends Entity {
    @Inject(method = "tick", at = @At("HEAD"))
    private void grantAdvancement(CallbackInfo ci) {
        if (!Consts.GREAT_THRASHER.is(this))
            return;

        if ((this.level().getGameTime() + this.uuid.getMostSignificantBits()) % 5 != 0) {
            return;
        }

        if (this.level().dimension() == Level.NETHER) {

            List<Player> players = this.level().getEntitiesOfClass(Player.class,
                    new AABB(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())).inflate(30, 30, 30));

            players.forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    LDTNAdvancementTriggers.THRASHER_IN_NETHER.get().trigger(serverPlayer);
                }
            });
        }
    }

    public ThrasherMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
}
