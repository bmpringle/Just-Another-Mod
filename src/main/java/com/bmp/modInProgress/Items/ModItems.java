package com.bmp.modInProgress.Items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	@GameRegistry.ObjectHolder("anothermod:compressediron")
	public static CompressedIron compressediron;
	
	@GameRegistry.ObjectHolder("anothermod:compressedgold")
	public static CompressedGold compressedgold;
	
	@GameRegistry.ObjectHolder("anothermod:compresseddiamond")
	public static CompressedDiamond compresseddiamond;
	
	@GameRegistry.ObjectHolder("anothermod:compressedemerald")
	public static CompressedEmerald compressedemerald;
	
	@GameRegistry.ObjectHolder("anothermod:hammer")
	public static Hammer hammer;
	
	
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		compressediron.initModel();
		compressedgold.initModel();
		compresseddiamond.initModel();
		compressedemerald.initModel();
		hammer.initModel();
    }
}
