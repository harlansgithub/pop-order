package com.jd.poporder.node;

import com.jd.poporder.core.ResourceWrapper;

public class DefaultNode extends StatisticNode {
    private ResourceWrapper id;
    public DefaultNode(ResourceWrapper id) {
        this.id = id;
    }
    @Override
    public void increaseBlockQps(int count) {
        super.increaseBlockQps(count);
    }
    @Override
    public void addPassRequest(int count) {
        super.addPassRequest(count);
    }
}
