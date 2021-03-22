package com.jd.poporder.test;


import com.jd.poporder.annotation.InitOrder;
import com.jd.poporder.slots.statistic.MetricEvent;

import java.util.concurrent.atomic.AtomicLong;
@InitOrder(3)
public class Test {
    public static void main(String[] args) {
        AtomicLong[] atomicLongs = new AtomicLong[10];
    }
}
