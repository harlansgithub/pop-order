package com.jd.poporder.core;

import com.jd.poporder.context.Context;
import com.jd.poporder.context.NullContext;
import com.jd.poporder.slots.ProcessorSlot;
import com.jd.poporder.utils.ContextUtil;

import javax.annotation.Resource;

/**
 * @ClassName CheckerEntry
 * @Description CheckerEntry
 * @Author liudianfei3
 * @Date 2020/8/30 23:26
 * @Version 1.0
 */
public class CheckerEntry extends Entry {
    protected Entry parent = null;
    protected Entry child = null;
    protected ProcessorSlot<Object> chain;
    protected Context context;

    public CheckerEntry(ResourceWrapper resourceWrapper, ProcessorSlot<Object> chain, Context context) {
        super(resourceWrapper);
        this.chain = chain;
        this.context = context;

        setUpEntryFor(context);
    }

    private void setUpEntryFor(Context context) {
        if (context instanceof NullContext){
            return;
        }
        this.parent = context.getCurEntry();
        if (parent != null){
            ((CheckerEntry)parent).child = this;
        }
        context.setCurEntry(this);
    }

    @Override
    public void exit(int count, Object... args) throws Exception {
        trueExit(count, args);
    }

    @Override
    protected Entry trueExit(int count, Object... args) throws Exception {
        exitForContext(context, count, args);
        // 返回上一个校验节点，处理退出逻辑
        return parent;
    }

    @Override
    protected void exitForContext(Context context, int count, Object... args) throws Exception {
        if (context != null){
            if (context instanceof NullContext){
                return;
            }
        }
        // 退出过程中要保证按照校验链节点的顺序退出
        if (context.getCurEntry() != this){
            String curEntryNameInContext = context.getCurEntry() == null ? null : context.getCurEntry().getResourceWrapper().getName();
            CheckerEntry checkerEntry = (CheckerEntry)context.getCurEntry();
            while (checkerEntry != null){
                checkerEntry.exit(count, args);
                checkerEntry = (CheckerEntry) checkerEntry.parent;
            }
            throw new RuntimeException("handle current node error");
        }else {
            if (chain != null){
                chain.exit(context, getResourceWrapper(), count, args);
            }
            context.setCurEntry(parent);
            // 通过设置null 清除父子关系
            if (parent != null){
                ((CheckerEntry)parent).child = null;
            }
            if (parent == null){
                if (ContextUtil.isDefaultContext(context)){
                    ContextUtil.exit();
                }
            }
            // 清除上下文
            clearEntryContext();
        }
    }

    /**
     * 清除当前上下文，避免重复退出
     */
    protected void clearEntryContext() {
        this.context = null;
    }

    public Entry getParent() {
        return parent;
    }

    public void setParent(Entry parent) {
        this.parent = parent;
    }

    public Entry getChild() {
        return child;
    }

    public void setChild(Entry child) {
        this.child = child;
    }
}
