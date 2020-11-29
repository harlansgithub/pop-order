package com.jd.poporder.slots.flow;

import com.jd.poporder.LimitedException.LimitedException;
import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.localstatics.LocalStaticsManager;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.slots.rule.PopFlowRule;
import com.jd.poporder.utils.EntryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class PopFlowRuleChecker {
    private static Logger logger = LoggerFactory.getLogger(PopFlowRuleChecker.class);
    public void checkFlow(Function<String, Collection<PopFlowRule>> ruleProvider, ResourceWrapper resource, Context context, DefaultNode node, int count, boolean prioritized ) throws Exception {
        if (ruleProvider == null || resource == null) {
            return;
        }
        Collection<PopFlowRule> flowRules = ruleProvider.apply(resource.getName());
        if (flowRules != null){
            for (PopFlowRule rule : flowRules) {
                if (!canPassCheck(rule, context, node, count, prioritized)) {
                    throw new LimitedException("被限流了");
                }else {
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
            // TODO liudianfei 添加动态获取节点数据
            DefaultNode staticNode = LocalStaticsManager.getNode(rule.getResource());
            if (staticNode == null){
                staticNode = new DefaultNode(new ResourceWrapper(rule.getResource(),EntryType.OUT, 1) {
                    @Override
                    public String getName() {
                        return super.getName();
                    }

                    @Override
                    public EntryType getEntryType() {
                        return super.getEntryType();
                    }

                    @Override
                    public int getResourceType() {
                        return super.getResourceType();
                    }
                });
                LocalStaticsManager.putNode(rule.getResource(), staticNode);
            }
            // TODO liudianfei
//            return true;
        }
        return rule.getRater().canPass(node, acquireCount, prioritized);
    }
}
