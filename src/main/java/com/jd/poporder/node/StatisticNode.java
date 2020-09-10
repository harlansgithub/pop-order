package com.jd.poporder.node;

import com.jd.poporder.property.IntervalProperty;
import com.jd.poporder.property.SampleCountProperty;
import com.jd.poporder.service.Metric;
import com.jd.poporder.service.Predicate;
import com.jd.poporder.service.impl.ArrayMetric;

import java.util.List;
import java.util.Map;

/**
 * 数据统计核心计算逻辑，使用滑动时间窗口计数
 */
public class StatisticNode implements Node {
    /**
     * 每秒请求计数器，每500ms一个时间窗口
     */
    private transient volatile Metric rollingCounterInSecond = new ArrayMetric(SampleCountProperty.SAMPLE_COUNT,
            IntervalProperty.INTERVAL);
    /**
     * 一分钟计数,每秒一个时间窗口
     */
    private transient Metric rollingCounterInMinute = new ArrayMetric(60, 60 * 1000, false);
    @Override
    public long totalRequest() {
        return rollingCounterInMinute.pass() + rollingCounterInMinute.block();
    }

    @Override
    public long totalPass() {
        return rollingCounterInMinute.pass();
    }

    @Override
    public long totalSuccess() {
        return 0;
    }

    @Override
    public long blockRequest() {
        return 0;
    }

    @Override
    public long totalException() {
        return 0;
    }

    @Override
    public double passQps() {
        return 0;
    }

    @Override
    public double blockQps() {
        return 0;
    }

    @Override
    public double totalQps() {
        return passQps() + blockQps();
    }

    @Override
    public double successQps() {
        return 0;
    }

    @Override
    public double maxSuccessQps() {
        return 0;
    }

    @Override
    public void increaseBlockQps(int count) {

    }

    @Override
    public double exceptionQps() {
        return 0;
    }

    @Override
    public double avgRt() {
        return 0;
    }

    @Override
    public double minRt() {
        return 0;
    }

    @Override
    public int curThreadNum() {
        return 0;
    }

    @Override
    public double previousBlockQps() {
        return 0;
    }

    @Override
    public double previousPassQps() {
        return 0;
    }

    @Override
    public Map<Long, MetricNode> metrics() {
        return null;
    }

    @Override
    public List<MetricNode> rawMetricsInMin(Predicate<Long> timePredicate) {
        return null;
    }

    @Override
    public void addPassRequest(int count) {

    }

    @Override
    public void addRtAndSuccess(long rt, int success) {

    }

    @Override
    public void increaseExceptionQps(int count) {

    }

    @Override
    public void increaseThreadNum() {

    }

    @Override
    public void decreaseThreadNum() {

    }

    @Override
    public void reset() {

    }

    @Override
    public long tryOccupyNext(long curTime, int acquiredCount, double threshold) {
        return 0;
    }

    @Override
    public long waiting() {
        return 0;
    }

    @Override
    public void addWaitingRequest(long futureTimeStamp, int acquireCount) {

    }

    @Override
    public void addOccupiedPass(int acquireCount) {

    }

    @Override
    public double occupiedPassQps() {
        return 0;
    }
}
