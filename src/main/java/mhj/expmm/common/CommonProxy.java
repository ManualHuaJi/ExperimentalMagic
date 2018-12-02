package mhj.expmm.common;

import mhj.expmm.client.gui.GuiLoader;
import mhj.expmm.common.block.BlockLoader;
import mhj.expmm.common.event.EventLoader;
import mhj.expmm.common.item.ItemLoader;
import mhj.expmm.common.lib.research.ResearchLoader;
import mhj.expmm.common.tile.TileEntityLoader;
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
