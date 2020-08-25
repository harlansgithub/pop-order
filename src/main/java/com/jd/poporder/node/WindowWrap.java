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
    private long windowStart;
    private T value;

}
