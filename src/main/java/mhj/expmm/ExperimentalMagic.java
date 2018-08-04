package mhj.expmm;

import mhj.expmm.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static mhj.expmm.ExperimentalMagic.*;

/**
 * @Author: ManualHuaJi
 */
@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = "required-after:thaumcraft@[6.1.BETA19,);", acceptedMinecraftVersions = "1.12.2")
public class ExperimentalMagic {
    @SidedProxy(clientSide = "mhj.expmm.client.ClinetProxy", serverSide = "mhj.expmm.client.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "expmm", NAME = "ExperimentalMagic", VERSION = "0.0.1";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.Init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
