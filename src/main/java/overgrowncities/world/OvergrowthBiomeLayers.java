package overgrowncities.world;

import net.minecraft.world.biome.layer.ScaleLayer;
import net.minecraft.world.biome.layer.type.ParentedLayer;
import net.minecraft.world.biome.layer.util.*;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;
import overgrowncities.world.layer.ContinentLayer;
import overgrowncities.world.layer.EdgeLayer;

import java.util.function.LongFunction;

public class OvergrowthBiomeLayers {

    private static <T extends LayerSampler, C extends LayerSampleContext<T>> LayerFactory<T> stack(long seed, ParentedLayer layer, LayerFactory<T> parent, int count, LongFunction<C> contextProvider) {
        LayerFactory<T> layerFactory = parent;

        for(int i = 0; i < count; ++i) {
            layerFactory = layer.create(contextProvider.apply(seed + (long)i), layerFactory);
        }

        return layerFactory;
    }

    public static <T extends LayerSampler, C extends LayerSampleContext<T>> LayerFactory<T> build(LevelGeneratorType generatorType, OverworldChunkGeneratorConfig settings, LongFunction<C> contextProvider) {
        //initialize wilderness and lakes
        LayerFactory<T> continentFactory = ContinentLayer.INSTANCE.create(contextProvider.apply(1L));

        //scale up
        continentFactory = stack(100L, ScaleLayer.NORMAL, continentFactory, 4, contextProvider);

        //apply beaches
        continentFactory = EdgeLayer.INSTANCE.create(contextProvider.apply(5L), continentFactory);

        //scale up again to make beaches bigger
        continentFactory = stack(100L, ScaleLayer.NORMAL, continentFactory, 2, contextProvider);

        return continentFactory;
    }

    public static BiomeLayerSampler build(long seed, LevelGeneratorType generatorType, OverworldChunkGeneratorConfig settings) {
        LayerFactory<CachingLayerSampler> layerFactory = build(generatorType, settings, (salt) -> new CachingLayerContext(25, seed, salt));
        return new BiomeLayerSampler(layerFactory);
    }
}
