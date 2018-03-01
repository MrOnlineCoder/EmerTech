package com.mronlinecoder.emertech;

import com.mronlinecoder.emertech.tile.AccumulatorTileEntity;
import com.mronlinecoder.emertech.tile.OreContainerTileEntity;
import com.mronlinecoder.emertech.tile.TransformerTileEntity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    //EventHandler handler;

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        GameRegistry.registerTileEntity(AccumulatorTileEntity.class, "emerald_accumulator_tile");
        GameRegistry.registerTileEntity(TransformerTileEntity.class, "emerald_transformer_tile");
        GameRegistry.registerTileEntity(OreContainerTileEntity.class, "emerald_ore_container_tile");

        NetworkRegistry.INSTANCE.registerGuiHandler(EmerTech.instance, new ModGUIHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(EmerTech.MODID + ":" + id, "inventory"));
    }
}
