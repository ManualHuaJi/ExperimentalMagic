package mhj.expmm.api;

import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.item.Item;

public interface IReference {
    Item getReference();

    void active(TileAdvancedResearchTable table);

}
