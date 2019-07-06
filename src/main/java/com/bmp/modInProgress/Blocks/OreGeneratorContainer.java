package com.bmp.modInProgress.Blocks;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class OreGeneratorContainer extends Container {

    private OreGeneratorTileEntity te;

    public OreGeneratorContainer(IInventory playerInventory, OreGeneratorTileEntity te) {
        this.te = te;

        // This container references items out of our own inventory (the 9 slots we hold ourselves)
        // as well as the slots from the player inventory so that the user can transfer items between
        // both inventories. The two calls below make sure that slots are defined for both inventories.
        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
    	int i;
    	// PLAYER'S INVENTORY (3*9 = 27 slots added here with index starting at OUTPUT+1 and ending at OUTPUT+27)
    	for (i = 0; i < 3; ++i) {
	    	for (int j = 0; j < 9; ++j) {
	    		this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, (8 + j * 18)+1, (84 + i * 18)-15));
	    	}
    	}

    	// ACTION BAR (9 more slots added here, index starts at OUTPUT+27+1 and ends at OUTPUT+27+1+9)
    	for (i = 0; i < 9; ++i) {
    		this.addSlotToContainer(new Slot(playerInventory, i, (8 + i * 18)+1, (142)-15));
    	}
    }
    
    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int xIn = 49;
        int yIn = 30;
        int xOut = 111;
        int yOut = 30;
   
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, xIn, yIn){
		      @Override
		      public boolean isItemValid(@Nonnull ItemStack itemStack) {
		    	  return Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.COAL_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.DIAMOND_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.EMERALD_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.GOLD_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.IRON_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.LAPIS_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.QUARTZ_ORE)) || Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(Item.getItemFromBlock(Blocks.REDSTONE_ORE));
		      }
		});        		
        addSlotToContainer(new SlotItemHandler(itemHandler, 1, xOut, yOut){
		      @Override
		      public boolean isItemValid(@Nonnull ItemStack itemStack) {
		    	  return false;
		      }
		});
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack()) { 
            itemstack = slot.getStack();
            if (index < OreGeneratorTileEntity.SIZE) {
                if (!this.mergeItemStack(itemstack, OreGeneratorTileEntity.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack, 0, OreGeneratorTileEntity.SIZE-1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
}