package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.ArrayList;

public class ResearchPages {
    private ResearchStage pages;

    public ResearchPages() {
        this.pages = new ResearchStage();
    }

    public ResearchPages setText(final String text) {
        this.pages.setText(text);
        return this;
    }
    public ResearchPages setRequiredCraft(final ItemStack... items) {
        this.pages.setCraft(items);
        if (this.pages.getCraft() != null && this.pages.getCraft().length > 0) {
            final int[] refs = new int[this.pages.getCraft().length];
            int q = 0;
            for (final ItemStack stack : this.pages.getCraft()) {
                final int code = ResearchManager.createItemStackHash(stack);
                ResearchManager.craftingReferences.add(code);
                refs[q] = code;
                ++q;
            }
            this.pages.setCraftReference(refs);
        }
        return this;
    }

    public ResearchPages setRecipes(final ResourceLocation... recipes) {
        this.pages.setRecipes(recipes);
        return this;
    }

    public ResearchPages setWarp(final int warp) {
        this.pages.setWarp(warp);
        return this;
    }

    public ResearchPages setRequiredItems(final ItemStack... obtain) {
        this.pages.setObtain(obtain);
        return this;
    }

    public ResearchPages setCraftReference(final int... craftReference) {
        this.pages.setCraftReference(craftReference);
        return this;
    }

    public ResearchPages setKnow(Type type, Category category, int ammout) {
        String[] sl = new String[]{String.valueOf(type) + ";" + String.valueOf(category) + ";" + String.valueOf(ammout)};
        if ((sl != null) && (sl.length > 0)) {
            ArrayList<ResearchStage.Knowledge> kl = new ArrayList();
            for (String s : sl) {
                ResearchStage.Knowledge k = ResearchStage.Knowledge.parse(s);
                if (k != null) {
                    kl.add(k);
                }
            }
            if (kl.size() > 0) {
                pages.setKnow((ResearchStage.Knowledge[]) kl.toArray(new ResearchStage.Knowledge[kl.size()]));
            }
        }
        return this;
    }

    public ResearchPages setResearch(final String... research) {
        this.pages.setResearch(research);
        return this;
    }

    public ResearchStage registerStage() {
        if (this.pages == null) {
            throw new IllegalStateException("Already built!");
        }
        final ResearchStage re = this.pages;
        this.pages = null;
        return re;
    }

    public enum Category {
        BASICS, ALCHEMY, AUROMANCY, ARTIFICE, INFUSION, GOLEMANCY, ELDRITCH
    }

    public enum Type {
        THEORY, OBSERVATION
    }
}
