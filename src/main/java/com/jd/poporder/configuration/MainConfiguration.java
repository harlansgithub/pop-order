package com.jd.poporder.configuration;

import com.jd.poporder.test.TestAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName MainConfiguration
 * @Description MainConfiguration
 * @Author liudianfei3
 * @Date 2020/8/12 14:25
 * @Version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.jd.poporder")
@EnableAspectJAutoProxy
public class MainConfiguration {
//    @Bean
//    public TestAnnotation testAnnotation(){
//        return new TestAnnotation();
//    }
}
