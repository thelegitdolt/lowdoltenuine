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

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class DummyItemsRegistry {


    @SubscribeEvent
    public static void registerDummies(RegisterEvent event) {
        var list = List.of(Consts.RAW_ZINC);
        if (DatagenModLoader.isRunningDataGen()) {
            list.forEach(location ->
                    event.register(Registries.ITEM, reg -> reg.register(location, new Item(new Item.Properties()))));
        }
    }
}
