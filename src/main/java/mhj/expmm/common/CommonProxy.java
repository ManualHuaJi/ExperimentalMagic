package mhj.expmm.common;

import mhj.expmm.common.item.foci.FocusEffectFluxErode;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @Author: ManualHuaJi
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {

    }


    public void Init(FMLInitializationEvent event) {
        new FocusEffectFluxErode();
    }


    public void postInit(FMLPostInitializationEvent event) {
    }
}
