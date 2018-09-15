package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardMemoryFlash extends TheorycraftCard {
    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        return super.initialize(player, data);
    }

    @Override
    public int getInspirationCost() {
        return -2;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.memoryflash.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.memoryflash.text", new Object[0]).getUnformattedText();
    }

    public boolean isInspirationExhausted() {

        return false;
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
// max research =166

        String[] s = (String[]) ResearchCategories.researchCategories.keySet().toArray(new String[0]);
        String cat = s[player.getRNG().nextInt(s.length)];
        data.addTotal(cat, 15);
        data.bonusDraws += 1;
        int tot = 0;
        IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
        for (String string : knowledge.getResearchList()) {
            if (ThaumcraftCapabilities.knowsResearchStrict(player, new String[]{string})) {
                ResearchEntry re = ResearchCategories.getResearch(string);
                if (re != null) {
                    tot++;
                }
            }
        }
        data.addInspiration((MathHelper.floor(Math.pow(tot, 10 / 3))));
        return true;
    }
}
