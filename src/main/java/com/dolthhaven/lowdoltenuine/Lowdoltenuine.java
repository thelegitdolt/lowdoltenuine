package com.dolthhaven.lowdoltenuine;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Lowdoltenuine.MOD_ID)
public class Lowdoltenuine {
    public static final String MOD_ID = "lowdoltenuine";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Lowdoltenuine(IEventBus modEventBus, ModContainer modContainer) {
    }
}
