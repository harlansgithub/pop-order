package com.jd.poporder.service.impl;

import com.jd.poporder.chain.ProcessorSlotChain;
import com.jd.poporder.chain.SlotChainProvider;
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
import sun.awt.windows.ThemeReader;

import java.util.HashMap;
import java.util.Map;

public class PopResourceFlowAction implements PopResourceService {
    private static volatile Map<ResourceWrapper, ProcessorSlotChain> locCacheChainMap = new HashMap<>();
    private static final Object LOCK = new Object();
    @Override
    public Entry entry(String name,int resourceType, EntryType entryType, int count, Object... args) throws Throwable {
        return entryWithType(name, resourceType, entryType, count, args);
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
    public Entry entryWithType(String name, int resourceType, EntryType entryType, int count, Object[] objects) throws Throwable {
        return entryWithType( name, resourceType, entryType, count, false, objects);
    }

    public Entry entryWithType(String name, int resourceType, EntryType entryType, int count, boolean proritized, Object[] objects) throws Throwable {
        StringResourceWrapper stringResourceWrapper = new StringResourceWrapper(name,entryType,resourceType);
        return entryWithPriority(stringResourceWrapper,count,false,objects);
    }

    public Entry entryWithPriority(ResourceWrapper resourceWrapper, int count, boolean prioritized, Object[] objects) throws Throwable {
        Context context = ContextUtil.getContext();
        // 上下文的个数超过了最大数量2000,返回一个没有校验规则的entry
        if (context instanceof NullContext){
            return new CheckerEntry(resourceWrapper,null,context);
        }
        System.out.println(Thread.currentThread().getId());
        if (context == null){
            context = InternalContextUtils.internalEnter(ContextNameConstants.DEFAULT_CONTEXT_NAME);
        }
        System.out.println("context name is : " + context.getName());
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
        ProcessorSlotChain chain = locCacheChainMap.get(resourceWrapper);
        if (chain == null){
            synchronized (LOCK){
                // DOUBLE CHECK
                chain = locCacheChainMap.get(resourceWrapper);
                if (chain == null){
                    if (locCacheChainMap.size() >= Constans.MAX_SLOT_SIZE){
                        return null;
                    }
                    chain = SlotChainProvider.newSlotChain();
                    Map<ResourceWrapper, ProcessorSlotChain> newMap = new HashMap<>(locCacheChainMap.size() + 1);
                    newMap.putAll(locCacheChainMap);
                    newMap.put(resourceWrapper, chain);
                    locCacheChainMap = newMap;
                }
            }
        }
        return chain;
    }
}
