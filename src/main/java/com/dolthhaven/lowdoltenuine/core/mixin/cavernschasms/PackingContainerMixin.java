package com.dolthhaven.lowdoltenuine.core.mixin.cavernschasms;

import com.dolthhaven.lowdoltenuine.other.LDTNConfig;
import com.teamabnormals.caverns_and_chasms.common.item.PackingContainerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PackingContainerItem.class)
public class PackingContainerMixin {
    @ModifyConstant(method = "appendHoverText", constant = @Constant(intValue = 512))
    private int sex(int constant) {
        return LDTNConfig.COMMON.modpackStackSize.get() * 8;
    }
}
