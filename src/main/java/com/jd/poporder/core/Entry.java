package com.jd.poporder.core;

import com.jd.poporder.context.Context;
import com.jd.poporder.node.Node;
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
    // 资源包装对象
    private ResourceWrapper resourceWrapper;
    // entry 创建时间
    private long createTime;
    // 当前校验的节点
    private Node curNode;
    // 校验链的源头校验节点
    private ResourceNode originNode;
    private Throwable error;


    @Override
    public void close() throws Exception {

    }

    public ResourceWrapper getResourceWrapper() {
        return resourceWrapper;
    }

    /**
     * 设置创建时间
     * @param resourceWrapper
     */
    public Entry(ResourceWrapper resourceWrapper) {
        this.resourceWrapper = resourceWrapper;
        this.createTime = TimeUtil.currentTimeMillis();
    }

    /**
     * 校验完毕之后，全校验链路退出逻辑，public 暴露方法，供包外使用
     * @param count 校验的数量
     * @param args
     * @throws Exception
     */
    public abstract void exit(int count, Object... args) throws RuntimeException;

    /**
     * 对exit包了一层,使用protected修改，允许同一包或者子类实现，核心退出逻辑不允许修改
     * @param count
     * @param args
     * @throws Exception
     */
    protected abstract Entry trueExit(int count, Object... args) throws RuntimeException;

    /**
     * 从上下文中退出逻辑
     * @param count
     * @param args
     * @throws Exception
     */
    protected abstract void exitForContext(Context context, int count, Object... args) throws RuntimeException;

    public Node getCurNode() {
        return curNode;
    }

    public void setCurNode(Node curNode) {
        this.curNode = curNode;
    }
}
