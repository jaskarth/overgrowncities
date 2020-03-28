package overgrowncities.biome;

import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class OvergrowthBeachBiome extends OvergrowthBiome {
    public OvergrowthBeachBiome() {
        super(new Settings()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .precipitation(Precipitation.RAIN)
                .category(Category.BEACH)
                .depth(0.0F)
                .scale(0.1F)
                .temperature(0.8F)
                .downfall(0.4F)
                .waterColor(0x236e37)
                .waterFogColor(0x236e37)
                .parent(null), 0.3);

        DefaultBiomeFeatures.addLandCarvers(this);
        DefaultBiomeFeatures.addPlainsTallGrass(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addDefaultDisks(this);
        DefaultBiomeFeatures.addDefaultMushrooms(this);
        DefaultBiomeFeatures.addDefaultVegetation(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFrozenTopLayer(this);
    }
}
