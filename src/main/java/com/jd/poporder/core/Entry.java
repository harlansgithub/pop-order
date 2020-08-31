package com.jd.poporder.core;

import com.jd.poporder.service.ResourceNode;
import com.jd.poporder.utils.TimeUtil;

/**
 * @ClassName Entry
 * @Description Entry
 * @Author liudianfei3
 * @Date 2020/8/16 15:10
 * @Version 1.0
 */
public abstract class Entry implements AutoCloseable{
    private static final Object[] OBJECTS = new Object[0];
    private ResourceWrapper resourceWrapper;
    private long createTime;
    private ResourceNode curNode;
    private ResourceNode originNode;
    private Throwable error;


    @Override
    public void close() throws Exception {

    }

    public Entry(ResourceWrapper resourceWrapper) {
        this.resourceWrapper = resourceWrapper;
        this.createTime = TimeUtil.currentTimeMillis();
    }
}
