package com.mronlinecoder.emertech.item;

import com.mronlinecoder.emertech.EmerTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CustomItem extends Item {
    protected String name;

    public CustomItem(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModItems.tab);
    }

    public void registerItemModel() {
        EmerTech.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public CustomItem setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
