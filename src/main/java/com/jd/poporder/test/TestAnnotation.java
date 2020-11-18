package com.jd.poporder.test;

import com.jd.poporder.annotation.PopOrderFlowResource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName TestAnnotation
 * @Description TestAnnotation
 * @Author liudianfei3
 * @Date 2020/8/12 14:27
 * @Version 1.0
 */
@Component
public class TestAnnotation {
//    @PopOrderFlowResource("test_aspect")
    public String testAspect() {
//        return "my aspect is test aspect";
        System.out.println(this.getClass().isAnnotationPresent(PopOrderFlowResource.class));
        return null;
    }

//    @PopOrderFlowResource
    public String test1(){
        return "awefw";
    }


    public static void main(String[] args) {
        TestAnnotation testAnnotation = new TestAnnotation();
        testAnnotation.testAspect();
        Class clazz = testAnnotation.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method:methods){
            System.out.println();
        }
    }
}
