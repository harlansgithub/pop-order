package com.jd.poporder.chain;

import com.jd.poporder.slots.AbstractLinkedProcessorSlot;

/**
 * @ClassName ProcessorSlotChain
 * @Description ProcessorSlotChain
 * @Author liudianfei3
 * @Date 2020/9/1 13:01
 * @Version 1.0
 */
public abstract class ProcessorSlotChain extends AbstractLinkedProcessorSlot<Object> {
    public abstract void addFirst(AbstractLinkedProcessorSlot<?> protocolProcessor);
    public abstract void addLast(AbstractLinkedProcessorSlot<?> protocolProcessor);
}
