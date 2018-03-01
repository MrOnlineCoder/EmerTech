package com.mronlinecoder.emertech.net;


import com.mronlinecoder.emertech.gui.OutputSlot;
import com.mronlinecoder.emertech.tile.OreContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class OreContainer extends Container {

    private OreContainerTileEntity te;

    public OreContainer(IInventory playerInventory, OreContainerTileEntity te) {
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
        addSlotToContainer(new Slot(te, 0, 18, 33));
        addSlotToContainer(new OutputSlot(te, 1, 63, 12)); //coal
        addSlotToContainer(new OutputSlot(te, 2, 63, 32)); //iron
        addSlotToContainer(new OutputSlot(te, 3, 63, 52)); //gold
        addSlotToContainer(new OutputSlot(te, 4, 121, 12)); //redstone
        addSlotToContainer(new OutputSlot(te, 5, 121, 32)); //lapiz lazuli
        addSlotToContainer(new OutputSlot(te, 6, 121, 52)); //diamond
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < OreContainerTileEntity.SIZE) {
                if (!this.mergeItemStack(itemstack1, OreContainerTileEntity.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, OreContainerTileEntity.SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        if (index == OreContainerTileEntity.INDEX_INPUT) {
            te.updateSlots();
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
}

