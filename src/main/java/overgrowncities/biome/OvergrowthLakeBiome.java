package overgrowncities.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class OvergrowthLakeBiome extends OvergrowthBiome {
    public OvergrowthLakeBiome() {
        super(new Biome.Settings()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .precipitation(Biome.Precipitation.RAIN)
                .category(Category.SWAMP)
                .depth(-0.65F)
                .scale(0.01F)
                .temperature(0.8F)
                .downfall(0.4F)
                .waterColor(0x236e37)
                .waterFogColor(0x236e37)
                .parent(null), 0.3);

        DefaultBiomeFeatures.addLandCarvers(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addDefaultDisks(this);
        DefaultBiomeFeatures.addDefaultMushrooms(this);
        DefaultBiomeFeatures.addDefaultVegetation(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFrozenTopLayer(this);

        this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                Feature.NORMAL_TREE.configure(OVERGROWN_TREE)
                        .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP
                                .configure(new CountExtraChanceDecoratorConfig(6, 0.5F, 1))));
    }
}
