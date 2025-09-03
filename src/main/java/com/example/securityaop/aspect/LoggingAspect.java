package com.example.securityaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(999)
public class LoggingAspect {

    @AfterReturning("execution(* com.example.securityaop.controller..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[LOG] Executed: " + joinPoint.getSignature());
    }
}
