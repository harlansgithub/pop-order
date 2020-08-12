package com.jd.poporder.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @ClassName AbstractPopOrderAnnotationAspect
 * @Description AbstractPopOrderAnnotationAspect
 * @Author liudianfei3
 * @Date 2020/8/12 11:28
 * @Version 1.0
 */
public class AbstractPopOrderAnnotationAspect {
    /**
     * annotation切面模板方法：处理获取请求方法
     * @param pjp
     * @param signature
     * @return
     */
    public Method resolveMethod(ProceedingJoinPoint pjp,MethodSignature signature){
        Class<?> targetClass = pjp.getTarget().getClass();
        Method method = null;
        try {
            method = targetClass.getDeclaredMethod(signature.getName(),signature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            // TODO 方法不存在异常
        }
        return method;
    }
}
