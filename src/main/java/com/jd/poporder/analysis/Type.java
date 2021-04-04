package com.jd.poporder.analysis;


import java.io.Serializable;

/**
 * @author liudianfei3
 * @description desc
 * @date 2021/4/4 17:58
 */
public class Type implements Serializable {
    // 类型英文名称
    private String key;
    // 显示名称
    private String text;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
