package mhj.expmm.common.item.foci;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.common.items.casters.foci.FocusEffectFlux;

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
        if (!(target.entityHit == null) && target.entityHit == getPackage().getCaster() && FocusEffectFlux.class.equals(getPackage().getFocusEffects())) {
            EntityLivingBase entity = (EntityLivingBase) target.entityHit;
            for (int i = 0; i < 3; i++) {
                System.out.print(entity.getName() + "by" + getPackage().getFocusEffects() + "    ");
            }
/*
            if (entity.getHealth() <= entity.getMaxHealth() * 0.15) {

            }
*/
        }
        return false;
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
