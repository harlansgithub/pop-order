package com.jd.poporder.service.impl;

import com.jd.poporder.core.MetricBucket;
import com.jd.poporder.node.LeapArray;
import com.jd.poporder.service.Metric;

import java.util.List;

public class ArrayMetric implements Metric {
    private final LeapArray<MetricBucket> data;
    public ArrayMetric(int sampleCount, int intervalInMs) {
        this.data = new OccupiableBucketLeapArray(sampleCount, intervalInMs);
    }

    public ArrayMetric(int sampleCount, int intervalInMs, boolean enableOccupy) {
        if (enableOccupy) {
            this.data = new OccupiableBucketLeapArray(sampleCount, intervalInMs);
        } else {
            this.data = new BucketLeapArray(sampleCount, intervalInMs);
        }
    }
    @Override
    public long success() {
        return 0;
    }

    @Override
    public long maxSuccess() {
        return 0;
    }

    @Override
    public long block() {
        long block = 0l;
        List<MetricBucket> list = data.values();
        for (MetricBucket bucket:list){
            block += bucket.block();
        }
        return block;
    }

    @Override
    public long exception() {
        return 0;
    }

    @Override
    public long pass() {
        long pass = 0l;
        List<MetricBucket> list = data.values();
        for (MetricBucket bucket:list){
            pass += bucket.pass();
        }
        return pass;
    }

    @Override
    public void addSuccess(int n) {

    }

    @Override
    public void addBlock(int n) {

    }

    @Override
    public void addException() {

    }

    @Override
    public void addPass() {

    }
}
