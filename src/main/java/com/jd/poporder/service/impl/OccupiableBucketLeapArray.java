package com.jd.poporder.service.impl;

import com.jd.poporder.core.MetricBucket;
import com.jd.poporder.node.LeapArray;
import com.jd.poporder.node.WindowWrap;

public class OccupiableBucketLeapArray extends LeapArray<MetricBucket> {

    private FutureBucketLeapArray borrowArray;

    public OccupiableBucketLeapArray(int sampleCount, int intervalInMs) {
        super(sampleCount, intervalInMs);
        this.borrowArray = new FutureBucketLeapArray(sampleCount, intervalInMs);
    }
    @Override
    public MetricBucket newEmptyBucket(long timeMillis) {
        MetricBucket newBucket = new MetricBucket();

        MetricBucket borrowBucket = borrowArray.getWindowValue(timeMillis);
        if (borrowBucket != null) {
            newBucket.reset(borrowBucket);
        }

        return newBucket;
    }

    @Override
    protected WindowWrap<MetricBucket> resetWindowTo(WindowWrap<MetricBucket> windowWrap, long startTime) {
        windowWrap.resetTo(startTime);
        MetricBucket metricBucket = borrowArray.getWindowValue(startTime);
        if (metricBucket != null){
            windowWrap.value().reset();
            windowWrap.value().addPass((int) metricBucket.pass());
        } else {
            windowWrap.value().reset();
        }
        return windowWrap;
    }
}
