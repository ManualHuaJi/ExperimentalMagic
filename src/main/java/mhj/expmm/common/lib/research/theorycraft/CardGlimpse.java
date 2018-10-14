package mhj.expmm.common.lib.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import java.util.Random;

/**
 * @Author: ManualHuaJi
 */
public class CardGlimpse extends TheorycraftCard {
    ItemStack stack = ItemStack.EMPTY;
    String cat = "";
    int amt;
    ItemStack options[] = {new ItemStack(ItemsTC.visResonator), new ItemStack(ItemsTC.thaumometer), new ItemStack(Blocks.ANVIL), new ItemStack(Blocks.ACTIVATOR_RAIL), new ItemStack(Blocks.DISPENSER), new ItemStack(Blocks.DROPPER), new ItemStack(Blocks.ENCHANTING_TABLE), new ItemStack(Blocks.ENDER_CHEST), new ItemStack(Blocks.JUKEBOX), new ItemStack(Blocks.DAYLIGHT_DETECTOR), new ItemStack(Blocks.PISTON), new ItemStack(Blocks.HOPPER), new ItemStack(Blocks.STICKY_PISTON), new ItemStack(Items.MAP), new ItemStack(Items.COMPASS), new ItemStack(Items.TNT_MINECART), new ItemStack(Items.COMPARATOR), new ItemStack(Items.CLOCK), new ItemStack(ItemsTC.quicksilver), new ItemStack(ItemsTC.amber)};

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("cat", this.cat);
        nbt.setInteger("amt", this.amt);
        nbt.setTag("stack", this.stack.serializeNBT());
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        this.cat = nbt.getString("cat");
        this.amt = nbt.getInteger("amt");
        this.stack = new ItemStack(nbt.getCompoundTag("stack"));
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
        Random r = new Random(getSeed());
        this.stack = options[r.nextInt(options.length)].copy();
        return data.inspiration < 3 && this.stack != null;
    }

    @Override
    public int getInspirationCost() {

        return 0;
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{this.stack};
    }


    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addInspiration(4);
        data.addTotal(this.cat, this.amt);
        return true;
    }


}
