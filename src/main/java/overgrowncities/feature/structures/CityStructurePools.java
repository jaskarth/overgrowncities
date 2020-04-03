package overgrowncities.feature.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import overgrowncities.OvergrownCities;

public class CityStructurePools {
    static {
    	//adds a ruined/abandoned look to buildings and roads
		ImmutableList<StructureProcessor> destructionRules = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LIGHT_GRAY_CONCRETE_POWDER, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LIGHT_GRAY_CONCRETE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.IRON_TRAPDOOR, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE_SLAB, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BUTTON, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_PRESSURE_PLATE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.ANDESITE_WALL, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_SLAB, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.IRON_BARS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CYAN_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SMOOTH_STONE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),

				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SMOOTH_STONE, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LIGHT_GRAY_CONCRETE_POWDER, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_CONCRETE_POWDER.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LIGHT_GRAY_CONCRETE, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.CYAN_TERRACOTTA.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_WALL, 0.03F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_WALL.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_WALL, 0.03F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBBLESTONE_WALL.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_WALL, 0.03F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE_WALL.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE_STAIRS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE_SLAB, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.ANDESITE_WALL, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBBLESTONE_WALL.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE_SLAB, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CYAN_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_CONCRETE.getDefaultState()),

				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICKS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICK_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICK_SLAB, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.PINK_TERRACOTTA, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.ACACIA_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_ANDESITE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_WART_BLOCK, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRANITE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE_SLAB, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_DOOR, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.REDSTONE_LAMP, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.ORANGE_BED, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POTTED_FERN, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.REDSTONE_LAMP, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.JUNGLE_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),

				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.YELLOW_STAINED_GLASS_PANE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.03F), AlwaysTrueRuleTest.INSTANCE, Blocks.WHITE_STAINED_GLASS_PANE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_STAINED_GLASS_PANE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.03F), AlwaysTrueRuleTest.INSTANCE, Blocks.BLACK_STAINED_GLASS_PANE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICKS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.PINK_TERRACOTTA.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICKS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_STAIRS.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICKS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BRICK_STAIRS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE_STAIRS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.POLISHED_GRANITE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE_STAIRS, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRANITE_STAIRS.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE_SLAB, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRANITE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_WART_BLOCK, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.PINK_TERRACOTTA.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.PINK_TERRACOTTA, 0.08F), AlwaysTrueRuleTest.INSTANCE, Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.JUNGLE_STAIRS, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.JUNGLE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.JUNGLE_STAIRS, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.JUNGLE_PRESSURE_PLATE.getDefaultState()),

				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_DOOR, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_SLAB, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_PLANKS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SPRUCE_TRAPDOOR, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LADDER, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POTTED_LILY_OF_THE_VALLEY, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.POTTED_DEAD_BUSH, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BIRCH_LEAVES, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.LARGE_FERN, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.TALL_GRASS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.QUARTZ_BLOCK, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.QUARTZ_SLAB, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.QUARTZ_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),

				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.QUARTZ_BLOCK, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.QUARTZ_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.QUARTZ_BLOCK, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.QUARTZ_STAIRS.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.QUARTZ_STAIRS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.QUARTZ_SLAB.getDefaultState()),

				//place rare cobwebs
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.AIR, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),

				//place RED_NETHER_BRICK_SLAB or END_STONE_BRICK_SLAB for where you want extra debris to be placed in the building (on the floor usually)
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.18F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_PRESSURE_PLATE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.NORTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.WEST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.SOUTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.EAST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.06F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_BUTTON.getDefaultState().with(StoneButtonBlock.FACE, WallMountLocation.FLOOR).with(StoneButtonBlock.FACING, Direction.NORTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_BUTTON.getDefaultState().with(StoneButtonBlock.FACE, WallMountLocation.FLOOR).with(StoneButtonBlock.FACING, Direction.EAST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.RED_NETHER_BRICK_SLAB, 1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()), //remove excess remaining red nether brick slabs

				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.18F), AlwaysTrueRuleTest.INSTANCE, Blocks.JUNGLE_PRESSURE_PLATE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.NORTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.WEST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.SOUTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.EAST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.06F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICK_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.BRICKS.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.JUNGLE_BUTTON.getDefaultState().with(StoneButtonBlock.FACE, WallMountLocation.FLOOR).with(StoneButtonBlock.FACING, Direction.NORTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.JUNGLE_BUTTON.getDefaultState().with(StoneButtonBlock.FACE, WallMountLocation.FLOOR).with(StoneButtonBlock.FACING, Direction.EAST)),
		new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.END_STONE_BRICK_SLAB, 1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()) //remove excess remaining end stone brick slab
		)));

		ImmutableList<StructureProcessor> pathModifier = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.18F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_PRESSURE_PLATE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.NORTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.WEST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.SOUTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.01F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_STAIRS.getDefaultState().with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.FACING, Direction.EAST)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.06F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_SLAB.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_BUTTON.getDefaultState().with(StoneButtonBlock.FACE, WallMountLocation.FLOOR).with(StoneButtonBlock.FACING, Direction.NORTH)),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_BUTTON.getDefaultState().with(StoneButtonBlock.FACE, WallMountLocation.FLOOR).with(StoneButtonBlock.FACING, Direction.EAST)),
				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRAY_CONCRETE_POWDER), new BlockMatchRuleTest(Blocks.WATER), Blocks.GRAY_CONCRETE.getDefaultState()),
				new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRAY_CONCRETE_POWDER, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_CONCRETE.getDefaultState()),
				new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()))));




		//Actual connections for the city
		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":streets/start"), new Identifier("empty"), ImmutableList.of(
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/start/street4way", pathModifier), 1)),
				StructurePool.Projection.TERRAIN_MATCHING));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":buildings"), new Identifier(OvergrownCities.MOD_ID+":streets/straight/street_terminator"), ImmutableList.of(
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/testbuilding", destructionRules), 1),
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/residential_1", destructionRules), 1),
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":buildings/business_1", destructionRules), 1)),
				StructurePool.Projection.RIGID));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(OvergrownCities.MOD_ID+":streets/straight"), new Identifier(OvergrownCities.MOD_ID+":streets/straight/street_terminator"), ImmutableList.of(
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/straight/street1", pathModifier), 1),
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/straight/street2", pathModifier), 1),
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/straight/street3", pathModifier), 1),
				new Pair<>(new SinglePoolElement(OvergrownCities.MOD_ID+":streets/straight/street4", pathModifier), 1)),
				StructurePool.Projection.TERRAIN_MATCHING));
	}

	public static void initialize() {
	}
}
