package com.jd.poporder.service;

import com.jd.poporder.core.Entry;
import com.jd.poporder.utils.EntryType;

public interface ResourceTypeSupportService {
    /**
     *
     * @param name 被保护的资源的名称
     * @param resourceType 资源的类型
     * @param entryType TODO 未知
     * @param count TODO 未知 好像是什么令牌？
     * @param objects 拓展参数，我想应该是应对加字段的场景
     * @return
     */
    Entry entryWithType(String name, int resourceType , EntryType entryType , int count ,Object[] objects) throws Throwable;
}
