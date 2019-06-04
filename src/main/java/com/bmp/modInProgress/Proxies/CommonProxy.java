package com.bmp.modInProgress.Proxies;

import com.bmp.modInProgress.ModInProgress;
import com.bmp.modInProgress.Blocks.ModBlocks;
import com.bmp.modInProgress.Blocks.OreGenerator;
import com.bmp.modInProgress.Blocks.OreGeneratorTileEntity;
import com.bmp.modInProgress.Items.CompressedDiamond;
import com.bmp.modInProgress.Items.CompressedEmerald;
import com.bmp.modInProgress.Items.CompressedGold;
import com.bmp.modInProgress.Items.CompressedIron;
import com.bmp.modInProgress.Items.Hammer;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		
	}
	
	public void init(FMLInitializationEvent e) {
		 NetworkRegistry.INSTANCE.registerGuiHandler(ModInProgress.instance, new GuiProxy());
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	event.getRegistry().register(new OreGenerator());
    	GameRegistry.registerTileEntity(OreGeneratorTileEntity.class, ModInProgress.MODID + ":oregeneratorcontainerblock");
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	event.getRegistry().register(new ItemBlock(ModBlocks.oreGenerator).setRegistryName(ModBlocks.oreGenerator.getRegistryName()));
    	event.getRegistry().register(new CompressedIron());
    	event.getRegistry().register(new CompressedGold());
    	event.getRegistry().register(new CompressedDiamond());
    	event.getRegistry().register(new CompressedEmerald());
    	event.getRegistry().register(new Hammer());
    }
}
