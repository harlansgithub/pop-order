package com.jd.poporder.slots.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.jd.poporder.context.Context;
import com.jd.poporder.controller.TrafficShapingController;
import com.jd.poporder.node.DefaultNode;

public class PopFlowRule extends AbstractRule{
    private TrafficShapingController controller;
    public PopFlowRule() {
        super();
        setLimitApp(RuleConstant.LIMIT_APP_DEFAULT);
    }

    public PopFlowRule(String resourceName) {
        super();
        setResource(resourceName);
        setLimitApp(RuleConstant.LIMIT_APP_DEFAULT);
    }

    public TrafficShapingController getRater() {
        return controller;
    }
    @Override
    public boolean passCheck(Context context, DefaultNode node, int count, Object... args) {
        return false;
    }
}
