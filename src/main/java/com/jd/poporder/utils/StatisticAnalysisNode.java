package com.jd.poporder.utils;

import com.jd.poporder.analysis.AnalysisUnit;
import com.jd.poporder.analysis.Common;
import com.jd.poporder.analysis.Data;
import com.jd.poporder.analysis.Type;

import java.io.Serializable;
import java.util.List;

/**
 * @author liudianfei3
 * @description 统计分析引擎
 * @date 2021/4/4 16:16
 */
public class StatisticAnalysisNode implements Serializable {
    // 时间序列
    private Common common;
    // 系统类型
    private List<Type> type;
    // 系统A
    private AnalysisUnit synEsService;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public AnalysisUnit getSynEsService() {
        return synEsService;
    }

    public void setSynEsService(AnalysisUnit synEsService) {
        this.synEsService = synEsService;
    }
}
