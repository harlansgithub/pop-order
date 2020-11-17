package com.jd.poporder.controller;


import com.jd.poporder.node.Node;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

public interface TrafficShapingController {
    boolean canPass(Node node, int acquireCount, boolean prioritized);
    boolean canPass(Node node, int acquireCount);
}
