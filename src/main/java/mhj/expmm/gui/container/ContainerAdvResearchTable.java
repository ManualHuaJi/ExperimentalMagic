package mhj.expmm.gui.container;

import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.common.container.slot.SlotLimitedByClass;
import thaumcraft.common.container.slot.SlotLimitedByItemstack;
import thaumcraft.common.lib.utils.InventoryUtils;

import java.util.HashMap;

public class ContainerAdvResearchTable extends Container {
    public TileAdvancedResearchTable tileEntity;

    String[] aspects;

    EntityPlayer player;

    public ContainerAdvResearchTable(InventoryPlayer iinventory, TileAdvancedResearchTable iinventory1) {
        this.player = iinventory.player;
        this.tileEntity = iinventory1;
        this.aspects = (String[]) Aspect.aspects.keySet().toArray(new String[0]);
        addSlotToContainer(new SlotLimitedByClass(thaumcraft.api.items.IScribeTools.class, iinventory1, 0, 16, 15));
        addSlotToContainer(new SlotLimitedByItemstack(new ItemStack(Items.PAPER), iinventory1, 1, 224, 16));
        bindPlayerInventory(iinventory);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 77 + j * 18, 190 + i * 18));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                addSlotToContainer(new Slot(inventoryPlayer, i + j * 3, 20 + i * 18, 190 + j * 18));
        }
    }

    static HashMap<Integer, Long> antiSpam = new HashMap();

    public boolean enchantItem(EntityPlayer playerIn, int button) {
        if (button == 1) {
            if (this.tileEntity.data.lastDraw != null)
                this.tileEntity.data.savedCards.add(Long.valueOf(this.tileEntity.data.lastDraw.card.getSeed()));
            for (ResearchTableData.CardChoice cc : this.tileEntity.data.cardChoices) {
                if (cc.selected) {
                    this.tileEntity.data.lastDraw = cc;
                    break;
                }
            }
            this.tileEntity.data.cardChoices.clear();
            this.tileEntity.syncTile(false);
            return true;
        }
        if (button == 4 || button == 5 || button == 6) {
            long tn = System.currentTimeMillis();
            long to = 0L;
            if (antiSpam.containsKey(Integer.valueOf(playerIn.getEntityId())))
                to = ((Long) antiSpam.get(Integer.valueOf(playerIn.getEntityId()))).longValue();
            if (tn - to < 333L)
                return false;
            antiSpam.put(Integer.valueOf(playerIn.getEntityId()), Long.valueOf(tn));
            try {
                TheorycraftCard card = ((ResearchTableData.CardChoice) this.tileEntity.data.cardChoices.get(button - 4)).card;
                if (card.getRequiredItems() != null) {
                    for (ItemStack stack : card.getRequiredItems()) {
                        if (stack != null && !stack.isEmpty() && !InventoryUtils.isPlayerCarryingAmount(this.player, stack, true))
                            return false;
                    }
                    if (card.getRequiredItemsConsumed() != null && card.getRequiredItemsConsumed().length == card.getRequiredItems().length)
                        for (int a = 0; a < card.getRequiredItems().length; a++) {
                            if (card.getRequiredItemsConsumed()[a] && card.getRequiredItems()[a] != null && !card.getRequiredItems()[a].isEmpty())
                                InventoryUtils.consumePlayerItem(this.player, card.getRequiredItems()[a], true, true);
                        }
                }
                if (card.activate(playerIn, this.tileEntity.data)) {
                    this.tileEntity.consumeInkFromTable();
                    ((ResearchTableData.CardChoice) this.tileEntity.data.cardChoices.get(button - 4)).selected = true;
                    this.tileEntity.data.addInspiration(-card.getInspirationCost());
                    this.tileEntity.syncTile(false);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (button == 7 &&
                this.tileEntity.data.isComplete()) {
            this.tileEntity.finishTheory(playerIn);
            this.tileEntity.syncTile(false);
            return true;
        }
        if (button == 9 &&
                !this.tileEntity.data.isComplete()) {
            this.tileEntity.data = null;
            this.tileEntity.syncTile(false);
            return true;
        }
        if (button == 2 || button == 3) {
            if (this.tileEntity.data != null && !this.tileEntity.data.isComplete() && this.tileEntity
                    .consumepaperFromTable()) {
                this.tileEntity.data.drawCards(button, playerIn);
                this.tileEntity.syncTile(false);
            }
            return true;
        }
        return false;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slotObject = (Slot) this.inventorySlots.get(slot);
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            if (slot < 2) {
                if (!this.tileEntity.isItemValidForSlot(slot, stackInSlot) || !mergeItemStack(stackInSlot, 2, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!this.tileEntity.isItemValidForSlot(slot, stackInSlot) || !mergeItemStack(stackInSlot, 0, 2, false)) {
                return ItemStack.EMPTY;
            }
            if (stackInSlot.getCount() == 0) {
                slotObject.putStack(ItemStack.EMPTY);
            } else {
                slotObject.onSlotChanged();
            }
        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }


}
