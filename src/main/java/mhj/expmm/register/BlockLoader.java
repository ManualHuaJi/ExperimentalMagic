package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.block.BlocksExpmm;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ExperimentalMagic.MODID)
public class BlockLoader {
//    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event) {
        initBlock(event.getRegistry());
        initTileEntity();
    }

    private static void initBlock(IForgeRegistry<Block> iForgeRegistry) {
        registerBlock(BlocksExpmm.advancedResearchTable);
    }

    private static void initTileEntity() {
        GameRegistry.registerTileEntity(TileAdvancedResearchTable.class, "expmm:TileAdvancedResearchTable");
    }

    private static void registerBlock(Block block) {
        ItemBlock itemBlock = new ItemBlock(block);
        ForgeRegistries.BLOCKS.register(block);
        itemBlock.setRegistryName(block.getRegistryName());
        ForgeRegistries.ITEMS.register(itemBlock);
        ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(itemBlock.getRegistryName(), "inventory"));
    }

}
