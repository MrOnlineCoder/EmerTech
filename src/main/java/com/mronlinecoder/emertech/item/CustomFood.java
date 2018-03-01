package com.mronlinecoder.emertech.item;

import com.mronlinecoder.emertech.EmerTech;
import net.minecraft.item.ItemFood;


public class CustomFood extends ItemFood  {
    String name;

    public CustomFood(String name, int healAmount)
    {
        super(healAmount, false);
        this.name = name;
        this.setCreativeTab(ModItems.tab);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }

    public void registerItemModel() {
        EmerTech.proxy.registerItemRenderer(this, 0, name);
    }
}