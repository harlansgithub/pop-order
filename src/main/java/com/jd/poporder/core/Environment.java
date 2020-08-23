package com.jd.poporder.core;

import com.jd.poporder.service.PopResourceService;
import com.jd.poporder.service.impl.PopResourceFlowAction;

/**
 * @ClassName Environment
 * @Description Environment
 * @Author liudianfei3
 * @Date 2020/8/16 11:17
 * @Version 1.0
 */
public class Environment {
    public static PopResourceService popResourceService = new PopResourceFlowAction();
    static {

    }
}
