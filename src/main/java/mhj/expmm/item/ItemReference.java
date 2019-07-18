package mhj.expmm.item;

import mhj.expmm.CreativeTabEXPMM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * @Author: ManualHuaJi
 */
public class ItemReference extends Item {
    public ItemReference() {
        super();
        this.setUnlocalizedName("reference").setRegistryName(this.getUnlocalizedName()).setHasSubtypes(true);
    }

    public String[] ReferenceTypes =
            new String[]
                    {
                            "basics", "auromancy", "alchemy", "artifice", "infusion", "golemancy", "eldritch"
                    };

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if ((tab == CreativeTabEXPMM.TAB_EXPMM) || (tab == CreativeTabs.SEARCH)) {
            if (!getHasSubtypes()) {
                super.getSubItems(tab, items);
            } else {
                for (int meta = 0; meta < ReferenceTypes.length; meta++) {
                    items.add(new ItemStack(this, 1, meta));
                }
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format(super.getUnlocalizedName() + ".%s", new Object[]{this.ReferenceTypes[stack.getMetadata()]});
    }

}
