package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardIgnore extends TheorycraftCard {
    String cat = "AUROMANCY";

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
        return new TextComponentTranslation("card.ignore.name", new Object[0]).getUnformattedText();

    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.ignore.text", new Object[0]).getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.categoriesBlocked.add(this.cat);
        if (data.hasTotal(this.cat)) {
            data.addTotal(this.cat, -(data.getTotal(this.cat)));
        }
        return true;

    }
}
