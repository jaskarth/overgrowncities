package overgrowncities.world.layer;

import net.minecraft.world.biome.layer.type.MergingLayer;
import net.minecraft.world.biome.layer.util.IdentityCoordinateTransformer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import net.minecraft.world.biome.layer.util.LayerSampler;

public enum CityMerger implements MergingLayer, IdentityCoordinateTransformer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, LayerSampler continentLayer, LayerSampler cityLayer, int x, int z) {
        int citySample = cityLayer.sample(x, z);
        if (citySample != 1) {
            return citySample;
        }

        return continentLayer.sample(x, z);
    }
}
