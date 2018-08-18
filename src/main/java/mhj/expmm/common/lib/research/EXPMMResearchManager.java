package mhj.expmm.common.lib.research;

/**
 * @Author: ManualHuaJi
 */
@Deprecated
public class EXPMMResearchManager {

    //    public static ResearchEntry addResearch(String key, String name, String category, String icon[], String parents[], String siblings[], ResearchEntry.EnumResearchMeta researchType[], int displayColumn, int displayRow, ItemStack rewardItem[], ResearchStage.Knowledge[] rewardKnow, String text, ResourceLocation recipes[], ItemStack requiredItem[], ItemStack requiredCraft[], ResearchStage.Knowledge requiredKnow[], String requiredResearch[], int warp, String addendaText, ResourceLocation addendaRecpices[], String addendaResearch[]) {
//        ResearchEntry entry = new ResearchEntry();
//        entry.setKey(key);
//        entry.setName(name);
//        entry.setCategory(category);
//        ItemStack stack;
//        String[] icons = icon;
//        Object[] ir = new Object[icons.length];
//        for (int a = 0; a < icons.length; a++) {
//            stack = parseJSONtoItemStack(icons[a]);
//            if ((stack != null) && (!stack.isEmpty())) {
//                ir[a] = stack;
//            } else if (icons[a].startsWith("focus")) {
//                ir[a] = icons[a];
//            } else {
//                ir[a] = new ResourceLocation(icons[a]);
//            }
//        }
//        entry.setIcons(ir);
//        entry.setParents(parents);
//        entry.setSiblings(siblings);
//        entry.setMeta(researchType);
//        entry.setDisplayColumn(displayColumn);
//        entry.setDisplayRow(displayRow);
//        entry.setRewardItem(rewardItem);
//        entry.setRewardKnow(rewardKnow);
//
//
//        /*研究页面*/
//        ArrayList<ResearchStage> stages = new ArrayList();
//        ResearchStage stage = new ResearchStage();
//        stage.setText(text);
//        stage.setRecipes(recipes);
//        stage.setObtain(requiredItem);
//        stage.setCraft(requiredCraft);
//        if ((stage.getCraft() != null) && (stage.getCraft().length > 0)) {
//            int[] refs = new int[stage.getCraft().length];
//            int q = 0;
//            for (ItemStack stack1 : stage.getCraft()) {
//                int code = createItemStackHash(stack1);
//                craftingReferences.add(Integer.valueOf(code));
//                refs[q] = code;
//                q++;
//            }
//            stage.setCraftReference(refs);
//        }
//        stage.setKnow(requiredKnow);
//        stage.setResearch(requiredResearch);
//        stage.setWarp(warp);
//        entry.setStages(stages.toArray(new ResearchStage[stages.size()]));
//        /*研究附加页面*/
//        ArrayList<ResearchAddendum> addenda = new ArrayList();
//        ResearchAddendum addendum = new ResearchAddendum();
//        addendum.setText(addendaText);
//        addendum.setRecipes(addendaRecpices);
//        addendum.setResearch(addendaResearch);
//        addenda.add(addendum);
//        entry.setAddenda(addenda.toArray(new ResearchAddendum[addenda.size()]));
//        return entry;
//    }

}

