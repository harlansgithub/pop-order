package com.jd.poporder.slots;

import com.jd.poporder.chain.ProcessorSlotChain;
import com.jd.poporder.chain.SlotChainBuilder;

public class DefaultSlotChainBuilder implements SlotChainBuilder {
    @Override
    public ProcessorSlotChain build() {
        ProcessorSlotChain chain = new DefaultProcessorSlotChain();
        chain.addLast(new PopStatisticSlot());
        chain.addLast(new PopFlowSlot());
        return chain;
    }
}
