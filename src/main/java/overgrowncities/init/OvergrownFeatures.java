package overgrowncities.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import overgrowncities.feature.AbandonedRoomFoundationFeature;
import overgrowncities.feature.AbandonedRoomWallsFeature;

public class OvergrownFeatures {
    public static Feature<DefaultFeatureConfig> ABANDONED_ROOM_FOUNDATION;
    public static Feature<DefaultFeatureConfig> ABANDONED_ROOM_WALLS;

    public static void init() {
        ABANDONED_ROOM_FOUNDATION = Registry.register(Registry.FEATURE, new Identifier("overgrowncities", "abandoned_room_foundation"), new AbandonedRoomFoundationFeature(DefaultFeatureConfig::deserialize));
        ABANDONED_ROOM_WALLS = Registry.register(Registry.FEATURE, new Identifier("overgrowncities", "abandoned_room_walls"), new AbandonedRoomWallsFeature(DefaultFeatureConfig::deserialize));
    }

}
