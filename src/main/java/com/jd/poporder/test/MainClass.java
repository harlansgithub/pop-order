package com.jd.poporder.test;

import com.jd.poporder.configuration.MainConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName MainClass
 * @Description MainClass
 * @Author liudianfei3
 * @Date 2020/8/12 14:33
 * @Version 1.0
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        TestAnnotation testAnnotation = context.getBean(TestAnnotation.class);
        testAnnotation.testAspect();
    }
}
