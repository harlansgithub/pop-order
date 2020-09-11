package com.jd.poporder.processor;

import com.jd.poporder.annotation.PopOrderFlowResource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
public class ResourceRegisterProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getMethods();
        List<String> resourceList = new ArrayList<>();

        for (Method method:methods){
            PopOrderFlowResource resource = AnnotationUtils.findAnnotation(method,PopOrderFlowResource.class);
            if (resource != null){
                if (resource.value().equals("")){
                    resourceList.add(clazz.getName().split("\\$\\$")[0] + "." + method.getName());
                }else {
                    resourceList.add(clazz.getName().split("\\$\\$")[0] + "." + resource.value());
                }
            }
        }
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8080/register?resource=";
        for (String path:resourceList) {
            Request request = new Request.Builder().url(url+path).build();
            try {
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
