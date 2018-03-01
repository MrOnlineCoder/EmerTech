package com.mronlinecoder.emertech.tile;

import com.mronlinecoder.emertech.item.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;


public class TransformerTileEntity extends TileEntity implements ITickable, IInventory {
    public static final int SIZE = 3;

    public static final int INDEX_INPUT = 0;
    public static final int INDEX_OUTPUT = 1;
    public static final int INDEX_OUTPUT2 = 2;

    public static final int TIME_PER_DIAMOND = 5 * 20;

    private int ticks = 0;
    NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);

    public TransformerTileEntity() {
        clear();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.itemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    public boolean canTransform() {
        if (this.getStackInSlot(INDEX_INPUT).isEmpty()) return false;
        if (!this.getStackInSlot(INDEX_OUTPUT).isEmpty()) {
            return this.getStackInSlot(INDEX_OUTPUT).getCount() < 64;
        }

        return true;
    }

    public double getFractionProgress() {
        return (double) ticks / (double) TIME_PER_DIAMOND;
    }

    @Override
    public void update() {
        if (canTransform()) {
            ticks++;

            if (ticks >= TIME_PER_DIAMOND) {
                ticks = 0;
                this.decrStackSize(INDEX_INPUT, 1);

                this.markDirty();


                ItemStack o = this.getStackInSlot(INDEX_OUTPUT);

                if (o.isEmpty()) {
                    this.setInventorySlotContents(INDEX_OUTPUT, new ItemStack(Items.EMERALD, 1));
                } else {
                    o.setCount(o.getCount() + 1);
                    this.setInventorySlotContents(INDEX_OUTPUT, o);
                }


                ItemStack o2 = this.getStackInSlot(INDEX_OUTPUT2);
                if (o2.isEmpty()) {
                    this.setInventorySlotContents(INDEX_OUTPUT2, new ItemStack(ModItems.diamond_piece, 1));
                } else {
                    if (o2.getCount() < 64) {
                        o2.setCount(o2.getCount() + 1);
                        this.setInventorySlotContents(INDEX_OUTPUT2, o2);
                    } else {
                        EntityItem item = new EntityItem(world, this.getPos().getX(), this.getPos().getY()+1, this.getPos().getZ(), new ItemStack(ModItems.diamond_piece, 1));
                        world.spawnEntity(item);
                    }
                }
            }
        } else {
            if (ticks > 0) ticks = 0;
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
        if (index == INDEX_INPUT) {
            return stack.getItem() == Items.DIAMOND;
        }

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
        itemStacks.set(INDEX_OUTPUT, ItemStack.EMPTY);
        itemStacks.set(INDEX_OUTPUT2, ItemStack.EMPTY);
    }

    @Override
    public String getName() {
        return "Emerald Transformer";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
