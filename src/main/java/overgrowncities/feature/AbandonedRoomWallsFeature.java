package overgrowncities.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class AbandonedRoomWallsFeature extends Feature<DefaultFeatureConfig> {
    public AbandonedRoomWallsFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
        super(configDeserializer);
    }

    private static boolean isStoneBrick(IWorld world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state == Blocks.STONE_BRICKS.getDefaultState() || state == Blocks.CRACKED_STONE_BRICKS.getDefaultState() || state == Blocks.MOSSY_STONE_BRICKS.getDefaultState();
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
        boolean isGround = false;
        for (int x = -15; x < 32; x++) {
            for (int z = -15; z < 32; z++) {
                //we are at ground level
                BlockPos localPos = pos.add(x, world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX() + x, pos.getZ() + z) - 1, z);
                if (world.getBlockState(localPos) == Blocks.GRASS.getDefaultState()) {
                    isGround = true;
                    break;
                }
            }
        }

        if (isGround) {
            for (int x = -15; x < 32; x++) {
                for (int z = -15; z < 32; z++) {
                    //we are at ground level
                    BlockPos localPos = pos.add(x, world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX() + x, pos.getZ() + z) - 1, z);

                    //castle transformer
                    boolean n = isStoneBrick(world, localPos.north());
                    boolean e = isStoneBrick(world, localPos.east());
                    boolean s = isStoneBrick(world, localPos.south());
                    boolean w = isStoneBrick(world, localPos.west());
                    boolean center = isStoneBrick(world, localPos);

                    //check center
                    if (center) {
                        //if center is stone brick, that means we can check the sides for grass/air
                        if (!n || !e || !s || !w) {
                            //next to grass: build wall
                            for (int i = 0; i < 4; i++) {
                                placeStoneBrick(world, random, localPos.up(i + 1));
                            }
                        }
                        //place roof
                        placeStoneBrick(world, random, localPos.up(5));
                    }
                }
            }
        }

        return true;
	}
}
