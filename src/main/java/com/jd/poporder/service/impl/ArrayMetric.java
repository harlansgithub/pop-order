package com.jd.poporder.service.impl;

import com.jd.poporder.service.Metric;

public class ArrayMetric implements Metric {
    @Override
    public long success() {
        return 0;
    }

    @Override
    public long maxSuccess() {
        return 0;
    }

    @Override
    public long block() {
        return 0;
    }

    @Override
    public long exception() {
        return 0;
    }

    @Override
    public long pass() {
        return 0;
    }

    @Override
    public void addSuccess(int n) {

    }

    @Override
    public void addBlock(int n) {

    }

    @Override
    public void addException() {

    }

    @Override
    public void addPass() {

    }
}
