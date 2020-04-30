package overgrowncities.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class AbandonedRoomFoundationFeature extends Feature<DefaultFeatureConfig> {
    public AbandonedRoomFoundationFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
        super(configDeserializer);
    }

    private static void placeStoneBrick(IWorld world, Random random, BlockPos pos) {
        switch (random.nextInt(3)) {
            case 0:
                world.setBlockState(pos, Blocks.STONE_BRICKS.getDefaultState(), 0);
                break;
            case 1:
                world.setBlockState(pos, Blocks.CRACKED_STONE_BRICKS.getDefaultState(), 0);
                break;
            case 2:
                world.setBlockState(pos, Blocks.MOSSY_STONE_BRICKS.getDefaultState(), 0);
                break;
        }
    }

	@Override
	public boolean generate(IWorld world, StructureAccessor accessor, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		//if the position is not opaque, cancel generation
        if (!world.getBlockState(pos.down()).isOpaque()) return false;

        int length = random.nextInt(3) + 3;
        int width = random.nextInt(3) + 3;

        //place the foundation
        for (int x = -length; x <= length; x++) {
            for (int z = -width; z <= width; z++) {
                placeStoneBrick(world, random, pos.add(x, -1, z));
            }
        }

        return true;
	}
}
