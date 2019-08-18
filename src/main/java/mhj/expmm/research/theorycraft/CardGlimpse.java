package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

import java.util.Random;

/**
 * @Author: ManualHuaJi
 */
public class CardGlimpse extends TheorycraftCard {
    private ItemStack stack = ItemStack.EMPTY;
    private int amt;
    private ItemStack[] options = {new ItemStack(ItemsTC.visResonator), new ItemStack(ItemsTC.thaumometer), new ItemStack(ItemsTC.quicksilver), new ItemStack(ItemsTC.amber)};

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setInteger("amt", this.amt);
        nbt.setTag("stack", this.stack.serializeNBT());
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        this.amt = nbt.getInteger("amt");
        this.stack = new ItemStack(nbt.getCompoundTag("stack"));
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.glimpse.name").getUnformattedText();
    }


    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.glimpse.text", amt).getUnformattedText();
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        if (data.categoryTotals.size() < 1) {
            return false;
        }
        this.amt = (int) (5 + Math.log(ThaumcraftCraftingManager.getObjectTags(stack).visSize()));
        Random r = new Random(getSeed());
        this.stack = options[r.nextInt(options.length)].copy();
        return data.inspiration < 3;
    }

    @Override
    public int getInspirationCost() {

        return 0;
    }

    @Override
    public String getResearchCategory() {
        return "INFUSION";
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{this.stack};
    }


    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addInspiration(4);
        data.addTotal("INFUSION", this.amt);
        return true;
    }


}
