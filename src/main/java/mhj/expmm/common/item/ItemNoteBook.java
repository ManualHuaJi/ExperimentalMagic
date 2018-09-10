package mhj.expmm.common.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import thaumcraft.common.items.curios.ItemCelestialNotes;

/**
 * @Author: ManualHuaJi
 */

@Mod.EventBusSubscriber
public class ItemNoteBook extends ItemEXPMM {
    public ItemNoteBook() {
        this.setNoRepair();
    }

    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent events) {
        if (events.crafting.getItem() == ItemsEXPMM.itemNoteBook) {
            for (int var1 = 0; var1 < 9; var1++) {
                ItemStack var2 = events.craftMatrix.getStackInSlot(var1);
                if (!var2.isEmpty() && var2.getItem() instanceof ItemCelestialNotes) {

                }
            }
        }
    }
}
