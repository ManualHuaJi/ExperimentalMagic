package mhj.expmm;

import mhj.expmm.event.EventLoader;
import mhj.expmm.tile.TileEntityLoader;
import mhj.expmm.register.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @Author: ManualHuaJi
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        TileEntityLoader.registerTileEntity();
        BlockLoader.regitser();

    }


    public void Init(FMLInitializationEvent event) {

        new ResearchLoader();
        new GuiLoader();
        new EventLoader();


    }


    public void postInit(FMLPostInitializationEvent event) {

    }
}
