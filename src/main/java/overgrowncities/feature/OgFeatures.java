package overgrowncities.feature;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import overgrowncities.OvergrownCities;
import overgrowncities.feature.structures.CityStructure;
import overgrowncities.feature.structures.CityStructurePieces;

public class OgFeatures {
    public static StructureFeature<DefaultFeatureConfig> CITY_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, "city_structure", new CityStructure(DefaultFeatureConfig::deserialize));
    public static StructurePieceType CITY_PIECES =  Registry.register(Registry.STRUCTURE_PIECE, "city_structure_pieces", CityStructurePieces.Piece::new);

    public static void setupFeatures() {
        Feature.STRUCTURES.put(OvergrownCities.MOD_ID + ":city_structure", CITY_STRUCTURE);
    }
}
