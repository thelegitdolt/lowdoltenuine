package com.dolthhaven.lowdoltenuine.other;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import net.minecraft.network.codec.ByteBufCodecs;

public class LDTNTrackedData {
    public static final TrackedData<Integer> THUNDERDOME_SCORE = TrackedData.Builder.create(ByteBufCodecs.INT, () -> 0).build();

    public static void registerTrackedData() {
        TrackedDataManager.INSTANCE.registerData(Lowdoltenuine.rl("thunderdome_score"), THUNDERDOME_SCORE);
    }
}
