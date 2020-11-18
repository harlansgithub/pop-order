package com.jd.poporder.node;

import com.jd.poporder.core.MetricBucket;
import com.jd.poporder.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据统计基础数据结构，一个LeapArray横跨一个时间间隔，在这个时间间隔中，又会被分割成若干个Bucket
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
    private ReentrantLock updatLock = new ReentrantLock(false);

    public LeapArray(int sampleCount, int intervalInMs){
        this.sampleCount = sampleCount;
        this.intervalInMs = intervalInMs;
        this.windowLengthInMs = intervalInMs / sampleCount;
        array = new AtomicReferenceArray<WindowWrap<MetricBucket>>(sampleCount);
    }

    /**
     * 根据当前时间或者所处的时间窗口
     * @param timeInMillis
     * @return
     */
    public WindowWrap<MetricBucket> currentWindow(long timeInMillis){
        // 计算在LeapArray中的位置
        int indexId = calculateTimeIdx(timeInMillis);

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
            if (updatLock.tryLock()){
                WindowWrap<MetricBucket> windowWrap = new WindowWrap<>(windowLengthInMs, windowStart, new MetricBucket());
                old = windowWrap;
                return windowWrap;
            }
        }


        return null;
    }

    private int calculateTimeIdx(long timeInMillis) {
        int count = (int) (timeInMillis / windowLengthInMs);
        return count % sampleCount;
    }

    public MetricBucket getWindowValue(long timeMillis) {
        if (timeMillis < 0) {
            return null;
        }
        int idx = calculateTimeIdx(timeMillis);

        WindowWrap<MetricBucket> bucket = array.get(idx);

        if (bucket == null || !bucket.isTimeInWondow(timeMillis)) {
            return null;
        }

        return bucket.value();
    }
    /**
     * 创建一个空的bucket
     * @param timeMillis
     * @return
     */
    public abstract T newEmptyBucket(long timeMillis);

    /**
     * 重置window的starttime 和 value
     * @param windowWrap
     * @param startTime
     * @return
     */
    protected abstract WindowWrap<T> resetWindowTo(WindowWrap<T> windowWrap, long startTime);

    /**
     * 当前时间所在的窗口
     * @return
     */
    public WindowWrap<MetricBucket> currentWindow(){
        return currentWindow(TimeUtil.currentTimeMillis());
    }

    public List<MetricBucket> values(){
        return values(TimeUtil.currentTimeMillis());
    }

    /**
     * 收集数据
     * @param timeMillis
     * @return
     */
    private List<MetricBucket> values(long timeMillis){
        if (timeMillis < 0){
            return new ArrayList<>();
        }
        int size = array.length();
        List<MetricBucket> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            WindowWrap<MetricBucket> windowWrap = array.get(i);
            if (windowWrap == null || isWindowDeprecated(timeMillis, windowWrap)){
                continue;
            }
            list.add(windowWrap.value());
        }
        return list;
    }

    /**
     * 判断当前窗口是否已经可以被废弃了
     * @param timeInMs
     * @param windowWrap
     * @return
     */
    public boolean isWindowDeprecated(long timeInMs, WindowWrap windowWrap){
        return timeInMs - windowWrap.windowStart() > intervalInMs;
    }

    /**
     * 获取统计时间窗口的总间隔
     * @return
     */
    public double getIntervalInSecond(){
        return intervalInMs / 1000.00;
    }
}
