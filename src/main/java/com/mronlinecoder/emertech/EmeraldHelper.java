package com.mronlinecoder.emertech;

import com.mronlinecoder.emertech.item.ModItems;
import com.mronlinecoder.emertech.tile.AccumulatorTileEntity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EmeraldHelper {

    private static final int ENERGY_PER_EMERALD = AccumulatorTileEntity.MAX_ENERGY / 64;

    public static boolean isEnergySource(ItemStack stack) {
        Item i = stack.getItem();
        return i == Items.EMERALD || i == Item.getItemFromBlock(Blocks.EMERALD_BLOCK);
    }

    public static int getEnergyAmount(ItemStack stack) {
        if (stack.getItem() == Items.EMERALD) return ENERGY_PER_EMERALD;
        if (stack.getItem() == Item.getItemFromBlock(Blocks.EMERALD_BLOCK)) return ENERGY_PER_EMERALD * 9;

        return 0;
    }

    public static int getChargeAmount(ItemStack stack) {
        if (stack.getItem() == ModItems.emerald_crystal_empty) return 1000;

        return 0;
    }

    public static ItemStack getChargedItem(ItemStack stack) {
        if (stack.getItem() == ModItems.emerald_crystal_empty) return new ItemStack(ModItems.emerald_crystal_full, 1);

        return ItemStack.EMPTY;
    }
}
