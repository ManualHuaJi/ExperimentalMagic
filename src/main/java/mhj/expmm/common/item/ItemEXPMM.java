package mhj.expmm.common.item;

import mhj.expmm.common.CreativeTabEXPMM;
import net.minecraft.item.Item;

/**
 * @Author: ManualHuaJi
 */
public abstract class ItemEXPMM extends Item {
    public ItemEXPMM(String name) {
        this.setUnlocalizedName(name).setRegistryName(name).setCreativeTab(CreativeTabEXPMM.TAB_EXPMM);
    }
}
