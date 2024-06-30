# Advice 종류

1. `@Around`
   - 사용 JoinPoint : ProceedingJoinPoint
   - 메서드 호출 전후에 실행, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능
   - ProceedingJoinPoint의 **proceed** 메서드를 호출해줘야 한다
2. `@Before`
   - 사용 JoinPoint : JoinPoint
   - 메서드 호출 전에 실행
   - JoinPoint의 **proceed** 메서드를 호출하지 않아도 된다.
3. `@AfterReturning`
   - 사용 JoinPoint : JoinPoint
   - 메서드 호출 후에 정상 완료(예외가 발생하지 않을 경우) 실행
4. `@AfterThrowing`
   - 사용 JoinPoint : JoinPoint
   - 메서드 호출 후에 예외가 발생한 경우 실행
5. `@After`
   - 사용 JoinPoint : JoinPoint
   - 메서드 호출 후에 실행

---

# JoinPoint 주요 기능

1. `getArgs()` : 메서드 인수를 반환
2. `getThis()` : 프록시 객체를 반환
3. `getTarget()` : JoinPoint의 타겟 객체를 반환
4. `getSignature()` : 메서드에 대한 Signature 반환
   - Signature가 제공하는 메서드
     - `getName` : 메서드 명
     - `toShortString` : Signature 정보(요약)
     - `toLongString` : Signature 정보
5. `toString()`

---

# Pointcut 지시자

- `excution` : 메서드 실행 조인 포인트를 매칭
- `within` : 특정 타입 내의 조인 포인트를 매칭
- `args` : 메서드의 인자가 주어진 타입의 인스턴스인 조인 포인트
- `this` : AOP 프록시가 가르키는 실제 Target 객체를 대상
- `@target` : 실행 객체의 클래스(부모포함)에 주어진 타입의 애노테이션이 있는 조인 포인트
- `@within` : 주어진 애노테이션이 있는 타입 내 조인 포인트
- `@annotation` : 주어진 애노테이션을 가지고 있는 조인 포인트
- `@args` : 전달된 실제 인수의 런타임 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
- `bean` : 스프링 전용 포인트컷 지시자. 빈의 이름으로 포인트컷 지정

## excution

```java
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

// 설명
execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
```
- `?`는 생략할 수 있다.
- `*`와 같은 패턴을 지정할 수 있다.

### 예시

특정 메서드 지정
```java
execution(public ResponseEntity<String> com.jddng.custom_validation.customConstraintV0(com.jddng.custom_validation.dto.CustomConstraintV0Request.class))
```

전체 지정
```java
// 반환타입 *
// 메서드 이름 *
// 파라미터 전체 (..)
execution(* *(..))
```

특정 패키지 지정
```java
// 반환타입 *
// 메서드 이름 *Controller
//  -> ..은 해당 패키지의 하위패키지 모두 포함한다는 뜻
//  -> com 패키지 하위에 있는 모든 XXXController
// 파라미터 전체 (..)
execution(* com..*Controller.*(..))
```

특정 파라미터 지정
- `(String)` : 정확하게 String 타입 파라미터
- `()` : 파라미터가 없어야 한다.
- `(*, *)` : 정확히 두 개의 파라미터, 단 모든 타입 허용
- `(..)` : 모든 파라미터 허용
- `(String, ..)` : String 타입이 첫번째 파라미터로 있고, 그 외 모든 파라미터 허용

---

# 매개변수 전달

- Advice에 필요한 매개변수를 전달받을 수 있다.

```java
import org.aspectj.lang.annotation.Before;

@Before("execution(* com.jddng.https_data_binding.RequestBodyBindingController..*(..)) && @annotation(annotation)")
public void beforeExecute(JoinPoint joinPoint, AnnotationPointcut annotation) {
        log.info("Before call");
}
```
