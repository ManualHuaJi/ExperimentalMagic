package mhj.expmm.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thaumcraft.Thaumcraft;

import static mhj.expmm.common.block.BlocksEXPMM.mirrorAura;
import static mhj.expmm.common.block.BlocksEXPMM.referenceBookcase;

/**
 * @Author: ManualHuaJi
 */

public class BlockLoader {

    public static Block blocks[] = {referenceBookcase};


    public static void register() {
        for (Block block : blocks) {
            registerBlock(block);
        }
    }

    public static void registerSpecial() {
        mirrorAura = (BlockEXPMM) registerBlockSpecial(new MirrorAura(), MirrorAuraItem.class);
    }

    private static Block registerBlock(Block block) {
        return registerBlock(block, new ItemBlock(block));
    }

    private static Block registerBlock(Block block, ItemBlock itemBlock) {
        ForgeRegistries.BLOCKS.register(block);
        itemBlock.setRegistryName(block.getRegistryName());
        ForgeRegistries.ITEMS.register(itemBlock);
        Thaumcraft.proxy.registerModel(itemBlock);
        return block;
    }

    private static Block registerBlockSpecial(Block block, Class clazz) {
        ForgeRegistries.BLOCKS.register(block);
        try {
            ItemBlock itemBlock = (ItemBlock) clazz.getConstructors()[0].newInstance(new Object[]{block});
            itemBlock.setRegistryName(block.getRegistryName());
            ForgeRegistries.ITEMS.register(itemBlock);
            Thaumcraft.proxy.registerModel(itemBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return block;
    }

}

