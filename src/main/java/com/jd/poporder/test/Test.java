package com.jd.poporder.test;

import com.alibaba.csp.sentinel.init.InitOrder;
import com.alibaba.csp.sentinel.slots.statistic.MetricEvent;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
@InitOrder(3)
public class Test {
    public static void main(String[] args) {
        System.out.println(MetricEvent.BLOCK.ordinal());
        System.out.println(MetricEvent.EXCEPTION.ordinal());
        AtomicLong[] atomicLongs = new AtomicLong[10];
    }
}
