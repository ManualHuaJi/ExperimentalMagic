package mhj.expmm.research.theorycraft;

import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.tiles.crafting.TileResearchTable;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: ManualHuaJi
 */
public class CardExperience extends TheorycraftCard {
    private int amt;
    private String cat;

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setInteger("amt", amt);
        nbt.setString("cat", cat);
        return nbt;
    }

    @Override
    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        amt = nbt.getInteger("amt");
        cat = nbt.getString("cat");
    }

    @Override
    public int getInspirationCost() {
        return 3;
    }

    @Override
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
        amt = MathHelper.getInt(player.getRNG(), 5, 10);
        return data.categoryTotals.size() > 2 && cat != null;
    }

    @Override
    public String getLocalizedName() {
        return new TextComponentTranslation("card.experience.name").getUnformattedText();
    }

    @Override
    public String getLocalizedText() {
        return new TextComponentTranslation("card.experience.text", cat, amt).
                getUnformattedText();
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        data.addTotal(cat, -data.getTotal(cat) / 2);
        for (String category : data.categoryTotals.keySet()) {
            if (!data.categoriesBlocked.contains(category) && !category.equals(cat))
                data.addTotal(category, amt);
        }
        for (int i = 0; i < 4; i++) {
            if (data.table instanceof TileResearchTable)
                if ((data.table != null) && (((TileResearchTable) data.table).getStackInSlot(0) != null) &&
                        (((TileResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileResearchTable) data.table).getStackInSlot(0).getMaxDamage()) &&
                        (((TileResearchTable) data.table).getStackInSlot(1) != null)) {
                    ((TileResearchTable) data.table).consumeInkFromTable();
                    ((TileResearchTable) data.table).consumepaperFromTable();
                }
            for (int a = 0; a < 4; a++) {
                if ((data.table != null) && (((TileAdvancedResearchTable) data.table).getStackInSlot(0) != null) &&
                        (((TileAdvancedResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileAdvancedResearchTable) data.table).getStackInSlot(0).getMaxDamage()) &&
                        (((TileAdvancedResearchTable) data.table).getStackInSlot(1) != null)) {
                    ((TileAdvancedResearchTable) data.table).consumeInkFromTable();
                    ((TileAdvancedResearchTable) data.table).consumepaperFromTable();
                }
            }
        }
        return true;
    }
}
