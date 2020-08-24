package com.jd.poporder.node;

public interface OccupySupport {
    /**
     * 占用最近下一个时间窗口中令牌
     * 线程每抢占一次，线程就要休眠对应的时间，避免过度抢占未来的时间窗口的令牌
     * @param curTime 当前时间（ms）
     * @param acquiredCount 占用令牌的数量
     * @param threshold qps 限制值
     * @return
     */
    long tryOccupyNext(long curTime, int acquiredCount, double threshold);

    /**
     * 当前处于等待状态的数量，用来debug
     * @return
     */
    long waiting();
    void addWaitingRequest(long futureTimeStamp, int acquireCount);
    void addOccupiedPass(int acquireCount);
    double occupiedPassQps();
}
