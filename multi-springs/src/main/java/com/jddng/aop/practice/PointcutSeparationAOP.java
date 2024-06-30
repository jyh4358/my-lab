package com.jddng.aop.practice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * <pre>
 *   하나의 Aspect에서 포인트컷을 여러개 선언 후 사용할 수 있다.
 * </pre>
 */

@Slf4j
@Aspect
@Component
public class PointcutSeparationAOP {

  //  @Pointcut("execution(* com.jddng.custom_validation..*(..))")
  private void pointcut1() {
  } // pointcut signature

  //  @Pointcut("execution(* com.jddng.https_data_binding..*(..))")
  private void pointcut2() {
  } // pointcut signature

  //  @Around("pointcut1()")
  public Object advice1(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("advice1 call");
    return joinPoint.proceed();
  }

  //  @Around("pointcut2()")
  public Object advice2(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("advice2 call");
    return joinPoint.proceed();
  }

  // 여러 포인트 컷을 논리 연산자를 이용하여 활용할 수 있다.
//  @Around("pointcut1() || pointcut2()")
  public Object advice3(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("advice3 call");
    return joinPoint.proceed();
  }
}
