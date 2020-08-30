package com.jd.poporder.service.impl;

import com.jd.poporder.constants.ContextNameConstants;
import com.jd.poporder.context.Context;
import com.jd.poporder.context.NullContext;
import com.jd.poporder.core.Entry;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.core.StringResourceWrapper;
import com.jd.poporder.service.PopResourceService;
import com.jd.poporder.utils.ContextUtil;
import com.jd.poporder.utils.EntryType;

import java.util.Objects;

public class PopResourceFlowAction implements PopResourceService {
    @Override
    public Entry entry(String name, EntryType entryType, int count, Object... args) {

        return null;
    }

    @Override
    public Entry entryWithType(String name, int resourceType, EntryType entryType, int count, Object[] objects) {
        return entryWithType( name, resourceType, entryType, count, false, objects);
    }

    public Entry entryWithType(String name, int resourceType, EntryType entryType, int count, boolean proritized, Object[] objects){
        StringResourceWrapper stringResourceWrapper = new StringResourceWrapper(name,entryType,resourceType);
        Context context = ContextUtil.getContext();
        if (context instanceof NullContext){
//            return context;
        }

        return null;
    }

    public Entry entryWithProritized(ResourceWrapper resourceWrapper, int count, boolean proritized, Object... objects){
        return null;
    }

    private static final class InternalContextUtils extends ContextUtil {
        static Context internalEnter(String name){
            return trurEnter(name);
        }
    }
}
