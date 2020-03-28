package overgrowncities.world.features;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.VillageGenerator;
import overgrowncities.world.features.structures.CityStructure;
import overgrowncities.world.features.structures.CityStructurePieces;

public class OgFeatures {
    public static StructurePieceType CITY_PIECES = StructurePieceType.register(CityStructurePieces.Piece::new, "city_structure_pieces");
}
