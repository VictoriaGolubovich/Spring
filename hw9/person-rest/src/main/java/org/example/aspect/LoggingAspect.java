package org.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j // Slf4j - Simple logging facade for java
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.hw8.service.PersonService.*(..))")
    public void personServiceMethodsPointcut() {
    }

    @Before(value = "personServiceMethodsPointcut()")
    public void beforePersonServiceFindById(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("Before -> PersonService#{}", methodName);
    }

    @AfterThrowing(value = "personServiceMethodsPointcut()", throwing = "ex")
    public void afterPersonServiceFindById(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        log.info("AfterThrowing -> PersonService#{} -> {}", methodName, ex.getClass().getName());
    }


}
