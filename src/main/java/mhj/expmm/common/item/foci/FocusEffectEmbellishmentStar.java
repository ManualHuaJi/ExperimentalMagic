package mhj.expmm.common.item.foci;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.Trajectory;

/**
 * @Author: ManualHuaJi
 */
public class FocusEffectEmbellishmentStar extends FocusEffectEmbellishment {
    @Override
    public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
        return false;
    }

    @Override
    public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {

    }

    @Override
    public int getComplexity() {
        return 2;
    }


    @Override
    public Aspect getAspect() {
        return Aspect.ELDRITCH;
    }

    @Override
    public EnumSupplyType[] willSupply() {
        return new EnumSupplyType[]{EnumSupplyType.TARGET};
    }

    @Override
    public String getKey() {
        return "expmm.EF.STAR";
    }

    @Override
    public String getResearch() {
        return null;
    }


}
