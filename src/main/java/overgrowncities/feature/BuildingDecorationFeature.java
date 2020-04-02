package overgrowncities.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class BuildingDecorationFeature extends Feature<DefaultFeatureConfig> {
    public BuildingDecorationFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
        super(configDeserializer);
    }

    /**
     * Not for regular worldgen. This is to be called for every block position in a structure's pieces generate method
     */
    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos position, DefaultFeatureConfig config) {
        BlockState currentBlock = world.getBlockState(position);

        if(random.nextFloat() < 0.05f && currentBlock.getMaterial() == Material.AIR){
            if(world.getBlockState(position.down()).isOpaque()){
                float chance = random.nextFloat();
                if(chance < 0.40f){
                    world.setBlockState(position, Blocks.STONE_BUTTON.getDefaultState().with(StoneButtonBlock.FACING, random.nextBoolean() ? Direction.WEST : Direction.NORTH).with(StoneButtonBlock.FACE, WallMountLocation.FLOOR), 2);
                }
                else if(chance < 0.60f){
                    world.setBlockState(position, Blocks.STONE_PRESSURE_PLATE.getDefaultState(), 2);
                }
                else if(chance < 0.80f){
                    world.setBlockState(position, Blocks.ANDESITE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM), 2);
                }
                else if(chance < 0.90f){
                    world.setBlockState(position, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.fromHorizontal(random.nextInt(4))), 2);
                }
                else{
                    world.setBlockState(position, Blocks.ANDESITE.getDefaultState(), 2);
                }
            }
            else{
                for (Direction direction : Direction.Type.HORIZONTAL)
                {
                    BlockState vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.valueOf(true));
                    if (vineBlock.canPlaceAt(world, position))
                    {
                        world.setBlockState(position, vineBlock, 2);
                        break;
                    }
                    else{
                        BlockState aboveBlock = world.getBlockState(position.up());
                        if(aboveBlock.getBlock() instanceof SlabBlock && aboveBlock.get(SlabBlock.TYPE) == SlabType.BOTTOM){
                            world.setBlockState(position.up(), Blocks.ANDESITE.getDefaultState(), 2);
                            world.setBlockState(position, vineBlock.with(VineBlock.UP, true), 2);

                            for(int down = 0; down <= random.nextInt(8); down++){
                                if(world.getBlockState(position.down(down)).getMaterial() == Material.AIR)
                                    world.setBlockState(position.down(down), vineBlock, 2);
                            }

                            break;
                        }
                        else if (aboveBlock.getBlock() == Blocks.VINE)
                        {
                            world.setBlockState(position, world.getBlockState(position), 2);
                            break;
                        }
                    }
                }
            }
        }

        return true;
    }


    private static void removeAttachedBlocks(IWorld world, BlockPos position){
        for (Direction direction : Direction.Type.HORIZONTAL) {
            Block neighboringBlock = world.getBlockState(position.offset(direction)).getBlock();
            if (neighboringBlock instanceof StoneButtonBlock || neighboringBlock instanceof TrapdoorBlock) {
                world.setBlockState(position.offset(direction), Blocks.AIR.getDefaultState(), 2);
            }

            neighboringBlock = world.getBlockState(position.up()).getBlock();
            if(neighboringBlock instanceof PressurePlateBlock){
                world.setBlockState(position.up(), Blocks.AIR.getDefaultState(), 2);
            }
        }
    }
}
