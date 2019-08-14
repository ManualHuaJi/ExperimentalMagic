package mhj.expmm.register;

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

    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(new GuiLoader());
    }



}

