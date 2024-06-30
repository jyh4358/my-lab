package com.jddng.aop.practice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AdviceTypeAOP {

  /**
   * <pre>
   *   Around의 경우 joinPoint.proceed() 메서드를 호출해줘야 진행이 된다.
   *   호출하지 않으면 해당 Advice에서 종료 되게 된다.
   *   result(return 값)을 조작할 수 있다.
   * </pre>
   */
//  @Around("execution(* com.jddng.https_data_binding.RequestBodyBindingController..*(..))")
  public Object aroundExecute(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Around call");
    return joinPoint.proceed();
  }

  /**
   * <pre>
   *   Before의 경우 자동으로 proceed 메서드를 호출하지 않아도 된다.
   * </pre>
   */
//  @Before("execution(* com.jddng.https_data_binding.RequestBodyBindingController..*(..))")
  public void beforeExecute(JoinPoint joinPoint) {
    log.info("Before call");
  }

  /**
   * <pre>
   *   returning 속성 값으로 메서드 파라미터에 매핑해준다.
   *   Around에서는 result 값을 조작할 수 있지만 AfterReturning에서는 조작이 불가하다.
   * </pre>
   */
//  @AfterReturning(value = "execution(* com.jddng.https_data_binding.RequestBodyBindingController..*(..))", returning = "result")
  public void afterReturningExecute(JoinPoint joinPoint, Object result) {
    log.info("Before call");
  }

  /**
   * <pre>
   *   throwing 속상 값으로 메서드 파라미터에 매핑해준다.
   * </pre>
   */
//  @AfterThrowing(value = "execution(* com.jddng.https_data_binding.RequestBodyBindingController..*(..))", throwing = "e")
  public void afterThrowingExecute(JoinPoint joinPoint, Exception e) {
    log.info("Before call");
  }


}
