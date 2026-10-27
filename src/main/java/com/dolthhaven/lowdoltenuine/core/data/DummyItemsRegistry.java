package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class DummyItemsRegistry {


    @SubscribeEvent
    public static void registerDummies(RegisterEvent event) {
        var list = List.of(Consts.ZINC_INGOT, Consts.SPAWN_DATES, Consts.ARID_SPROUTS, Consts.BEACHGRASS, Consts.TALL_BEACHGRASS, Consts.MYCELIUM_SPROUTS,
                Consts.ARID_SAND, Consts.RED_ARID_SAND, Consts.BB_ASHEN_SAND, Consts.BANANARROW, Consts.SUSPICIOUS_STICK);
        if (DatagenModLoader.isRunningDataGen()) {
            list.forEach(location -> {
                if (!ModList.get().isLoaded(location.getNamespace()))
                    event.register(Registries.ITEM, reg -> reg.register(location, new Item(new Item.Properties())));
            });
        }
    }
}
