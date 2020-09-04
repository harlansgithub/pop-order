package com.jd.poporder.chain;

import com.jd.poporder.slots.ProcessorSlot;

/**
 * @ClassName SlotChainProvider
 * @Description SlotChainProvider
 * @Author liudianfei3
 * @Date 2020/9/1 12:47
 * @Version 1.0
 */
public final class SlotChainProvider {
    private static volatile SlotChainBuilder slotChainBuilder = null;
    public static ProcessorSlot newSlotChain(){
        if (slotChainBuilder != null){
            return  slotChainBuilder.build();
        }
    }


}
