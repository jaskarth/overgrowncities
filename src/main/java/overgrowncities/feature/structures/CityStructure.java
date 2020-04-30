package overgrowncities.feature.structures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import overgrowncities.OvergrownCities;

import java.util.Random;
import java.util.function.Function;

public class CityStructure extends StructureFeature<DefaultFeatureConfig> {
    public CityStructure(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator, Random random, int startXChunk, int startZChunk, int currentXChunk, int currentZChunk) {
        int maxSeperation = 10;
        int minSeperation = 9;
        int k = startXChunk + maxSeperation * currentXChunk;
        int p = startZChunk + maxSeperation * currentZChunk;

        //voodoo mathy thingy here from vanilly
        int q = k < 0 ? k - maxSeperation + 1 : k;
        int r = p < 0 ? p - maxSeperation + 1 : p;
        int validXChunk = q / maxSeperation;
        int validZChunk = r / maxSeperation;

        ((ChunkRandom)random).setSeed(chunkGenerator.getSeed() * 43114311);
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
        return CityStructure.Start::new;
    }

    //What shows up in locate command. Needs mod_id or else MC slaps on minecraft namespace.
    public String getName() {
        return OvergrownCities.MOD_ID+":city_structure";
    }

    //legacy minecraft code that does nothing
    public int getRadius() {
        return 6;
    }

    public static class Start extends StructureStart {
        public Start(StructureFeature<?> structureFeature, int chunkX, int chunkZ, BlockBox blockBox, int references, long seed) {
            super(structureFeature, chunkX, chunkZ, blockBox, references, seed);
        }

        public void init(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);
            CityStructurePieces.addPieces(chunkGenerator, structureManager, blockPos, this.children, this.random);
            this.setBoundingBoxFromChildren();
        }
    }
}
