package com.jd.poporder.test.controller;

/**
 * @author liudianfei3
 * @description desc
 * @date 2021/4/3 11:16
 */
public class ResponseEntity {
    private MapTrend map;
    private Common common;
    private Type type;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public MapTrend getMap() {
        return map;
    }

    public void setMap(MapTrend map) {
        this.map = map;
    }
}
