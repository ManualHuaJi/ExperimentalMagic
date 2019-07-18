package mhj.expmm.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class BlockEXPMMTile extends BlockEXPMM implements ITileEntityProvider {
    public BlockEXPMMTile(Material material, Class tileClass, String name) {
        super(material, name);
        this.tileClass = tileClass;
    }

    protected Class tileClass = null;

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        if (this.tileClass == null) {
            return null;
        }
        try {
            return (TileEntity) this.tileClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

