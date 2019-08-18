package mhj.expmm.research.reference;

import mhj.expmm.api.IReference;
import mhj.expmm.item.ItemsExpmm;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.api.research.theorycraft.TheorycraftManager;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ReferenceAuromancy implements IReference {
    @Override
    public Item getReference() {
        return new ItemStack(ItemsExpmm.itemReference, 1, 1).getItem();
    }

    @Override
    public void active(TileAdvancedResearchTable table) {
        HashMap<String, Class<TheorycraftCard>> cards = new HashMap();
        for (String key : TheorycraftManager.cards.keySet()) {
            Class<TheorycraftCard> tcc = TheorycraftManager.cards.get(key);
            if (tcc == null) {
                continue;
            }
            try {
                TheorycraftCard tc = tcc.newInstance();
                if (tc.getResearchCategory().equals("AUORMANCY")) {
                    cards.put(tcc.getName(), tcc);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (Class c : cards.values()) {
                for (int i = 0; i < 3; i++) {
                    TheorycraftManager.registerCard(c);
                }
            }
        }

    }

    Method generateCard;

    public TheorycraftCard generateCard(String key, long seed, EntityPlayer pe) {
        TheorycraftCard card = null;
        if (generateCard == null) {
            try {
                generateCard = ResearchManager.class.getDeclaredMethod("generateCard", String.class, Long.class, EntityPlayer.class);
                generateCard.setAccessible(true);
            } catch (NoSuchMethodException | SecurityException ignored) {
            }
        }
        try {
            card = (TheorycraftCard) generateCard.invoke(key, seed, pe);
        } catch (Throwable ignored) {
        }
        return card;
    }

}
