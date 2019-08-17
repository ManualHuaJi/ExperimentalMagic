package mhj.expmm.api;

import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.item.Item;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public interface IReference {
    Item getReference();

    void active(TileAdvancedResearchTable table);

    Class<TheorycraftCard>[] getCards();
}
