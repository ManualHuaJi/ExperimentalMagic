package mhj.expmm.common.lib.research;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;

/**
 * @Author: ManualHuaJi
 */
public class ResearchAddenda {
    private ResearchAddendum entry;

    public ResearchAddenda() {
        this.entry = new ResearchAddendum();
    }


    public ResearchAddenda setText(final String text) {
        this.entry.setText(text);
        return this;
    }

    public ResearchAddenda setRecipes(final ResourceLocation... recipes) {
        this.entry.setRecipes(recipes);
        return this;
    }

    public ResearchAddenda setResearch(final String... research) {
        this.entry.setResearch(research);
        return this;
    }

    public ResearchAddendum build() {
        if (this.entry == null) {
            throw new IllegalStateException("Already built!");
        }
        final ResearchAddendum re = this.entry;
        this.entry = null;
        return re;
    }
}
