package com.mronlinecoder.emertech.gui;


import com.mronlinecoder.emertech.EmerTech;
import com.mronlinecoder.emertech.net.AccumulatorContainer;
import com.mronlinecoder.emertech.tile.AccumulatorTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class AccumulatorGUI extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    final int LABEL_XPOS = 66;
    final int LABEL_YPOS = 10;

    final int STATUS_XPOS = 26;
    final int STATUS_YPOS = 36;

    private static final ResourceLocation background = new ResourceLocation(EmerTech.MODID, "textures/gui/accumulator.png");

    private AccumulatorTileEntity te;

    public AccumulatorGUI(AccumulatorTileEntity tileEntity, AccumulatorContainer container) {
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

        fontRenderer.drawString("Accumulator", LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
        int energy = te.getEnergy();
        if (energy == AccumulatorTileEntity.MAX_ENERGY) {
            fontRenderer.drawString("Energy: 10 000 / 10 000", STATUS_XPOS, STATUS_YPOS, Color.darkGray.getRGB());
        } else {
            fontRenderer.drawString("Energy: "+te.getEnergy()+" / 10 000", STATUS_XPOS, STATUS_YPOS, Color.darkGray.getRGB());
        }

    }
}