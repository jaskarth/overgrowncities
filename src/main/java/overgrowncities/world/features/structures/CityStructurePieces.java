package overgrowncities.world.features.structures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.VillageFeatureConfig;
import overgrowncities.OvergrownCities;
import overgrowncities.world.features.OgFeatures;

import java.util.List;

public class CityStructurePieces {
    public static void addPieces(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        //"village/plains/town_centers"
        StructurePoolBasedGenerator.addPieces(new Identifier(OvergrownCities.MOD_ID+":city/center"), 50, VillageGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int size, BlockRotation blockRotation, BlockBox blockBox) {
            super(OgFeatures.CITY_PIECES, structureManager, structurePoolElement, blockPos, size, blockRotation, blockBox);
        }

        public Piece(StructureManager structureManager, CompoundTag compoundTag) {
            super(structureManager, compoundTag, OgFeatures.CITY_PIECES);
        }
    }
}
