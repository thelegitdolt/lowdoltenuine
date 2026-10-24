package com.dolthhaven.lowdoltenuine.core;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class LDTNEvents {
    @SubscribeEvent
    public static void hello(PlayerInteractEvent.RightClickBlock event) {}
}
