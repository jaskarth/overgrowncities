package overgrowncities.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import overgrowncities.biome.OvergrowthWildernessBiome;

public class OvergrownBiomes {
    public static Biome WILDERNESS;

    public static void init() {
        WILDERNESS = Registry.register(Registry.BIOME, new Identifier("overgrowncities", "wilderness"), new OvergrowthWildernessBiome());
    }
}
