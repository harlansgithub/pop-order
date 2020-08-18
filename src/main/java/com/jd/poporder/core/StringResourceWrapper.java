package com.jd.poporder.core;

import com.jd.poporder.utils.EntryType;

public class StringResourceWrapper extends ResourceWrapper{
    public StringResourceWrapper(String name, EntryType entryType, int resourceType) {
        super(name, entryType, resourceType);
    }
    public StringResourceWrapper(String name, EntryType entryType) {
        super(name, entryType, 0);
    }
}
