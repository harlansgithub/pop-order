package com.jd.poporder.slots;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleChecker;
import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.slots.flow.PopFlowRuleChecker;
import com.jd.poporder.slots.flow.PopFlowRuleManager;
import com.jd.poporder.slots.rule.PopFlowRule;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PopFlowSlot extends AbstractLinkedProcessorSlot<DefaultNode>{
    private PopFlowRuleChecker checker;

    public PopFlowSlot() {
        this(new PopFlowRuleChecker());
    }

    public PopFlowSlot(PopFlowRuleChecker checker) {
        this.checker = checker;
    }

    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count, boolean prioritized, Object... args) throws Throwable {
        checkFlow(resourceWrapper, context, node, count, prioritized);
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }

    void checkFlow(ResourceWrapper resource, Context context, DefaultNode node, int count, boolean prioritized) throws Exception {
        checker.checkFlow(ruleProvider, resource, context, node, count, prioritized);
    }

    private final Function<String, Collection<PopFlowRule>> ruleProvider = new Function<String, Collection<PopFlowRule>>() {
        @Override
        public Collection<PopFlowRule> apply(String resource) {
            Map<String, List<PopFlowRule>> flowRules = PopFlowRuleManager.getFlowRuleMap();
            return flowRules.get(resource);
        }
    };
}
