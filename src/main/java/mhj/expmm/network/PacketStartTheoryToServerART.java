package mhj.expmm.network;

import io.netty.buffer.ByteBuf;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashSet;
import java.util.Set;

public class PacketStartTheoryToServerART extends Object implements IMessage, IMessageHandler<PacketStartTheoryToServerART, IMessage> {
    private long pos;

    private Set<String> aids = new HashSet();

    public PacketStartTheoryToServerART(BlockPos pos, Set<String> aids) {

        this.pos = pos.toLong();
        this.aids = aids;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeLong(this.pos);
        buffer.writeByte(this.aids.size());
        for (String aid : this.aids)
            ByteBufUtils.writeUTF8String(buffer, aid);
    }

    public void fromBytes(ByteBuf buffer) {
        this.pos = buffer.readLong();
        int s = buffer.readByte();
        for (int a = 0; a < s; a++)
            this.aids.add(ByteBufUtils.readUTF8String(buffer));
    }

    public IMessage onMessage(final PacketStartTheoryToServerART message, final MessageContext ctx) {
        IThreadListener mainThread = ctx.getServerHandler().player.getServerWorld();
        mainThread.addScheduledTask(() -> {
            World world = ctx.getServerHandler().player.getServerWorld();
            Entity player = ctx.getServerHandler().player;
            BlockPos bp = BlockPos.fromLong(message.pos);
            if (world != null && player != null && player instanceof EntityPlayer && bp != null) {
                TileEntity te = world.getTileEntity(bp);
                if (te != null && te instanceof TileAdvancedResearchTable) {
                    ((TileAdvancedResearchTable) te).startNewTheory((EntityPlayer) player, message.aids);
                }
            }

        });
        return null;
    }

    public PacketStartTheoryToServerART() {
    }
}
