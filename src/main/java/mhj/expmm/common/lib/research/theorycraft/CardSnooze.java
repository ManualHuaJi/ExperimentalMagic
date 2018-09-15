package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import java.util.Iterator;

/**
 * @Author: ManualHuaJi
 */
public class CardSnooze extends TheorycraftCard {
    int Inspiration = 0;
    String cat = "";
    int amt;
    @Override
    public int getInspirationCost() {
        return -(Inspiration);
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        Inspiration = ResearchTableData.getAvailableInspiration(player);
        int a = 0;
        for (String category : data.categoryTotals.keySet()) {
            a += data.getTotal(category);
        }
        return a >= 20;

    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.snooze.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.snooze.text", new Object[0]).getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        if (!initialize(player, data)) {
            return false;
        }
        int a = 0;
        for (Iterator localIterator = data.categoryTotals.keySet().iterator(); localIterator.hasNext();)
        {
           String category = (String)localIterator.next();
            a += data.getTotal(category);
        }
        a = Math.min(a, 10);
        int tries = 0;
        while ((a > 0) && (tries < 1000))
        {
            tries++;
            for (String category : data.categoryTotals.keySet())
            {
                data.addTotal(category, -1);
                a--;
                if ((a <= 0) || (!data.hasTotal(category))) {
                    break;
                }
            }
        }
        data.bonusDraws += 1;
        data.addTotal("BASICS", MathHelper.getInt(player.getRNG(), 1, 20));
        return true;
    }
}
