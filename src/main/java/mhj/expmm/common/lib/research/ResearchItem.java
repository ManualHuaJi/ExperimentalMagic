package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;

import java.util.ArrayList;

import static thaumcraft.common.lib.research.ResearchManager.parseJSONtoItemStack;

public class ResearchItem {
    private ResearchEntry entry;

    public ResearchItem(final String key, final String category, final String name, final int displayColum, final int displayRow) {
        this.entry = new ResearchEntry();
        this.entry.setKey(key);
        this.entry.setCategory(category);
        this.entry.setName(name);
        this.entry.setDisplayColumn(displayColum);
        this.entry.setDisplayRow(displayRow);

    }


    public ResearchItem setKey(final String key) {
        this.entry.setKey(key);
        return this;
    }

    public ResearchItem setIcons(final String... icon) {
        ItemStack stack;
        String[] icons = icon;
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
            this.entry.setIcons(ir);
        }
        return this;
    }

    public ResearchItem setAddenda(final ResearchAddendum... addenda) {
        this.entry.setAddenda(addenda);
        return this;
    }


    public ResearchItem setParents(final String... parents) {
        this.entry.setParents(parents);
        return this;
    }

    public ResearchItem setRewardItems(final ItemStack... rewards) {
        this.entry.setRewardItem(rewards);
        return this;
    }

    public ResearchItem setRewardKnow(final ResearchStage.Knowledge... rewardKnow) {
        this.entry.setRewardKnow(rewardKnow);
        return this;
    }

    public ResearchItem setSiblings(final String... siblings) {
        this.entry.setSiblings(siblings);
        return this;
    }

    public ResearchItem setStages(final ResearchStage... stages) {
        this.entry.setStages(stages);
        return this;
    }

    public ResearchItem setAutolock() {
        this.entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.AUTOUNLOCK});
        return this;
    }

    public ResearchItem setSpiky() {
        this.entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.SPIKY});
        return this;
    }

    public ResearchItem setReverse() {
        this.entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.REVERSE});
        return this;
    }

    public ResearchItem setHidden() {
        this.entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.HIDDEN});
        return this;
    }

    public ResearchItem setHex() {
        this.entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.HEX});
        return this;
    }

    public ResearchItem setRound() {
        this.entry.setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.ROUND});
        return this;
    }

    public ResourceLocation[] setRecipe(String... recipe) {
        ArrayList<ResourceLocation> out = new ArrayList();
        for (String re : recipe) {
            out.add(new ResourceLocation(re));
        }
        return out.size() == 0 ? null : (ResourceLocation[]) out.toArray(new ResourceLocation[out.size()]);
    }

    public ResearchEntry registerResearchItem() {
        final ResearchEntry re = this.entry;
        this.entry = null;
        ResearchLoader.addResearch(re);
        return re;
    }
}
