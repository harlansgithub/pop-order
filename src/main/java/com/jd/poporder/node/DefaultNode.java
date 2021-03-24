package com.jd.poporder.node;

import com.jd.poporder.core.ResourceWrapper;

public class DefaultNode extends StatisticNode {
    private ResourceWrapper id;
    private ClusterNode clusterNode;
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

    public ResourceWrapper getId(){
        return id;
    }

    public void setClusterNode(ClusterNode clusterNode){
        this.clusterNode = clusterNode;
    }

    public ClusterNode getClusterNode(){
        return clusterNode;
    }
}
