package com.jd.poporder.slots.flow;

import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.slots.rule.PopFlowRule;
import java.util.Collection;
import java.util.function.Function;

public class PopFlowRuleChecker {
    public void checkFlow(Function<String, Collection<PopFlowRule>> ruleProvider, ResourceWrapper resource, Context context, DefaultNode node, int count, boolean prioritized ) throws Exception {
        if (ruleProvider == null || resource == null) {
            return;
        }
        Collection<PopFlowRule> flowRules = ruleProvider.apply(resource.getName());
        if (flowRules != null){
            for (PopFlowRule rule : flowRules) {
                if (!canPassCheck(rule, context, node, count, prioritized)) {
                    throw new Exception(rule.getLimitApp() + "rule not pass");
                }
            }
        }
    }

    public boolean canPassCheck(PopFlowRule rule, Context context, DefaultNode node, int acquireCount,
                                             boolean prioritized) {
        String limitApp = rule.getLimitApp();
        if (limitApp == null) {
            return true;
        }

        return passLocalCheck(rule, context, node, acquireCount, prioritized);
    }

    private static boolean passLocalCheck(PopFlowRule rule, Context context, DefaultNode node, int acquireCount,
                                          boolean prioritized) {
        if (node == null) {
            return true;
        }

        return rule.getRater().canPass(node, acquireCount, prioritized);
    }
}
