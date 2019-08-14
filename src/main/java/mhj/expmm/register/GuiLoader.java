package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.gui.GuiAdvResearchTable;
import mhj.expmm.gui.GuiResearchBrowserRevision;
import mhj.expmm.gui.container.ContainerAdvResearchTable;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.client.gui.GuiResearchBrowser;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class GuiLoader implements IGuiHandler {


    public GuiLoader() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ExperimentalMagic.instance, this);
    }


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new ContainerAdvResearchTable(player.inventory, (TileAdvancedResearchTable) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch (ID) {
            case 0:
                return new GuiAdvResearchTable(player, (TileAdvancedResearchTable) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }

    }

    @SubscribeEvent
    public void ChangeGuiResearchBrowser(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiResearchBrowser) {
            event.setGui(new GuiResearchBrowserRevision());
        }
    }
}
