package com.mronlinecoder.emertech.gui;

import com.mronlinecoder.emertech.EmerTech;
import com.mronlinecoder.emertech.net.TransformerContainer;
import com.mronlinecoder.emertech.tile.TransformerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class TransformerGUI extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    final int LABEL_XPOS = 10;
    final int LABEL_YPOS = 10;

    final int BAR_XPOS = 57;
    final int BAR_YPOS = 35;

    final int BAR_ICON_U = 176;
    final int BAR_ICON_V = 14;

    final int BAR_WIDTH = 23;
    final int BAR_HEIGHT = 16;

    private static final ResourceLocation background = new ResourceLocation(EmerTech.MODID, "textures/gui/transformer.png");

    private TransformerTileEntity te;

    public TransformerGUI(TransformerTileEntity tileEntity, TransformerContainer container) {
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

        double progress = te.getFractionProgress();

        drawTexturedModalRect(guiLeft + BAR_XPOS, guiTop + BAR_YPOS, BAR_ICON_U, BAR_ICON_V,
                (int)(progress * BAR_WIDTH), BAR_HEIGHT);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        fontRenderer.drawString("Emerald Transformer", LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());


    }
}