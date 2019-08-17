package mhj.expmm.block;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXGeneric;
import thaumcraft.common.lib.utils.InventoryUtils;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockAdvancedResearchTable extends Block implements ITileEntityProvider {
    public BlockAdvancedResearchTable() {
        super(Material.WOOD);
        this.setCreativeTab(ExperimentalMagic.TABEXPMM).setUnlocalizedName("advancedResearchTable").setRegistryName(this.getUnlocalizedName());
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        InventoryUtils.dropItems(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;
        player.openGui(ExperimentalMagic.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }


    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        TileEntity te = world.getTileEntity(pos);
        if (rand.nextInt(5) == 0 && te != null && ((TileAdvancedResearchTable) te).data != null) {
            double xx = rand.nextGaussian() / 2.0D;
            double zz = rand.nextGaussian() / 2.0D;
            double yy = 1.5D + rand.nextFloat();
            int a = 40 + rand.nextInt(20);
            FXGeneric fb = new FXGeneric(world, pos.getX() + 0.5D + xx, pos.getY() + yy, pos.getZ() + 0.5D + zz, -xx / a, -(yy - 0.85D) / a, -zz / a);
            fb.setMaxAge(a);
            fb.setRBGColorF(0.5F + rand.nextFloat() * 0.5F, 0.5F + rand.nextFloat() * 0.5F, 0.5F + rand.nextFloat() * 0.5F);
            fb.setAlphaF(new float[]{0.0F, 0.25F, 0.5F, 0.75F, 0.0F});
            fb.setParticles(384 + rand.nextInt(16), 1, 1);
            fb.setScale(new float[]{0.8F + rand.nextFloat() * 0.3F, 0.3F});
            fb.setLayer(0);
            ParticleEngine.addEffect(world, fb);
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        try {
            return TileAdvancedResearchTable.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
