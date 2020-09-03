package com.jd.poporder.slots;

public abstract class ProcessorSlotChain extends AbstractLinkedProcessorSlot<Object>{
    public abstract void addFirst(AbstractLinkedProcessorSlot<?> protocolProcessor);
    public abstract void addLast(AbstractLinkedProcessorSlot<?> protocolProcessor);
}
