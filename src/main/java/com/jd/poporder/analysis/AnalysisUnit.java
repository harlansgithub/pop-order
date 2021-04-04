package com.jd.poporder.analysis;

import java.io.Serializable;
import java.util.List;

/**
 * @author liudianfei3
 * @description desc
 * @date 2021/4/4 18:07
 */
public class AnalysisUnit implements Serializable {
    private String title;
    private List<Data> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
