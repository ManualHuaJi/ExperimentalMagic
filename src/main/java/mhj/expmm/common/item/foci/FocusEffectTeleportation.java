package mhj.expmm.common.item.foci;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class FocusEffectTeleportation extends FocusEffectEXPMM{
    @Override
    public boolean execute(RayTraceResult target, @Nullable Trajectory trajectory, float finalPower, int num) {
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
        return Aspect.MOTION;
    }

    @Override
    public String getKey() {
        return "expmm.TELEPORTATION";
    }

    @Override
    public String getResearch() {
        return null;
    }


}
