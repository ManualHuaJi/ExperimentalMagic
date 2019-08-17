package mhj.expmm.research.reference;

import mhj.expmm.api.IReference;
import mhj.expmm.item.ItemsExpmm;
import mhj.expmm.research.theorycraft.CardRethinkRevision;
import mhj.expmm.research.theorycraft.CardScriptingRevision;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.research.theorycraft.CardRethink;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.api.research.theorycraft.TheorycraftManager;

public class ReferenceBasic implements IReference {
    @Override
    public Item getReference() {
        return new ItemStack(ItemsExpmm.itemReference, 1, 0).getItem();
    }

    @Override
    public void active(TileAdvancedResearchTable table) {
        TheorycraftManager.cards.remove(CardRethink.class);
    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        return new Class[]{CardRethinkRevision.class};
    }
}
