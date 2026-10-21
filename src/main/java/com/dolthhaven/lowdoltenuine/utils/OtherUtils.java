package com.dolthhaven.lowdoltenuine.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class OtherUtils {
    public static void addToInvOrDrop(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) player.drop(stack, true);
    }
}
