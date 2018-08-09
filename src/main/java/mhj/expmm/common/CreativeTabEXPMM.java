package mhj.expmm.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * @Author: ManualHuaJi
 */
public class CreativeTabEXPMM extends CreativeTabs {
    public static final CreativeTabEXPMM TAB_EXPMM = new CreativeTabEXPMM("TabEXPMM");

    public CreativeTabEXPMM(String label) {
        super("TabEXPMM");
    }

    @Override
    @Nonnull
    public ItemStack getIconItemStack() {
        return new ItemStack(Items.BOOK);
    }

    @Override
    public ItemStack getTabIconItem() {
        return this.getIconItemStack();
    }

}

