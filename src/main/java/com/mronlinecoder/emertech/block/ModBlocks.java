package com.mronlinecoder.emertech.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

    static AccumulatorBlock accumulator = new AccumulatorBlock();
    static TransformerBlock transformer = new TransformerBlock();
    static OreContainerBlock oreContainer = new OreContainerBlock();

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(accumulator);
        event.getRegistry().register(transformer);
        event.getRegistry().register(oreContainer);
    }

    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(accumulator.itemBlock);
        event.getRegistry().register(transformer.itemBlock);
        event.getRegistry().register(oreContainer.itemBlock);
    }

    public static void registerBlockModels() {
        accumulator.registerItemModel();
        transformer.registerItemModel();
        oreContainer.registerItemModel();
    }

}
