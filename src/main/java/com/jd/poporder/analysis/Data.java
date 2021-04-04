package com.jd.poporder.analysis;

import java.io.Serializable;

/**
 * @author liudianfei3
 * @description desc
 * @date 2021/4/4 22:38
 */
public class Data implements Serializable {
    private String name;
    private String[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
