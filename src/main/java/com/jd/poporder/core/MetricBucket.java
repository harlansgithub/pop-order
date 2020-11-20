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

    public MetricBucket reset(){
        for (MetricEvent event:MetricEvent.values()){
            counters[event.ordinal()].reset();
        }
        return this;
    }
    public MetricBucket reset(MetricBucket bucket) {
        for (MetricEvent event : MetricEvent.values()) {
            counters[event.ordinal()].reset();
            counters[event.ordinal()].add(bucket.get(event));
        }
        return this;
    }

    public MetricBucket add(MetricEvent metricEvent, long count){
        counters[metricEvent.ordinal()].add(count);
        System.out.println("add pass count:" + counters[metricEvent.ordinal()]);
        return this;
    }
    private long get(MetricEvent event) {
        return counters[event.ordinal()].sum();
    }

    /**
     * 请求通过的数量
     * @return
     */
    public long pass(){
        return get(MetricEvent.PASS);
    }

    /**
     * 熔断的数量
     * @return
     */
    public long block(){
        return get(MetricEvent.BLOCKED);
    }

    public void addPass(int n){
        add(MetricEvent.PASS, n);
    }
}
