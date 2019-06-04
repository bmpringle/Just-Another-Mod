package com.bmp.modInProgress.Blocks;

import com.bmp.modInProgress.ModInProgress;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class OreGeneratorGui extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;
    private OreGeneratorTileEntity te;
    private static final ResourceLocation background = new ResourceLocation(ModInProgress.MODID, "textures/gui/oregeneratorgui.png");
    
    public OreGeneratorGui(OreGeneratorTileEntity tileEntity, OreGeneratorContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
        te = tileEntity;
    }

    @Override 
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) { 
        mc.getTextureManager().bindTexture(background);       
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize); 
        GlStateManager.color(1f, 1f, 1f);
        int progressLevelArrow = getProgressLevelArrrow(24);
        drawTexturedModalRect(guiLeft+77, guiTop+30, 180, 0, progressLevelArrow, 17);
        int progressLevelEnergyBar = getProgressLevelEnergyBar(51);
        drawTexturedModalRect(guiLeft+6, guiTop+59-progressLevelEnergyBar, 180, 17, 20, progressLevelEnergyBar);
        fontRenderer.drawString("Inventory", guiLeft+WIDTH-60, guiTop+57, 0);
        fontRenderer.drawString("Ore Generator", (guiLeft+WIDTH/2)-37, guiTop+15, 0);
        
        //Good Ole' Fashioned Unreadable Collision Code!
    	if(mouseX >= guiLeft+6 && mouseX < guiLeft+26) {
    		if(mouseY >= guiTop+8 && mouseY < guiTop+59){
    			drawHoveringText("Energy: " + te.energy, mouseX, mouseY);
    		}	
    	}
    }
    
    private int getProgressLevelEnergyBar(int progressIndicatorPixelHeight)
    {
        return (te.energy*progressIndicatorPixelHeight)/te.maxenergy;
    }
    
    private int getProgressLevelArrrow(int progressIndicatorPixelWidth)
    {
        int stepssofar = (289-te.stepstillcreation); 
        int stepsperitem = 288;
        if(te.itemStackHandler.getStackInSlot(1).getCount() == 64) {
        	return -1;
        }
        return (stepssofar*progressIndicatorPixelWidth)/stepsperitem;
    }
}