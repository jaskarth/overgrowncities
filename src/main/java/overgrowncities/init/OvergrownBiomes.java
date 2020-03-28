package overgrowncities.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import overgrowncities.biome.*;

public class OvergrownBiomes {

    //wilderness biomes
    public static Biome WILDERNESS;
    public static Biome LAKE;
    public static Biome BEACH;

    //city
    public static Biome CITY;
    public static Biome SUBURBS;

    public static void init() {
        WILDERNESS = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "wilderness"), new OvergrowthWildernessBiome());
        LAKE = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "lake"), new OvergrowthLakeBiome());
        BEACH = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "beach"), new OvergrowthBeachBiome());
        CITY = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "city"), new OvergrowthCityBiome());
        SUBURBS = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "suburbs"), new OvergrowthSuburbsBiome());
    }
}
