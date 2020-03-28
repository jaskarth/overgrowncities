package overgrowncities.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import overgrowncities.biome.OvergrowthBeachBiome;
import overgrowncities.biome.OvergrowthLakeBiome;
import overgrowncities.biome.OvergrowthWildernessBiome;

public class OvergrownBiomes {
    public static Biome WILDERNESS;
    public static Biome LAKE;
    public static Biome BEACH;

    public static void init() {
        WILDERNESS = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "wilderness"), new OvergrowthWildernessBiome());
        LAKE = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "lake"), new OvergrowthLakeBiome());
        BEACH = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "beach"), new OvergrowthBeachBiome());
    }
}
