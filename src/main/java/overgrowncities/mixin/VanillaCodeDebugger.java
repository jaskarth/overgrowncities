package overgrowncities.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import overgrowncities.OvergrownCities;

import java.util.List;
import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;

@Mixin(targets = "net.minecraft.structure.pool.StructurePoolBasedGenerator$StructurePoolGenerator")
public abstract class VanillaCodeDebugger {

    @Inject(method = "<init>(Lnet/minecraft/util/Identifier;ILnet/minecraft/structure/pool/StructurePoolBasedGenerator$PieceFactory;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;Lnet/minecraft/util/math/BlockPos;Ljava/util/List;Ljava/util/Random;)V",
            at = @At("HEAD"))
    private void debug(Identifier startingPool, int maxSize, StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator<?> chunkGenerator, StructureManager structureManager, BlockPos blockPos, List<StructurePiece> children, Random random, CallbackInfo ci) {
        String angryDebugging = "LETS SEE YOU BREAK THIS BREAKPOINT MOTHERF***ING INTELLIJ" + random.nextBoolean();
        String angryDebugging2 = angryDebugging +"!";
    }
}