package com.jd.poporder.property;

import com.jd.poporder.listener.PopPropertyListener;
import com.jd.poporder.slots.rule.PopFlowRule;

import java.util.*;

/**
 * @ClassName PopDynamicProperty
 * @Description PopDynamicProperty
 * @Author liudianfei3
 * @Date 2020/9/4 14:41
 * @Version 1.0
 */
public class PopDynamicProperty<T> implements Property<T>{
    // TODO Collections.synchronizedSet ???
    protected Set<PopPropertyListener<T>> listeners = Collections.synchronizedSet(new HashSet<PopPropertyListener<T>>());
    private T value = null;

    public PopDynamicProperty() {
        List<PopFlowRule> value = new ArrayList<>();
        PopFlowRule rule = new PopFlowRule("test");
        rule.setCount(2);
        value.add(rule);
        this.value = (T) value;
    }
    public PopDynamicProperty(T value) {
        super();
        this.value = value;
    }
    public void setValue(T value){
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
