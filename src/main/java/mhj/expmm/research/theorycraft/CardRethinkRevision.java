package mhj.expmm.research.theorycraft;

import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;

import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.tiles.crafting.TileResearchTable;

import java.util.ArrayList;
import java.util.Random;

public class CardRethinkRevision extends TheorycraftCard {
    private String cat;

    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        ArrayList<String> s = new ArrayList();
        for (String c : data.categoryTotals.keySet()) {
            if (!data.categoriesBlocked.contains(c)) {
                s.add(c);
            }
        }
        if (s.size() < 1) {
            return false;
        }
        Random r = new Random(getSeed());
        this.cat = ((String) s.get(r.nextInt(s.size())));
        return data.getTotal(cat) >= 10;
    }

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("cat", cat);
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        cat = nbt.getString("cat");
    }

    public int getInspirationCost() {
        return -2;
    }

    public String getLocalizedName() {
        return new TextComponentTranslation("card.rethinkr.name").getUnformattedText();
    }

    public String getLocalizedText() {
        return new TextComponentTranslation("card.rethinkr.text", cat).getUnformattedText();
    }

    public boolean activate(EntityPlayer player, ResearchTableData data) {
        if (data.table instanceof TileResearchTable)
            if ((data.table != null) && (((TileResearchTable) data.table).getStackInSlot(0) != null) &&
                    (((TileResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileResearchTable) data.table).getStackInSlot(0).getMaxDamage()) &&
                    (((TileResearchTable) data.table).getStackInSlot(1) != null)) {
                ((TileResearchTable) data.table).consumeInkFromTable();
                ((TileResearchTable) data.table).consumepaperFromTable();
                data.addTotal(cat, -10);
                return true;
            }
        if ((data.table != null) && (((TileAdvancedResearchTable) data.table).getStackInSlot(0) != null) &&
                (((TileAdvancedResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileAdvancedResearchTable) data.table).getStackInSlot(0).getMaxDamage()) &&
                (((TileAdvancedResearchTable) data.table).getStackInSlot(1) != null)) {
            ((TileAdvancedResearchTable) data.table).consumeInkFromTable();
            ((TileAdvancedResearchTable) data.table).consumepaperFromTable();
            data.addTotal(cat, -10);
            return true;
        }
        return false;
    }
}
