package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardGlimpse extends TheorycraftCard {
    String cat = "";
    int amt;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("cat", this.cat);
        nbt.setInteger("amt", this.amt);
        return nbt;
    }

    @Override
    public boolean isAidOnly() {
        return false;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        this.cat = nbt.getString("cat");
        this.amt = nbt.getInteger("amt");
        super.deserialize(nbt);
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.glimpse.name", new Object[0]).getUnformattedText();
    }


    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.glimpse.text", new Object[]{Integer.valueOf(this.amt), TextFormatting.BOLD + new TextComponentTranslation(new StringBuilder().append("tc.research_category.").append(this.cat).toString(), new Object[0]).getFormattedText() + TextFormatting.RESET}).getUnformattedText();
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        if (data.categoryTotals.size() < 1) {
            return false;
        }
        int hVal = 0;
        String hKey = "";
        for (String category : data.categoryTotals.keySet()) {
            int q = data.getTotal(category);
            if (q > hVal) {
                hVal = q;
                hKey = category;
            }
        }
        this.cat = hKey;
        this.amt = (10 + hVal / 2);
        return true;
    }

    @Override
    public int getInspirationCost() {

        return 0;
    }


    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addTotal(this.cat, this.amt);
        data.addInspiration(MathHelper.floor(1 + Math.random() * 5));
        return true;
    }


}
