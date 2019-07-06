package com.bmp.modInProgress.Blocks;

import com.bmp.modInProgress.Capabilities.CreativeGeneratorEnergyCapability;
import com.bmp.modInProgress.Capabilities.OreGeneratorEnergyCapability;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CreativeGeneratorTileEntity extends TileEntity implements ITickable {

	public CreativeGeneratorEnergyCapability cgencap= new CreativeGeneratorEnergyCapability();
	
	@Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(cgencap);
        }
        return super.getCapability(capability, facing);
    }
    
	@Override
	public void update() {
		if(!getWorld().isRemote) { 
            for (EnumFacing facing : EnumFacing.VALUES) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                    IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
                    if (handler != null && handler.canReceive()) {
                        int accepted = handler.receiveEnergy(handler.getMaxEnergyStored(), false);
                        if (cgencap.getEnergyStored() <= 0) {
                            break;
                        }
                    }
                }
            }
            markDirty();
		}
	}
}
