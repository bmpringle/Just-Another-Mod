package com.bmp.modInProgress.Items;

import com.bmp.modInProgress.ModInProgress;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CompressedIron extends Item {

	public CompressedIron() {
		setRegistryName("compressediron");
		setUnlocalizedName(ModInProgress.MODID + ".compressediron");
		setCreativeTab(CreativeTabs.MISC);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
