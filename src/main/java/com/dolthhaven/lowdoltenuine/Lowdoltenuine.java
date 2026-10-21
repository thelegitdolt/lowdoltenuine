package com.dolthhaven.lowdoltenuine;

import com.dolthhaven.lowdoltenuine.core.data.LDTNAdvancements;
import com.dolthhaven.lowdoltenuine.core.data.LDTNLootTables;
import com.dolthhaven.lowdoltenuine.core.data.LDTNRecipes;
import com.dolthhaven.lowdoltenuine.core.data.client.LDTNBlockStateModel;
import com.dolthhaven.lowdoltenuine.core.data.client.LDTNItemModelGen;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNBlockTags;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNDataMaps;
import com.dolthhaven.lowdoltenuine.core.data.tag.LDTNItemTags;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks;
import com.dolthhaven.lowdoltenuine.core.registry.LDTNItems;
import com.dolthhaven.lowdoltenuine.other.LDTNConfig;
import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(Lowdoltenuine.MOD_ID)
public class Lowdoltenuine {
    public static final String MOD_ID = "lowdoltenuine";
    public static final Consts.ModId WRAPPED_ID = new Consts.ModId(MOD_ID);
    
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final Logger LOGGER = LogUtils.getLogger();

    public Lowdoltenuine(IEventBus bus, ModContainer modContainer) {
        registrate(bus);
        
        bus.addListener(this::dataSetup);
        bus.addListener(this::clientSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, LDTNConfig.COMMON_SPEC);
    }

    private static void registrate(IEventBus bus) {
        LDTNBlocks.BLOCKS.register(bus);
        LDTNItems.ITEMS.register(bus);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(LDTNItems::setUpTabEditors);
    }

    private void dataSetup(GatherDataEvent event) {
        var dataGen = event.getGenerator();

        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuiltinEntriesProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), registrySetBuilder, Set.of(MOD_ID));
        CompletableFuture<HolderLookup.Provider> newProvider = datapackProvider.getRegistryProvider();

        boolean includeServer = event.includeServer();

        dataGen.addProvider(includeServer, datapackProvider);
        LDTNBlockTags taggies = new LDTNBlockTags(event);
        dataGen.addProvider(includeServer, taggies);
        dataGen.addProvider(includeServer, new LDTNItemTags(event, taggies));
        dataGen.addProvider(includeServer, new LDTNDataMaps(event));
        dataGen.addProvider(includeServer, new LDTNLootTables(event));
        dataGen.addProvider(includeServer, new LDTNRecipes(event));
        dataGen.addProvider(includeServer, LDTNAdvancements.create(event, newProvider));

        boolean includeClient = event.includeClient();
        dataGen.addProvider(includeClient, new LDTNBlockStateModel(event));
        dataGen.addProvider(includeClient, new LDTNItemModelGen(event));
    }
    
    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
