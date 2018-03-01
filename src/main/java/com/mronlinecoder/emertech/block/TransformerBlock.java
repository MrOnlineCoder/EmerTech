package com.mronlinecoder.emertech.block;

import com.mronlinecoder.emertech.EmerTech;
import com.mronlinecoder.emertech.item.ModItems;
import com.mronlinecoder.emertech.tile.TransformerTileEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TransformerBlock extends CustomBlock implements ITileEntityProvider {

    public static int GUI_ID = 2;

    public TransformerBlock() {
        super(Material.ROCK, "emerald_transformer");
        setHardness(2.0f);
        setResistance(25.f);
        setHarvestLevel("pickaxe", 2);
        setCreativeTab(ModItems.tab);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TransformerTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof TransformerTileEntity)) {
            return false;
        }
        playerIn.openGui(EmerTech.instance, TransformerBlock.GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}