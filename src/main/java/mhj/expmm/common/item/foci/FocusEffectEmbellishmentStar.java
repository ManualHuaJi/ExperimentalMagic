package mhj.expmm.common.item.foci;

import thaumcraft.api.aspects.Aspect;

/**
 * @Author: ManualHuaJi
 */
public class FocusEffectEmbellishmentStar extends FocusEffectEmbellishment {
    @Override
    public int getComplexity() {
        return 2;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public Aspect getAspect() {
        return Aspect.ELDRITCH;
    }
    @Override
    public EnumSupplyType[] mustBeSupplied() {
        return new EnumSupplyType[]{EnumSupplyType.TRAJECTORY};
    }

    @Override
    public EnumSupplyType[] willSupply() {
        return new EnumSupplyType[]{EnumSupplyType.TARGET};
    }

    @Override
    public String getKey() {
        return "EF.star";
    }

    @Override
    public String getResearch() {
        return null;
    }
}
