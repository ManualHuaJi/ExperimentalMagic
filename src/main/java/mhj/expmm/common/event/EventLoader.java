package mhj.expmm.common.event;

import mhj.expmm.common.lib.research.theorycraft.CardSnooze;
import net.minecraftforge.common.MinecraftForge;

/**
 * @Author: ManualHuaJi
 */
public class EventLoader {
    public void init() {
        MinecraftForge.EVENT_BUS.register(new CardSnooze());
    }
}
