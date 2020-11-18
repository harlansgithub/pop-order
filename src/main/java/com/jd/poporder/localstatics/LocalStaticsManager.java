package com.jd.poporder.localstatics;

import com.jd.poporder.node.DefaultNode;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建一个本地节点数据统计缓存
 */
public class LocalStaticsManager {
    private static ConcurrentHashMap<String, DefaultNode> staticsMap = new ConcurrentHashMap<>();

    public static DefaultNode getNode(String resourceName){
        return staticsMap.get(resourceName);
    }
    public static void putNode(String resourceName,DefaultNode node){
        staticsMap.put(resourceName,node);
    }
}
