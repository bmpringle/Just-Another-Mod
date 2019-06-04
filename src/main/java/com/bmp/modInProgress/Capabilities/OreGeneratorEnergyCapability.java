package com.bmp.modInProgress.Capabilities;

import net.minecraftforge.energy.IEnergyStorage;

public class OreGeneratorEnergyCapability implements IEnergyStorage{
	private int capacity;
    public int energy;
    public int machineUpdateCost=375;
    
    public OreGeneratorEnergyCapability() {
    	capacity = 10000;
    	energy = 0;
    }
    
    public OreGeneratorEnergyCapability(int maxStorage) {
    	capacity = maxStorage;
    	energy = 0;
    } 
    
    public OreGeneratorEnergyCapability(int maxStorage, int energyAmount) {
    	capacity = maxStorage;
    	energy = energyAmount;
    }
    
    
    @Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if(!simulate) {
			if(energy+maxReceive<=capacity) {
				energy=energy+maxReceive;
				return maxReceive;
			}else {
				int recieve = capacity-energy;
				energy=energy+recieve;
				return recieve;
			}
		}else {
			if(energy+maxReceive<=capacity) {
				return maxReceive;
			}else {
				int recieve = capacity-energy;
				return recieve;
			}
		}
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if(!simulate) {
			if(energy-maxExtract>=0) {
				energy=energy-maxExtract;
				return maxExtract;
			}else {
				energy=0;
				return energy;
			}
		}else {
			if(energy-maxExtract>=0) {
				return maxExtract;
			}else {
				return energy;
			}
		}
		
	}

	@Override
	public int getEnergyStored() {
		return energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {	
		//Not Yet Done
		return false;
	}

	@Override
	public boolean canReceive() {
		if(capacity-energy>0) {
			return true;
		}
		return false;
	}
}
