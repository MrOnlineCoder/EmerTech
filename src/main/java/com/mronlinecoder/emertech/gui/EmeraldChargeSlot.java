package com.mronlinecoder.emertech.gui;

import com.mronlinecoder.emertech.EmeraldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class EmeraldChargeSlot extends Slot {
    public EmeraldChargeSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return EmeraldHelper.getChargeAmount(stack) > 0;
    }


    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        super.onTake(thePlayer, stack);
        return stack;
    }
}