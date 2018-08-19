package mhj.expmm.common.lib.research;

import net.minecraft.item.ItemStack;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: ManualHuaJi
 */
public class ResearchLoader {


    public static void init() {
        new ResearchItem("reseatch.FOCUSFLUXERODE.title", "EXPMM", "research.FOCUS.FLUXERODE.name", 0, 0)
                .setIcons("thaumcraft:textures/research/cat_auromancy.png")
                .setParents("FOCUSFLUX")
                .setRound()
                .setStages(new ResearchPages()
                        .setText("research.FOCUSFLUXERODE.pages.1")
                        .setKnow(ResearchPages.Type.THEORY, ResearchPages.Category.AUROMANCY, 2)
                        .setWarp(2)
                        .setRequiredItems(new ItemStack[]{ConfigItems.ORDER_CRYSTAL})
                        .registerStage())
                .registerResearchItem();
    }


    public static void addResearch(ResearchEntry entry) {
        ResearchManager researchManager = new ResearchManager();
        Method method = null;
        try {
            method = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
            method.setAccessible(true);
            try {
                method.invoke(null, entry);
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