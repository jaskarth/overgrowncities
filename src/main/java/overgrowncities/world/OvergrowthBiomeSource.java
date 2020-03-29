package overgrowncities.world;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;
import overgrowncities.init.OvergrownBiomes;

public class OvergrowthBiomeSource extends BiomeSource {
    private final BiomeLayerSampler biomeSampler;

    protected OvergrowthBiomeSource(VanillaLayeredBiomeSourceConfig config) {
        super(ImmutableSet.of(
                OvergrownBiomes.LAKE,
                OvergrownBiomes.BEACH,
                OvergrownBiomes.CITY,
                OvergrownBiomes.SUBURBS,
                OvergrownBiomes.WILDERNESS
        ));
        this.biomeSampler = OvergrowthBiomeLayers.build(config.getSeed(), config.getGeneratorType(), config.getGeneratorSettings());
    }

    @Override
    public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
        return this.biomeSampler.sample(biomeX, biomeZ);
    }
}
