package mhj.expmm.common.item;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.item.foci.FocusEffectFluxErode;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.casters.FocusEngine;

/**
 * @Author: ManualHuaJi
 */
@Mod.EventBusSubscriber(modid = ExperimentalMagic.MODID)
public class ItemLoader {
    public static Item[] items = {};

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> itemRegister) {
        IForgeRegistry<Item> ir = itemRegister.getRegistry();
        for (final Item item : items) {
            ir.register(item);
        }
    }

    public static void init() {
        FocusEngine.registerElement(FocusEffectFluxErode.class, new ResourceLocation(ExperimentalMagic.MODID, "textures/foci/fluxreode.png"), 511256);
    }

}
