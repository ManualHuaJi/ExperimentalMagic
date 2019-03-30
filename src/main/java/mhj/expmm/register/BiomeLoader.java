package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.world.biome.BiomeFurthestTower;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeLoader {

    public static final Biome furthestTower = new BiomeFurthestTower();

    public static void register() {
        registerBiome(furthestTower, "Furthest Tower", true, BiomeManager.BiomeType.WARM, new BiomeDictionary.Type[]{BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS});
    }

    public static Biome registerBiome(Biome biome, String name, boolean canSpawn, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... type) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, type);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, 10));
        if (canSpawn)
            BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}

