package com.jddng.aop.practice.log_trace;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

  private final LogTrace logTrace;

  @Around("execution(* com.jddng.custom_validation..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    TraceStatus status = null;
    try {
      String message = joinPoint.getSignature().toShortString();
      Object[] args = joinPoint.getArgs();
      if (!ObjectUtils.isEmpty(args)) {
      }
      status = logTrace.begin(message);

      // 로직 호출
      Object result = joinPoint.proceed();
      logTrace.end(status);
      return result;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }
}
