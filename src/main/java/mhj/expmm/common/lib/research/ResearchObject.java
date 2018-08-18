package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Author: ManualHuaJi
 */
public class ResearchObject {
    public final String key;
    public final String category;
    public final String name;
    public String[] parents;
    public String[] siblings;
    public final int displayColumn;
    public final int displayRow;
    public Object[] icons;
    public ResearchEntry.EnumResearchMeta[] meta;
    public ItemStack[] rewardItem;
    public ResearchStage.Knowledge[] rewardKnow;
    public ResearchStage[] stages = null;
    public ResearchAddendum[] addenda = null;

    public ResearchObject(String key, String category, String name, int displayColumn, int displayRow) {
        this.key = key;
        this.category = category;
        this.name = name;
        this.displayColumn = displayColumn;
        this.displayRow = displayRow;
    }

    public ResearchEntry.EnumResearchMeta[] getMeta() {
        return this.meta;
    }

    public void setMeta(ResearchEntry.EnumResearchMeta[] meta) {
        this.meta = meta;
    }

    public void setRewardItem(ItemStack... rewardItem) {
        this.rewardItem = rewardItem;
    }

    public void setRewardKnow(ResearchStage.Knowledge... rewardKnow) {
        this.rewardKnow = rewardKnow;
    }

    public ResearchObject setParents(String... par) {
        this.parents = par;
        return this;
    }


    public ResearchObject setSiblings(String... sib) {
        this.siblings = sib;
        return this;
    }

    public ResearchStage[] getStages() {
        return this.stages;
    }

    public ResearchObject setStages(mhj.expmm.common.lib.research.ResearchStage... stages) {
        this.stages = stages;
        return this;
    }

    public ResearchAddendum[] getAddenda() {
        return this.addenda;
    }

    public ResearchObject setAddenda(ResearchAddendum... addenda) {
        this.addenda = addenda;
        return this;
    }

    public void setIcons(ItemStack[] icon) {
        this.icons = icon;
    }

    public ResearchObject registerResearch() {
        addResearch(processResearch(this));
        return this;
    }

    public static ResearchEntry processResearch(ResearchObject object) {
        ResearchEntry entry = new ResearchEntry();
        entry.setKey(object.key);
        entry.setCategory(object.category);
        entry.setName(object.name);
        entry.setIcons(object.icons);
        entry.setParents(object.parents);
        entry.setSiblings(object.siblings);
        entry.setMeta(object.meta);
        entry.setDisplayColumn(object.displayColumn);
        entry.setDisplayRow(object.displayRow);
        entry.setRewardItem(object.rewardItem);
        entry.setRewardKnow(object.rewardKnow);
        if (object.getStages() != null) {
            ArrayList<ResearchStage> stages = new ArrayList();
            ResearchStage stage = object.getStages()[0];
            stages.add(stage);
            entry.setStages((ResearchStage[]) stages.toArray(new ResearchStage[stages.size()]));
        }
        if (object.getAddenda() != null) {
            ArrayList<ResearchAddendum> addenda = new ArrayList();
            ResearchAddendum addendum = object.getAddenda()[0];
            addenda.add(addendum);
            entry.setAddenda(addenda.toArray(new ResearchAddendum[addenda.size()]));
        }
        return entry;
    }

    public static void addResearch(ResearchEntry entry) {
        ResearchManager researchManager = new ResearchManager();
        Method method = null;
        try {
            method = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
            method.setAccessible(true);
            try {
                method.invoke((ResearchEntry) entry);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}