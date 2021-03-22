package com.jd.poporder.slots;

import com.jd.poporder.LimitedException.LimitedException;
import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.node.DefaultNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PopStatisticSlot
 * @Description PopStatisticSlot
 * @Author liudianfei3
 * @Date 2020/9/12 10:18
 * @Version 1.0
 */
public class PopStatisticSlot extends AbstractLinkedProcessorSlot<DefaultNode>{
    private AtomicInteger throwCounter = new AtomicInteger();
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count,
                      boolean prioritized, Object... args) throws Throwable {
        try {
            fireEntry(context, resourceWrapper, node, count, prioritized, args);
            // 验证通过
            node.increaseThreadNum();
            node.addPassRequest(count);
        } catch (LimitedException throwable) {
            System.out.println("throwCounter : " + throwCounter.incrementAndGet());
            throw throwable;
        } finally {
        }
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }
}
