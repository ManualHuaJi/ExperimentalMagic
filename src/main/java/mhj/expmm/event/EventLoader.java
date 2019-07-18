package mhj.expmm.event;

import mhj.expmm.gui.GuiResearchBrowserRevision;
import mhj.expmm.register.GuiLoader;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.client.gui.GuiResearchBrowser;

/**
 * @Author: ManualHuaJi
 */
public class EventLoader {
    Class[] classes = {this.getClass()};

    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GuiLoader());
//        MinecraftForge.EVENT_BUS.register(new BiomeFurthestTower());
    }

    @SubscribeEvent
    public void ChangeGuiResearchBrowser(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiResearchBrowser) {
            event.setGui(new GuiResearchBrowserRevision());
        }
    }

}

