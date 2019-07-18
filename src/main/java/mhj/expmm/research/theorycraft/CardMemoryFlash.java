package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        return data.inspiration < ResearchTableData.getAvailableInspiration(player) / 4;
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


    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
// max research =166
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
        String[] s = (String[]) ResearchCategories.researchCategories.keySet().toArray(new String[0]);
        String cat = s[player.getRNG().nextInt(s.length)];
        int amt = tot / 10;
        Math.min(20, amt);
        data.addTotal(cat, amt);
        data.bonusDraws += 1;

        return true;
    }

}
