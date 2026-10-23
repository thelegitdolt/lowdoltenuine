package com.dolthhaven.lowdoltenuine.other;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

import static com.dolthhaven.lowdoltenuine.core.registry.LDTNBlocks.*;

public class LDTNClientSetup {
    public static void clientCompat() {
        registerPipeRender();
    }

    private static void registerPipeRender() {
        ItemBlockRenderTypes.setRenderLayer(WHITE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BROWN_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GRAY_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(LIGHT_GRAY_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(RED_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ORANGE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(YELLOW_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(LIME_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GREEN_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BLUE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(LIGHT_BLUE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CYAN_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PURPLE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MAGENTA_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PINK_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BLACK_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ROSE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MAROON_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GINGER_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(TAN_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BEIGE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CORAL_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(OLIVE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FOREST_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VERDANT_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(AMBER_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(TEAL_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MINT_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(AQUA_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(SLATE_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(NAVY_ENCASED_PIPE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(INDIGO_ENCASED_PIPE.get(), RenderType.translucent());
    }
}
