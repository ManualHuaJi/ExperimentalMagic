package mhj.expmm.research.reference;

import mhj.expmm.api.IReference;
import mhj.expmm.item.ItemsExpmm;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReferenceEldritch implements IReference {
    @Override
    public Item getReference() {
        return new ItemStack(ItemsExpmm.itemReference, 1, 5).getItem();
    }

    @Override
    public void active(TileAdvancedResearchTable table) {

    }

}
