package com.jd.poporder.property;

import com.jd.poporder.listener.PopPropertyListener;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PopDynamicProperty
 * @Description PopDynamicProperty
 * @Author liudianfei3
 * @Date 2020/9/4 14:41
 * @Version 1.0
 */
public class PopDynamicProperty<T> implements Property<T>{
    protected Set<PopPropertyListener<T>> listeners = Collections.synchronizedSet(new HashSet<PopPropertyListener<T>>());
    private T value = null;

    public PopDynamicProperty() {
    }

    public PopDynamicProperty(T value) {
        super();
        this.value = value;
    }

    @Override
    public void addListener(PopPropertyListener<T> listener) {
        listeners.add(listener);
        listener.configLoad(value);
    }

    @Override
    public boolean updataValue(T value) {
        return false;
    }
}
