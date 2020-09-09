package com.jd.poporder.service.impl;

import com.jd.poporder.core.MetricBucket;
import com.jd.poporder.node.LeapArray;
import com.jd.poporder.node.WindowWrap;

public class BucketLeapArray extends LeapArray<MetricBucket> {

    public BucketLeapArray(int sampleCount, int intervalInMs) {
        super(sampleCount, intervalInMs);
    }

    @Override
    public MetricBucket newEmptyBucket(long time) {
        return new MetricBucket();
    }

    @Override
    protected WindowWrap<MetricBucket> resetWindowTo(WindowWrap<MetricBucket> w, long startTime) {
        w.resetTo(startTime);
        w.value().reset();
        return w;
    }
}