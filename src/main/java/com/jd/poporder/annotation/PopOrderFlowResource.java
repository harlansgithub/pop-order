package com.annotation;

import com.jd.poporder.utils.EntryType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PopOrderFlowResource {
    String value() default "";
    EntryType entryType() default EntryType.OUT;
    int resourceType() default 0;
}
