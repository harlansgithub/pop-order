package com.jd.poporder.test.controller;

import com.jd.poporder.annotation.PopOrderFlowResource;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.utils.ContextUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/limiter")
public class FllowerLimiter {
    private AtomicInteger count = new AtomicInteger();
    @PopOrderFlowResource("getOrderData")
    @RequestMapping("/orderdata")
    public String getOrderData(){
        return "success";
    }



    @PopOrderFlowResource("getOrderData1")
    @RequestMapping("/orderdata1")
    public String getOrderData1(){
        return "success";
    }
}
