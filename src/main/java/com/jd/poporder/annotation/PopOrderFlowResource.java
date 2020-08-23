package com.jd.poporder.annotation;

import com.jd.poporder.utils.EntryType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PopOrderFlowResource {
    String value() default "";
    EntryType entryType() default EntryType.OUT;
    int resourceType() default 0;
}
