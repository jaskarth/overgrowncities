package overgrowncities.biome;

import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import overgrowncities.init.OvergrownFeatures;
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

        this.addFeature(GenerationStep.Feature.RAW_GENERATION,
                OvergrownFeatures.ABANDONED_ROOM_FOUNDATION.configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.65f, 1))));

        this.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,
                OvergrownFeatures.ABANDONED_ROOM_WALLS.configure(FeatureConfig.DEFAULT)
                        .createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)));

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
