package com.jd.poporder.controller;

import com.jd.poporder.node.Node;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName DefaultController
 * @Description DefaultController
 * @Author liudianfei3
 * @Date 2020/9/4 12:26
 * @Version 1.0
 */
public class DefaultController implements TrafficShapingController{
    private static final int DEFAULT_AVG_USED_TOKENS = 0;
    // qps的临界值
    private double count;
    private AtomicInteger blockCounter = new AtomicInteger();
    public DefaultController(double count) {
        this.count = count;
    }

    @Override
    public boolean canPass(Node node, int acquireCount, boolean prioritized) {
        int curCount = avgUsedTokens(node);
        if (curCount + acquireCount > count) {
            System.out.println("block count : " + blockCounter.incrementAndGet() + ",qps : "+curCount + ", acquireCount : " + acquireCount + ",  threshold : " + count);
            return false;
        }else {
            System.out.println("qps : " + curCount);
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
