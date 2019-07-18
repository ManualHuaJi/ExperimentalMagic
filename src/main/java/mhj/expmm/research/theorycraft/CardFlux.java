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
    Aspect aspect = Aspect.FLUX;
    ItemStack stack = ItemStack.EMPTY;
    ItemStack options[] = new ItemStack[]{new ItemStack(ThaumcraftApiHelper.makeCrystal(this.aspect).getItem())
    };

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("aspect", this.aspect.getTag());

        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        this.aspect = Aspect.getAspect(nbt.getString("aspect"));

    }

    @Override
    public int getInspirationCost() {
        return 1;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        Random r = new Random(getSeed());
        this.stack = options[r.nextInt(options.length)].copy();
        return (this.stack != null);
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{
                ItemPhial.makeFilledPhial(Aspect.FLUX), ThaumcraftApiHelper.makeCrystal(Aspect.FLUX)
        };
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[]{true};
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.flux.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.flux.text", new Object[0]).getUnformattedText();
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
