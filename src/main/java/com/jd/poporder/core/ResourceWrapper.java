package com.jd.poporder.core;

import com.jd.poporder.utils.EntryType;

/**
 * @ClassName ResourceWrapper
 * @Description ResourceWrapper
 * @Author liudianfei3
 * @Date 2020/8/16 15:21
 * @Version 1.0
 */
public abstract class ResourceWrapper {
    private final String name;
    private final EntryType entryType;
    private final int resourceType;

    public ResourceWrapper(String name, EntryType entryType, int resourceType) {
        this.name = name;
        this.entryType = entryType;
        this.resourceType = resourceType;
    }

    public String getName() {
        return name;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public int getResourceType() {
        return resourceType;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceWrapper) {
            ResourceWrapper rw = (ResourceWrapper)obj;
            return rw.getName().equals(getName());
        }
        return false;
    }
}
