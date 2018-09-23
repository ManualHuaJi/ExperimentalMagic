package mhj.expmm.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.common.lib.utils.BlockStateUtils;
import thaumcraft.common.lib.utils.Utils;

/**
 * @Author: ManualHuaJi
 */
public class TileMirrorAura extends TileEntityEXPMM implements ITickable {
    public boolean linked = false;
    public int linkX;
    public int linkY;
    public int linkZ;
    public int linkDim;
    public EnumFacing linkedFacing = EnumFacing.DOWN;
    public int instability;
    public float AuraSource = 0;
    public float AuraTo = 0;

    @Override
    public void readSyncNBT(NBTTagCompound nbttagcompound) {
        this.linked = nbttagcompound.getBoolean("linked");
        this.linkX = nbttagcompound.getInteger("linkX");
        this.linkY = nbttagcompound.getInteger("linkY");
        this.linkZ = nbttagcompound.getInteger("linkZ");
        this.linkDim = nbttagcompound.getInteger("linkDim");
        this.instability = nbttagcompound.getInteger("instability");
    }

    @Override
    public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setBoolean("linked", this.linked);
        nbttagcompound.setInteger("linkX", this.linkX);
        nbttagcompound.setInteger("linkY", this.linkY);
        nbttagcompound.setInteger("linkZ", this.linkZ);
        nbttagcompound.setInteger("linkDim", this.linkDim);
        nbttagcompound.setInteger("instability", this.instability);
        return nbttagcompound;
    }

    protected void addInstability(World targetWorld, int amt) {
        this.instability += amt;
        markDirty();
        if (targetWorld != null) {
            TileEntity te = targetWorld.getTileEntity(new BlockPos(this.linkX, this.linkY, this.linkZ));
            if ((te != null) && ((te instanceof TileMirrorAura))) {
                ((TileMirrorAura) te).instability += amt;
                if (((TileMirrorAura) te).instability < 0) {
                    ((TileMirrorAura) te).instability = 0;
                }
                te.markDirty();
            }
        }
    }

    public void restoreLink() {
        if (isDestinationValid()) {
            World targetWorld = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(this.linkDim);
            if (targetWorld == null) {
                return;
            }
            TileEntity te = targetWorld.getTileEntity(new BlockPos(this.linkX, this.linkY, this.linkZ));
            if ((te != null) && ((te instanceof TileMirrorAura))) {
                TileMirrorAura tm = (TileMirrorAura) te;
                tm.linked = true;
                tm.linkX = getPos().getX();
                tm.linkY = getPos().getY();
                tm.linkZ = getPos().getZ();
                tm.linkDim = this.world.provider.getDimension();
                tm.syncTile(false);
                this.linkedFacing = BlockStateUtils.getFacing(targetWorld.getBlockState(new BlockPos(this.linkX, this.linkY, this.linkZ)));
                this.linked = true;
                markDirty();
                tm.markDirty();
                syncTile(false);
            }
        }
    }

    public boolean isDestinationValid() {
        World targetWorld = DimensionManager.getWorld(this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.getTileEntity(new BlockPos(this.linkX, this.linkY, this.linkZ));
        if ((te == null) || (!(te instanceof TileMirrorAura))) {
            this.linked = false;
            markDirty();
            syncTile(false);
            return false;
        }
        TileMirrorAura tm = (TileMirrorAura) te;
        if (tm.isLinkValid()) {
            return false;
        }
        return true;
    }

    public void invalidateLink() {
        World targetWorld = DimensionManager.getWorld(this.linkDim);
        if (targetWorld == null) {
            return;
        }
        if (!Utils.isChunkLoaded(targetWorld, this.linkX, this.linkZ)) {
            return;
        }
        TileEntity te = targetWorld.getTileEntity(new BlockPos(this.linkX, this.linkY, this.linkZ));
        if ((te != null) && ((te instanceof TileMirrorAura))) {
            TileMirrorAura tm = (TileMirrorAura) te;
            tm.linked = false;
            tm.linkedFacing = EnumFacing.DOWN;
            markDirty();
            tm.markDirty();
            tm.syncTile(false);
        }
    }

    public boolean isLinkValid() {
        if (!this.linked) {
            return false;
        }
        World targetWorld = DimensionManager.getWorld(this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.getTileEntity(new BlockPos(this.linkX, this.linkY, this.linkZ));
        if ((te == null) || (!(te instanceof TileMirrorAura))) {
            this.linked = false;
            markDirty();
            syncTile(false);
            return false;
        }
        TileMirrorAura tm = (TileMirrorAura) te;
        if (!tm.linked) {
            this.linked = false;
            markDirty();
            syncTile(false);
            return false;
        }
        if ((tm.linkX != getPos().getX()) || (tm.linkY != getPos().getY()) || (tm.linkZ != getPos().getZ()) ||
                (tm.linkDim != this.world.provider.getDimension())) {
            this.linked = false;
            markDirty();
            syncTile(false);
            return false;
        }
        return true;
    }

    public boolean isLinkValidSimple() {
        if (!this.linked) {
            return false;
        }
        World targetWorld = DimensionManager.getWorld(this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.getTileEntity(new BlockPos(this.linkX, this.linkY, this.linkZ));
        if ((te == null) || (!(te instanceof TileMirrorAura))) {
            return false;
        }
        TileMirrorAura tm = (TileMirrorAura) te;
        if (!tm.linked) {
            return false;
        }
        if ((tm.linkX != getPos().getX()) || (tm.linkY != getPos().getY()) || (tm.linkZ != getPos().getZ()) ||
                (tm.linkDim != this.world.provider.getDimension())) {
            return false;
        }
        return true;
    }

    public void checkInstability() {
        if (this.instability > 64) {
            AuraHelper.polluteAura(this.world, this.pos, 1.0F, true);
            this.instability -= 64;
            markDirty();
        }
        if ((this.instability > 0) && (this.count % 100 == 0)) {
            this.instability -= 1;
        }
    }

    int count = 0;
    int inc = 40;

    @Override
    public void update() {
        if (!this.world.isRemote) {
            checkInstability();
            AuraSource = AuraHelper.getVis(this.world, pos);
            AuraTo = AuraHelper.getVis(this.world, new BlockPos(this.linkX, this.linkY, this.linkZ));
            float AuraVaule = (AuraSource + AuraTo) / 2;
            if (AuraSource != AuraTo) {
                AuraHelper.addVis(this.world, new BlockPos(this.linkX, this.linkY, this.linkZ), -AuraTo);
                AuraHelper.addVis(this.world, this.pos, -AuraTo);
                AuraHelper.addVis(this.world, new BlockPos(this.linkX, this.linkY, this.linkZ), AuraVaule);
                AuraHelper.addVis(this.world, this.pos, AuraVaule);
            }
            if (this.count++ % this.inc == 0) {
                if (!isLinkValidSimple()) {
                    if (this.inc < 600) {
                        this.inc += 20;
                    }
                    restoreLink();
                } else {
                    this.inc = 40;
                }
            }
        }
    }
}
