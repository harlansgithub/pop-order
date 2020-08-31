package com.jd.poporder.core;

import com.jd.poporder.context.Context;
import com.jd.poporder.slots.ProcessorSlot;

import javax.annotation.Resource;

/**
 * @ClassName CheckerEntry
 * @Description CheckerEntry
 * @Author liudianfei3
 * @Date 2020/8/30 23:26
 * @Version 1.0
 */
public class CheckerEntry extends Entry {
    protected Entry parent = null;
    protected Entry child = null;
    protected ProcessorSlot<Object> chain;
    protected Context context;

    public CheckerEntry(ResourceWrapper resourceWrapper, ProcessorSlot<Object> chain, Context context) {
        super(resourceWrapper);
        this.chain = chain;
        this.context = context;
    }
}
