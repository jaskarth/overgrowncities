package overgrowncities.feature.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.*;
import net.minecraft.util.Identifier;
import overgrowncities.OvergrownCities;

public class CityStructurePools {
    static {

		ImmutableList<StructureProcessor> destructionRules = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LIGHT_GRAY_CONCRETE_POWDER, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LIGHT_GRAY_CONCRETE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_WALL, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.IRON_TRAPDOOR, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE_SLAB, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BUTTON, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_PRESSURE_PLATE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.ANDESITE_WALL, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_SLAB, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.IRON_BARS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CYAN_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())
		)));

		ImmutableList<StructureProcessor> pathModifier = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRAY_CONCRETE_POWDER), new BlockMatchRuleTest(Blocks.WATER), Blocks.GRAY_CONCRETE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRAY_CONCRETE_POWDER, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_CONCRETE.getDefaultState()))));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":streets/start"), new Identifier("empty"), ImmutableList.of(
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/start/street4way", pathModifier), 2)),
				StructurePool.Projection.TERRAIN_MATCHING));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":buildings"), new Identifier("village/plains/terminators"), ImmutableList.of(
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/warehouse_se_corner", destructionRules), 2)),
				StructurePool.Projection.RIGID));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":buildings/warehouse"), new Identifier("village/plains/terminators"), ImmutableList.of(
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_sw_corner", destructionRules), 2),
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_ne_corner", destructionRules), 2),
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_nw_corner", destructionRules), 2)),
				StructurePool.Projection.RIGID));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":streets"), new Identifier("village/plains/terminators"), ImmutableList.of(
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/street1", pathModifier), 10),
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/street2", pathModifier), 10),
				new Pair(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/street4way", pathModifier), 2)),
				StructurePool.Projection.TERRAIN_MATCHING));
	}

	public static void initialize() {
	}

    //formatted village pools for better reading
    static {
//        ImmutableList<StructureProcessor> immutableList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
//				new StructureProcessorRule(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
//				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
//				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.OAK_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.OAK_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.OAK_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STRIPPED_OAK_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
//				new StructureProcessorRule[]{
//                    new StructureProcessorRule(new BlockStateMatchRuleTest((BlockState)((BlockState)Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, (BlockState)((BlockState)Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true)),
//                    new StructureProcessorRule(new BlockStateMatchRuleTest((BlockState)((BlockState)Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true)).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, (BlockState)((BlockState)Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true)).with(PaneBlock.WEST, true)),
//                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
//                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
//                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())
//				})));
//
//        ImmutableList<StructureProcessor> immutableList2 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/plains/town_centers"), new Identifier("empty"), ImmutableList.of(
//                new Pair(new SinglePoolElement("village/plains/town_centers/plains_fountain_01", ImmutableList.of(
//                        new RuleStructureProcessor(ImmutableList.of(
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 50),
//                new Pair(new SinglePoolElement("village/plains/town_centers/plains_meeting_point_1", ImmutableList.of(
//                        new RuleStructureProcessor(ImmutableList.of(
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 50),
//                new Pair(new SinglePoolElement("village/plains/town_centers/plains_meeting_point_2"), 50),
//                new Pair(new SinglePoolElement("village/plains/town_centers/plains_meeting_point_3", ImmutableList.of(
//                        new RuleStructureProcessor(ImmutableList.of(
//                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 50)),
//                StructurePool.Projection.RIGID));
//
//        ImmutableList<StructureProcessor> pathRandomizer = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
//				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.OAK_PLANKS.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
//				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
//				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()))));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/plains/streets"), new Identifier("village/plains/terminators"), ImmutableList.of(
//                new Pair(new SinglePoolElement("village/plains/streets/corner_01", pathRandomizer), 2),
//                new Pair(new SinglePoolElement("village/plains/streets/corner_02", pathRandomizer), 2),
//                new Pair(new SinglePoolElement("village/plains/streets/corner_03", pathRandomizer), 2),
//                new Pair(new SinglePoolElement("village/plains/streets/straight_01", pathRandomizer), 4),
//                new Pair(new SinglePoolElement("village/plains/streets/straight_02", pathRandomizer), 4),
//                new Pair(new SinglePoolElement("village/plains/streets/straight_03", pathRandomizer), 7),
//                new Pair(new SinglePoolElement("village/plains/streets/straight_04", pathRandomizer), 7),
//                new Pair(new SinglePoolElement("village/plains/streets/straight_05", pathRandomizer), 3),
//                new Pair(new SinglePoolElement("village/plains/streets/straight_06", pathRandomizer), 4),
//                new Pair(new SinglePoolElement("village/plains/streets/crossroad_01", pathRandomizer), 2),
//                new Pair(new SinglePoolElement("village/plains/streets/crossroad_02", pathRandomizer), 1),
//                new Pair(new SinglePoolElement("village/plains/streets/crossroad_03", pathRandomizer), 2),
//                new Pair[]{new Pair(new SinglePoolElement("village/plains/streets/crossroad_04", pathRandomizer), 2),
//                            new Pair(new SinglePoolElement("village/plains/streets/crossroad_05", pathRandomizer), 2),
//                            new Pair(new SinglePoolElement("village/plains/streets/crossroad_06", pathRandomizer), 2),
//                            new Pair(new SinglePoolElement("village/plains/streets/turn_01", pathRandomizer), 3)}),
//                StructurePool.Projection.TERRAIN_MATCHING));
//
//
//        ImmutableList<StructureProcessor> cropRandomizer = ImmutableList.of(
//				new RuleStructureProcessor(ImmutableList.of(
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
//				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()))));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/plains/houses"), new Identifier("village/plains/terminators"), ImmutableList.of(
//				        new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_1", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_2", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_3", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_4", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_5", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_6", immutableList2), 1),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_7", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_small_house_8", immutableList2), 3),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_medium_house_1", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_medium_house_2", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_big_house_1", immutableList2), 2),
//                new Pair(new SinglePoolElement("village/plains/houses/plains_butcher_shop_1", immutableList2), 2),
//                new Pair[]{new Pair(new SinglePoolElement("village/plains/houses/plains_butcher_shop_2", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_tool_smith_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_fletcher_house_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_shepherds_house_1"), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_armorer_house_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_fisher_cottage_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_tannery_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_cartographer_1", immutableList2), 1),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_library_1", immutableList2), 5),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_library_2", immutableList2), 1),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_masons_house_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_weaponsmith_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_temple_3", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_temple_4", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_stable_1", immutableList2), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_stable_2"), 2),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_large_farm_1", cropRandomizer), 4),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_small_farm_1", cropRandomizer), 4),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_animal_pen_1"), 1),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_animal_pen_2"), 1),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_animal_pen_3"), 5),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_accessory_1"), 1),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_meeting_point_4", ImmutableList.of(
//                                new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 3),
//                        new Pair(new SinglePoolElement("village/plains/houses/plains_meeting_point_5"), 1), Pair.of(EmptyPoolElement.INSTANCE, 10)}), StructurePool.Projection.RIGID));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/plains/terminators"), new Identifier("empty"), ImmutableList.of(
//                new Pair(new SinglePoolElement("village/plains/terminators/terminator_01", pathRandomizer), 1),
//                new Pair(new SinglePoolElement("village/plains/terminators/terminator_02", pathRandomizer), 1),
//                new Pair(new SinglePoolElement("village/plains/terminators/terminator_03", pathRandomizer), 1),
//                new Pair(new SinglePoolElement("village/plains/terminators/terminator_04", pathRandomizer), 1)),
//                StructurePool.Projection.TERRAIN_MATCHING));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/plains/trees"), new Identifier("empty"), ImmutableList.of(
//				        new Pair(new FeaturePoolElement(Feature.NORMAL_TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG)), 1)),
//                StructurePool.Projection.RIGID));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/plains/decor"), new Identifier("empty"), ImmutableList.of(
//                    new Pair(new SinglePoolElement("village/plains/plains_lamp_1"), 2),
//                    new Pair(new FeaturePoolElement(Feature.NORMAL_TREE.configure(DefaultBiomeFeatures.OAK_TREE_CONFIG)), 1),
//                    new Pair(new FeaturePoolElement(Feature.FLOWER.configure(DefaultBiomeFeatures.PLAINS_FLOWER_CONFIG)), 1),
//                    new Pair(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
//                Pair.of(EmptyPoolElement.INSTANCE, 2)),
//                StructurePool.Projection.RIGID));
//
//        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier("village/common/well_bottoms"), new Identifier("empty"), ImmutableList.of(
//                    new Pair(new SinglePoolElement("village/common/well_bottom"), 1)),
//                StructurePool.Projection.RIGID));
    }
    
}
