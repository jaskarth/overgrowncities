package overgrowncities.biome;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Overwrite;
import overgrowncities.treedecorator.ManyVinesTreeDecorator;
import overgrowncities.world.features.OgFeatures;

public class OvergrowthWildernessBiome extends OvergrowthBiome {
    public OvergrowthWildernessBiome() {
        super(new Biome.Settings()
                .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
                .precipitation(Biome.Precipitation.RAIN)
                .category(Category.SWAMP)
                .depth(0.25F)
                .scale(0.1F)
                .temperature(0.8F)
                .downfall(0.4F)
                .waterColor(0x236e37)
                .waterFogColor(0x236e37)
                .parent(null), 5);

        this.addStructureFeature(OgFeatures.CITY_STRUCTURE.configure(FeatureConfig.DEFAULT));
        this.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, OgFeatures.CITY_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)));

        DefaultBiomeFeatures.addLandCarvers(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addDefaultDisks(this);
        DefaultBiomeFeatures.addDefaultMushrooms(this);
        DefaultBiomeFeatures.addDefaultVegetation(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFrozenTopLayer(this);

        this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.GRASS_CONFIG)
                        .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_DOUBLE
                                .configure(new NoiseHeightmapDecoratorConfig(-0.8D, 10, 20))));

        this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                Feature.NORMAL_TREE.configure(OVERGROWN_TREE)
                        .createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP
                                .configure(new CountExtraChanceDecoratorConfig(16, 0.5F, 1))));

        this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.COW, 8, 4, 4));
    }
}
