package mhj.expmm.item;

import mhj.expmm.ExperimentalMagic;
import net.minecraft.item.Item;

public class ItemReference extends Item {
    public ItemReference() {
        this.setCreativeTab(ExperimentalMagic.TABEXPMM).setUnlocalizedName("itemReference").setRegistryName(this.getUnlocalizedName()).setHasSubtypes(true);
    }
}
