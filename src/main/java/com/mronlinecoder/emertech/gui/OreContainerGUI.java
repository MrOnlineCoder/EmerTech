package com.mronlinecoder.emertech.gui;


import com.mronlinecoder.emertech.EmerTech;
import com.mronlinecoder.emertech.net.OreContainer;
import com.mronlinecoder.emertech.tile.OreContainerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class OreContainerGUI extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    final int LABEL_XPOS = 8;
    final int LABEL_YPOS = 8;

    private static final ResourceLocation background = new ResourceLocation(EmerTech.MODID, "textures/gui/ore_container.png");

    private OreContainerTileEntity te;

    public OreContainerGUI(OreContainerTileEntity tileEntity, OreContainer container) {
        super(container);

        te = tileEntity;

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        fontRenderer.drawString("Container", LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());

        fontRenderer.drawString("x "+te.getStorage(0), 81, 15, Color.darkGray.getRGB());
        fontRenderer.drawString("x "+te.getStorage(1), 81, 35, Color.darkGray.getRGB());
        fontRenderer.drawString("x "+te.getStorage(2), 81, 55, Color.darkGray.getRGB());
        fontRenderer.drawString("x "+te.getStorage(3), 139, 15, Color.darkGray.getRGB());
        fontRenderer.drawString("x "+te.getStorage(4), 139, 35, Color.darkGray.getRGB());
        fontRenderer.drawString("x "+te.getStorage(5), 139, 55, Color.darkGray.getRGB());
    }
}