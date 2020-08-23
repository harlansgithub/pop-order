package com.jd.poporder.test;

import com.jd.poporder.annotation.PopOrderFlowResource;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestAnnotation
 * @Description TestAnnotation
 * @Author liudianfei3
 * @Date 2020/8/12 14:27
 * @Version 1.0
 */
@Component
public class TestAnnotation {
    @PopOrderFlowResource("test_aspect")
    public String testAspect() {
//        return "my aspect is test aspect";
        System.out.println(this.getClass().isAnnotationPresent(PopOrderFlowResource.class));
        return null;
    }

    public static void main(String[] args) {
        TestAnnotation testAnnotation = new TestAnnotation();
        testAnnotation.testAspect();
    }
}
