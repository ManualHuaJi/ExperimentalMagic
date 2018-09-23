package mhj.expmm.common.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

/**
 * @Author: ManualHuaJi
 */
public class TileEntityEXPMMInv extends TileEntity implements ISidedInventory {
    public NonNullList<ItemStack> inventory;
    protected int[] syncedSlots = new int[0];
    protected String customName;
    private int[] faceSlots;

    public TileEntityEXPMMInv(int inventorySize) {
        this.inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.faceSlots = new int[inventorySize];
        for (int a = 0; a < inventorySize; a++) {
            this.faceSlots[a] = a;
        }

    }


    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return (ItemStack) getItems().get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(getItems(), index, count);
        if ((!itemstack.isEmpty()) &&
                (isSyncedSlot(index))) {
            syncTile(false);
        }
        markDirty();
        return itemstack;
    }


    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack s = ItemStackHelper.getAndRemove(getItems(), index);
        if (isSyncedSlot(index)) {
            syncTile(false);
        }
        markDirty();
        return s;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        getItems().set(index, stack);
        if (stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        markDirty();
        if (isSyncedSlot(index)) {
            syncTile(false);
        }
    }

    @Override
    public String getName() {
        return hasCustomName() ? this.customName : "container.thaumcraft";
    }

    @Override
    public boolean hasCustomName() {
        return (this.customName != null) && (this.customName.length() > 0);
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    private boolean isSyncedSlot(int slot) {
        for (int s : this.syncedSlots) {
            if (s == slot) {
                return true;
            }
        }
        return false;
    }

    public void readSyncNBT(NBTTagCompound nbtCompound) {
        this.inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        NBTTagList nbttaglist = nbtCompound.getTagList("ItemsSynced", 10);
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (isSyncedSlot(b0)) {
                this.inventory.set(b0, new ItemStack(nbttagcompound1));
            }
        }
    }

    public NBTTagCompound writeSyncNBT(NBTTagCompound nbtCompound) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.size(); i++) {
            if ((!((ItemStack) this.inventory.get(i)).isEmpty()) && (isSyncedSlot(i))) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                ((ItemStack) this.inventory.get(i)).writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbtCompound.setTag("ItemsSynced", nbttaglist);
        return nbtCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtCompound) {
        super.readFromNBT(nbtCompound);
        if (nbtCompound.hasKey("CustomName")) {
            this.customName = nbtCompound.getString("CustomName");
        }
        ItemStackHelper.loadAllItems(nbtCompound, this.inventory);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtCompound) {
        super.writeToNBT(nbtCompound);
        if (hasCustomName()) {
            nbtCompound.setString("CustomName", this.customName);
        }
        ItemStackHelper.saveAllItems(nbtCompound, this.inventory);
        return nbtCompound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.world.getTileEntity(getPos()) == this;
    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack stack2) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing par1) {
        return this.faceSlots;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean canInsertItem(int par1, ItemStack stack2, EnumFacing par3) {
        return isItemValidForSlot(par1, stack2);
    }

    @Override
    public boolean canExtractItem(int par1, ItemStack stack2, EnumFacing par3) {
        return true;
    }

    public void syncTile(boolean rerender) {
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.notifyBlockUpdate(this.pos, state, state, 2 + (rerender ? 4 : 0));
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void update() {
    }

    IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    IItemHandler handlerWest = new SidedInvWrapper(this, EnumFacing.WEST);
    IItemHandler handlerEast = new SidedInvWrapper(this, EnumFacing.EAST);
    IItemHandler handlerNorth = new SidedInvWrapper(this, EnumFacing.NORTH);
    IItemHandler handlerSouth = new SidedInvWrapper(this, EnumFacing.SOUTH);

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if ((facing != null) && (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
            if (facing == EnumFacing.DOWN) {
                return (T) this.handlerBottom;
            }
            if (facing == EnumFacing.UP) {
                return (T) this.handlerTop;
            }
            if (facing == EnumFacing.WEST) {
                return (T) this.handlerWest;
            }
            if (facing == EnumFacing.EAST) {
                return (T) this.handlerEast;
            }
            if (facing == EnumFacing.NORTH) {
                return (T) this.handlerNorth;
            }
            return (T) this.handlerSouth;
        }
        return (T) super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) || (super.hasCapability(capability, facing));
    }

}
