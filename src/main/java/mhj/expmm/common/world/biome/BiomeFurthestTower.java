package mhj.expmm.common.world.biome;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BiomeFurthestTower extends Biome {

    public BiomeFurthestTower() {
        super(new BiomeProperties("furthestTower").setBaseHeight(0.2F).setHeightVariation(0.3F).setTemperature(0.8F).setRainDisabled().setBaseBiome("plains"));
        this.spawnableMonsterList.clear();
        this.decorator.flowersPerChunk = 256;
        this.decorator.generateFalls = false;
        this.decorator.treesPerChunk = 2;
    }


    @SubscribeEvent
    public void BiomeEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;
        Biome currentBiome = world.getBiome(new BlockPos(player));
        if (currentBiome instanceof BiomeFurthestTower && !world.isRemote && (player.ticksExisted % 20 == 0)) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2));
        }
    }

}