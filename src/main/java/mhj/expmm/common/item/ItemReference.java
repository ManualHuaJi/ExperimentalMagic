package mhj.expmm.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * @Author: ManualHuaJi
 */
public class ItemReference extends ItemEXPMM {
    public ItemReference() {
        this.setHasSubtypes(true);
    }

    public enum ReferenceTypes {
        base, auromancy, alchemy, artifice, infusion, golemancy, eldritch
    }


    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < ReferenceTypes.values().length; i++) {
            items.add(new ItemStack(this, 1, i));
        }

    }
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack)+ "." + ReferenceTypes.values()[stack.getMetadata()];
    }
}
