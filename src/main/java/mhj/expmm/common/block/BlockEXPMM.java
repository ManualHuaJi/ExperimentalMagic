package mhj.expmm.common.block;

import mhj.expmm.common.CreativeTabEXPMM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @Author: ManualHuaJi
 */
public class BlockEXPMM extends Block {
    public BlockEXPMM(Material materialIn) {
        super(materialIn);

        this.setUnlocalizedName(this.getClass().getSimpleName().toLowerCase()).setRegistryName(this.getUnlocalizedName()).setCreativeTab(CreativeTabEXPMM.TAB_EXPMM);
    }
}
