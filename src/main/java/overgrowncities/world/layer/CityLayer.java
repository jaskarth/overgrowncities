package overgrowncities.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.type.InitLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import overgrowncities.init.OvergrownBiomes;

public enum CityLayer implements InitLayer {
    INSTANCE;

    private static int CITY = Registry.BIOME.getRawId(OvergrownBiomes.CITY);

    @Override
    public int sample(LayerRandomnessSource context, int x, int y) {
        return context.nextInt(40) == 0 ? CITY : 1;
    }
}
