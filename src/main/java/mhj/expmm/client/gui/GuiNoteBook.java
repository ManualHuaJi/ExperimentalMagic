package mhj.expmm.client.gui;

import mhj.expmm.common.container.ContainerNoteBook;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * @Author: ManualHuaJi
 */
public class GuiNoteBook extends GuiContainer {
    public GuiNoteBook(ContainerNoteBook inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
