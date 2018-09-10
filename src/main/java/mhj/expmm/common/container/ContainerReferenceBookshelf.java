package mhj.expmm.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * @Author: ManualHuaJi
 */
public class ContainerReferenceBookshelf extends Container {
    public ContainerReferenceBookshelf() {
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
