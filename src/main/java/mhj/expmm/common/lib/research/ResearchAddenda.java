package mhj.expmm.common.lib.research;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ResearchAddenda {
    private ResearchAddendum entry = new ResearchAddendum();

    public static ResearchAddenda start() {
        return new ResearchAddenda();
    }

    public ResearchAddenda setText(String text) {
        entry.setText(text);
        return this;
    }

    public ResearchAddenda setRecipes(String... recipes) {
        return setRecipes(Arrays.stream(recipes).map(ResourceLocation::new).collect(Collectors.toList()).toArray(new ResourceLocation[0]));
    }

    public ResearchAddenda setRecipes(ResourceLocation... recipes) {
        entry.setRecipes(recipes);
        return this;
    }

    public ResearchAddenda setResearch(String... research) {
        entry.setResearch(research);
        return this;
    }

    public ResearchAddendum registerResearchAddenda() {
        if (entry == null) {
            throw new IllegalStateException("Already built!");
        }
        ResearchAddendum re = entry;
        entry = null;
        return re;
    }
}