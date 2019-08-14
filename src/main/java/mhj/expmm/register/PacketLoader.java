package mhj.expmm.register;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.network.PacketStartTheoryToServerART;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketLoader {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("expmm");

    public static void init() {

        INSTANCE.registerMessage(PacketStartTheoryToServerART.class, PacketStartTheoryToServerART.class, 0, Side.SERVER);
    }
}
