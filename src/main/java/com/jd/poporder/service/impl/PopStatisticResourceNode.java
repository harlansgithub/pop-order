package com.jd.poporder.service.impl;

import com.jd.poporder.service.ResourceNode;

/**
 * 用来统计多种请求维度的数据
 */
public class PopStatisticResourceNode implements ResourceNode {
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
    public double exceptionQps() {
        return 0;
    }
}
