package com.bmp.modInProgress.Proxies;

import com.bmp.modInProgress.Blocks.ModBlocks;
import com.bmp.modInProgress.Items.ModItems;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	@Override 
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModBlocks.initModels();
		ModItems.initModels();
	}
	
}
