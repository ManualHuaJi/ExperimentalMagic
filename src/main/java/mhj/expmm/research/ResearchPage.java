package mhj.expmm.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.ResearchStage.Knowledge;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ResearchPage {
    private ResearchStage entry = new ResearchStage();

    public static ResearchPage start() {
        return new ResearchPage();
    }

    public ResearchPage setText(String text) {
        entry.setText(text);
        return this;
    }

    public ResearchPage setRecipes(String... recipes) {
        return setRecipes(Arrays.stream(recipes).map(ResourceLocation::new).collect(Collectors.toList()).toArray(new ResourceLocation[0]));
    }

    public ResearchPage setRequiredCraft(ItemStack... items) {
        entry.setCraft(items);
        if (entry.getCraft() != null && entry.getCraft().length > 0) {
            int[] refs = new int[entry.getCraft().length];
            int q = 0;
            Object[] arritemStack = entry.getCraft();
            int n = arritemStack.length;
            for (int i = 0; i < n; ++i) {
                Object stack = arritemStack[i];
                int code = ResearchManager.createItemStackHash((ItemStack) stack);
                ResearchManager.craftingReferences.add(code);
                refs[q] = code;
                ++q;
            }
            entry.setCraftReference(refs);
        }
        return this;
    }

    public ResearchPage setRecipes(ResourceLocation... recipes) {
        entry.setRecipes(recipes);
        return this;
    }

    public ResearchPage setWarp(int warp) {
        entry.setWarp(warp);
        return this;
    }

    public ResearchPage setRequiredItems(ItemStack... obtain) {
        entry.setObtain(obtain);
        return this;
    }

    public ResearchPage setCraftReference(int... craftReference) {
        entry.setCraftReference(craftReference);
        return this;
    }

    public ResearchPage setKnow(Knowledge... know) {
        entry.setKnow(know);
        return this;
    }

    public ResearchPage setResearch(String... research) {
        entry.setResearch(research);
        return this;
    }

    public ResearchStage registerResearchPages() {
        if (entry == null) {
            throw new IllegalStateException("Already built!");
        }
        ResearchStage re = entry;
        entry = null;
        return re;
    }
}