package com.jd.poporder.context;

import com.jd.poporder.core.Entry;
import com.jd.poporder.node.DefaultNode;

/**
 * 数据统计上下文
 */
public class Context {
    // 当前上下文存储的统计数据
    private DefaultNode defaultNode;

    // 当前正在处理的校验的节点
    private Entry entry;

    public static final Context NULL_CONTEXT = new NullContext();
}
