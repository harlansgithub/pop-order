package com.jd.poporder.slots;

import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.node.ClusterNode;
import com.jd.poporder.node.DefaultNode;

import java.util.HashMap;
import java.util.Map;

public class PopClusterBuilderSlot extends AbstractLinkedProcessorSlot<DefaultNode>{
    // 静态缓存所有资源限流节点
    private static volatile Map<ResourceWrapper, ClusterNode> map = new HashMap<>();
    // 资源校验链条中的资源数据统计节点
    private ClusterNode clusterNode = null;
    // 锁对象
    private static final Object LOCK = new Object();
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count, boolean prioritized, Object... args) throws Throwable {
        if (clusterNode == null){
            synchronized (LOCK){
                // DOUBLE CHECK
                if (clusterNode == null){
                    // 创建一个clusterNode
                    clusterNode = new ClusterNode(resourceWrapper.getName(),resourceWrapper.getResourceType());
                    HashMap<ResourceWrapper,ClusterNode> newCache = new HashMap<>(Math.max(map.size(),16));
                    newCache.putAll(map);
                    newCache.put(node.getId(),clusterNode);

                    map = newCache;
                }
            }
        }
        node.setClusterNode(clusterNode);
        fireEntry(context,resourceWrapper,node,count,prioritized,args);
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }
}
