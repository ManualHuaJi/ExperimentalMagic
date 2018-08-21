package mhj.expmm.common.lib.research;

import mhj.expmm.ExperimentalMagic;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;

import static thaumcraft.api.research.ResearchCategories.getResearchCategory;

public class ResearchLoader {

    public static final OnetimeCaller init = new OnetimeCaller(ResearchLoader::$init);
    public static final OnetimeCaller clInit = new OnetimeCaller(ResearchLoader::$);

    @SubscribeEvent
    public void commandEvent(final CommandEvent ce) {
        if (ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload")) {
            new Thread(() -> {
                while (ExperimentalMagic.EXPMM.research.containsKey("EXPMMFIRST")) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    public static void $init() {
        new RI().setBaseInfo("EXPMMFIRST", "expmmfisrt", 0, 0)
                .setSpiky()
                .setRound()
                .setIcons("thaumcraft:textures/research/cat_auromancy.png")
                .setParents("FIRSTSTEPS")
                .setStages(new RP()
                        .setText("research.EXPMMFIRST.stage.1")
                        .registerResearchPages()).registerResearchItem();
        new RI().setBaseInfo("FLUXERODE", "fluxerode", 0, 5)
                .setIcons("thaumcraft:textures/research/cat_auromancy.png")
                .setParents("FOCUSFLUX", "EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.FOCUSFLUXERODE.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .setWarp(2)
                        .setRequiredItems(new ItemStack[]{new ItemStack(ItemsTC.bottleTaint)})
                        .registerResearchPages())
                .registerResearchItem();

    }

    private static class RA extends ResearchAddenda {
    }

    private static class RP extends ResearchPage {
    }

    private static class RI extends ResearchItem {
        public ResearchItem setBaseInfo(String key, String name, int x, int y) {
            return super.setBaseInfo(key, "EXPMM", "research." + ExperimentalMagic.MODID + "." + name + ".name", x, y);
        }
    }

    private static Method addResearchToCategory = null;

    public static void addResearchToCategory(ResearchEntry ri) {
        if (addResearchToCategory == null) {
            try {
                addResearchToCategory = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
                addResearchToCategory.setAccessible(true);
            } catch (NoSuchMethodException | SecurityException e) {
            }
        }

        try {
            addResearchToCategory.invoke(null, ri);
        } catch (Throwable e) {

        }

    }

    private static void $() {
    }
}