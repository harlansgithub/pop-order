package com.jd.poporder.core;

import com.jd.poporder.slots.statistic.MetricEvent;

import java.util.concurrent.atomic.LongAdder;

/**
 * 度量每个时间周期中的数据
 */
public class MetricBucket {
    private final LongAdder[] counters;

    public MetricBucket() {
        MetricEvent[] metricEvents = MetricEvent.values();
        this.counters = new LongAdder[metricEvents.length];
        for (MetricEvent event:metricEvents){
            counters[event.ordinal()] = new LongAdder();
        }

    }
}
