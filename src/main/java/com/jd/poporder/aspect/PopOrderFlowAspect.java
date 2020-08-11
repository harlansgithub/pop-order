package com.jd.poporder.aspect;

import com.jd.poporder.annotation.PopOrderFlowResource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class PopOrderFlowAspect {
    public PopOrderFlowAspect(){}
    @Pointcut("@annotation(com.jd.poporder.annotation.PopOrderFlowResource)")
    public void popOrderFlowAnnotationPointcut(){
    }

    @Around("popOrderFlowAnnotationPointcut()")
    public Object invokeResourceWithPopOrder(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Class<?> targetClass = pjp.getTarget().getClass();
        Method method = null;
        try {
            method = targetClass.getDeclaredMethod(methodSignature.getName(),methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            // TODO 方法不存在异常
        }
        PopOrderFlowResource popOrderFlowResource = (PopOrderFlowResource)method.getAnnotation(PopOrderFlowResource.class);
        return null;

    }

}
