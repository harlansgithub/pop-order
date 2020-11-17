package com.jd.poporder.slots;

import com.jd.poporder.chain.ProcessorSlotChain;
import com.jd.poporder.chain.SlotChainBuilder;
import com.jd.poporder.slots.flow.PopFlowRuleChecker;

public class DefaultSlotChainBuilder implements SlotChainBuilder {
    @Override
    public ProcessorSlotChain build() {
        ProcessorSlotChain chain = new DefaultProcessorSlotChain();
        chain.addLast(new PopFlowSlot(new PopFlowRuleChecker()));
        return chain;
    }
}
