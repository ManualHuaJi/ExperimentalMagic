package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.items.consumables.ItemPhial;

import java.util.Random;


/**
 * @Author: ManualHuaJi
 */
public class CardFlux extends TheorycraftCard {
    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public ItemStack[] getRequiredItems() {
        Random r = new Random();
        boolean a = r.nextBoolean();
        if (a) {
            return new ItemStack[]{ThaumcraftApiHelper.makeCrystal(Aspect.FLUX)};
        } else {
            return new ItemStack[]{ItemPhial.makeFilledPhial(Aspect.FLUX)};
        }
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[]{true};
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.flux.name").getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.flux.text").getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addTotal("AUROMANCY", 25);
        return true;
    }

    @Override
    public String getResearchCategory() {
        return "AUORMANCY";
    }
}
