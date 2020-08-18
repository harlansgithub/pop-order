package com.jd.poporder.core;

/**
 * @ClassName ControlNode
 * @Description ControlNode
 * @Author liudianfei3
 * @Date 2020/8/16 14:22
 * @Version 1.0
 */
public interface ResourceNode {
    // 总请求量
    long totalRequest();
    // 总通过量，通过不代表请求成功结束
    long totalPass();
    // 成功请求的数量
    long totalSuccess();
    long blockRequest();
    long totalException();
    double passQps();
    double blockQps();
    // passQps+blockQps
    double totalQps();
    double successQps();
    double exceptionQps();
}
