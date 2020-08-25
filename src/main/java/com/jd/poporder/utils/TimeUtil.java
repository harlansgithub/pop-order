package com.jd.poporder.utils;


import java.util.concurrent.TimeUnit;

public class TimeUtil {
    private static volatile long currentTimeMillis;
    static {
        currentTimeMillis = System.currentTimeMillis();
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    currentTimeMillis = System.currentTimeMillis();
                    try {
                        // 睡眠1毫秒
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }
        },"pop-time-tick-thread");
        daemon.start();
    }
    public static long currentTimeMillis(){
        return currentTimeMillis;
    }
}
