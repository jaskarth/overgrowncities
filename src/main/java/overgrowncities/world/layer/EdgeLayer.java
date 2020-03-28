package overgrowncities.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import overgrowncities.init.OvergrownBiomes;
import overgrowncities.world.layer.type.SquareCrossSamplingLayer;

public enum EdgeLayer implements SquareCrossSamplingLayer {
    INSTANCE;

    private static int LAKE = Registry.BIOME.getRawId(OvergrownBiomes.LAKE);
    private static int WILD = Registry.BIOME.getRawId(OvergrownBiomes.WILDERNESS);
    private static int BEACH = Registry.BIOME.getRawId(OvergrownBiomes.BEACH);

    private static int CITY = Registry.BIOME.getRawId(OvergrownBiomes.CITY);
    private static int SUBURBS = Registry.BIOME.getRawId(OvergrownBiomes.SUBURBS);

    @Override
    public int sample(LayerRandomnessSource layerRandomnessSource, int w, int nw, int n, int ne, int e, int se, int s, int sw, int center) {
        //lake -> beach transformation
        if (center == LAKE) {
            if (w == WILD ||
                    nw == WILD ||
                    n == WILD ||
                    ne == WILD ||
                    e == WILD ||
                    se == WILD ||
                    s == WILD ||
                    sw == WILD) {
                return BEACH;
            }
        }

        //city -> suburb transformation
        if (center == 1) { //replace the plains
            if (w == CITY ||
                    nw == CITY ||
                    n == CITY ||
                    ne == CITY ||
                    e == CITY ||
                    se == CITY ||
                    s == CITY ||
                    sw == CITY) {
                return SUBURBS;
            }
        }

        return center;
    }
}
