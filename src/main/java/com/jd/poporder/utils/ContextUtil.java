package com.jd.poporder.utils;

import com.jd.poporder.constants.ConstantsTable;
import com.jd.poporder.context.Context;
import com.jd.poporder.context.NullContext;
import com.jd.poporder.core.StringResourceWrapper;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.node.EntranceNode;
import com.jd.poporder.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ContextUtil
 * @Description 全局上下文的管理
 * @Author liudianfei3
 * @Date 2020/8/30 2:39
 * @Version 1.0
 */
public class  ContextUtil {
    // 线程对应的上线文需要保存起来
    private static ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();

    /**
     * 1.支持多个上下文统计数据隔离的情况，每个上线文名称对应一个独立的统计数据节点
     * 2.这么做是为了达到不同的上下文数据隔离
     * 3.key:上下文的名称 value:统计数据节点
     */
    private static volatile Map<String, DefaultNode> localCacheNode = new HashMap<>();

    // 同一个上下文对应多个线程，故这个里需要锁同步，保证同时只有一个线程创建相同的上下文
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static Context getContext(){
        return contextThreadLocal.get();
    }

    /**
     * 获取线程对应的上线文
     * @param contextName 数据格式依赖上下文名称
     * @return
     */
    public static Context trurEnter(String contextName){
        Context context = contextThreadLocal.get();
        if (context == null){
            DefaultNode node = localCacheNode.get(contextName);
            if (node == null){
                // 控制上下文的个数
                // 超过最大上下文个数，默认是两千个
                if (localCacheNode.size() > ConstantsTable.MAX_CONTEXT_SIZE){
                    contextThreadLocal.set(Context.NULL_CONTEXT);
                    return Context.NULL_CONTEXT;
                }else {
                    try {
                        LOCK.lock();
                        node = localCacheNode.get(contextName);
                        // DOUBLE CHECK
                        if (node == null) {
                            if (localCacheNode.size() > ConstantsTable.MAX_CONTEXT_SIZE){
                                contextThreadLocal.set(Context.NULL_CONTEXT);
                                return Context.NULL_CONTEXT;
                            }else {
                                // 创建Node
                                node = new EntranceNode(new StringResourceWrapper(contextName,EntryType.IN));
                                // 指针替换法修改缓存
                                Map<String,DefaultNode> contextMap = new HashMap<>(localCacheNode.size()+1);
                                contextMap.putAll(localCacheNode);
                                contextMap.put(contextName,node);
                                localCacheNode = contextMap;
                            }
                        }
                    } finally {
                        LOCK.unlock();
                    }
                }
            }
            context = new Context(node,contextName);
            contextThreadLocal.set(context);
        }
        return context;
    }
}
