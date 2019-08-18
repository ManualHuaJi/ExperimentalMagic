package mhj.expmm.event;


import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class ResearchTableEvent extends Event {
    public final TileAdvancedResearchTable table;


    public ResearchTableEvent(TileAdvancedResearchTable table) {
        this.table = table;
    }
}
