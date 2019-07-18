package mhj.expmm.register;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static mhj.expmm.block.BlocksEXPMM.referenceBookcase;

/**
 * @Author: ManualHuaJi
 */

public class BlockLoader {
    public static Block[] blocks = {referenceBookcase};

    public static void regitser() {
        registerBlock();
        registerRender();
    }


    public static void registerBlock() {
        for (Block block : blocks) {
            ForgeRegistries.BLOCKS.register(block);
            ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
            ForgeRegistries.ITEMS.register(itemBlock);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for (Block block : blocks) {
            ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
        }
    }

}

