package mhj.expmm.common.item.foci;

import net.minecraft.util.math.RayTraceResult;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.Trajectory;

/**
 * @Author: ManualHuaJi
 */
public class FocusEmbellishmentDeathMagic extends FocusEffectEmbellishment {

    @Override
    public int getComplexity() {
        return 2;
    }

    @Override
    public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
        int var1 = (int) (1 + Math.random() * (1 - 0 + 1));
        if(){

        }
        return false;
    }

    @Override
    public Aspect getAspect() {
        return Aspect.DEATH;
    }

    @Override
    public EnumSupplyType[] mustBeSupplied() {
        return new EnumSupplyType[]{EnumSupplyType.TARGET};
    }

    @Override
    public EnumSupplyType[] willSupply() {
        return new EnumSupplyType[]{EnumSupplyType.TARGET, EnumSupplyType.TRAJECTORY};
    }

    @Override
    public String getKey() {
        return "EF.deathmagic";
    }

    @Override
    public String getResearch() {
        return "EFDEADTHMAGIC";
    }


}
