package com.dolthhaven.lowdoltenuine.core.data.tag;

import com.dolthhaven.lowdoltenuine.Lowdoltenuine;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;

public class LDTNTags {
    public static final TagKey<Item> HIDES_CAPES = itemTag("hides_capes");

    private static TagKey<Item> itemTag(String path) {
        return externalItemTag(Lowdoltenuine.MOD_ID, path);
    }

    private static TagKey<Item> externalItemTag(String modId, String path) {
        return TagUtil.itemTag(modId, path);
    }

    @EventBusSubscriber(modid = Lowdoltenuine.MOD_ID)
    public static class Datamaps {
        public static final DataMapType<EntityType<?>, Compostable> COMPOSTABLE_ENTITIES = DataMapType
                .builder(Lowdoltenuine.rl("compostable_entities"), Registries.ENTITY_TYPE, Compostable.CODEC)
                .synced(Compostable.CHANCE_CODEC, false)
                .build();

        @SubscribeEvent
        public static void registerDataMaps(RegisterDataMapTypesEvent event) {
            event.register(COMPOSTABLE_ENTITIES);
        }
    }
}
