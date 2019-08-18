package mhj.expmm.register;

import mhj.expmm.item.ItemsExpmm;
import mhj.expmm.research.ResearchAddenda;
import mhj.expmm.research.ResearchItem;
import mhj.expmm.research.ResearchPage;
import mhj.expmm.research.theorycraft.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ScanItem;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.api.research.theorycraft.TheorycraftManager;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.theorycraft.CardScripting;

import java.lang.reflect.Method;

public class ResearchLoader {
    public ResearchLoader() {
//        TheorycraftManager.cards.remove(CardRethink.class.getName());
        TheorycraftManager.cards.remove(CardScripting.class.getName());
        initCard();
        initResearch();
        initScan();
    }

    private void initScan() {
        ScanningManager.addScannableThing(new ScanItem("!RBA", new ItemStack(ItemsExpmm.itemReference, 1, 0)));
        ScanningManager.addScannableThing(new ScanItem("!RAU", new ItemStack(ItemsExpmm.itemReference, 1, 1)));
        ScanningManager.addScannableThing(new ScanItem("!RAL", new ItemStack(ItemsExpmm.itemReference, 1, 2)));
        ScanningManager.addScannableThing(new ScanItem("!RAR", new ItemStack(ItemsExpmm.itemReference, 1, 3)));
        ScanningManager.addScannableThing(new ScanItem("!RIN", new ItemStack(ItemsExpmm.itemReference, 1, 4)));
        ScanningManager.addScannableThing(new ScanItem("!RGO", new ItemStack(ItemsExpmm.itemReference, 1, 5)));
        ScanningManager.addScannableThing(new ScanItem("!REL", new ItemStack(ItemsExpmm.itemReference, 1, 6)));

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
            CardWhirling.class,
            CardScriptingRevision.class
    };

    private static void initCard() {
        for (Class clazz : card) {
            TheorycraftManager.registerCard(clazz);
        }
    }

    public static void initResearch() {
        new RI().setBaseInfo("EXPMMFIRST", 0, 0)
                .setSpiky()
                .setRound()
                .setIcons("expmm:textures/misc/cat.png")
                .setParents("FIRSTSTEPS")
                .setStages(new RP()
                        .setText("research.EXPMMFIRST.stage.1")
                        .registerResearchPages()).registerResearchItem();

        new RI().setBaseInfo("!Reference", -1, -2)
                .setIcons("expmm:textures/item/itemReferences.png")
                .setHex()
                .setHidden()
                .setStages(new RP().setText("research.!Reference.stage.1").registerResearchPages())
                .setAddenda(
                        new RA().setText("research.!Reference.addenda.ba").setResearch("!RBA").registerResearchAddenda(),
                        new RA().setText("research.!Reference.addenda.au").setResearch("!RAU").registerResearchAddenda(),
                        new RA().setText("research.!Reference.addenda.al").setResearch("!RAL").registerResearchAddenda(),
                        new RA().setText("research.!Reference.addenda.ar").setResearch("!RAR").registerResearchAddenda(),
                        new RA().setText("research.!Reference.addenda.ar").setResearch("!RIN").registerResearchAddenda(),
                        new RA().setText("research.!Reference.addenda.go").setResearch("!RGO").registerResearchAddenda(),
                        new RA().setText("research.!Reference.addenda.el").setResearch("!REL").registerResearchAddenda()
                ).registerResearchItem();
        new RI().setBaseInfo("ADVRESEARCHTABLE", 2, 2)
                .setIcons("expmm:textures/block/advResearchTable.png")
                .setParents("EXPMMFIRST", "~!Reference", "WARDEDJARS")
                .setStages(new RP()
                        .setText("research.ADVRESEARCHTABLE.stage.1")
                        .setRecipes("expmm:advancedResearchTable")
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
            initResearch();
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