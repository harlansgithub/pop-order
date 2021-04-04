package com.jd.poporder.test.controller;

/**
 * @author liudianfei3
 * @description desc
 * @date 2021/4/3 22:32
 */
public class MapTrend {
    private String title;
    private Integer base;
    private String unit;
    private DataTrend data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public DataTrend getData() {
        return data;
    }

    public void setData(DataTrend data) {
        this.data = data;
    }
}
