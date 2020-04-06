package overgrowncities.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class BuildingDebrisFeature extends Feature<DefaultFeatureConfig> {
    public BuildingDebrisFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
        super(configDeserializer);
    }

    /**
     * Not for regular worldgen. This is to be called for every block position in a structure's pieces generate method
     */
    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos position, DefaultFeatureConfig config) {
        BlockState currentBlock = world.getBlockState(position);

        if(random.nextFloat() < 0.05f && currentBlock.getMaterial() == Material.AIR){
            BlockState belowBlock = world.getBlockState(position.down());
            if(( belowBlock.isOpaque() &&
                    !(belowBlock.getBlock() instanceof StairsBlock ||
                      belowBlock.getBlock() instanceof SlabBlock ||
                      belowBlock.getBlock() instanceof WallBlock) ) ||
                    (belowBlock.getBlock() instanceof StairsBlock && belowBlock.get(StairsBlock.HALF) == BlockHalf.TOP) ||
                    (belowBlock.getBlock() instanceof SlabBlock && belowBlock.get(SlabBlock.TYPE) == SlabType.TOP)){

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
        }

        return true;
    }
}
