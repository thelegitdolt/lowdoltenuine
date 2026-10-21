package com.dolthhaven.lowdoltenuine.core.mixin.cavernschasms;

import com.dolthhaven.lowdoltenuine.utils.OtherUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Equipable.class)
public interface EquipableMixin {
    @Inject(method = "swapWithEquipmentSlot", at = @At("HEAD"), cancellable = true)
    default void DoltModHow$TakeIntoAccountStackables(Item item, Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack handStack = player.getItemInHand(hand);
        EquipmentSlot slot = player.getEquipmentSlotForItem(handStack);
        ItemStack armorStack = player.getItemBySlot(slot);

        if (handStack.getCount() > 1 && !EnchantmentHelper.has(armorStack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
            if (!armorStack.isEmpty()) {
                ItemStack newArmorStack = armorStack.copyAndClear();
                OtherUtils.addToInvOrDrop(player, newArmorStack);
            }

            player.setItemSlot(slot, handStack.split(1));
            cir.setReturnValue(InteractionResultHolder.sidedSuccess(handStack, level.isClientSide()));
        }
    }
}
