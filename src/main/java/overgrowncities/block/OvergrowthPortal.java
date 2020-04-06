package overgrowncities.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import overgrowncities.OvergrownCities;

public class OvergrowthPortal extends Block {
    public OvergrowthPortal(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.changeDimension(OvergrownCities.OVERGROWTH);
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
