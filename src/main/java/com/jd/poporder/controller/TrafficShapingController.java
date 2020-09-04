package com.jd.poporder.controller;


import com.jd.poporder.node.Node;

public interface TrafficShapingController {
    boolean canPass(Node node, int acquireCount, boolean prioritized);
    boolean canPass(Node node, int acquireCount);
}
