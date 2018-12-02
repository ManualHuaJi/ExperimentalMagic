package mhj.expmm.common.item.foci;

import net.minecraft.util.math.RayTraceResult;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;

/**
 * @Author: ManualHuaJi
 */
public abstract class FocusEffectEmbellishment extends FocusEffect {

    @Override
    public abstract boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num);

    @Override
    public abstract int getComplexity();

    @Override
    public abstract Aspect getAspect();

    @Override
    public abstract EnumSupplyType[] willSupply();

    @Override
    public abstract String getKey();

    @Override
    public abstract String getResearch();

    @Override
    public EnumUnitType getType() {
        return EnumUnitType.MOD;
    }

}
