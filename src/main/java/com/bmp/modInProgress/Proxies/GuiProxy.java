package com.bmp.modInProgress.Proxies;

import com.bmp.modInProgress.Blocks.OreGeneratorContainer;
import com.bmp.modInProgress.Blocks.OreGeneratorGui;
import com.bmp.modInProgress.Blocks.OreGeneratorTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof OreGeneratorTileEntity) {
            return new OreGeneratorContainer(player.inventory, (OreGeneratorTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof OreGeneratorTileEntity) {
        	OreGeneratorTileEntity containerTileEntity = (OreGeneratorTileEntity) te;
            return new OreGeneratorGui(containerTileEntity, new OreGeneratorContainer(player.inventory, containerTileEntity));
        }
        return null;
    }
}