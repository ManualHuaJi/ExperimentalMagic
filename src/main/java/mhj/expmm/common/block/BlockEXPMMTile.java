package mhj.expmm.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class BlockEXPMMTile extends BlockEXPMM implements ITileEntityProvider {
    public BlockEXPMMTile(Material material) {
        super(material);

    }

    protected final Class tileClass = null;

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
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
