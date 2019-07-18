package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static mhj.expmm.item.ItemsEXPMM.itemReference;

/**
 * @Author: ManualHuaJi
 */
@Mod.EventBusSubscriber(modid = ExperimentalMagic.MODID)
public class ItemLoader {
    public static Item[] items = {
            itemReference
    };


    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> itemRegister) {
        IForgeRegistry<Item> ir = itemRegister.getRegistry();
//        ir.register(itemWand = new ItemWand().setCreativeTab(CreativeTabEXPMM.TAB_EXPMM));
        for (final Item item : items) {
            ir.register(item);
        }
    }




}
