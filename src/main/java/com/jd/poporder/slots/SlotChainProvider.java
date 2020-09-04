package com.jd.poporder.slots;


import com.jd.poporder.chain.ProcessorSlotChain;
import com.jd.poporder.chain.SlotChainBuilder;
import com.jd.poporder.spi.PopSpiLoader;

public class SlotChainProvider {
    private static volatile SlotChainBuilder slotChainBuilder = null;
    public static ProcessorSlotChain newSlotChain() {
        if (slotChainBuilder != null) {
            return slotChainBuilder.build();
        }

        // Resolve the slot chain builder SPI.
        slotChainBuilder = PopSpiLoader.loadFirstInstanceOrDefault(SlotChainBuilder.class, DefaultSlotChainBuilder.class);

        if (slotChainBuilder == null) {
            slotChainBuilder = new DefaultSlotChainBuilder();
        }
        return slotChainBuilder.build();
    }

    private SlotChainProvider() {}
}
