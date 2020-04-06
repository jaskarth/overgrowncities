package overgrowncities;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import overgrowncities.block.OvergrowthPortal;
import overgrowncities.init.OvergrownBiomes;
import overgrowncities.init.OvergrownFeatures;
import overgrowncities.world.OvergrowthDimension;
import overgrowncities.feature.OgFeatures;

public class OvergrownCities implements ModInitializer {
	//code kindly yeeted from the hallow
	public static EntityPlacer FIND_SURFACE = (entity, world, dim, offsetX, offsetZ) -> {
		world.setBlockState(entity.getBlockPos(), OvergrownCities.PORTAL.getDefaultState());

		return new BlockPattern.TeleportTarget(
				new Vec3d(entity.getBlockPos().getX(),
						world.getChunk(entity.getBlockPos().getX() >> 4,
								entity.getBlockPos().getZ() >> 4)
								.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING,
										entity.getBlockPos().getX() & 15,
										entity.getBlockPos().getZ() & 15) + 1,
						entity.getBlockPos().getZ()),
				entity.getVelocity(),
				(int)entity.yaw);
	};


	public static DimensionType OVERGROWTH;
	public static String MOD_ID = "overgrowncities";
	public static Block PORTAL;

	@Override
	public void onInitialize() {
        FindBiomeCommand.register();

		PORTAL = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "portal"), new OvergrowthPortal(FabricBlockSettings.of(Material.STONE).hardness(1.5f).resistance(3f).build()));
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "portal"), new BlockItem(PORTAL, new Item.Settings().group(ItemGroup.DECORATIONS)));

		OvergrownFeatures.init();
		OvergrownBiomes.init();
		OgFeatures.setupFeatures();
		OgFeatures.addWarehouseToOverworld();

		OVERGROWTH = FabricDimensionType.builder()
				.biomeAccessStrategy(HorizontalVoronoiBiomeAccessType.INSTANCE)
				.factory(OvergrowthDimension::new)
				.defaultPlacer(FIND_SURFACE)
				.skyLight(true)
				.buildAndRegister(new Identifier(MOD_ID, "overgrowth"));
		System.out.println("Registering The Overgrowth at id " + OVERGROWTH.getRawId());
	}
}