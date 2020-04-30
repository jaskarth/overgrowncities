package overgrowncities.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class BuildingDestructionFeature extends Feature<DefaultFeatureConfig> {
    public BuildingDestructionFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
        super(configDeserializer);
    }

    private static final Map<Block, Block> BLOCK_TRANSFORM_MAP;
    static {
        BLOCK_TRANSFORM_MAP = new HashMap<Block, Block>();
        BLOCK_TRANSFORM_MAP.put(Blocks.CYAN_TERRACOTTA, Blocks.GRAY_CONCRETE);
        BLOCK_TRANSFORM_MAP.put(Blocks.GRAY_CONCRETE, Blocks.BLACK_CONCRETE);
        BLOCK_TRANSFORM_MAP.put(Blocks.LIGHT_GRAY_CONCRETE, Blocks.GRAY_CONCRETE);
        BLOCK_TRANSFORM_MAP.put(Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER);
        BLOCK_TRANSFORM_MAP.put(Blocks.GRAY_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER);
        BLOCK_TRANSFORM_MAP.put(Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
        BLOCK_TRANSFORM_MAP.put(Blocks.POLISHED_ANDESITE_STAIRS, Blocks.ANDESITE_STAIRS);
        BLOCK_TRANSFORM_MAP.put(Blocks.POLISHED_ANDESITE_SLAB, Blocks.ANDESITE_SLAB);
        BLOCK_TRANSFORM_MAP.put(Blocks.ANDESITE_SLAB, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.STONE_SLAB, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.STONE_PRESSURE_PLATE, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.IRON_BARS, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.IRON_TRAPDOOR, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.ANDESITE_WALL, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.STONE_BRICK_WALL, Blocks.AIR);
        BLOCK_TRANSFORM_MAP.put(Blocks.STONE_BUTTON, Blocks.AIR);
    }

    private static <T extends Comparable<T>> BlockState syncBlockProperties(BlockState newBlock, BlockState oldBlock, Property<T> property){
        return newBlock.with((Property<T>)property, (T)oldBlock.get(property));
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

    /**
     * Not for regular worldgen. This is to be called for every block position in a structure's pieces generate method
     */
	@Override
	public boolean generate(IWorld world, StructureAccessor accessor, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        BlockState currentBlock = world.getBlockState(pos);

        if(BLOCK_TRANSFORM_MAP.containsKey(currentBlock.getBlock())) {
            Block aboveBlock = world.getBlockState(pos.up()).getBlock();

            if (random.nextFloat() < 0.05f &&
                    BLOCK_TRANSFORM_MAP.containsKey(world.getBlockState(pos.up()).getBlock()) &&
                    aboveBlock != Blocks.SCAFFOLDING) {

                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
                removeAttachedBlocks(world, pos);
            }
            else if (random.nextFloat() < 0.15f) {
                BlockState newBlock = BLOCK_TRANSFORM_MAP.get(currentBlock.getBlock()).getDefaultState();
                if (newBlock != Blocks.AIR.getDefaultState()) {
                    for (Property<?> property : currentBlock.getProperties()) {
                        newBlock = syncBlockProperties(newBlock, currentBlock, property);
                    }

                    world.setBlockState(pos, newBlock, 2);
                } else {
                    removeAttachedBlocks(world, pos);
                }
            }
        }

        return true;
	}
}
