package com.jd.poporder.slots;

import com.jd.poporder.context.Context;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.node.Node;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PopNodeSelectorSlot
 * @Description PopNodeSelectorSlot
 * @Author liudianfei3
 * @Date 2020/9/12 11:40
 * @Version 1.0
 */
public class PopNodeSelectorSlot extends AbstractLinkedProcessorSlot<DefaultNode>{
    private volatile Map<String, DefaultNode> map =  new HashMap<>();
    @Override
    public void entry(Context context, ResourceWrapper resourceWrapper, DefaultNode param, int count,
                      boolean prioritized, Object... args) throws Throwable {
        DefaultNode node = map.get(context.getName());
        if (node == null){
            synchronized (this){
                // double check
                if (node == null) {
                    node = new DefaultNode(resourceWrapper);
                    Map<String, DefaultNode> newMap = new HashMap<>(map.size() + 1);
                    newMap.putAll(map);
                    newMap.put(context.getName(), node);
                    map = newMap;
                }
            }
        }
        context.setCurNode(node);
        fireEntry(context, resourceWrapper, node, count, prioritized, args);
    }

    @Override
    public void exit(Context context, ResourceWrapper resourceWrapper, int count, Object... args) {

    }
}
