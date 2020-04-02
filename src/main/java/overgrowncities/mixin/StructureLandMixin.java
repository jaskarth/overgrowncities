package overgrowncities.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import overgrowncities.OvergrownCities;
import overgrowncities.feature.OgFeatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;

@Mixin(Feature.class)
public abstract class StructureLandMixin {

    @Shadow @Final @Mutable static List<StructureFeature<?>> JIGSAW_STRUCTURES;

    @Inject(method = "<clinit>",
            at = @At(value = "RETURN"))
    private static void addToStructureTerrainList(CallbackInfo info) {
        List<StructureFeature<?>> structureList = new ArrayList<>(Feature.JIGSAW_STRUCTURES);
        structureList.add(OgFeatures.CITY_STRUCTURE);
        structureList.add(OgFeatures.WAREHOUSE_STRUCTURE);
        JIGSAW_STRUCTURES = Collections.unmodifiableList(structureList);
    }
}