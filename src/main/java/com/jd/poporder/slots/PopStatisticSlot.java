package com.jd.poporder.slots;

import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.node.DefaultNode;

/**
 * @ClassName PopStatisticSlot
 * @Description PopStatisticSlot
 * @Author liudianfei3
 * @Date 2020/9/12 10:18
 * @Version 1.0
 */
public class PopStatisticSlot extends AbstractLinkedProcessorSlot<DefaultNode>{
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode node, int count,
                      boolean prioritized, Object... args) throws Throwable {
//        try {
            fireEntry(context, resourceWrapper, node, count, prioritized, args);
            // 验证通过
//            node.increaseThreadNum();
//            node.addPassRequest(count);
//        } catch (Throwable throwable) {
//            System.out.println("你被限流拉......");
//        } finally {
//        }

    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }
}
