package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.client.gui.GuiNoteBook;
import mhj.expmm.client.gui.GuiReferenceBookShelf;
import mhj.expmm.common.container.ContainerNoteBook;
import mhj.expmm.common.container.ContainerReferenceBookshelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
                return new ContainerReferenceBookshelf();
            case 1:
                return new ContainerNoteBook();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new GuiReferenceBookShelf(new ContainerReferenceBookshelf());
            case 1:
                return new GuiNoteBook(new ContainerNoteBook());
            default:
                return null;
        }

    }
}
