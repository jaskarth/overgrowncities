package overgrowncities.feature.structures;

import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeatureConfig;
import overgrowncities.OvergrownCities;
import overgrowncities.feature.OgFeatures;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class WarehouseGenerator {
   private static final Identifier SE_TEMPLATE = new Identifier(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_se_corner");
   private static final Identifier SW_TEMPLATE = new Identifier(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_sw_corner");
   private static final Identifier NE_TEMPLATE = new Identifier(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_ne_corner");
   private static final Identifier NW_TEMPLATE = new Identifier(OvergrownCities.MOD_ID+":buildings/warehouse/warehouse_nw_corner");
   private static final Map<Identifier, BlockPos> PIECES_OFFSET;
   private static final Map<Identifier, BlockPos> COUNTER_OFFSET;
   static {
      PIECES_OFFSET = ImmutableMap.of(
              SE_TEMPLATE, new BlockPos(-8, 0, -6),
              SW_TEMPLATE, new BlockPos(9, 0, -6),
              NE_TEMPLATE, new BlockPos(-8, 0, 6),
              NW_TEMPLATE, new BlockPos(9, 0, 6)
              );

      COUNTER_OFFSET = ImmutableMap.of(
              SE_TEMPLATE, new BlockPos(8, 0, 6),
              SW_TEMPLATE, new BlockPos(-9, 0, 6),
              NE_TEMPLATE, new BlockPos(8, 0, -6),
              NW_TEMPLATE, new BlockPos(-9, 0, -6)
      );
   }

   public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces, Random random) {
      pieces.add(new WarehouseGenerator.Piece(manager, SE_TEMPLATE, pos, rotation));
      pieces.add(new WarehouseGenerator.Piece(manager, SW_TEMPLATE, pos, rotation));
      pieces.add(new WarehouseGenerator.Piece(manager, NE_TEMPLATE, pos, rotation));
      pieces.add(new WarehouseGenerator.Piece(manager, NW_TEMPLATE, pos, rotation));
   }


   public static class Piece extends SimpleStructurePiece {
      private final Identifier template;
      private final BlockRotation rotation;

      public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
         super(OgFeatures.WAREHOUSE_PIECES, 0);
         this.template = identifier;
         BlockPos blockPos = (BlockPos) WarehouseGenerator.COUNTER_OFFSET.get(identifier);
         this.pos = pos.add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
         this.rotation = rotation;
         this.initializeStructureData(manager);
      }

      public Piece(StructureManager manager, CompoundTag tag) {
         super(OgFeatures.WAREHOUSE_PIECES, tag);
         this.template = new Identifier(tag.getString("Template"));
         this.rotation = BlockRotation.valueOf(tag.getString("Rot"));
         this.initializeStructureData(manager);
      }

      private void initializeStructureData(StructureManager manager) {
         Structure structure = manager.getStructureOrBlank(this.template);
         StructurePlacementData structurePlacementData = (new StructurePlacementData())
                 .setRotation(this.rotation)
                 .setMirrored(BlockMirror.NONE)
                 .setPosition((BlockPos) WarehouseGenerator.PIECES_OFFSET.get(this.template))
                 .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);

         this.setStructureData(structure, this.pos, structurePlacementData);
      }

      protected void toNbt(CompoundTag tag) {
         super.toNbt(tag);
         tag.putString("Template", this.template.toString());
         tag.putString("Rot", this.rotation.name());
      }

      protected void handleMetadata(String metadata, BlockPos pos, IWorld world, Random random, BlockBox boundingBox) {
//         if ("chest".equals(metadata)) {
//            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
//            BlockEntity blockEntity = world.getBlockEntity(pos.down());
//            if (blockEntity instanceof ChestBlockEntity) {
//               ((ChestBlockEntity)blockEntity).setLootTable(LootTables.IGLOO_CHEST_CHEST, random.nextLong());
//            }
//         }
      }

      public boolean generate(IWorld world, ChunkGenerator<?> generator, Random random, BlockBox box, ChunkPos pos) {
         StructurePlacementData structurePlacementData = (new StructurePlacementData()).setRotation(this.rotation).setMirrored(BlockMirror.NONE).setPosition((BlockPos) WarehouseGenerator.PIECES_OFFSET.get(this.template)).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
         BlockPos blockPos = (BlockPos) WarehouseGenerator.COUNTER_OFFSET.get(this.template);
         this.pos.add(Structure.method_15171(structurePlacementData, new BlockPos(-blockPos.getX(), 0, -blockPos.getZ())));

         boolean created = super.generate(world, generator, random, box, pos);
         if(created){
            for(BlockPos boxPosition : BlockPos.iterate(box.minX, this.pos.getY(), box.minZ, box.maxX, this.pos.getY()+17, box.maxZ)){
               OgFeatures.BUILDING_DESTRUCTION.generate(world, generator, random, boxPosition, FeatureConfig.DEFAULT);
            }

            for(BlockPos boxPosition : BlockPos.iterate(box.minX, this.pos.getY(), box.minZ, box.maxX, this.pos.getY()+17, box.maxZ)){
               OgFeatures.BUILDING_VEGETATION.generate(world, generator, random, boxPosition, FeatureConfig.DEFAULT);
            }
         }
         return created;
      }
   }
}
