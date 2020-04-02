package overgrowncities.feature.structures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import overgrowncities.OvergrownCities;

import java.util.Random;
import java.util.function.Function;

public class WarehouseStructure extends StructureFeature<DefaultFeatureConfig> {
    public WarehouseStructure(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator, Random random, int startXChunk, int startZChunk, int currentXChunk, int currentZChunk) {
        int maxSeperation = 20;
        int minSeperation = 10;
        int k = startXChunk + maxSeperation * currentXChunk;
        int p = startZChunk + maxSeperation * currentZChunk;

        //voodoo mathy thingy here from vanilly
        int q = k < 0 ? k - maxSeperation + 1 : k;
        int r = p < 0 ? p - maxSeperation + 1 : p;
        int validXChunk = q / maxSeperation;
        int validZChunk = r / maxSeperation;

        ((ChunkRandom)random).setStructureSeed(chunkGenerator.getSeed(), validXChunk, validZChunk, 43114322);
        validXChunk *= maxSeperation;
        validZChunk *= maxSeperation;
        validXChunk += random.nextInt(maxSeperation - minSeperation);
        validZChunk += random.nextInt(maxSeperation - minSeperation);
        return new ChunkPos(validXChunk, validZChunk);
    }

    public boolean shouldStartAt(BiomeAccess biomeAccess, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
        ChunkPos chunkPos = this.getStart(chunkGenerator, random, chunkX, chunkZ, 0, 0);
        return chunkX == chunkPos.x && chunkZ == chunkPos.z ? chunkGenerator.hasStructure(biome, this) : false;
    }

    public StructureStartFactory getStructureStartFactory() {
        return WarehouseStructure.Start::new;
    }

    //What shows up in locate command. Needs mod_id or else MC slaps on minecraft namespace.
    public String getName() {
        return OvergrownCities.MOD_ID+":abandoned_warehouse";
    }

    //legacy minecraft code that does nothing
    public int getRadius() {
        return 6;
    }

    public static class Start extends StructureStart {
        public Start(StructureFeature<?> structureFeature, int chunkX, int chunkZ, BlockBox blockBox, int references, long seed) {
            super(structureFeature, chunkX, chunkZ, blockBox, references, seed);
        }

        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            BlockPos blockPos = new BlockPos(x * 16, chunkGenerator.getHeightOnGround(x*16+15, z*16+15, Heightmap.Type.WORLD_SURFACE_WG), z * 16);
            BlockRotation blockRotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            WarehouseGenerator.addPieces(structureManager, blockPos, blockRotation, this.children, this.random);
            this.setBoundingBoxFromChildren();
        }
    }
}
