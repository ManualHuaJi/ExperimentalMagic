package mhj.expmm.common.block;

import mhj.expmm.common.tile.TileMirrorAura;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.common.lib.SoundsTC;

import java.util.List;

/**
 * @Author: ManualHuaJi
 */
public class MirrorAuraItem extends ItemBlock {
    public MirrorAuraItem(Block block) {
        super(block);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if ((world.getBlockState(pos).getBlock() instanceof MirrorAura)) {
            if (world.isRemote) {
                player.swingArm(hand);
                return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
            }
            if (this.block == BlocksEXPMM.mirrorAura) {
                TileEntity tm = world.getTileEntity(pos);
                if ((tm != null) && ((tm instanceof TileMirrorAura)) && (!((TileMirrorAura) tm).isLinkValid())) {
                    ItemStack st = player.getHeldItem(hand).copy();
                    st.setCount(1);
                    st.setItemDamage(1);
                    st.setTagInfo("linkX", new NBTTagInt(tm.getPos().getX()));
                    st.setTagInfo("linkY", new NBTTagInt(tm.getPos().getY()));
                    st.setTagInfo("linkZ", new NBTTagInt(tm.getPos().getZ()));
                    st.setTagInfo("linkDim", new NBTTagInt(world.provider.getDimension()));
                    world.playSound(null, pos, SoundsTC.jar, SoundCategory.BLOCKS, 1.0F, 2.0F);
                    if ((!player.inventory.addItemStackToInventory(st)) &&
                            (!world.isRemote)) {
                        world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, st));
                    }
                    if (!player.capabilities.isCreativeMode) {
                        player.getHeldItem(hand).shrink(1);
                    }
                    player.inventoryContainer.detectAndSendChanges();
                } else if ((tm != null) && ((tm instanceof TileMirrorAura))) {
                    player.sendMessage(new TextComponentTranslation("That mirror is already linked to a valid destination.", new Object[0]));
                }
            }
        }
        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, World worldIn, List<String> list, ITooltipFlag flagIn) {
        if (item.hasTagCompound()) {
            int lx = item.getTagCompound().getInteger("linkX");
            int ly = item.getTagCompound().getInteger("linkY");
            int lz = item.getTagCompound().getInteger("linkZ");
            int ldim = item.getTagCompound().getInteger("linkDim");
            String desc = "" + ldim;
            World world = DimensionManager.getWorld(ldim);
            if (world != null) {
                desc = world.provider.getDimensionType().getName();
            }
            list.add("Linked to " + lx + "," + ly + "," + lz + " in " + desc);
        }
    }
}
