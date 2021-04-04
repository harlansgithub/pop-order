package com.jd.poporder.analysis;

import java.io.Serializable;
import java.util.List;

/**
 * @author liudianfei3
 * @description desc
 * @date 2021/4/4 17:57
 */
public class Common implements Serializable {
    // 时间序列
    private String[] time;

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }
}
