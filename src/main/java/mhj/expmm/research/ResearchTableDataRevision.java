package mhj.expmm.research;

import mhj.expmm.event.ResearchTableEvent;
import mhj.expmm.register.EventLoader;
import mhj.expmm.tile.TileAdvancedResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.theorycraft.ITheorycraftAid;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.api.research.theorycraft.TheorycraftManager;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class ResearchTableDataRevision extends ResearchTableData  {
    public TileEntity table;
    public String player;
    public int inspiration;
    public int inspirationStart;
    public int bonusDraws;
    public int placedCards;
    public int aidsChosen;
    public int penaltyStart;
    public ArrayList<Long> savedCards;
    public ArrayList<String> aidCards;
    public TreeMap<String, Integer> categoryTotals;
    public ArrayList<String> categoriesBlocked;
    public ArrayList<ResearchTableData.CardChoice> cardChoices;
    public ResearchTableData.CardChoice lastDraw;

    public ResearchTableDataRevision(EntityPlayer player2, TileEntity tileResearchTable) {
        super(player2, tileResearchTable);
        this.savedCards = new ArrayList<Long>();
        this.aidCards = new ArrayList<String>();
        this.categoryTotals = new TreeMap<String, Integer>();
        this.categoriesBlocked = new ArrayList<String>();
        this.cardChoices = new ArrayList<ResearchTableData.CardChoice>();
        this.player = player2.getName();
        this.table = tileResearchTable;
    }

    public ResearchTableDataRevision(TileEntity tileResearchTable) {
        super(tileResearchTable);
    }

    public boolean isComplete() {
        return this.inspiration <= 0;
    }

    public boolean hasTotal(final String cat) {
        return this.categoryTotals.containsKey(cat);
    }

    public int getTotal(final String cat) {
        return this.categoryTotals.containsKey(cat) ? this.categoryTotals.get(cat) : 0;
    }

    public void addTotal(final String cat, final int amt) {
        ResearchTableEvent event= new ResearchTableEvent((TileAdvancedResearchTable) table);
        EventLoader.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            int current = this.categoryTotals.containsKey(cat) ? this.categoryTotals.get(cat) : 0;
            current += amt;
            if (current <= 0) {
                this.categoryTotals.remove(cat);
            } else {
                this.categoryTotals.put(cat, current);
            }
        }
    }

    public void addInspiration(final int amt) {
        this.inspiration += amt;
        if (this.inspiration > this.inspirationStart) {
            this.inspiration = this.inspirationStart;
        }
    }

    public NBTTagCompound serialize() {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("player", this.player);
        nbt.setInteger("inspiration", this.inspiration);
        nbt.setInteger("inspirationStart", this.inspirationStart);
        nbt.setInteger("placedCards", this.placedCards);
        nbt.setInteger("bonusDraws", this.bonusDraws);
        nbt.setInteger("aidsChosen", this.aidsChosen);
        nbt.setInteger("penaltyStart", this.penaltyStart);
        final NBTTagList savedTag = new NBTTagList();
        for (final Long card : this.savedCards) {
            final NBTTagCompound gt = new NBTTagCompound();
            gt.setLong("card", (long) card);
            savedTag.appendTag((NBTBase) gt);
        }
        nbt.setTag("savedCards", (NBTBase) savedTag);
        final NBTTagList categoriesBlockedTag = new NBTTagList();
        for (final String category : this.categoriesBlocked) {
            final NBTTagCompound gt2 = new NBTTagCompound();
            gt2.setString("category", category);
            categoriesBlockedTag.appendTag((NBTBase) gt2);
        }
        nbt.setTag("categoriesBlocked", (NBTBase) categoriesBlockedTag);
        final NBTTagList categoryTotalsTag = new NBTTagList();
        for (final String category2 : this.categoryTotals.keySet()) {
            final NBTTagCompound gt3 = new NBTTagCompound();
            gt3.setString("category", category2);
            gt3.setInteger("total", (int) this.categoryTotals.get(category2));
            categoryTotalsTag.appendTag((NBTBase) gt3);
        }
        nbt.setTag("categoryTotals", (NBTBase) categoryTotalsTag);
        final NBTTagList aidCardsTag = new NBTTagList();
        for (final String mc : this.aidCards) {
            final NBTTagCompound gt4 = new NBTTagCompound();
            gt4.setString("aidCard", mc);
            aidCardsTag.appendTag((NBTBase) gt4);
        }
        nbt.setTag("aidCards", (NBTBase) aidCardsTag);
        final NBTTagList cardChoicesTag = new NBTTagList();
        for (ResearchTableData.CardChoice mc2 : this.cardChoices) {
            final NBTTagCompound gt5 = this.serializeCardChoice(mc2);
            cardChoicesTag.appendTag((NBTBase) gt5);
        }
        nbt.setTag("cardChoices", (NBTBase) cardChoicesTag);
        if (this.lastDraw != null) {
            nbt.setTag("lastDraw", (NBTBase) this.serializeCardChoice(this.lastDraw));
        }
        return nbt;
    }

    public NBTTagCompound serializeCardChoice(final CardChoice mc) {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("cardChoice", mc.key);
        nbt.setBoolean("aid", mc.fromAid);
        nbt.setBoolean("select", mc.selected);
        try {
            nbt.setTag("cardNBT", (NBTBase) mc.card.serialize());
        } catch (Exception ex) {
        }
        return nbt;
    }

    public void deserialize(final NBTTagCompound nbt) {
        if (nbt == null) {
            return;
        }
        this.inspiration = nbt.getInteger("inspiration");
        this.inspirationStart = nbt.getInteger("inspirationStart");
        this.placedCards = nbt.getInteger("placedCards");
        this.bonusDraws = nbt.getInteger("bonusDraws");
        this.aidsChosen = nbt.getInteger("aidsChosen");
        this.penaltyStart = nbt.getInteger("penaltyStart");
        this.player = nbt.getString("player");
        final NBTTagList savedTag = nbt.getTagList("savedCards", 10);
        this.savedCards = new ArrayList<Long>();
        for (int x = 0; x < savedTag.tagCount(); ++x) {
            final NBTTagCompound nbtdata = savedTag.getCompoundTagAt(x);
            this.savedCards.add(nbtdata.getLong("card"));
        }
        final NBTTagList categoriesBlockedTag = nbt.getTagList("categoriesBlocked", 10);
        this.categoriesBlocked = new ArrayList<String>();
        for (int x2 = 0; x2 < categoriesBlockedTag.tagCount(); ++x2) {
            final NBTTagCompound nbtdata2 = categoriesBlockedTag.getCompoundTagAt(x2);
            this.categoriesBlocked.add(nbtdata2.getString("category"));
        }
        final NBTTagList categoryTotalsTag = nbt.getTagList("categoryTotals", 10);
        this.categoryTotals = new TreeMap<String, Integer>();
        for (int x3 = 0; x3 < categoryTotalsTag.tagCount(); ++x3) {
            final NBTTagCompound nbtdata3 = categoryTotalsTag.getCompoundTagAt(x3);
            this.categoryTotals.put(nbtdata3.getString("category"), nbtdata3.getInteger("total"));
        }
        final NBTTagList aidCardsTag = nbt.getTagList("aidCards", 10);
        this.aidCards = new ArrayList<String>();
        for (int x4 = 0; x4 < aidCardsTag.tagCount(); ++x4) {
            final NBTTagCompound nbtdata4 = aidCardsTag.getCompoundTagAt(x4);
            this.aidCards.add(nbtdata4.getString("aidCard"));
        }
        EntityPlayer pe = null;
        if (this.table != null && this.table.getWorld() != null && !this.table.getWorld().isRemote) {
            pe = this.table.getWorld().getPlayerEntityByName(this.player);
        }
        final NBTTagList cardChoicesTag = nbt.getTagList("cardChoices", 10);
        this.cardChoices = new ArrayList<>();
        for (int x5 = 0; x5 < cardChoicesTag.tagCount(); ++x5) {
            final NBTTagCompound nbtdata5 = cardChoicesTag.getCompoundTagAt(x5);
            final ResearchTableData.CardChoice cc = this.deserializeCardChoice(nbtdata5);
            if (cc != null) {
                this.cardChoices.add(cc);
            }
        }
        this.lastDraw = this.deserializeCardChoice(nbt.getCompoundTag("lastDraw"));
    }

    public ResearchTableData.CardChoice deserializeCardChoice(NBTTagCompound nbt) {
        if (nbt == null) return null;
        String key = nbt.getString("cardChoice");
        TheorycraftCard tc = generateCardWithNBT(nbt.getString("cardChoice"), nbt.getCompoundTag("cardNBT"));
        if (tc == null) return null;
        return new ResearchTableData.CardChoice(key, tc, nbt.getBoolean("aid"), nbt.getBoolean("select"));
    }

    private boolean isCategoryBlocked(final String cat) {
        return this.categoriesBlocked.contains(cat);
    }

    public void drawCards(int draw, final EntityPlayer pe) {
        if (draw == 3) {
            if (this.bonusDraws > 0) {
                --this.bonusDraws;
            } else {
                draw = 2;
            }
        }
        this.cardChoices.clear();
        this.player = pe.getName();
        final ArrayList<String> availCats = this.getAvailableCategories(pe);
        final ArrayList<String> drawnCards = new ArrayList<String>();
        final boolean aidDrawn = false;
        int failsafe = 0;
        while (draw > 0 && failsafe < 10000) {
            ++failsafe;
            if (!aidDrawn && !this.aidCards.isEmpty() && pe.getRNG().nextFloat() <= 0.25) {
                final int idx = pe.getRNG().nextInt(this.aidCards.size());
                final String key = this.aidCards.get(idx);
                final TheorycraftCard card = this.generateCard(key, -1L, pe);
                if (card == null || card.getInspirationCost() > this.inspiration) {
                    continue;
                }
                if (this.isCategoryBlocked(card.getResearchCategory())) {
                    continue;
                }
                if (drawnCards.contains(key)) {
                    continue;
                }
                drawnCards.add(key);
                this.cardChoices.add(new ResearchTableData.CardChoice(key, card, true, false));
                this.aidCards.remove(idx);
            } else {
                try {
                    final String[] cards = TheorycraftManager.cards.keySet().toArray(new String[0]);
                    final int idx2 = pe.getRNG().nextInt(cards.length);
                    final TheorycraftCard card = this.generateCard(cards[idx2], -1L, pe);
                    if (card == null || card.isAidOnly() || card.getInspirationCost() > this.inspiration) {
                        continue;
                    }
                    if (card.getResearchCategory() != null) {
                        boolean found = false;
                        for (final String cn : availCats) {
                            if (cn.equals(card.getResearchCategory())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            continue;
                        }
                    }
                    if (drawnCards.contains(cards[idx2])) {
                        continue;
                    }
                    drawnCards.add(cards[idx2]);
                    this.cardChoices.add(new ResearchTableData.CardChoice(cards[idx2], card, false, false));
                } catch (Exception e) {
                    continue;
                }
            }
            --draw;
        }
    }

    private TheorycraftCard generateCard(final String key, final long seed, final EntityPlayer pe) {
        if (key == null) {
            return null;
        }
        final Class<TheorycraftCard> tcc = TheorycraftManager.cards.get(key);
        if (tcc == null) {
            return null;
        }
        TheorycraftCard tc = null;
        try {
            tc = tcc.newInstance();
            if (seed < 0L) {
                if (pe != null) {
                    tc.setSeed(pe.getRNG().nextLong());
                } else {
                    tc.setSeed(System.nanoTime());
                }
            } else {
                tc.setSeed(seed);
            }
            if (pe != null && !tc.initialize(pe, (ResearchTableData) this)) {
                return null;
            }
        } catch (Exception ex) {
        }
        return tc;
    }

    private TheorycraftCard generateCardWithNBT(final String key, final NBTTagCompound nbt) {
        if (key == null) {
            return null;
        }
        final Class<TheorycraftCard> tcc = TheorycraftManager.cards.get(key);
        if (tcc == null) {
            return null;
        }
        TheorycraftCard tc = null;
        try {
            tc = tcc.newInstance();
            tc.deserialize(nbt);
        } catch (Exception ex) {
        }
        return tc;
    }

    public void initialize(final EntityPlayer player1, final Set<String> aids) {
        this.inspirationStart = getAvailableInspiration(player1);
        this.inspiration = this.inspirationStart - aids.size();
        for (final String muk : aids) {
            final ITheorycraftAid mu = TheorycraftManager.aids.get(muk);
            if (mu != null) {
                for (final Class clazz : mu.getCards()) {
                    this.aidCards.add(clazz.getName());
                }
            }
        }
    }

    public ArrayList<String> getAvailableCategories(final EntityPlayer player) {
        final ArrayList<String> cats = new ArrayList<String>();
        for (final String rck : ResearchCategories.researchCategories.keySet()) {
            final ResearchCategory rc = ResearchCategories.getResearchCategory(rck);
            if (rc != null) {
                if (this.isCategoryBlocked(rck)) {
                    continue;
                }
                if (rc.researchKey != null && !ThaumcraftCapabilities.knowsResearchStrict(player, rc.researchKey)) {
                    continue;
                }
                cats.add(rck);
            }
        }
        return cats;
    }

    public static int getAvailableInspiration(final EntityPlayer player) {
        float tot = 5.0f;
        final IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
        for (final String s : knowledge.getResearchList()) {
            if (ThaumcraftCapabilities.knowsResearchStrict(player, s)) {
                final ResearchEntry re = ResearchCategories.getResearch(s);
                if (re == null) {
                    continue;
                }
                if (re.hasMeta(ResearchEntry.EnumResearchMeta.SPIKY)) {
                    tot += 0.5f;
                }
                if (!re.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN)) {
                    continue;
                }
                tot += 0.1f;
            }
        }
        return Math.min(15, Math.round(tot));
    }

    public class CardChoice {
        public TheorycraftCard card;
        public String key;
        public boolean fromAid;
        public boolean selected;

        public CardChoice(final String key, final TheorycraftCard card, final boolean aid, final boolean selected) {
            this.key = key;
            this.card = card;
            this.fromAid = aid;
            this.selected = selected;
        }

        @Override
        public String toString() {
            return "key:" + this.key + " card:" + this.card.getSeed() + " fromAid:" + this.fromAid + " selected:" + this.selected;
        }
    }
}
