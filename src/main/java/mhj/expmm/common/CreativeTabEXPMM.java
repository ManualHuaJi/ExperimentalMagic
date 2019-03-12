package mhj.expmm.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @Author: ManualHuaJi
 */
public class CreativeTabEXPMM extends CreativeTabs {
    public static final CreativeTabEXPMM TAB_EXPMM = new CreativeTabEXPMM("TabEXPMM");

    public CreativeTabEXPMM(String label) {
        super("TabEXPMM");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.BOOK);
    }

}

