package mhj.expmm.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class TileReferenceBookcase extends TileEntityEXPMMInv{
    public NonNullList<ItemStack> inventory;

    public TileReferenceBookcase(int inventorySize) {
        super(64);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return super.hasCapability(capability, facing);
    }
}
