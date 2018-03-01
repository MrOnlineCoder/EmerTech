package com.mronlinecoder.emertech.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static EmeraldTab tab = new EmeraldTab();

    //Tools
    public static CustomItem emerald_dust = new CustomItem("emerald_dust");

    public static CustomItem emerald_crystal_empty = new CustomItem("emerald_crystal_empty");
    public static EnchantedItem emerald_crystal_full = new EnchantedItem("emerald_crystal_full");

    public static CustomItem diamond_piece = new CustomItem("diamond_piece");

    public static CustomItem lower_ore_storage = new CustomItem("lower_ore_storage");
    public static CustomItem higher_ore_storage = new CustomItem("higher_ore_storage");


    public static void registerItems(RegistryEvent.Register<Item> event) {
        emerald_crystal_full.setMaxStackSize(1);

        event.getRegistry().register(emerald_dust);

        event.getRegistry().register(emerald_crystal_empty);
        event.getRegistry().register(emerald_crystal_full);

        event.getRegistry().register(diamond_piece);

        event.getRegistry().register(lower_ore_storage);
        event.getRegistry().register(higher_ore_storage);

        GameRegistry.addSmelting(Items.EMERALD, new ItemStack(emerald_dust, 2), 1f);
    }

    public static void registerModels() {
        emerald_dust.registerItemModel();

        emerald_crystal_empty.registerItemModel();
        emerald_crystal_full.registerItemModel();

        diamond_piece.registerItemModel();

        lower_ore_storage.registerItemModel();
        higher_ore_storage.registerItemModel();
    }
}
