package overgrowncities.block;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import overgrowncities.OvergrownCities;
import overgrowncities.world.OvergrowthDimension;

public class OvergrowthPortal extends Block {
    public OvergrowthPortal(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (player.getEntityWorld().dimension instanceof OvergrowthDimension) {
                FabricDimensions.teleport(player, DimensionType.OVERWORLD, OvergrownCities.FIND_SURFACE);
            } else {
                FabricDimensions.teleport(player, OvergrownCities.OVERGROWTH, OvergrownCities.FIND_SURFACE);
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
}
