package mhj.expmm.common.block;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.tile.TileReferenceCabinet;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class ReferenceCabinet extends BlockEXPMMTile implements ITheorycraftAid, ITileEntityProvider {
    public ReferenceCabinet(Material material) {
        super(Material.WOOD,"referencecabinet");
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public Object getAidObject() {
        return BlocksEXPMM.referenceCabinet;

    }

    @Override
    public Class<TheorycraftCard>[] getCards() {
        return new Class[0];
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileReferenceCabinet();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(ExperimentalMagic.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
