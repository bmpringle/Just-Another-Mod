package com.bmp.modInProgress.Blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	@GameRegistry.ObjectHolder("anothermod:oregenerator")
	public static OreGenerator oreGenerator;
	
	@GameRegistry.ObjectHolder("anothermod:creativegenerator")
	public static CreativeGenerator creativeGenerator;
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        oreGenerator.initModel();    
        creativeGenerator.initModel();
    }
}
