package mhj.expmm.common.tile;

import mhj.expmm.ExperimentalMagic;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @Author: ManualHuaJi
 */
public class TileEntityLoader {
    public static Class[] tileEntities = {
            TileReferenceBookcase.class,
            TileMirrorAura.class
    };

    public static void registerTileEntities() {
        for (Class c : tileEntities) {
            GameRegistry.registerTileEntity(c, ExperimentalMagic.MODID + c.getSimpleName());
        }
    }
}
