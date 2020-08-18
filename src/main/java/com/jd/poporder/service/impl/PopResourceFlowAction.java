package com.jd.poporder.service.impl;

import com.jd.poporder.core.Entry;
import com.jd.poporder.core.ResourceWrapper;
import com.jd.poporder.core.StringResourceWrapper;
import com.jd.poporder.service.PopResourceService;
import com.jd.poporder.utils.EntryType;

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
        return null;
    }
}
