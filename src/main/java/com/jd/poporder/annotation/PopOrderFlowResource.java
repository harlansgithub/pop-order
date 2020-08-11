package com.jd.poporder.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PopOrderFlowResource {
    String value() default "";
}
