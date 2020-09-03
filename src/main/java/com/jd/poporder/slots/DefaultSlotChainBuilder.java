package com.jd.poporder.slots;

public class DefaultSlotChainBuilder implements SlotChainBuilder{
    @Override
    public ProcessorSlotChain build() {
        ProcessorSlotChain chain = new DefaultProcessorSlotChain();
        chain.addLast(new PopFlowSlot());
        return null;
    }
}
