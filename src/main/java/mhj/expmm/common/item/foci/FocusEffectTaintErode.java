package mhj.expmm.common.item.foci;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class FocusEffectTaintErode extends FocusEffect {
    @Override
    public boolean execute(RayTraceResult target, @Nullable Trajectory trajectory, float finalPower, int num) {
        //判断记得加概率
        if ((target.entityHit instanceof EntityLivingBase) && (target.entityHit) != null && (target.typeOfHit == RayTraceResult.Type.ENTITY) && ((EntityLivingBase) target.entityHit).getHealth() <= ((EntityLivingBase) target.entityHit).getMaxHealth() * 0.15F) {
            target.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(getPackage().getCaster(), getPackage().getCaster()), getSettingValue("power") * finalPower * 1.5F + ((EntityLivingBase) target.entityHit).getMaxHealth() * 2F);

        }
        return false;
    }

    @Override
    public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {

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
        return null;
    }

    @Override
    public String getResearch() {
        return this.getClass().getName().toUpperCase();
    }
}
