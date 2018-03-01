package com.mronlinecoder.emertech.block;

import com.mronlinecoder.emertech.EmerTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class CustomBlock extends Block {

    String name;
    final ItemBlock itemBlock;

    public CustomBlock(Material materialIn, String name) {
        super(materialIn);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);

        itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(name);

        EmerTech.logger.info("Registered block and item: "+name+", itemBlock is null: "+(itemBlock == null));
    }

    @Override
    public CustomBlock setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public void registerItemModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        EmerTech.logger.info("Registered item block model "+name);
    }
}
