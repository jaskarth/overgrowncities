package overgrowncities.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class BuildingVegetationFeature extends Feature<DefaultFeatureConfig> {
    public BuildingVegetationFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
        super(configDeserializer);
    }

    /**
     * Not for regular worldgen. This is to be called for every block position in a structure's pieces generate method
     */
    @Override
    public boolean generate(IWorld world, StructureAccessor accessor, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        BlockState currentBlock = world.getBlockState(pos);

        if(random.nextFloat() < 0.05f && currentBlock.getMaterial() == Material.AIR){

            //places horizontal vines
            for (Direction direction : Direction.Type.HORIZONTAL)
            {
                BlockState vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), true);
                if (vineBlock.canPlaceAt(world, pos))
                {
                    world.setBlockState(pos, vineBlock, 2);
                    break;
                }
            }


            //places vertical vines
            BlockState vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(Direction.fromHorizontal(random.nextInt(4))), true);
            BlockState aboveBlock = world.getBlockState(pos.up());

            if((aboveBlock.getBlock() == Blocks.ANDESITE_SLAB ||
                    aboveBlock.getBlock() == Blocks.STONE_SLAB ||
                    aboveBlock.getBlock() == Blocks.SMOOTH_STONE_SLAB) &&
                    aboveBlock.get(SlabBlock.TYPE) == SlabType.BOTTOM) {

                world.setBlockState(pos.up(), Blocks.ANDESITE.getDefaultState(), 2);
            }
            if(VineBlock.shouldConnectTo(world, pos.up(), Direction.UP)){
                world.setBlockState(pos, vineBlock.with(VineBlock.UP, true), 2);

                for(int down = 0; down <= random.nextInt(random.nextInt(6) + 3) + 3; down++){
                    if(world.getBlockState(pos.down(down)).getMaterial() == Material.AIR)
                        world.setBlockState(pos.down(down), vineBlock, 2);
                }
            }
            else if (aboveBlock.getBlock() == Blocks.VINE)
            {
                world.setBlockState(pos, world.getBlockState(pos.up()), 2);
            }
        }

        return true;
    }
}
