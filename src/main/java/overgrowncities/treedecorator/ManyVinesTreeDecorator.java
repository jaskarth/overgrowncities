package overgrowncities.treedecorator;

import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class ManyVinesTreeDecorator extends TreeDecorator {
    private static Random random = new Random();

    public ManyVinesTreeDecorator() {
        super(null);
    }

    // FIXME: method_27370 = isAir, this is not yet mapped.
    
    public void generate(IWorld world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> set, BlockBox box) {
        leavesPositions.forEach((blockPos) -> {
            BlockPos blockPos5;
            blockPos5 = blockPos.west();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.generateVines(world, blockPos5, VineBlock.EAST, set, box);
            }

            blockPos5 = blockPos.east();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.generateVines(world, blockPos5, VineBlock.WEST, set, box);
            }

            blockPos5 = blockPos.north();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.generateVines(world, blockPos5, VineBlock.SOUTH, set, box);
            }

            blockPos5 = blockPos.south();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.generateVines(world, blockPos5, VineBlock.NORTH, set, box);
            }
        });

        logPositions.forEach((blockPos) -> {
            BlockPos blockPos5;
            blockPos5 = blockPos.west();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.placeVine(world, blockPos5, VineBlock.EAST, set, box);
            }
            blockPos5 = blockPos.east();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.placeVine(world, blockPos5, VineBlock.WEST, set, box);
            }
            blockPos5 = blockPos.north();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.placeVine(world, blockPos5, VineBlock.SOUTH, set, box);
            }
            blockPos5 = blockPos.south();
            if (AbstractTreeFeature.method_27370(world, blockPos5)) {
                this.placeVine(world, blockPos5, VineBlock.NORTH, set, box);
            }
        });
    }

    private void generateVines(ModifiableTestableWorld world, BlockPos blockPos, BooleanProperty booleanProperty, Set<BlockPos> set, BlockBox blockBox) {
        this.placeVine(world, blockPos, booleanProperty, set, blockBox);
        int i = 5 + random.nextInt(4);

        for(blockPos = blockPos.down(); AbstractTreeFeature.method_27370(world, blockPos) && i > 0; --i) {
            this.placeVine(world, blockPos, booleanProperty, set, blockBox);
            blockPos = blockPos.down();
        }

    }

    @Override
    public <T> T serialize(DynamicOps<T> ops) {
        return null;
    }
}
