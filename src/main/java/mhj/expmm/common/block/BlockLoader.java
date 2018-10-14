package mhj.expmm.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static mhj.expmm.common.block.BlocksEXPMM.mirrorAura;
import static mhj.expmm.common.block.BlocksEXPMM.referenceBookcase;

/**
 * @Author: ManualHuaJi
 */

public class BlockLoader {
    public static Block[] blocks = {referenceBookcase};

    public static void regitser() {
        registerBlock();
        registerBlockSpecial(mirrorAura, MirrorAuraItem.class);
        registerRender();
    }


    public static void registerBlock() {
        for (Block block : blocks) {
            ForgeRegistries.BLOCKS.register(block);
            ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
            ForgeRegistries.ITEMS.register(itemBlock);
        }
    }

    public static Block registerBlockSpecial(Block block, Class clazz) {
        ForgeRegistries.BLOCKS.register(block);
        try {
            ItemBlock itemBlock = (ItemBlock) clazz.getConstructors()[0].newInstance(new Object[]{block});
            itemBlock.setRegistryName(block.getRegistryName());
            ForgeRegistries.ITEMS.register(itemBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return block;
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for (Block block : blocks) {
            ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
        }
    }

}

