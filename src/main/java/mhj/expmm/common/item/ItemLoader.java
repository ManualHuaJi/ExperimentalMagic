package mhj.expmm.common.item;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.item.foci.FocusEffectFluxErode;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.casters.FocusEngine;

/**
 * @Author: ManualHuaJi
 */
public class ItemLoader {
    public static void pre(IForgeRegistry<Item> iForgeRegistry) {

    }

    public static void init() {
        FocusEngine.registerElement(FocusEffectFluxErode.class, new ResourceLocation(ExperimentalMagic.MODID, "textures/foci/fluxreode.png"), 511256);
    }

    public static void post() {
    }
}
