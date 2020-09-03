package com.jd.poporder.slots.rule;

import com.jd.poporder.context.Context;
import com.jd.poporder.node.DefaultNode;

public interface Rule {
    boolean passCheck(Context context, DefaultNode node, int count, Object...args);
}
