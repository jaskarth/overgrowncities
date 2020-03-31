package overgrowncities.feature;

import net.minecraft.structure.PlainsVillageData;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;
import overgrowncities.OvergrownCities;
import overgrowncities.feature.structures.CityStructure;
import overgrowncities.feature.structures.CityStructurePieces;
import overgrowncities.feature.structures.CityStructurePools;

public class OgFeatures {
    public static StructureFeature<DefaultFeatureConfig> CITY_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, "city_structure", new CityStructure(DefaultFeatureConfig::deserialize));
    public static StructurePieceType CITY_PIECES =  Registry.register(Registry.STRUCTURE_PIECE, "city_structure_pieces", CityStructurePieces.Piece::new);

    public static void setupFeatures() {
        Feature.STRUCTURES.put(OvergrownCities.MOD_ID+":city_structure", CITY_STRUCTURE);
        CityStructurePools.initialize();
        PlainsVillageData.initialize();
    }

    public static void addFeaturesOfStructures(Biome biome) {
        biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, OgFeatures.CITY_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)));
    }
}
