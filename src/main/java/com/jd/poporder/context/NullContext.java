package com.jd.poporder.context;

import com.jd.poporder.node.DefaultNode;

/**
 * @ClassName NullContext
 * @Description NullContext
 * @Author liudianfei3
 * @Date 2020/8/30 2:58
 * @Version 1.0
 */
public class NullContext extends Context{
    public NullContext(){
        this(null,null);
    }
    public NullContext(DefaultNode defaultNode, String name) {
        super(null, "null_context_name");
    }
}
