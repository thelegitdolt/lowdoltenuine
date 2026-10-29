package com.dolthhaven.lowdoltenuine.core.data;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
public class DummyItemsRegistry {
    public static final List<Consts.ModResource> DUMMY_ITEMS = new ArrayList<>();
    public static final List<ResourceLocation> DUMMY_BLOCKS = List
            .of(Lowdoltenuine.rl("heart_geode"));

    @SubscribeEvent
    public static void registerDummies(RegisterEvent event) {
        if (DatagenModLoader.isRunningDataGen()) {
            DUMMY_ITEMS.forEach(resource -> {
                if (!resource.exists())
                    event.register(Registries.ITEM, reg -> reg.register(resource.loc(), new Item(new Item.Properties())));
            });
            DUMMY_BLOCKS.forEach(resource -> {
                event.register(Registries.BLOCK, reg -> reg.register(resource, new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE))));
            });
        }
    }
}
