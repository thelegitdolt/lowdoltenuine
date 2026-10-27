package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class DummyItemsRegistry {
    public static final List<Consts.ModResource> DUMMY_ITEMS = new ArrayList<>();

    @SubscribeEvent
    public static void registerDummies(RegisterEvent event) {
        if (DatagenModLoader.isRunningDataGen()) {
            DUMMY_ITEMS.forEach(resource -> {
                if (!resource.exists())
                    event.register(Registries.ITEM, reg -> reg.register(resource.loc(), new Item(new Item.Properties())));
            });
        }
    }
}
