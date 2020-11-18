package com.jd.poporder.controller;

import com.jd.poporder.annotation.PopOrderFlowResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TestController
 * @Author liudianfei3
 * @Date 2020/9/11 16:47
 * @Version 1.0
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    @PopOrderFlowResource("test")
    public String test(){
        return "yes";
    }
}
