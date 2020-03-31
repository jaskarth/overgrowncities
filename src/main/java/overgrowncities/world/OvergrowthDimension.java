package overgrowncities.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import overgrowncities.OvergrownCities;

public class OvergrowthDimension extends Dimension {
    public OvergrowthDimension(World world, DimensionType type) {
        super(world, type, 0.0F);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        OverworldChunkGeneratorConfig config = new OverworldChunkGeneratorConfig();
        VanillaLayeredBiomeSourceConfig biomeConfig = new VanillaLayeredBiomeSourceConfig(world.getLevelProperties());
        return new OvergrowthChunkGenerator(this.world, new OvergrowthBiomeSource(biomeConfig), config);
    }

    @Override
    public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean checkMobSpawnValidity) {
       return null;
    }

    @Override
    public BlockPos getTopSpawningBlockPosition(int x, int z, boolean checkMobSpawnValidity) {
        return null;
    }

    @Override
    public float getSkyAngle(long timeOfDay, float tickDelta) {
        double d = MathHelper.fractionalPart((double)timeOfDay / 24000.0D - 0.25D);
        double e = 0.5D - Math.cos(d * 3.141592653589793D) / 2.0D;
        return (float)(d * 2.0D + e) / 3.0F;
    }

    @Override
    public boolean hasVisibleSky() {
        return true;
    }

    @Override
    public Vec3d getFogColor(float skyAngle, float tickDelta) {
//        float f = MathHelper.cos(skyAngle * 6.2831855F) * 2.0F + 0.5F;
//        f = MathHelper.clamp(f, 0.0F, 1.0F);
//        float g = 0.7529412F;
//        float h = 0.84705883F;
//        float i = 1.0F;
//        g *= f * 0.94F + 0.06F;
//        h *= f * 0.94F + 0.06F;
//        i *= f * 0.91F + 0.09F;
        return new Vec3d(199/256f, 222/256f, 235/256f);
    }

    @Override
    public boolean canPlayersSleep() {
        return true;
    }

    @Override
    public boolean isFogThick(int x, int z) {
        return false;
    }

    @Override
    public DimensionType getType() {
        return OvergrownCities.OVERGROWTH;
    }
}
