package com.mronlinecoder.emertech;

import com.mronlinecoder.emertech.block.AccumulatorBlock;
import com.mronlinecoder.emertech.block.OreContainerBlock;
import com.mronlinecoder.emertech.block.TransformerBlock;
import com.mronlinecoder.emertech.gui.AccumulatorGUI;
import com.mronlinecoder.emertech.gui.OreContainerGUI;
import com.mronlinecoder.emertech.gui.TransformerGUI;
import com.mronlinecoder.emertech.net.AccumulatorContainer;
import com.mronlinecoder.emertech.net.OreContainer;
import com.mronlinecoder.emertech.net.TransformerContainer;
import com.mronlinecoder.emertech.tile.AccumulatorTileEntity;
import com.mronlinecoder.emertech.tile.OreContainerTileEntity;
import com.mronlinecoder.emertech.tile.TransformerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class ModGUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        if (ID == AccumulatorBlock.GUI_ID) {
            if (te instanceof AccumulatorTileEntity) {
                return new AccumulatorContainer(player.inventory, (AccumulatorTileEntity) te);
            }
        }

        if (ID == TransformerBlock.GUI_ID) {
            if (te instanceof TransformerTileEntity) {
                return new TransformerContainer(player.inventory, (TransformerTileEntity) te);
            }
        }

        if (ID == OreContainerBlock.GUI_ID) {
            if (te instanceof OreContainerTileEntity) {
                return new OreContainer(player.inventory, (OreContainerTileEntity) te);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

       if (ID == AccumulatorBlock.GUI_ID) {
            if (te instanceof AccumulatorTileEntity) {
                AccumulatorTileEntity containerTileEntity = (AccumulatorTileEntity) te;
                return new AccumulatorGUI(containerTileEntity, new AccumulatorContainer(player.inventory, containerTileEntity));
            }
        }

        if (ID == TransformerBlock.GUI_ID) {
            if (te instanceof TransformerTileEntity) {
                TransformerTileEntity containerTileEntity = (TransformerTileEntity) te;
                return new TransformerGUI(containerTileEntity, new TransformerContainer(player.inventory, containerTileEntity));
            }
        }

        if (ID == OreContainerBlock.GUI_ID) {
            if (te instanceof OreContainerTileEntity) {
                OreContainerTileEntity containerTileEntity = (OreContainerTileEntity) te;
                return new OreContainerGUI(containerTileEntity, new OreContainer(player.inventory, containerTileEntity));
            }
        }

        return null;
    }
}
