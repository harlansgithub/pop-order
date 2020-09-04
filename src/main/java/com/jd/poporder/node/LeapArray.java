package com.jd.poporder.node;

import com.jd.poporder.core.MetricBucket;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 数据统计基础数据结构，一个LeapArray横跨一个时间间隔，在这个时间间隔中，又会被分割成若干个Bucket
 * 被分割的越多，统计的数据就会越准确
 * sampleCount =  intervalInMs / windowLengthInMs
 */
public abstract class LeapArray<T> {
    // 每一个Bucket的时间跨度
    protected int windowLengthInMs;
    // 一个时间窗口被分割的Bucket的数量
    protected int sampleCount;
    // 时间窗口的间隔
    protected int intervalInMs;

    protected final AtomicReferenceArray<WindowWrap<MetricBucket>> array;

    public LeapArray(int sampleCount, int intervalInMs){
        this.sampleCount = sampleCount;
        this.intervalInMs = intervalInMs;
        this.windowLengthInMs = intervalInMs / sampleCount;
        array = new AtomicReferenceArray<WindowWrap<MetricBucket>>(sampleCount);
    }

    public WindowWrap<MetricBucket> currentWindow(long timeInMillis){
        // 计算在LeapArray中的位置
        int count = (int) (timeInMillis / windowLengthInMs);
        int indexId = count % sampleCount;

        // 读取对应的时间Bucket
        WindowWrap old = array.get(indexId);

        // 计算当前时间的Bucket的开始时间
        long windowStart = timeInMillis - timeInMillis % windowLengthInMs;

        WindowWrap<MetricBucket> newWindow = new WindowWrap<MetricBucket>(windowLengthInMs,windowStart,new MetricBucket());
        // 初始化或者数据被清空
        if (old == null){
            if (array.compareAndSet(indexId,null,newWindow)){
                return newWindow;
            }else {
                Thread.yield();
            }
        }
        // 当前窗口数据失效，更新窗口开始时间
        if (windowStart > old.windowStart()){
           new WindowWrap<T>(windowLengthInMs,windowStart, null);
        }


        return null;
    }
}
