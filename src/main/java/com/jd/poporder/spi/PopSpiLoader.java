package com.jd.poporder.spi;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 禁止继承
 */
public final class PopSpiLoader {
    private static final Map<String, ServiceLoader> SERVICE_LOADER_MAP = new ConcurrentHashMap<>();
    public static <T> T loadFirstInstanceOrDefault(Class<T> clazz, Class<? extends T> defaultClazz){
        try {
            String className = clazz.getName();
            ServiceLoader<T> serviceLoader = SERVICE_LOADER_MAP.get(className);
            if (serviceLoader == null){
                ServiceLoader sServiceLoader = ServiceLoader.load(clazz, clazz.getClassLoader());
                SERVICE_LOADER_MAP.put(className, sServiceLoader);
            }
            for (T instance:serviceLoader){
                if (instance.getClass() != defaultClazz){
                    return instance;
                }
            }
            return defaultClazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
