package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * @Author: ManualHuaJi
 */
public class ResearchStage extends thaumcraft.api.research.ResearchStage {
    String text;
    ResourceLocation[] recipes;
    ItemStack[] obtain;
    ItemStack[] craft;
    Knowledge[] know;
    String[] research;
    int warp;

    public ResearchStage(String text) {
        this.text = text;
    }

    public ResearchStage(ResourceLocation[] recipes) {
        this.recipes = recipes;
    }

    public ResearchStage(ItemStack[] rewardItem, ItemStack... requiredCraft) {
        this.obtain = rewardItem;
        this.craft = requiredCraft;
    }

    public ResearchStage(String[] s) {
        ArrayList<Knowledge> kl = new ArrayList();
        for (String s1 : s) {
            Knowledge knowledge = Knowledge.parse(s1);
            if (knowledge != null) {
                kl.add(knowledge);
            }
        }

        this.know = (Knowledge[]) kl.toArray(new Knowledge[kl.size()]);
    }

    public ResearchStage(String[]... requiredResearch) {
    }

    public ResearchStage(int warp) {
        this.warp = warp;
    }
}
