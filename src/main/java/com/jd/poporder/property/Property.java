package com.jd.poporder.property;

import com.jd.poporder.listener.PopPropertyListener;

/**
 * @ClassName Property
 * @Description Property
 * @Author liudianfei3
 * @Date 2020/9/4 14:42
 * @Version 1.0
 */
public interface Property<T> {
    void addListener(PopPropertyListener<T> listener);
    boolean updataValue(T value);
}
