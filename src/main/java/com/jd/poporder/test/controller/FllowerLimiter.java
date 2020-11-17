package com.jd.poporder.test.controller;

import com.jd.poporder.annotation.PopOrderFlowResource;
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
        System.out.println(count.getAndIncrement());
        return "success";
    }
}
