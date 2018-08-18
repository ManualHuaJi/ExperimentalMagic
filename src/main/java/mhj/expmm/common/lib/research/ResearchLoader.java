package mhj.expmm.common.lib.research;

import mhj.expmm.ExperimentalMagic;
import mhj.expmm.common.item.foci.FocusEffectFluxErode;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;

/**
 * @Author: ManualHuaJi
 */
public class ResearchLoader {
    public static void init() {
        new ResearchObject(FocusEffectFluxErode.class.getSimpleName().toUpperCase(), ExperimentalMagic.CATEGORY, "research" + FocusEffectFluxErode.class.getSimpleName().toUpperCase() + "title", 0, 0)
                .setStages(new ResearchStage("research." + FocusEffectFluxErode.class.getSimpleName().toUpperCase() + ".stage.1"), new ResearchStage(new String[]{IPlayerKnowledge.EnumKnowledgeType.THEORY.toString() + ";" + String.valueOf(ResearchCategories.getResearchCategory("AUROMANCY") + ";" + "1")}), new ResearchStage(new String[]{"taint", "mortuus"}), new ResearchStage(5)).registerResearch().setParents("FOCUSFLUX").setMeta(new ResearchEntry.EnumResearchMeta[]{ResearchEntry.EnumResearchMeta.AUTOUNLOCK});
    }
}