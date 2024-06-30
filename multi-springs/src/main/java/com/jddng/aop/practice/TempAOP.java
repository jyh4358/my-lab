package com.jddng.aop.practice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *   프록시를 적용하려면 Pointcut과 Advice로 구성되어 있는 Advisor를 만들어서
 *   스프링 Bean으로 등록하면 자동 프록시 생성기가 스프링 Bean으로 등록되어 있는
 *   Aean들에게 자동으로 프dvisor들을 찾고, Pointcut에 해당되는 스프링 B록시를 적용해 준다.
 *
 *   스프링은 @Aspect Annotation으로 Pointcut과 Advice로 구성되어 있는
 *   Advisor 생성 기능을 지원한다.
 * </pre>
 */
@Slf4j
@Aspect
@Component
public class TempAOP {

  // Advisor 생성

  //  @Around("execution(* com.jddng..*(..))") // @Around의 value : Pointcut
  // 아래 Method : Advice
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("TempAOP call");
    return joinPoint.proceed();
  }

}
