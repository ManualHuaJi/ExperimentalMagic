package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;

import java.util.ArrayList;

import static thaumcraft.common.lib.research.ResearchManager.parseJSONtoItemStack;

public class ResearchItem {

    private ResearchEntry entry = new ResearchEntry();

    public static ResearchItem start() {
        return new ResearchItem();
    }

    public ResearchItem setBaseInfo(String key, String category, String name, int x, int y, String... icons) {
        return setKey(key).setCategory(category).setName(name).setPosition(x, y).setIcons(icons);
    }

    public ResearchItem setKey(String key) {
        entry.setKey(key);
        return this;
    }

    public ResearchItem setCategory(String category) {
        entry.setCategory(category);
        return this;
    }

    public ResearchItem setPosition(int x, int y) {
        entry.setDisplayColumn(x);
        entry.setDisplayRow(y);
        return this;
    }

    public ResearchItem setIcons(String... icon) {
        {
            ItemStack stack;
            String[] icons = icon;
            if ((icons != null) && (icons.length > 0)) {
                Object[] ir = new Object[icons.length];
                for (int a = 0; a < icons.length; a++) {
                    stack = parseJSONtoItemStack(icons[a]);
                    if ((stack != null) && (!stack.isEmpty())) {
                        ir[a] = stack;
                    } else if (icons[a].startsWith("focus")) {
                        ir[a] = icons[a];
                    } else {
                        ir[a] = new ResourceLocation(icons[a]);
                    }
                }
                entry.setIcons(ir);
            }
            return this;
        }
    }

    public ResearchItem setRound() {
        entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.ROUND});
        return this;
    }

    public ResearchItem setHex() {
        entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.HEX});
        return this;
    }

    public ResearchItem setHidden() {
        entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.HIDDEN});
        return this;
    }

    public ResearchItem setReverse() {
        entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.REVERSE});
        return this;
    }

    public ResearchItem setSpiky() {
        entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.SPIKY});
        return this;
    }

    public ResearchItem setAutoUnlock() {
        entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.AUTOUNLOCK});
        return this;
    }

    public ResourceLocation[] setRecipe(String... recipe) {
        ArrayList<ResourceLocation> out = new ArrayList();
        for (String re : recipe) {
            out.add(new ResourceLocation(re));
        }
        return out.size() == 0 ? null : (ResourceLocation[]) out.toArray(new ResourceLocation[out.size()]);
    }

    public ResearchItem setAddenda(ResearchAddendum... addenda) {
        entry.setAddenda(addenda);
        return this;
    }

    public ResearchItem setName(String name) {
        entry.setName(name);
        return this;
    }

    public ResearchItem setParents(String... parents) {
        entry.setParents(parents);
        return this;
    }

    public ResearchItem setRewardItems(ItemStack... rewards) {
        entry.setRewardItem(rewards);
        return this;
    }

    public ResearchItem setRewardKnow(Knowledge... rewardKnow) {
        entry.setRewardKnow(rewardKnow);
        return this;
    }

    public ResearchItem setSiblings(String... siblings) {
        entry.setSiblings(siblings);
        return this;
    }

    public ResearchItem setStages(ResearchStage... stages) {
        entry.setStages(stages);
        return this;
    }

    public ResearchEntry registerResearchItem() {
        if (entry == null) {
            throw new IllegalStateException("Already built!");
        }
        ResearchEntry re = entry;
        entry = null;
        ResearchLoader.addResearchToCategory(re);
        return re;
    }
}