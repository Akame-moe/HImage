package com.gentlehu.himage.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by gentle-hu on 2018/9/28 17:58.
 * Email:me@gentlehu.com
 */
@Aspect
@Component
public class HLoggerAspect {

    @Pointcut("execution(public * com.gentlehu.himage.controller..*.*(..)))")
    public void controllerPointcut(){

    }

    @Before("controllerPointcut()")
    public void beforeOperation(JoinPoint joinPoint){

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        System.out.println("query:"+attr.getRequest().getQueryString());

    }
}