package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public ResearchStage[] stages;
    public ResearchAddendum[] addenda;

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

    public ResearchObject setStages(ResearchStage... stages) {
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
        ResearchManager researchManager = new ResearchManager();
        Method method = null;
        try {
            method = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
            method.setAccessible(true);
            try {
                method.invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return this;
    }

}
