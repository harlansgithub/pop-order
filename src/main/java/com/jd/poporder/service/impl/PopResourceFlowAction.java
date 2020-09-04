package com.jd.poporder.service.impl;

import com.jd.poporder.constants.Constans;
import com.jd.poporder.constants.ContextNameConstants;
import com.jd.poporder.context.Context;
import com.jd.poporder.context.NullContext;
import com.jd.poporder.core.CheckerEntry;
import com.jd.poporder.core.Entry;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.core.StringResourceWrapper;
import com.jd.poporder.service.PopResourceService;
import com.jd.poporder.slots.ProcessorSlot;
import com.jd.poporder.utils.ContextUtil;
import com.jd.poporder.utils.EntryType;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PopResourceFlowAction implements PopResourceService {
    private static volatile Map<ResourceWrapper, ProcessorSlot> locCacheChainMap = new HashMap<>();
    private static final Object LOCK = new Object();
    @Override
    public Entry entry(String name, EntryType entryType, int count, Object... args) {

        return null;
    }

    /**
     *
     * @param name 被保护的资源的名称
     * @param resourceType 资源的类型
     * @param entryType TODO 未知
     * @param count TODO 未知 好像是什么令牌？
     * @param objects 拓展参数，我想应该是应对加字段的场景
     * @return
     */
    @Override
    public Entry entryWithType(String name, int resourceType, EntryType entryType, int count, Object[] objects) {
        return entryWithType( name, resourceType, entryType, count, false, objects);
    }

    public Entry entryWithType(String name, int resourceType, EntryType entryType, int count, boolean proritized, Object[] objects){
        StringResourceWrapper stringResourceWrapper = new StringResourceWrapper(name,entryType,resourceType);
        entryWithPriority(stringResourceWrapper,count,false,objects);
        return null;
    }

    public Entry entryWithPriority(ResourceWrapper resourceWrapper, int count, boolean prioritized, Object[] objects) throws Throwable {
        Context context = ContextUtil.getContext();
        // 上下文的个数超过了最大数量2000,返回一个没有校验规则的entry
        if (context instanceof NullContext){
            return new CheckerEntry(resourceWrapper,null,context);
        }

        if (context == null){
            context = InternalContextUtils.internalEnter(ContextNameConstants.DEFAULT_CONTEXT_NAME);
        }
        ProcessorSlot<Object> chain = lookProcessorChain(resourceWrapper);
        Entry e = new CheckerEntry(resourceWrapper, chain, context);
        chain.entry(context, resourceWrapper, null, count, false, objects);
        return e;
    }

    private static final class InternalContextUtils extends ContextUtil {
        static Context internalEnter(String name){
            return trurEnter(name);
        }
    }

    ProcessorSlot<Object> lookProcessorChain(ResourceWrapper resourceWrapper){
        ProcessorSlot<Object> chain = locCacheChainMap.get(resourceWrapper);
        if (chain == null){
            synchronized (LOCK){
                // DOUBLE CHECK
                chain = locCacheChainMap.get(resourceWrapper);
                if (chain == null){
                    if (locCacheChainMap.size() >= Constans.MAX_SLOT_SIZE){
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
