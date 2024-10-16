package org.example.aspect.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j // Slf4j - Simple logging facade for java
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {
    private final LoggingProperties properties;


    @Pointcut("@annotation(org.example.aspect.logging.Logging)") // method
    public void loggingMethodsPointcut() {}

    @Pointcut("@within(org.example.aspect.logging.Logging)") // class
    public void loggingTypePointcut() {}

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        log.atLevel(properties.getLevel()).log("Before -> PersonService#{}", methodName);
        try {
            return pjp.proceed();
        } finally {
            log.atLevel(properties.getLevel()).log("After -> PersonService#{}", methodName);
        }
    }
}
