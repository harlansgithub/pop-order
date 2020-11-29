package com.jd.poporder.LimitedException;

/**
 * 限流异常处理类
 */
public class LimitedException extends RuntimeException{
    public LimitedException(String message){
        super(message);
    }
}
