package mhj.expmm.common.item;

import mhj.expmm.ExperimentalMagic;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @Author: ManualHuaJi
 */

@Mod.EventBusSubscriber
public class ItemNoteBook extends ItemEXPMM {
    public ItemNoteBook() {
        super("notebook");
        this.setNoRepair().setMaxStackSize(1);
    }

    @Override
    public boolean getShareTag() {
        return super.getShareTag();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(ExperimentalMagic.instance, 1, worldIn, MathHelper.floor(playerIn.posX), MathHelper.floor(playerIn.posY), MathHelper.floor(playerIn.posZ));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("all")
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        /*if (stack.hasTagCompound()) {
            int noteSun = stack.getTagCompound().getInteger("noteSun");
            int noteStar1 = stack.getTagCompound().getInteger("noteStar1");
            int noteStar2 = stack.getTagCompound().getInteger("noteStar2");
            int noteStar3 = stack.getTagCompound().getInteger("noteStar3");
            int noteStar4 = stack.getTagCompound().getInteger("noteStar4");
            int noteMoon1 = stack.getTagCompound().getInteger("noteMoon1");
            int noteMoon2 = stack.getTagCompound().getInteger("noteMoon2");
            int noteMoon3 = stack.getTagCompound().getInteger("noteMoon3");
            int noteMoon4 = stack.getTagCompound().getInteger("noteMoon4");
            int noteMoon5 = stack.getTagCompound().getInteger("noteMoon5");
            int noteMoon6 = stack.getTagCompound().getInteger("noteMoon6");
            int noteMoon7 = stack.getTagCompound().getInteger("noteMoon7");
            int noteMoon8 = stack.getTagCompound().getInteger("noteMoon8");

            for (int var1 = 0; var1 < 8; var1++) {

                switch (var1) {
                    case 1:
                        if (noteSun != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.sun.text") + "x" + noteSun);
                        }
                    case 2:
                        if (noteStar1 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.stars_1.text") + "x" + noteStar1);
                        }
                    case 3:
                        if (noteStar2 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.stars_2.text") + "x" + noteStar2);
                        }
                    case 4:
                        if (noteStar3 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.stars_3.text") + "x" + noteStar3);
                        }
                    case 5:
                        if (noteStar4 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.stars_4.text") + "x" + noteStar4);
                        }
                    case 6:
                        if (noteMoon1 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_1") + "x" + noteMoon1);
                        }
                    case 7:
                        if (noteMoon2 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_2") + "x" + noteMoon2);
                        }
                    case 8:
                        if (noteMoon3 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_3") + "x" + noteMoon3);
                        }
                    case 9:
                        if (noteMoon4 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_4") + "x" + noteMoon4);
                        }
                    case 10:
                        if (noteMoon5 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_5") + "x" + noteMoon5);
                        }
                    case 11:
                        if (noteMoon6 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_6") + "x" + noteMoon6);
                        }
                    case 12:
                        if (noteMoon7 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_7") + "x" + noteMoon7);
                        }
                    case 13:
                        if (noteMoon8 != 0) {
                            tooltip.add(I18n.translateToLocal("item.celestial_notes.moon_8") + "x" + noteMoon8);
                        }
                }
            }
        }*/
    }
}
