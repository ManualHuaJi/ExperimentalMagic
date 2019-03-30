package mhj.expmm.common.event;

import mhj.expmm.client.gui.GuiResearchBrowserR;
import mhj.expmm.common.lib.research.theorycraft.CardSnooze;
import mhj.expmm.common.world.biome.BiomeFurthestTower;
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
        MinecraftForge.EVENT_BUS.register(new CardSnooze());
        MinecraftForge.EVENT_BUS.register(new GuiLoader());
        MinecraftForge.EVENT_BUS.register(new BiomeFurthestTower());
    }

    @SubscribeEvent
    public void ChangeGuiResearchBrowser(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiResearchBrowser) {
            event.setGui(new GuiResearchBrowserR());
        }
    }

}

