package mhj.expmm.common.world.biome;

import mhj.expmm.ExperimentalMagic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BiomeFurthestTower extends Biome {
    public BiomeFurthestTower(BiomeProperties properties) {
        super(properties);
        setRegistryName(ExperimentalMagic.MODID, "FurthestTower");
        this.spawnableMonsterList.clear();
        this.decorator.flowersPerChunk = 256;

    }


    @SubscribeEvent
    public void onEnteringBiome(EntityEvent.EnteringChunk event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLiving && entity.getEntityWorld().getBiome(entity.getPosition()) instanceof BiomeFurthestTower) {
            ((EntityLiving) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2));
        }
        if (event.getEntity() instanceof EntityPlayer) {

        }
    }
}