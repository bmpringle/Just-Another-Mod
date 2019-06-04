package com.bmp.modInProgress.Items;

import com.bmp.modInProgress.ModInProgress;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Hammer extends Item {

	public Hammer() {
		maxStackSize = 1;
		setContainerItem(this); 
		setRegistryName("hammer");
		setUnlocalizedName(ModInProgress.MODID + ".hammer");
		setCreativeTab(CreativeTabs.MISC);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
