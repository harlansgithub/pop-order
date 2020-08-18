package com.jd.poporder.service;

public interface Metric {
    long success();
    long maxSuccess();
    long block();
    long exception();
    long pass();
    void addSuccess(int n);
    void addBlock(int n);
    void addException();
    void addPass();

}
