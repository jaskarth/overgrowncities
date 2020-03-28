package overgrowncities.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.type.InitLayer;
import net.minecraft.world.biome.layer.util.IdentityCoordinateTransformer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import overgrowncities.init.OvergrownBiomes;

public enum ContinentLayer implements InitLayer, IdentityCoordinateTransformer {
    INSTANCE;

    private static int WILD = Registry.BIOME.getRawId(OvergrownBiomes.WILDERNESS);
    private static int LAKE = Registry.BIOME.getRawId(OvergrownBiomes.LAKE);

    @Override
    public int sample(LayerRandomnessSource context, int x, int y) {
        return context.nextInt(9) == 0 ? LAKE : WILD;
    }
}
