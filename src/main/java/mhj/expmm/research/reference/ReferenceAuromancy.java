package mhj.expmm.research.reference;

import mhj.expmm.api.IReference;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.item.Item;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class ReferenceAuromancy implements IReference {
    @Override
    public Item getReference() {
        return null;
    }

    @Override
    public void active(TileAdvancedResearchTable table) {

    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        return new Class[0];
    }
}
