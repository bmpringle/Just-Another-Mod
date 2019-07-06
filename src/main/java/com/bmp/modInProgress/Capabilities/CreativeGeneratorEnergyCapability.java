package com.bmp.modInProgress.Capabilities;

import net.minecraftforge.energy.IEnergyStorage;

public class CreativeGeneratorEnergyCapability implements IEnergyStorage {

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return maxExtract;
	}

	@Override
	public int getEnergyStored() {
		return 2147483647;
	}

	@Override
	public int getMaxEnergyStored() {
		return 2147483647;
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return false;
	}

}
