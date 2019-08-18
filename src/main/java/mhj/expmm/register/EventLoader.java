package mhj.expmm.register;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

/**
 * @Author: ManualHuaJi
 */
public class EventLoader {
    public static final EventBus EVENT_BUS = new EventBus();

    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(new GuiLoader());

    }


}

