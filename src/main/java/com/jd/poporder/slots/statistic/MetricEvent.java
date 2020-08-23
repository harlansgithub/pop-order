package com.jd.poporder.slots.statistic;

/**
 * @ClassName MetricEvent
 * @Description MetricEvent
 * @Author liudianfei3
 * @Date 2020/8/23 17:05
 * @Version 1.0
 */

/**
 * 度量维度类型
 */
public enum MetricEvent {
    PASS,
    BLOCKED,
    EXCEPTION,
    SUCCESS,
    RT,
    OCCUPIED_PASS;

    private MetricEvent() {
    }
}
