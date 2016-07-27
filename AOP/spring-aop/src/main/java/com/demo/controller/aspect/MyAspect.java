package com.demo.controller.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.demo.controller.HomeAction.*(..))")
    private void pointCutMethod() {
    }

    /**
     *  声明前置通知
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知");
    }

    /**
     *  声明后置通知
     * @param result
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        System.out.println("后置通知");
        System.out.println("---" + result + "---");
    }

}
