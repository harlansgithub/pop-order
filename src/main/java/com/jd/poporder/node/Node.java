package com.jd.poporder.node;


import com.jd.poporder.service.Predicate;

import java.util.List;
import java.util.Map;

public interface Node extends OccupySupport{
    long totalRequest();
    long totalPass();
    long totalSuccess();
    long blockRequest();
    long totalException();
    double passQps();
    double blockQps();
    // passQps + blockQps
    double totalQps();
    double successQps();
    double maxSuccessQps();
    void increaseBlockQps(int count);
    double exceptionQps();
    // 每秒中的平均响应时间
    double avgRt();
    // 最小的响应时间
    double minRt();
    // 当前活着的线程数量
    int curThreadNum();
    // 上一秒的熔断qps
    double previousBlockQps();
    // 上一秒通过的qps
    double previousPassQps();
    Map<Long, MetricNode> metrics();
    List<MetricNode> rawMetricsInMin(Predicate<Long> timePredicate);
    void addPassRequest(int count);
    void addRtAndSuccess(long rt, int success);
    void increaseExceptionQps(int count);
    void increaseThreadNum();
    void decreaseThreadNum();
    void reset();
}
