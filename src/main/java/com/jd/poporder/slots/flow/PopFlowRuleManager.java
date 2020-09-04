package com.jd.poporder.slots.flow;

import com.alibaba.csp.sentinel.property.PropertyListener;
import com.jd.poporder.slots.rule.PopFlowRule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName FlowRuleManager
 * @Description FlowRuleManager
 * @Author liudianfei3
 * @Date 2020/9/4 11:11
 * @Version 1.0
 */
public class PopFlowRuleManager {
    private static final Map<String, List<PopFlowRule>> flowRules = new ConcurrentHashMap<>();
    private static final PropertyListener LISTENER = new PopFlowPropertyListener();

    private static final class PopFlowPropertyListener implements PropertyListener<List<PopFlowRule>> {

        @Override
        public void configUpdate(List<PopFlowRule> value) {

        }

        @Override
        public void configLoad(List<PopFlowRule> value) {

        }
    }
}
