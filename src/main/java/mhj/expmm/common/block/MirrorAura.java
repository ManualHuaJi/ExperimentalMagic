package mhj.expmm.common.block;

import mhj.expmm.common.tile.TileMirrorAura;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.BlockStateUtils;
import thaumcraft.common.tiles.devices.TileMirror;
import thaumcraft.common.tiles.devices.TileMirrorEssentia;

/**
 * @Author: ManualHuaJi
 */
public class MirrorAura extends BlockEXPMMTile {
    public MirrorAura() {
        super(Material.IRON, TileMirrorAura.class, "mirroraura");
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
        EnumFacing d = BlockStateUtils.getFacing(state);
        if (!worldIn.getBlockState(pos.offset(d.getOpposite())).isSideSolid(worldIn, pos.offset(d.getOpposite()), d)) {
            dropBlockAsItem(worldIn, pos, getDefaultState(), 0);
            worldIn.setBlockToAir(pos);
        }
    }
    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (((te instanceof TileMirror)) || ((te instanceof TileMirrorEssentia))) {
            dropMirror(worldIn, pos, state, te);
        } else {
            super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
        }
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        if (te instanceof TileMirrorAura) {
            dropMirror(worldIn, pos, state, te);
        } else {
            super.harvestBlock(worldIn, player, pos, state, (TileEntity) null, stack);
        }
    }

    public void dropMirror(World world, BlockPos pos, IBlockState state, TileEntity te) {
        if (this.tileClass == TileMirrorAura.class) {
            TileMirrorAura tm = (TileMirrorAura) te;
            ItemStack drop = new ItemStack(this, 1, 0);
            if ((tm != null) && ((tm instanceof TileMirrorAura))) {
                if (tm.linked) {
                    drop.setItemDamage(1);
                    drop.setTagInfo("linkX", new NBTTagInt(tm.linkX));
                    drop.setTagInfo("linkY", new NBTTagInt(tm.linkY));
                    drop.setTagInfo("linkZ", new NBTTagInt(tm.linkZ));
                    drop.setTagInfo("linkDim", new NBTTagInt(tm.linkDim));
                    tm.invalidateLink();
                }
                spawnAsEntity(world, pos, drop);
            }
        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing facing = BlockStateUtils.getFacing(state);
        switch (facing.ordinal()) {
            default:
                return new AxisAlignedBB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
            case 1:
                return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
            case 2:
                return new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
            case 3:
                return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
            case 4:
                return new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return true;
    }
}
