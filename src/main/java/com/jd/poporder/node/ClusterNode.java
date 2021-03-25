package com.jd.poporder.node;

public class ClusterNode extends StatisticNode{
    // 限流资源名称
    private String resourceName;

    /*资源类型：
    IN 表示服务提供者
    OUT 表示服务消费者*/
    private int resourceType;

    public ClusterNode(String resourceName,int resourceType){
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public String getResourceName(){
        return resourceName;
    }
}
