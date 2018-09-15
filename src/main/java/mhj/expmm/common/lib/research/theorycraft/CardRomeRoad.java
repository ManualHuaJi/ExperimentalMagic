package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.tiles.crafting.TileResearchTable;

/**
 * @Author: ManualHuaJi
 */
public class CardRomeRoad extends TheorycraftCard {
    String cat = "ALCHEMY";
    String cat1 = "";
    int amt = 0;

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        if (data.categoryTotals.size() < 1) {
            return false;
        }
        int hVal = 0;
        String hKey = "";
        for (String category : data.categoryTotals.keySet()) {
            int q = data.getTotal(category);
            if (q > hVal) {
                hVal = q;
                hKey = category;
            }
        }
        this.cat1 = hKey;
        this.amt = (10 + hVal / 2);
        return true;
    }

    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public boolean isAidOnly() {
        return true;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.romeroad.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.romeroad.text", new Object[0]).getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        if ((data.table != null) && (((TileResearchTable) data.table).getStackInSlot(0) != null) &&
                (((TileResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileResearchTable) data.table).getStackInSlot(0).getMaxDamage()) &&
                (((TileResearchTable) data.table).getStackInSlot(1) != null)) {
            ((TileResearchTable) data.table).consumeInkFromTable();
            ((TileResearchTable) data.table).consumepaperFromTable();
            data.addTotal(this.cat, this.amt);
            data.addTotal(this.cat1, 5);

        }
        return true;
    }
}
