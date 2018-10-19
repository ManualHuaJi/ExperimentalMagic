package mhj.expmm.common.lib.research;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.block.ReferenceBookcase;
import mhj.expmm.common.lib.research.theorycraft.*;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.theorycraft.TheorycraftManager;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;

import static thaumcraft.api.research.ResearchCategories.getResearchCategory;

public class ResearchLoader {

    public static final OnetimeCaller init = new OnetimeCaller(ResearchLoader::$init);
    public static final OnetimeCaller clInit = new OnetimeCaller(ResearchLoader::$);

    public static void init() {
        initCard();
    }

    public static Class[] card = {
            CardChannelVis.class, CardDifferent.class, CardFlux.class, CardGlimpse.class, CardIdealExpm.class, CardImprove.class, CardInteraction.class, CardMemoryFlash.class, CardWhirling.class, CardRecollect.class
    };

    public static void initCard() {
        TheorycraftManager.registerAid(new ReferenceBookcase());
        for (Class cardclass : card) {
            TheorycraftManager.registerCard(cardclass);
        }
    }

    public static void $init() {
        new RI().setBaseInfo("EXPMMFIRST", "expmmfisrt", 0, 0)
                .setSpiky()
                .setRound()
                .setIcons("exomm:texture/misc/cat.png")
                .setParents("FIRSTSTEPS")
                .setStages(new RP()
                        .setText("research.EXPMMFIRST.stage.1")
                        .registerResearchPages()).registerResearchItem();
        new RI().setBaseInfo("FOCUSREDEYE", "focusredeye", 0, 5)
                .setIcons("thaumcraft:textures/research/cat_auromancy.png")
                .setParents("BASEAUROMANCY", "EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.FOCUSREDEYE.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages())
                .registerResearchItem();
        new RI().setBaseInfo("FOCUSGOLDENEYE", "FOCUSGOLDENEYE", 2, 6)
                .setIcons("expmm:textures/item/foci/goldeneye.png")
                .setParents("FOCUSREDEYE")
                .setReverse()
                .setStages(new RP()
                        .setText("research.FOCUSGOLDENEYE.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()).registerResearchItem();
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