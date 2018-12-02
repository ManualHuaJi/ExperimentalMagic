package mhj.expmm.common.lib.research;

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
    public ResearchLoader() {
        initCard();
        init();
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

    public static void init() {
        new RI().setBaseInfo("EXPMMFIRST", 0, 0)
                .setSpiky()
                .setRound()
                .setIcons("expmm:textures/misc/cat.png")
                .setParents("FIRSTSTEPS")
                .setStages(new RP()
                        .setText("research.EXPMMFIRST.stage.1")
                        .registerResearchPages()).registerResearchItem();

        new RI().setBaseInfo("EFDEADTHMAGIC", 0, -2)
                .setIcons("focus:expmm.EF.DEATHMAGIC")
                .setParents("BASEAUROMANCY", "EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFDEADTHMAGIC.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages())
                .registerResearchItem();

        new RI().setBaseInfo("EFGOLDENEYE", 2, -3)
//                .setIcons("expmm:textures/item/foci/goldeneye.png")
                .setParents("EFDEADTHMAGIC")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFGOLDENEYE.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()).registerResearchItem();

        new RI().setBaseInfo("EFMINDSEYE", -2, 3)
//                .setIcons("expmm:textures/item/foci/goldeneye.png")
                .setParents("EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFMINDSEYE.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()).registerResearchItem();

        new RI().setBaseInfo("EFFOGGYNIGHT", 2, 4)
//                .setIcons("")
                .setParents("EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFFOGGYNIGHT.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()) .registerResearchItem();;

        new RI().setBaseInfo("EFRUSTYMEMORY", -2, 4)
//                .setIcons("")
                .setParents("EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFRUSTYMEMORY.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()) .registerResearchItem();;


        new RI().setBaseInfo("EFAFTERIMAGE", -2, 5)
//                .setIcons("")
                .setParents("EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFAFTERIMAGE.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()) .registerResearchItem();;

        new RI().setBaseInfo("EFCELESTIAL", -2, 6)
//                .setIcons("")
                .setParents("EXPMMFIRST")
                .setReverse()
                .setStages(new RP()
                        .setText("research.EFCELESTIAL.pages.1")
                        .setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.THEORY, getResearchCategory("AUROMANCY"), 2))
                        .registerResearchPages()) .registerResearchItem();;
    }

    private static class RA extends ResearchAddenda {
    }

    private static class RP extends ResearchPage {
    }

    private static class RI extends ResearchItem {
        public ResearchItem setBaseInfo(String key, int x, int y) {
            return super.setBaseInfo(key, "EXPMM", "research." + key + ".name", x, y);
        }
    }

    @SubscribeEvent
    public void commandEvent(final CommandEvent ce) {
        if (ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload")) {
            init();
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

}