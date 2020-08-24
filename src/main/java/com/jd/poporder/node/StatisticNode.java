package com.jd.poporder.node;

import com.jd.poporder.service.Predicate;

import java.util.List;
import java.util.Map;

public class StatisticNode implements Node
{
    @Override
    public long totalRequest() {
        return 0;
    }

    @Override
    public long totalPass() {
        return 0;
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
        return 0;
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
