package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardIdealExpm extends TheorycraftCard {
    @Override
    public int getInspirationCost() {
        return 2;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        return true;
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
        ResearchCategory rc = ResearchCategories.getResearchCategory("ALCHEMY");
        int k = ThaumcraftCapabilities.getKnowledge(player).getKnowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc);
        if (k >= 1) {
            ThaumcraftCapabilities.getKnowledge(player).addKnowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc,
                    -IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression());
            data.addTotal("ALCHEMY", MathHelper.getInt(player.getRNG(), 25, 30));
            return true;
        }
        return true;
    }

}