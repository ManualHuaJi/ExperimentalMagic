package mhj.expmm.common;

import mhj.expmm.common.item.ItemLoader;
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
        ItemLoader.init();


    }


    public void postInit(FMLPostInitializationEvent event) {

    }
}
