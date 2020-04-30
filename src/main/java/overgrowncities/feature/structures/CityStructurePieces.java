package overgrowncities.feature.structures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeatureConfig;
import overgrowncities.OvergrownCities;
import overgrowncities.feature.OgFeatures;

import java.util.List;
import java.util.Random;

public class CityStructurePieces {
    public static void addPieces(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) {
        StructurePoolBasedGenerator.addPieces(new Identifier(OvergrownCities.MOD_ID+":streets/start"), 1000, CityStructurePieces.Piece::new, chunkGenerator, structureManager, pos, pieces, random, false, false);
    }

    public static class Piece extends PoolStructurePiece {
        private final StructureManager structureManager;
        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int size, BlockRotation blockRotation, BlockBox blockBox) {
            super(OgFeatures.CITY_PIECES, structureManager, structurePoolElement, blockPos, size, blockRotation, blockBox);
            this.structureManager = structureManager;
        }

        public Piece(StructureManager structureManager, CompoundTag compoundTag) {
            super(structureManager, compoundTag, OgFeatures.CITY_PIECES);
            this.structureManager = structureManager;
        }

        @Override
        public boolean generate(IWorld world, StructureAccessor structureAccessor, ChunkGenerator<?> chunkGenerator, Random random, BlockBox box, ChunkPos pos, BlockPos blockPos) {
            boolean created = this.poolElement.generate(this.structureManager, world, structureAccessor, chunkGenerator, this.pos, blockPos, this.rotation, box, random, false);
            if (created) {
                for(BlockPos boxPosition : BlockPos.iterate(box.minX, this.pos.getY(), box.minZ, box.maxX, this.pos.getY()+32, box.maxZ)){
                    OgFeatures.BUILDING_DEBRIS.generate(world, structureAccessor, chunkGenerator, random, boxPosition, FeatureConfig.DEFAULT);
                }
                for(BlockPos boxPosition : BlockPos.iterate(box.minX, this.pos.getY(), box.minZ, box.maxX, this.pos.getY()+32, box.maxZ)){
                    OgFeatures.BUILDING_VEGETATION.generate(world, structureAccessor, chunkGenerator, random, boxPosition, FeatureConfig.DEFAULT);
                }
            }
            return created;
        }
    }
}
