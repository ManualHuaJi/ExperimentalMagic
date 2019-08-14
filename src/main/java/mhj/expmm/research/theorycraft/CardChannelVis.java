package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

/**
 * @Author: ManualHuaJi
 */
public class CardChannelVis extends TheorycraftCard {
    private int amt;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();

        nbt.setInteger("amt", this.amt);
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);

        this.amt = nbt.getInteger("amt");
    }

    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        return true;
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{new ItemStack(ItemsTC.casterBasic)};
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.channelvis.name").getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.channelvis.text", this.amt).getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        amt = MathHelper.getInt(player.getRNG(), 0, 25);
        data.addTotal("AUROMANCY", amt);
        return true;
    }

    @Override
    public String getResearchCategory() {
        return "AUORMANCY";
    }
}
