package mhj.expmm.tile;

import mhj.expmm.ExperimentalMagic;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @Author: ManualHuaJi
 */
public class TileEntityLoader {
    public static Class[] tileEntities = {
            TileReferenceBookcase.class,

    };


    public static void registerTileEntity() {
        for(Class c:tileEntities){
        GameRegistry.registerTileEntity(c, new ResourceLocation(ExperimentalMagic.MODID, c.getSimpleName()));
    }}
}
