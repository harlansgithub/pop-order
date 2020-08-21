package com.jd.poporder.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface InitOrder {

    int LOWEST_OFFSET = Integer.MAX_VALUE;
    int HIGHTEST_OFFSET = Integer.MIN_VALUE;

    int value() default LOWEST_OFFSET;
}
