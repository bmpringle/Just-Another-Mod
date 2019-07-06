package com.bmp.modInProgress.Blocks;

import javax.annotation.Nullable;

import com.bmp.modInProgress.ModInProgress;
import com.bmp.modInProgress.Capabilities.OreGeneratorEnergyCapability;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;


public class OreGeneratorTileEntity extends TileEntity implements ITickable{

    public static final int SIZE = 2;
    private int tickstillupdate = 5;
    private int secondstillcreation=72;
    public int stepstillcreation = 288;
    private int updatetickg = 5;
    public int energy = 0;
    public int maxenergy = 0;
    
    public ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            OreGeneratorTileEntity.this.markDirty();
        }
    };
    
    public OreGeneratorEnergyCapability oregenenergycap = new OreGeneratorEnergyCapability();
    
    private void insertItem(int slot, ItemStack itemStack, boolean simulate){
    	itemStackHandler.insertItem(slot, itemStack, simulate);
    }
    
    private int getSlotAmount(int slot){
    	ItemStack itemsInSlot = itemStackHandler.getStackInSlot(slot);
    	return itemsInSlot.getCount();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("items")){
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        
        if(compound.hasKey("energy")){
        	energy = compound.getInteger("energy");
        }
        
        if(compound.hasKey("stepsTill")) {
        	stepstillcreation = compound.getInteger("stepsTill");
        }
        
        if(compound.hasKey("maxenergy")) {
        	maxenergy = compound.getInteger("maxenergy");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {  	
    	super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setInteger("stepsTill", stepstillcreation);
        compound.setInteger("energy", energy);
        compound.setInteger("maxenergy", maxenergy);
        return compound;
    }
    
    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(oregenenergycap);
        }
        
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
    
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	private void sendUpdates() {
		getWorld().markBlockRangeForRenderUpdate(pos, pos);
		getWorld().notifyBlockUpdate(pos, getState(), getState(), 3);
		getWorld().scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	private IBlockState getState() {
		return getWorld().getBlockState(pos);
	}
	
	@Override
	public void update() {
		
		if(updatetickg==1) {
			sendUpdates();
			updatetickg=5;
		}else {
			--updatetickg;
		}
		
		
		
		
		if(!getWorld().isRemote) {	
			
			if(ModInProgress.ifDev) {
				oregenenergycap.receiveEnergy(oregenenergycap.getMaxEnergyStored(), false);
			}
			energy = oregenenergycap.energy;
			maxenergy = oregenenergycap.getMaxEnergyStored();
			if(tickstillupdate==1) {
				if(oregenenergycap.getEnergyStored()-oregenenergycap.machineUpdateCost>=0) {
					ItemStack oldItemStack = itemStackHandler.getStackInSlot(0);
					if(stepstillcreation==1) {
						ItemStack newItemStack = new ItemStack(oldItemStack.getItem(), 1);
						insertItem(1, newItemStack, false);
						stepstillcreation=288;
						oregenenergycap.extractEnergy(oregenenergycap.machineUpdateCost, false);
					}else {
						if(itemStackHandler.getStackInSlot(1).getCount() != 64) {
							if(oldItemStack.getItem() != Items.AIR) {
								oregenenergycap.extractEnergy(oregenenergycap.machineUpdateCost, false);
								--stepstillcreation;
							}
						}
					}
					
					if(oldItemStack.getItem() == Items.AIR) {
						stepstillcreation=288;
					}
				}	
				tickstillupdate=5;
			}else{
				--tickstillupdate;
			}
		}else {
			
		}
	}
}
