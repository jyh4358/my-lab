package com.jddng.aop.practice.annotation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class AnnotationAOP {

//  @Pointcut("@annotation(com.jddng.aop.practice.annotation.AnnotationPointcut)")
  public void enableBefore() {
  }

//  @Before("enableBefore()")
  public void before(JoinPoint joinPoint) {
  }
}


