package com.example.task2;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogMethodParamAspect {

    @Before("@annotation(LogMethodParam)")
    public void logMethodParameters(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        System.out.println("Method called: " + methodName);
        System.out.println("Parameters passed: " + Arrays.toString(args));
    }

}
