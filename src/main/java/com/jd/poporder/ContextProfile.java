package com.jd.poporder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName ContextProfile
 * @Description ContextProfile
 * @Author liudianfei3
 * @Date 2020/11/25 15:40
 * @Version 1.0
 */
@Component
public class ContextProfile {

    @Autowired
    private ApplicationContext applicationContext;
    public void getApplicationContext() throws BeansException {
        System.out.println("sfasfsdfasd:"+applicationContext.getEnvironment().getActiveProfiles()[0]);
    }
}
