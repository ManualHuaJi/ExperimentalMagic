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
public abstract class FocusEffectEXPMM extends FocusEffect {


    @Override
    public boolean execute(RayTraceResult target, @Nullable Trajectory trajectory, float finalPower, int num) {
        return false;
    }

    @Override
    public void renderParticleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {

    }

    @Override
    public int getComplexity() {
        return 0;
    }

    @Override
    public Aspect getAspect() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getResearch() {
        return null;
    }


}
