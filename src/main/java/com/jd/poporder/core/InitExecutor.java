package com.jd.poporder.core;

import com.jd.poporder.annotation.InitOrder;
import com.jd.poporder.init.InitFunc;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

public final class InitExecutor {
    // CAS依赖硬件CMPXCHG CPU指令保证并发情况下操作的原子性
    private static AtomicBoolean initialized = new AtomicBoolean(false);

    public static void init(){
        if (!initialized.compareAndSet(false,true)){
            return;
        }
        ServiceLoader<InitFunc> serviceLoader = ServiceLoader.load(InitFunc.class);
        List<OrderWrapper> orderList = new ArrayList<>();
        // 按照注解InitOrder value定制initFunc 的加载顺序
        for (InitFunc func:serviceLoader){
            storeToList(orderList, func);
        }
    }

    private static void storeToList(List<OrderWrapper> orderList, InitFunc func) {
        int position = getOrderPosition(func);
        int idx = 0;
        for (;idx < orderList.size();idx++){
            if (orderList.get(idx).orderNum > position){
                break;
            }
        }
        orderList.add(idx,new OrderWrapper(position,func));
    }

    private static int getOrderPosition(InitFunc func) {
        int position = 0;
        Class clazz = func.getClass();
        boolean marked = clazz.isAnnotationPresent(InitOrder.class);
        if (marked){
            position = func.getClass().getAnnotation(InitOrder.class).value();
        }else {
            position = InitOrder.LOWEST_OFFSET;
        }
        return position;
    }

    private static class OrderWrapper {
        private int orderNum;
        private InitFunc initFunc;

        public OrderWrapper(int orderNum, InitFunc initFunc) {
            this.orderNum = orderNum;
            this.initFunc = initFunc;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public InitFunc getInitFunc() {
            return initFunc;
        }
    }
}

