package com.jd.poporder.utils;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.jd.poporder.constants.RuleConstant;
import com.jd.poporder.controller.DefaultController;
import com.jd.poporder.controller.TrafficShapingController;
import com.jd.poporder.slots.rule.PopFlowRule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @ClassName PopFlowRuleUtil
 * @Description PopFlowRuleUtil
 * @Author liudianfei3
 * @Date 2020/9/4 11:21
 * @Version 1.0
 */
public final class PopFlowRuleUtil {
    public static Map<String, List<PopFlowRule>> buildPopFlowRuleMap(List<PopFlowRule> list){
        return buildPopFlowRuleMap(list,null);
    }
    public static Map<String, List<PopFlowRule>> buildPopFlowRuleMap(List<PopFlowRule> list, Predicate<PopFlowRule> fitler){
        return buildPopFlowRuleMap(list,fitler,true);
    }
    public static Map<String, List<PopFlowRule>> buildPopFlowRuleMap(List<PopFlowRule> list, Predicate<PopFlowRule> filter, boolean shouldSort){
        return buildPopFlowRuleMap(list, extractMap,filter, true);
    }
    public static <K> Map<K, List<PopFlowRule>> buildPopFlowRuleMap(List<PopFlowRule> list, Function<PopFlowRule,K> groupFunction, Predicate<PopFlowRule> filter, boolean shouldSort){
        Map<K,List<PopFlowRule>> newRuleMap = new ConcurrentHashMap<>();
        if (list == null || list.isEmpty()){
            return newRuleMap;
        }
        Map<K,Set<PopFlowRule>> temRuleMap = new ConcurrentHashMap<>();

        for (PopFlowRule rule:list){
            if ( filter != null && !filter.test(rule)) {
                continue;
            }
            if (rule.getLimitApp() == null){
                rule.setLimitApp(RuleConstant.LIMIT_APP_DEFAULT);
            }

            TrafficShapingController controller = new DefaultController(rule.getCount());
            rule.setRater(controller);

            K key = groupFunction.apply(rule);
            if (key == null) {
                continue;
            }
            Set<PopFlowRule> flowRules = temRuleMap.get(key);

            if (flowRules == null) {
                flowRules = new HashSet<>();
                temRuleMap.put(key, flowRules);
            }

            flowRules.add(rule);
        }
        for (Map.Entry<K, Set<PopFlowRule>> entries : temRuleMap.entrySet()) {
            List<PopFlowRule> rules = new ArrayList<>(entries.getValue());
            newRuleMap.put(entries.getKey(), rules);
        }
        return newRuleMap;
    }
    public static final Function<PopFlowRule, String> extractMap = new Function<PopFlowRule, String>() {
        @Override
        public String apply(PopFlowRule popFlowRule) {
            return popFlowRule.getResource();
        }
    };
}
