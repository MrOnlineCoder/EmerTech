package com.mronlinecoder.emertech.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class OreContainerTileEntity extends TileEntity implements ITickable, IInventory {
    public static final int SIZE = 7;

    public static final int INDEX_INPUT = 0;
    public static final int INDEX_COAL = 1;
    public static final int INDEX_IRON = 2;
    public static final int INDEX_GOLD = 3;
    public static final int INDEX_REDSTONE = 4;
    public static final int INDEX_LAPIZ = 5;
    public static final int INDEX_DIAMOND = 6;

    public static final int MAX_ITEMS = 1024;

    int[] storage = new int[6];

    private int ticks = 0;
    NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);

    public OreContainerTileEntity() {
        //clear();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.itemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);
        storage[0] = compound.getInteger("storage_coal");
        storage[1] = compound.getInteger("storage_iron");
        storage[2] = compound.getInteger("storage_gold");
        storage[3] = compound.getInteger("storage_redstone");
        storage[4] = compound.getInteger("storage_lapiz");
        storage[5] = compound.getInteger("storage_diamond");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);
        compound.setInteger("storage_coal", storage[0]);
        compound.setInteger("storage_iron", storage[1]);
        compound.setInteger("storage_gold", storage[2]);
        compound.setInteger("storage_redstone", storage[3]);
        compound.setInteger("storage_lapiz", storage[4]);
        compound.setInteger("storage_diamond", storage[5]);
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void update() {
        if (!this.getStackInSlot(OreContainerTileEntity.INDEX_INPUT).isEmpty()) {
            int idx = -1;
            Item type = this.getStackInSlot(OreContainerTileEntity.INDEX_INPUT).getItem();
            if (type == Items.COAL) idx = 0;
            if (type == Items.IRON_INGOT) idx = 1;
            if (type == Items.GOLD_INGOT) idx = 2;
            if (type == Items.REDSTONE) idx = 3;
            if (type == Items.DYE && this.getStackInSlot(OreContainerTileEntity.INDEX_INPUT).getMetadata() == 4) idx = 4;
            if (type == Items.DIAMOND) idx = 5;

            if (idx == -1) return;

            int count = this.getStackInSlot(OreContainerTileEntity.INDEX_INPUT).getCount();

            if (storage[idx] + count > MAX_ITEMS) return;

            storage[idx] += count;

            this.decrStackSize(OreContainerTileEntity.INDEX_INPUT, count);
            this.markDirty();
            this.updateSlots();
        }
    }

    private static ItemStack getMaterialByIndex(int idx, int amount) {
        if (amount <= 0) return ItemStack.EMPTY;

        switch (idx) {
            case 0: return new ItemStack(Items.COAL, amount);
            case 1: return new ItemStack(Items.IRON_INGOT, amount);
            case 2: return new ItemStack(Items.GOLD_INGOT, amount);
            case 3: return new ItemStack(Items.REDSTONE, amount);
            case 4: return new ItemStack(Items.DYE, amount, 4);
            case 5: return new ItemStack(Items.DIAMOND, amount);
            default: return new ItemStack(Items.CARROT, 32);
        }
    }

    public void updateSlots() {
        clear();
        for (int i=0;i<storage.length;i++) {
            if (storage[i] == 0) continue;
            int amount = storage[i];
            if (storage[i] >= 64) {
                amount = 64;
            }

            this.setInventorySlotContents(i+1, getMaterialByIndex(i, amount));
        }
    }

    public int getStorage(int idx) {
        return storage[idx];
    }

    //Done
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        this.itemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
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
        updateSlots();
    }

    //Done
    @Override
    public void closeInventory(EntityPlayer player) {

    }



    //Done
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == OreContainerTileEntity.INDEX_INPUT) {
            return stack.getItem() == Items.COAL || stack.getItem() == Items.IRON_INGOT || stack.getItem() == Items.GOLD_INGOT || stack.getItem() == Items.REDSTONE || stack.getItem() == Items.DYE || stack.getItem() == Items.DIAMOND;
        }

        return false;
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
        itemStacks.set(OreContainerTileEntity.INDEX_INPUT, ItemStack.EMPTY);
        itemStacks.set(INDEX_COAL, ItemStack.EMPTY);
        itemStacks.set(INDEX_IRON, ItemStack.EMPTY);
        itemStacks.set(INDEX_GOLD, ItemStack.EMPTY);
        itemStacks.set(INDEX_REDSTONE, ItemStack.EMPTY);
        itemStacks.set(INDEX_LAPIZ, ItemStack.EMPTY);
        itemStacks.set(INDEX_DIAMOND, ItemStack.EMPTY);
    }

    @Override
    public String getName() {
        return "Emerald Ore Container";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    public void dropItems() {
        for (int i=0;i<storage.length;i++) {
            if (storage[i] == 0) continue;

            int amount = storage[i];

            BlockPos pos = this.getPos();

            if (storage[i] <= 64) {
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), getMaterialByIndex(i, amount));
                world.spawnEntity(item);
            } else {
                int stacksToDrop = storage[i] % 64;
                for (int j=0;j<stacksToDrop;j++) {
                    EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), getMaterialByIndex(i, 64));
                    world.spawnEntity(item);
                }

                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), getMaterialByIndex(i, amount - stacksToDrop * 64));
                world.spawnEntity(item);
            }
        }
    }
}
