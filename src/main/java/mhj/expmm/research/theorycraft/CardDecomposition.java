package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import java.util.Random;

/**
 * @Author: ManualHuaJi
 */
public class CardDecomposition extends TheorycraftCard {
    int amt;
    Aspect aspect1;
    Aspect aspect2;
    Aspect aspect3;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("aspect1", aspect1.getTag());
        nbt.setString("aspect2", aspect2.getTag());
        nbt.setString("aspect3", aspect3.getTag());
        nbt.setInteger("amt", amt);
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        aspect1 = Aspect.getAspect(nbt.getString("aspect1"));
        aspect2 = Aspect.getAspect(nbt.getString("aspect2"));
        amt = nbt.getInteger("amt");
    }

    @Override
    public int getInspirationCost() {
        return 2;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {

        Random r = new Random(getSeed());
        int num = r.nextInt(Aspect.getCompoundAspects().size());
        aspect3 = Aspect.getCompoundAspects().get(num);
        aspect1 = aspect3.getComponents()[0];
        aspect2 = aspect3.getComponents()[1];
        return aspect1 != null && aspect2 != null && aspect1 != aspect2;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.decomposition.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.decomposition.text", new Object[]{TextFormatting.BOLD + aspect3.getName() + TextFormatting.RESET, Integer.valueOf(amt)}).
                getUnformattedText();
    }

    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{ThaumcraftApiHelper.makeCrystal(aspect3)};

    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[]{true};
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        amt = MathHelper.getInt(player.getRNG(), 5, 15);
        data.addTotal("ALCHEMY", amt);
        ItemStack res = ThaumcraftApiHelper.makeCrystal(aspect1);
        ItemStack res1 = ThaumcraftApiHelper.makeCrystal(aspect2);
        if (!player.inventory.addItemStackToInventory(res) && !player.inventory.addItemStackToInventory(res1)) {
            player.dropItem(res, true);
            player.dropItem(res1, true);
        }
        return true;
    }

    @Override
    public String getResearchCategory() {
        return "ALCHEMY";
    }
}