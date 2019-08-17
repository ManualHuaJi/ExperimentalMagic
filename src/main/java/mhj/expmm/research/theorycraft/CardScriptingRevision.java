package mhj.expmm.research.theorycraft;

import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.common.lib.research.theorycraft.CardScripting;
import thaumcraft.common.tiles.crafting.TileResearchTable;


public class CardScriptingRevision extends CardScripting {
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        if (data.table != null && ((TileResearchTable) data.table).getStackInSlot(0) != null && ((TileResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileResearchTable) data.table).getStackInSlot(0).getMaxDamage() && ((TileResearchTable) data.table).getStackInSlot(1) != null) {
            ((TileResearchTable) data.table).consumeInkFromTable();
            ((TileResearchTable) data.table).consumepaperFromTable();
            data.addTotal(this.getResearchCategory(), 25);
            return true;
        }
        if (data.table != null && ((TileAdvancedResearchTable) data.table).getStackInSlot(0) != null && ((TileAdvancedResearchTable) data.table).getStackInSlot(0).getItemDamage() < ((TileAdvancedResearchTable) data.table).getStackInSlot(0).getMaxDamage() && ((TileAdvancedResearchTable) data.table).getStackInSlot(1) != null) {
            ((TileAdvancedResearchTable) data.table).consumeInkFromTable();
            ((TileAdvancedResearchTable) data.table).consumepaperFromTable();
            data.addTotal(this.getResearchCategory(), 25);
            return true;
        }
        return false;

    }
}
