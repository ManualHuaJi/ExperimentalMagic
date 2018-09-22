package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardIdealExpm extends TheorycraftCard {
    @Override
    public int getInspirationCost() {
        return 0;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.idealexpm.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.idealexpm.text", new Object[0]).getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        return false;
    }
}
