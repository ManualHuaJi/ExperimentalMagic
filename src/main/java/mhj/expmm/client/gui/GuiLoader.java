package mhj.expmm.client.gui;

import mhj.expmm.ExperimentalMagic;
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

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                new GuiReferenceBookShelf(new ContainerReferenceBookshelf());
            default:
                return null;
        }

    }
}
