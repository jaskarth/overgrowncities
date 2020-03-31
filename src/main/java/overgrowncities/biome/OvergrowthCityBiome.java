package overgrowncities.biome;

import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import overgrowncities.feature.OgFeatures;

public class OvergrowthCityBiome extends OvergrowthBiome {
    public OvergrowthCityBiome() {
        super(new Settings()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .precipitation(Precipitation.RAIN)
                .category(Category.BEACH)
                .depth(0.2F)
                .scale(0.01F)
                .temperature(0.8F)
                .downfall(0.4F)
                .waterColor(0x236e37)
                .waterFogColor(0x236e37)
                .parent(null), 0);

        this.addStructureFeature(OgFeatures.CITY_STRUCTURE.configure(FeatureConfig.DEFAULT));

        OgFeatures.addFeaturesOfStructures(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addPlainsTallGrass(this);
        DefaultBiomeFeatures.addDefaultDisks(this);
        DefaultBiomeFeatures.addDefaultMushrooms(this);
        DefaultBiomeFeatures.addDefaultVegetation(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFrozenTopLayer(this);
    }
}
