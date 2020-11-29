package com.jd.poporder.aspect;

import com.jd.poporder.annotation.PopOrderFlowResource;
import com.jd.poporder.core.Entry;
import com.jd.poporder.core.Environment;
import com.jd.poporder.utils.EntryType;
import kotlin.jvm.Throws;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PopOrderFlowAspect extends AbstractPopOrderAnnotationAspect{

    public PopOrderFlowAspect(){}
    @Pointcut("@annotation(com.jd.poporder.annotation.PopOrderFlowResource)")
    public void popOrderFlowAnnotationPointcut(){
    }

    @Around("popOrderFlowAnnotationPointcut()")
    public Object invokeResourceWithPopOrder(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method = this.resolveMethod(pjp,methodSignature);
        if (method == null){
            throw new IllegalStateException("target method was not found!");
        }
        PopOrderFlowResource popOrderFlowResource = (PopOrderFlowResource)method.getAnnotation(PopOrderFlowResource.class);
        if (popOrderFlowResource == null ){
            throw new IllegalStateException("Wrong state for PopOrderFlowAnnotation annotation!");
        }else {
            String resourceName = popOrderFlowResource.value();
            EntryType entryType = popOrderFlowResource.entryType();
            int resourceType = popOrderFlowResource.resourceType();
            Entry entry = null;
            try {
                // TODO 限流逻辑
                entry = Environment.service.entry(resourceName, resourceType, entryType, 1, pjp.getArgs());
                return pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                if (entry != null){
                    entry.exit(1, pjp.getArgs());
                }
            }
        }
        return null;
    }

}
