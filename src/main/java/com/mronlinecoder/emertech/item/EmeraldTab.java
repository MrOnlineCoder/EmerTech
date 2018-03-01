package com.mronlinecoder.emertech.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EmeraldTab extends CreativeTabs {

    public EmeraldTab() {
        super(CreativeTabs.getNextID(), "emertech");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.EMERALD);
    }
}
