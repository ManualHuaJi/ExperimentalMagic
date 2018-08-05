package mhj.expmm.common.item.foci;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.casters.ItemCaster;
import thaumcraft.common.items.casters.ItemFocus;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class FocusEffectFluxErode extends FocusEffect {
    public FocusEffectFluxErode() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean execute(RayTraceResult target, @Nullable Trajectory trajectory, float finalPower, int num) {
        return false;
    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        if (event.getEntityPlayer().getActiveHand() == null) {
            return;
        }
        ItemStack heldItem = event.getEntityPlayer().getHeldItem(event.getEntityPlayer().getActiveHand());
        if (!(heldItem == null) && !(heldItem.isEmpty()) && heldItem.isItemEqual(new ItemStack(ItemsTC.casterBasic)) && event.getTarget() instanceof EntityLivingBase && ((EntityLivingBase) event.getTarget()).getHealth() <= ((EntityLivingBase) event.getTarget()).getMaxHealth() * 0.15) {
            ItemFocus itemFocus = ((ItemCaster) heldItem.getItem()).getFocus(heldItem);
            NBTTagCompound nbtTagCompound = new ItemStack(itemFocus).getTagCompound();
            for (int i = 0; i < 3; i++) {
                System.out.print("sus");
            }
        }
    }

    @Override
    public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY,
                                 double motionZ) {

    }

    @Override
    public int getComplexity() {
        return 4;
    }

    @Override
    public Aspect getAspect() {
        return Aspect.FLUX;
    }

    @Override
    public String getKey() {
        return "expmm.FLUXERODE";
    }

    @Override
    public String getResearch() {
        return this.getClass().getName().toUpperCase();
    }
}
