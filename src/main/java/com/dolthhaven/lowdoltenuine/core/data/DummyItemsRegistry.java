package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Set;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class DummyItemsRegistry {

    @SubscribeEvent
    public static void registerDummies(RegisterEvent event) {
        if (DatagenModLoader.isRunningDataGen()) {
        }
    }
}
