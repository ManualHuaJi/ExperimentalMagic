package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.items.consumables.ItemPhial;

import java.util.Random;

/**
 * @Author: ManualHuaJi
 */
public class CardInteraction extends TheorycraftCard {
    Aspect aspect1;
    Aspect aspect2;
    Aspect aspect3;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("aspect1", this.aspect1.getTag());
        nbt.setString("aspect2", this.aspect2.getTag());
        nbt.setString("aspect3", this.aspect3.getTag());
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        this.aspect1 = Aspect.getAspect(nbt.getString("aspect1"));
        this.aspect2 = Aspect.getAspect(nbt.getString("aspect2"));

    }

    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        Random r = new Random(getSeed());
        int num = r.nextInt(Aspect.getCompoundAspects().size());
        this.aspect3 = ((Aspect) Aspect.getCompoundAspects().get(num));
        this.aspect1 = this.aspect3.getComponents()[0];
        this.aspect2 = this.aspect3.getComponents()[1];
        return aspect1 != null && aspect2 != null && aspect1 != aspect2;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.interaction.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.interaction.text", new Object[0]).getUnformattedText();
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{ItemPhial.makeFilledPhial(this.aspect1), ItemPhial.makeFilledPhial(this.aspect2)};
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[]{true, true};
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addTotal("INFUSION", MathHelper.getInt(player.getRNG(), 10, 20));
        return true;
    }
}
