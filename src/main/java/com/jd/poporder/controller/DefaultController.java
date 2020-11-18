package com.jd.poporder.controller;

import com.jd.poporder.node.Node;

/**
 * @ClassName DefaultController
 * @Description DefaultController
 * @Author liudianfei3
 * @Date 2020/9/4 12:26
 * @Version 1.0
 */
public class DefaultController implements TrafficShapingController{
    private static final int DEFAULT_AVG_USED_TOKENS = 0;
    private double count;

    public DefaultController(double count) {
        this.count = count;
    }

    @Override
    public boolean canPass(Node node, int acquireCount, boolean prioritized) {
        int curCount = avgUsedTokens(node);
        System.out.println("curCount    >>>>  "+curCount);
        if (curCount + acquireCount > count) {
            System.out.println("bloking....."+curCount);
            return false;
        }else {
            System.out.println("通过了....");
        }
        return true;
    }

    private int avgUsedTokens(Node node) {
        return (int)node.passQps();
    }

    @Override
    public boolean canPass(Node node, int acquireCount) {
        return canPass(node, acquireCount, false);
    }
}
