package mhj.expmm.common.lib.research;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author: ManualHuaJi
 */
public class EXPMMResearchManager {
    public static ResearchEntry addResearch(String key, String name, String category, String icon[], String parents[], String siblings[], ResearchEntry.EnumResearchMeta researchType[], int displayColumn, int displayRow, ItemStack rewardItem[], ResearchStage.Knowledge[] rewardKnow, String text, ResourceLocation recipes[], ItemStack requiredItem[], ItemStack requiredCraft[]) {
        ResearchEntry entry = new ResearchEntry();
        entry.setKey(key);
        entry.setName(name);
        entry.setCategory(category);
        ItemStack stack;
        String[] icons = icon;
        Object[] ir = new Object[icons.length];
        for (int a = 0; a < icons.length; a++) {
            stack = ResearchManager.parseJSONtoItemStack(icons[a]);
            if ((stack != null) && (!stack.isEmpty())) {
                ir[a] = stack;
            } else if (icons[a].startsWith("focus")) {
                ir[a] = icons[a];
            } else {
                ir[a] = new ResourceLocation(icons[a]);
            }
        }
        entry.setIcons(ir);
        entry.setParents(parents);
        entry.setSiblings(siblings);
        entry.setMeta(researchType);
        entry.setDisplayColumn(displayColumn);
        entry.setDisplayRow(displayRow);
        entry.setRewardItem(rewardItem);
        entry.setRewardKnow(rewardKnow);
        ArrayList<ResearchStage> stages = new ArrayList();
        ResearchStage stage = new ResearchStage();
        stage.setText(text);
        stage.setRecipes(recipes);
        stage.setObtain(requiredItem);
        stage.setCraft(requiredCraft);
        entry.setStages((ResearchStage[]) stages.toArray(new ResearchStage[stages.size()]));
        return entry;
    }

}

