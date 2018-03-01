package com.mronlinecoder.emertech;

import com.mronlinecoder.emertech.block.ModBlocks;
import com.mronlinecoder.emertech.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod(modid = EmerTech.MODID, version = EmerTech.VERSION)
public class EmerTech
{
    public static final String MODID = "emertech";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide="com.mronlinecoder.emertech.ClientProxy", serverSide="com.mronlinecoder.emertech.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static EmerTech instance;

    public static org.apache.logging.log4j.Logger logger;


    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        proxy.init(e);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        logger = e.getModLog();
        proxy.preInit(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            logger.info("Registering all items...");
            ModItems.registerItems(event);

            logger.info("Registering item blocks...");
            ModBlocks.registerItemBlocks(event);
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            logger.info("Registering all blocks...");
            ModBlocks.registerBlocks(event);
        }

        @SubscribeEvent
        public static void registerItems(ModelRegistryEvent event) {
            logger.info("Registering block models...");
            ModBlocks.registerBlockModels();

            logger.info("Registering item models...");
            ModItems.registerModels();
        }
    }
}
