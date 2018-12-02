package mhj.expmm.common.item.foci;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.NodeSetting;
import thaumcraft.api.casters.Trajectory;

/**
 * @Author: ManualHuaJi
 */
public class FocusEffectEmbellishmentDeathMagic extends FocusEffectEmbellishment {


    @Override
    public int getComplexity() {
        return 2;
    }

    @Override
    public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
        int var1 = (int) (1 + Math.random() * (10 - 0 + 1));
        EntityLiving entityLiving = (EntityLiving) target.entityHit;
        if (target.typeOfHit == RayTraceResult.Type.ENTITY && target.typeOfHit != null && entityLiving.getHealth() <= entityLiving.getMaxHealth() * (getEELevel() / 10) && var1 > 10 - getEELevel()) {
            entityLiving.setDead();
            return true;
        }
        return false;
    }

    public float getEELevel() {
        return getSettingValue("eelevel");
    }

    @Override
    public NodeSetting[] createSettings() {
        return new NodeSetting[]{new NodeSetting("eelevel", "expmm.focus.eelevel", new NodeSetting.NodeSettingIntRange(1, 3))};
    }

    @Override
    public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY,
                                 double motionZ) {

    }

    @Override
    public Aspect getAspect() {
        return Aspect.DEATH;
    }

    @Override
    public EnumSupplyType[] willSupply() {
        return new EnumSupplyType[]{EnumSupplyType.TARGET, EnumSupplyType.TRAJECTORY};
    }

    @Override
    public String getKey() {
        return "expmm.EF.DEATHMAGIC";
    }

    @Override
    public String getResearch() {
        return "EFDEADTHMAGIC";
    }



}
