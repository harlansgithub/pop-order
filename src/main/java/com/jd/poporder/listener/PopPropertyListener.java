package com.jd.poporder.listener;

/**
 * @ClassName PopPropertyListener
 * @Description PopPropertyListener
 * @Author liudianfei3
 * @Date 2020/9/4 11:14
 * @Version 1.0
 */
public interface PopPropertyListener<T> {
    /**
     * 当限流规则发生变化时通知引用者更新规则
     * @param value
     */
    void configUpdate(T value);

    /**
     * 程序启动时首次加载规则
     * @param value
     */
    void configLoad(T value);
}
