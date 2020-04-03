package overgrowncities.feature;

import net.minecraft.structure.PlainsVillageData;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;
import overgrowncities.OvergrownCities;
import overgrowncities.feature.structures.*;

public class OgFeatures {
    public static StructureFeature<DefaultFeatureConfig> CITY_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, "city_structure", new CityStructure(DefaultFeatureConfig::deserialize));
    public static StructurePieceType CITY_PIECES =  Registry.register(Registry.STRUCTURE_PIECE, "city_structure_pieces", CityStructurePieces.Piece::new);
    public static StructureFeature<DefaultFeatureConfig> WAREHOUSE_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, "abandoned_warehouse", new WarehouseStructure(DefaultFeatureConfig::deserialize));
    public static StructurePieceType WAREHOUSE_PIECES =  Registry.register(Registry.STRUCTURE_PIECE, "abandoned_warehouse_pieces", WarehouseGenerator.Piece::new);

    public static Feature<DefaultFeatureConfig> BUILDING_DESTRUCTION = Registry.register(Registry.FEATURE, "building_destruction", new BuildingDestructionFeature(DefaultFeatureConfig::deserialize));
    public static Feature<DefaultFeatureConfig> BUILDING_DECORATION = Registry.register(Registry.FEATURE, "building_decoration", new BuildingDecorationFeature(DefaultFeatureConfig::deserialize));

    public static void setupFeatures() {
        Feature.STRUCTURES.put(OvergrownCities.MOD_ID+":city_structure", CITY_STRUCTURE);
        Feature.STRUCTURES.put(OvergrownCities.MOD_ID+":abandoned_warehouse", WAREHOUSE_STRUCTURE);
        CityStructurePools.initialize();
        PlainsVillageData.initialize();
    }

    /**
     * Adds the warehouse fetaure to all biomes and then makes it only be able
     * to start generation in all vanilla swamp, jungle, and normal taiga biomes.
     */
    public static void addWarehouseToOverworld() {

        for(Biome biome : Registry.BIOME){
            Identifier id = Registry.BIOME.getId(biome);
            if(id.getNamespace().contains("minecraft") &&
                (id.getPath().contains("swamp") ||
                id.getPath().contains("jungle") ||
                id.getPath().contains("dark_oak") ||
                (id.getPath().contains("taiga") && !id.getPath().contains("giant") && !id.getPath().contains("snowy")))
            ){
                biome.addStructureFeature(OgFeatures.WAREHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT));
            }

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, OgFeatures.WAREHOUSE_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)));
        }
    }

    public static void addFeaturesOfStructures(Biome biome) {
        biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, OgFeatures.CITY_STRUCTURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT)));
    }
}
