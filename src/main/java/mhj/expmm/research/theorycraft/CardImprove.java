package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardImprove extends TheorycraftCard {
    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        return true;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.improve.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.improve.text", new Object[0]).getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {

        data.addTotal("GOLEMANCY", MathHelper.getInt(player.getRNG(), 10, 15));
        data.addInspiration(2);
        return true;
    }

    @Override
    public String getResearchCategory() {
        return " GOLEMANCY";
    }
}
