package com.dolthhaven.lowdoltenuine.core.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class LowdoltenuineMixinPlugin implements IMixinConfigPlugin {
    private static boolean quarkEmiLoaded() {
        try {
            Class.forName("org.violetmoon.quark.base.Quark");
            Class.forName("dev.emi.emi.api.EmiApi");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static String mixin(String name) {
        return "com.dolthhaven.lowdoltenuine.core.mixin." + name;
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (targetClassName.equals(mixin("emi.backpackscreenfillingfeature.EmiRecipeFillerMixin"))) {
            return quarkEmiLoaded();
        }
        if (targetClassName.equals(mixin("emi.backpackscreenfillingfeature.FillRecipeC2SPacketMixin"))) {
            return quarkEmiLoaded();
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
