package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.item.ItemsExpmm;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ExperimentalMagic.MODID)
public class ItemLoader {
    public static Item[] items = {ItemsExpmm.itemReference};

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        init(event.getRegistry());
    }

    public static void init(IForgeRegistry<Item> iForgeRegistry) {
        for (Item item : items) {
            iForgeRegistry.register(item);
        }
    }
}
