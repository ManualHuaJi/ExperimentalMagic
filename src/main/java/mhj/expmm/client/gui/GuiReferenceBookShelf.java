package mhj.expmm.client.gui;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.container.ContainerReferenceBookshelf;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @Author: ManualHuaJi
 */
@SideOnly(Side.CLIENT)
public class GuiReferenceBookShelf extends GuiContainer {
    public GuiReferenceBookShelf(ContainerReferenceBookshelf inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    private ResourceLocation texture1 = new ResourceLocation(ExperimentalMagic.MODID, "textures/gui/guisbookshelf.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mc.getTextureManager().bindTexture(texture1);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
