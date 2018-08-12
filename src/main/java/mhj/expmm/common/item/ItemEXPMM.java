package mhj.expmm.common.item;

import mhj.expmm.common.CreativeTabEXPMM;
import net.minecraft.item.Item;

/**
 * @Author: ManualHuaJi
 */
public class ItemEXPMM extends Item {
    public ItemEXPMM() {

        this.setUnlocalizedName(this.getClass().getName().toLowerCase()).setRegistryName(this.getUnlocalizedName()).setCreativeTab(CreativeTabEXPMM.TAB_EXPMM);
    }
}
