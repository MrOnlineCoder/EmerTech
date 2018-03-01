package com.mronlinecoder.emertech.tile;

import com.mronlinecoder.emertech.EmeraldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class AccumulatorTileEntity extends TileEntity implements ITickable, IInventory {
    public static final int SIZE = 2;

    public static final int INDEX_INPUT = 0;
    public static final int INDEX_USER = 1;

    public static final int MAX_ENERGY = 10000;

    private int energy = 0;
    private int ticks = 0;
    NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);

    public AccumulatorTileEntity() {
        clear();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.itemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);
        energy = compound.getInteger("energy");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);
        compound.setInteger("energy", energy);
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public void update() {
        if (!this.getStackInSlot(INDEX_INPUT).isEmpty()) {
            ticks++;

            if (ticks >= 10) {
                ItemStack in = this.getStackInSlot(INDEX_INPUT);
                int am = EmeraldHelper.getEnergyAmount(in);
                if (energy + am > MAX_ENERGY) {
                    ticks = 0;
                    return;
                }

                energy += am;
                this.decrStackSize(INDEX_INPUT, 1);
                ticks = 0;

                this.markDirty();
            }
        }

        if (!this.getStackInSlot(INDEX_USER).isEmpty()) {
            int am = EmeraldHelper.getChargeAmount(this.getStackInSlot(INDEX_USER));

            if (am == 0) return;

            if (energy - am < 0) return;

            energy -= am;

            this.setInventorySlotContents(INDEX_USER, EmeraldHelper.getChargedItem(this.getStackInSlot(INDEX_USER)));
            this.markDirty();
        }
    }

    //Done
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        this.itemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

    }

    //Done
    @Override
    public int getSizeInventory() {
        return SIZE;
    }

    //Done
    @Override
    public boolean isEmpty() {
        return false;
    }

    //Done
    @Override
    public ItemStack getStackInSlot(int index) {
        return itemStacks.get(index);
    }

    //Done
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if(stack != ItemStack.EMPTY)
        {
            if(stack.getCount() > count)
            {
                stack = stack.splitStack(count);
                // Don't forget this line or your inventory will not be saved!
                this.markDirty();
            }
            else
            {
                setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }
        return stack;
    }

    //Done
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack item = itemStacks.get(index);
        itemStacks.set(index, ItemStack.EMPTY);
        return item;
    }

    //Done
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    //Done
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    //Done
    @Override
    public void openInventory(EntityPlayer player) {

    }

    //Done
    @Override
    public void closeInventory(EntityPlayer player) {

    }

    //Done
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        itemStacks.set(INDEX_INPUT, ItemStack.EMPTY);
        itemStacks.set(INDEX_USER, ItemStack.EMPTY);
    }

    @Override
    public String getName() {
        return "Emerald Accumulator";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
