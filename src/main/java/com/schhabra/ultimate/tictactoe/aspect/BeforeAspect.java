package com.schhabra.ultimate.tictactoe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Aspect
@Configuration
@EnableAspectJAutoProxy
public class BeforeAspect {
    @Before(value = "execution(* com.schhabra.ultimate.tictactoe.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("before execution of {}", joinPoint);
    }
}
