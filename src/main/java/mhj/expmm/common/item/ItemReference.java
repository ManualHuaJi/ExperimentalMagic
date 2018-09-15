package mhj.expmm.common.item;

import mhj.expmm.common.CreativeTabEXPMM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * @Author: ManualHuaJi
 */
public class ItemReference extends ItemEXPMM {
    public ItemReference() {
        super("reference");
        this.setHasSubtypes(true);
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
