package mhj.expmm.research.theorycraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.tiles.crafting.TileResearchTable;

/**
 * @Author: ManualHuaJi
 */
public class CardExperience extends TheorycraftCard {
    int amt;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setInteger("amt", amt);
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        amt = nbt.getInteger("amt");
    }

    @Override
    public int getInspirationCost() {
        return 2;
    }

    @Override
    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        amt = MathHelper.getInt(player.getRNG(), 5, 10);
        return data.categoryTotals.size() > 2;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.experience.name", new Object[0]).getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.experience.text", new Object[]{Integer.valueOf(amt)}).
                getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        for (String category : data.categoryTotals.keySet()) {
            if (!data.categoriesBlocked.contains(category))
                data.addTotal(category, amt);
        }
        for (int i = 0; i < 4; i++) {
            if ((data.table != null) && (((TileResearchTable) data.table).getStackInSlot(0) != null) &&
                    (((TileResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileResearchTable) data.table).getStackInSlot(0).getMaxDamage()) &&
                    (((TileResearchTable) data.table).getStackInSlot(1) != null)) {
                ((TileResearchTable) data.table).consumeInkFromTable();
                ((TileResearchTable) data.table).consumepaperFromTable();
            }
        }
        return true;
    }
}
