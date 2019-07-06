package com.bmp.modInProgress;

import javax.swing.JFrame;

import org.apache.logging.log4j.Logger;

import com.bmp.modInProgress.Proxies.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInProgress.MODID, name = ModInProgress.MODNAME, version = ModInProgress.MODVERSION)
public class ModInProgress {

    public static final String MODID = "anothermod";
    public static final String MODNAME = "Example Mod";
    public static final String MODVERSION= "0.0.1";

    @SidedProxy(clientSide = "com.bmp.modInProgress.Proxies.ClientProxy", serverSide = "com.bmp.modInProgress.Proxies.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static ModInProgress instance;

    public static Logger logger;
    
    public static boolean ifDev = true;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}