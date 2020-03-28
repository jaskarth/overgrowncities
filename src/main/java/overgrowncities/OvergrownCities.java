package overgrowncities;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import overgrowncities.biome.OvergrowthWildernessBiome;
import overgrowncities.init.OvergrownBiomes;
import overgrowncities.world.OvergrowthDimension;

public class OvergrownCities implements ModInitializer {
	public static EntityPlacer FIND_SURFACE = (entity, world, dim, offsetX, offsetZ) -> new BlockPattern.TeleportTarget(
			new Vec3d(entity.getBlockPos().getX(),
					world.getChunk(entity.getBlockPos().getX() >> 4,
							entity.getBlockPos().getZ() >> 4)
							.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING,
									entity.getBlockPos().getX() & 15,
									entity.getBlockPos().getZ() & 15) + 1,
					entity.getBlockPos().getZ()),
			entity.getVelocity(),
			(int)entity.yaw);

	public static DimensionType OVERGROWTH;

	@Override
	public void onInitialize() {
		OvergrownBiomes.init();

		OVERGROWTH = FabricDimensionType.builder()
				.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
				.factory(OvergrowthDimension::new)
				.defaultPlacer(FIND_SURFACE)
				.skyLight(true)
				.buildAndRegister(new Identifier("overgrowncities", "overgrowth"));
		System.out.println("Registering The Overgrowth at id " + OVERGROWTH.getRawId());
	}
}