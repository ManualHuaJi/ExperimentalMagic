package mhj.expmm;

import mhj.expmm.common.CommonProxy;
import mhj.expmm.common.command.CommandEXPMM;
import mhj.expmm.common.lib.research.ResearchLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;

import static mhj.expmm.ExperimentalMagic.*;

/**
 * @Author: ManualHuaJi
 */
@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = "required-after:thaumcraft@[6.1.BETA20,);", acceptedMinecraftVersions = "1.12.2")
public class ExperimentalMagic {
    @SidedProxy(clientSide = "mhj.expmm.client.ClientProxy", serverSide = "mhj.expmm.common.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "expmm", NAME = "ExperimentalMagic", VERSION = "0.0.1";
    public static ResearchCategory EXPMM;

    @Mod.Instance(MODID)
    public static ExperimentalMagic instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ResearchLoader());
        ResearchLoader.clInit.call();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        EXPMM = ResearchCategories.registerCategory("EXPMM", (String) null, (AspectList) null, new ResourceLocation("thaumcraft", "textures/items/thaumonomicon_cheat.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"), null);
        ResearchLoader.$init();
        proxy.Init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        ResearchLoader.init.call();
    }
    @Mod.EventHandler
    public void severStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandEXPMM());
    }
}
