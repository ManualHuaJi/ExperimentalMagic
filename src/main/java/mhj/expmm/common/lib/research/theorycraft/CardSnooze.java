package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardSnooze extends TheorycraftCard {
    int Inspiration = 0;
    boolean wake = false;
     EntityPlayer entityPlayer = null;

    @Override
    public int getInspirationCost() {
        return 0;
    }

    @SubscribeEvent
    public void wakeup(PlayerSleepInBedEvent event) {
        this.entityPlayer = event.getEntityPlayer();
        if (event.getResultStatus() == EntityPlayer.SleepResult.OK) {
            this.wake = true;
        }
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
        int a = 25;
        int tries = 0;
        while ((a > 0) && (tries < 1000)) {
            tries++;
            for (String category : data.categoryTotals.keySet()) {
                if (data.categoriesBlocked.contains(category)) {
                    if (data.categoryTotals.size() <= 1) {
                        return false;
                    }
                } else {
                    data.addTotal(category, 1);
                    a--;
                    if (a <= 0) {
                        break;
                    }
                }
            }
        }
        return this.wake && player.isPlayerFullyAsleep();
    }
}
