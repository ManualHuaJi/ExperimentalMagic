package mhj.expmm.common;

import mhj.expmm.common.event.EventLoader;
import mhj.expmm.common.tile.TileEntityLoader;
import mhj.expmm.register.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @Author: ManualHuaJi
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        TileEntityLoader.registerTileEntities();
        BlockLoader.regitser();
        BiomeLoader.register();
    }


    public void Init(FMLInitializationEvent event) {
        ItemLoader.registerFocus();
        new ResearchLoader();
        new GuiLoader();
        new EventLoader();


    }


    public void postInit(FMLPostInitializationEvent event) {

    }
}
