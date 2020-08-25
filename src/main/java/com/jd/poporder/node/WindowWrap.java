package com.jd.poporder.node;

/**
 * @ClassName WindowWrap
 * @Description WindowWrap
 * @Author liudianfei3
 * @Date 2020/8/25 9:52
 * @Version 1.0
 */
public class WindowWrap<T> {
    // 时间窗口的长度，毫秒为单位
    private long windowLengthInMs;
    // 时间窗口的开始时间（ms）
    private long windowStart;
    // 统计数据
    private T value;

    public WindowWrap(long windowLengthInMs, long windowStart, T value) {
        this.windowLengthInMs = windowLengthInMs;
        this.windowStart = windowStart;
        this.value = value;
    }

    public long windowLength(){
        return windowLengthInMs;
    }

    public long windowStart(){
        return windowStart;
    }

    public T value(){
        return value;
    }

    public void setValue(T t){
        this.value = t;
    }

    public WindowWrap<T> resetTo(long startTime){
        this.windowStart = startTime;
        return this;
    }

    /**
     * 判断当前时间是否在当前的时间窗口中
     * @param timeMillis
     * @return
     */
    public boolean isTimeInWondow(long timeMillis){
        return windowStart <= timeMillis && timeMillis < windowStart + windowLengthInMs;
    }
}
