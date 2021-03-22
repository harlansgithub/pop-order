package com.jd.poporder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class PopOrderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(PopOrderApplication.class, args);
        ContextProfile profile = configurableApplicationContext.getBean(ContextProfile.class);
        profile.getApplicationContext();
    }

}
