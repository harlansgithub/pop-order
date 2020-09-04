package com.jd.poporder.chain;


import com.jd.poporder.slots.DefaultSlotChainBuilder;
import com.jd.poporder.spi.PopSpiLoader;

/**
 * @ClassName SlotChainProvider
 * @Description SlotChainProvider
 * @Author liudianfei3
 * @Date 2020/9/1 12:47
 * @Version 1.0
 */
public final class SlotChainProvider {
    private static volatile SlotChainBuilder slotChainBuilder = null;
    public static ProcessorSlotChain newSlotChain(){
        if (slotChainBuilder != null){
            return  slotChainBuilder.build();
        }
        slotChainBuilder = PopSpiLoader.loadFirstInstanceOrDefault(SlotChainBuilder.class, DefaultSlotChainBuilder.class);

        if (slotChainBuilder == null) {
            slotChainBuilder = new DefaultSlotChainBuilder();
        } else {
        }
        return slotChainBuilder.build();
    }
    private SlotChainProvider() {}
}
