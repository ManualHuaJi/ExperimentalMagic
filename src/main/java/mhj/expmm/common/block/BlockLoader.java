package mhj.expmm.common.block;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.tile.TileEntityLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static mhj.expmm.common.block.BlocksEXPMM.mirrorAura;
import static mhj.expmm.common.block.BlocksEXPMM.referenceBookshelf;

/**
 * @Author: ManualHuaJi
 */
@Mod.EventBusSubscriber(modid = ExperimentalMagic.MODID)
public class BlockLoader {

    public static Block blocks[] = {mirrorAura, referenceBookshelf};

    @SubscribeEvent
    public static void registerBlcok(RegistryEvent.Register<Block> blockRegister) {
        IForgeRegistry<Block> br = blockRegister.getRegistry();
        for (final Block block : blocks) {
            br.register(block);
        }
        TileEntityLoader.registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> itemRegister) {
        final IForgeRegistry<Item> registry = itemRegister.getRegistry();

        for (final Block block1 : blocks) {
            final ItemBlock[] items = {
                    new ItemBlock(block1)
            };
            for (final ItemBlock item : items) {
                final Block block = item.getBlock();
                registry.register(item.setRegistryName(item.getUnlocalizedName()));
            }
        }


    }
}
