package com.jd.poporder.factory;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PopNamedThreadFactory
 * @Description PopNamedThreadFactory
 * @Author liudianfei3
 * @Date 2020/9/4 14:26
 * @Version 1.0
 */
public class PopNamedThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean daemon;
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        thread.setDaemon(daemon);
        return thread;
    }

    public PopNamedThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    public PopNamedThreadFactory(String namePrefix){
        this(namePrefix,false);
    }
}
