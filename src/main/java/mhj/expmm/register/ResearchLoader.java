package mhj.expmm.register;

import mhj.expmm.research.ResearchAddenda;
import mhj.expmm.research.ResearchItem;
import mhj.expmm.research.ResearchPage;
import mhj.expmm.research.theorycraft.*;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.theorycraft.CardRethink;
import thaumcraft.api.research.theorycraft.TheorycraftManager;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;

public class ResearchLoader {
    public ResearchLoader() {
        TheorycraftManager.cards.remove(CardRethink.class.getName());
        initCard();
        init();
    }

    private static Class[] card = {
            CardChannelVis.class,
            CardDecomposition.class,
            CardDifferent.class,
            CardExperience.class,
            CardFlux.class,
            CardGlimpse.class,
            CardImprove.class,
            CardMemory.class,
            CardAccept.class,
            CardRethinkRevision.class,
            CardWhirling.class,

    };

    private static void initCard() {
        for (Class clazz : card) {
            TheorycraftManager.registerCard(clazz);
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
            } catch (NoSuchMethodException | SecurityException ignored) {
            }
        }
        try {
            addResearchToCategory.invoke(null, ri);
        } catch (Throwable ignored) {
        }
    }

}