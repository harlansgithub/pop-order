package com.jd.poporder.slots;

import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;

/**
 * @ClassName ProcessorSlot
 * @Description 一些处理流程或者通知方式的容器
 * @Author liudianfei3
 * @Date 2020/8/30 23:28
 * @Version 1.0
 */
public interface ProcessorSlot<T> {
    /**
     * 处理当前节点的校验规则
     * @param context 当前的上下文
     * @param resourceWrapper 被控制的资源名称，即被限流、熔断、降级的链接
     * @param param 节点之间参数的传递
     * @param count 请求的数量
     * @param prioritized 是否优先处理（v1.0不支持）
     * @param args
     * @throws Throwable
     */
    void entry(Context context, ResourceWrapper resourceWrapper, T param, int count, boolean prioritized, Object...args) throws Throwable;

    /**
     * 处罚处理下一个校验规则
     * 每一个校验规则就是一个slot
     * @param context
     * @param resourceWrapper
     * @param obj
     * @param count
     * @param prioritized
     * @param args
     * @throws Throwable
     */
    void fireEntry(Context context, ResourceWrapper resourceWrapper, Object obj, int count, boolean prioritized, Object...args) throws Throwable;

    /**
     * 退出当前的处理节点solt
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    void exit(Context context, ResourceWrapper resourceWrapper, int count, Object...args);

    /**
     * 这个方法意味着成功完成exit的退出
     * @param context
     * @param resourceWrapper
     * @param count
     * @param args
     */
    void fireExit(Context context, ResourceWrapper resourceWrapper, int count, Object...args);
}
