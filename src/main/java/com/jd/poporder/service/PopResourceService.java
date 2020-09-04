package com.jd.poporder.service;

import com.jd.poporder.core.Entry;
import com.jd.poporder.utils.EntryType;

public interface PopResourceService extends ResourceTypeSupportService{
    Entry entry(String name,int resourceType, EntryType entryType, int count, Object ... args) throws Throwable;
}
