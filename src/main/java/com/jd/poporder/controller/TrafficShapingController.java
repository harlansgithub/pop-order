package com.jd.poporder.controller;


import com.jd.poporder.node.Node;

public interface TrafficShapingController {
    boolean canPass(Node node, int acquireCount, boolean prioritized);
    boolean canPass(com.alibaba.csp.sentinel.node.Node node, int acquireCount);
}
