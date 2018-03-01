package com.mronlinecoder.emertech.net;

import com.mronlinecoder.emertech.gui.EmeraldSlot;
import com.mronlinecoder.emertech.tile.AccumulatorTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class AccumulatorContainer extends Container {

    private AccumulatorTileEntity te;

    public AccumulatorContainer(IInventory playerInventory, AccumulatorTileEntity te) {
        this.te = te;

        addOwnSlots(playerInventory);
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 84;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            //int y = 128;
            int y = 142;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }

    }

    private void addOwnSlots(IInventory inv) {
        addSlotToContainer(new EmeraldSlot(te, 0, 26, 12));
        addSlotToContainer(new Slot(te, 1, 26, 52));
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < AccumulatorTileEntity.SIZE) {
                if (!this.mergeItemStack(itemstack1, AccumulatorTileEntity.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, AccumulatorTileEntity.SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
}
