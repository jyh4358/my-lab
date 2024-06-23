# 데이터베이스 관련

### spring.datasource.url

- 데이터베이스 URL

### spring.datasource.username

- 데이터베이스 사용자 이름

### spring.datasource.password

- 데이터베이스 비밀번호

### spring.datasource.driver-class-name

- 데이터베이스 드라이버 클래스명
    1. MySQL
        - 8 이상 : com.mysql.cj.jdbc.Driver
        - 5.X : com.mysql.jdbc.Driver
    2. Oracle
        - oracle.jdbc.driver.OracleDriver
    3. MariaDB
        - org.mariadb.jdbc.Driver
    4. PostgreSQL
        - org.postgresql.Driver
    5. H2 Database
        - org.h2.Driver
    6. Microsoft SQL Server
        - com.microsoft.sqlserver.jdbc.SQLServerDriver

---

# JPA

### spring.jpa.hibernate.ddl-auto

- 스키마 자동 생성 옵션
    - none : 자동 생성 기능을 사용하지 않음
    - validate : 엔티티 매핑을 유효성 검사하고, 데이터베이스 스키마를 변경하지 않음
    - update : 엔티티 매핑을 유효성 검사하고, 데이터베이스 스키마를 필요에 따라 변경
    - create : 엔티티 매핑을 유효성 검사하고, 데이터베이스 스키마를 매번 생성
    - create-drop : 엔티티 매핑을 유효성 검사하고, 애플리케이션 종료 시 데이터베이스 스키마를 삭제

### spring.jpa.database-platform, spring.jpa.properties.hibernate.dialect

- Hibernate가 특정 데이터베이스에 맞는 SQL을 생성할 수 있도록 도와주는 방언(dialect)을 지정하는 설정
- 설정 값
    1. MySQL
        - org.hibernate.dialect.MySQLDialect
        - MySQL 5.x : org.hibernate.dialect.MySQL5Dialect
        - MySQL 8.x : org.hibernate.dialect.MySQL8Dialect
    2. Oracle
        - org.hibernate.dialect.OracleDialect
        - Oracle 9i : org.hibernate.dialect.Oracle9Dialect
        - Oracle 10g : org.hibernate.dialect.Oracle10gDialect
        - Oracle 12c : org.hibernate.dialect.Oracle12cDialect
    3. MariaDB
        - org.hibernate.dialect.MariaDBDialect
        - MariaDB 5.3 : org.hibernate.dialect.MariaDB53Dialect
        - MariaDB 10.2 : org.hibernate.dialect.MariaDB102Dialect
        - MariaDB 10.3 : org.hibernate.dialect.MariaDB103Dialect
        - MariaDB 10.6 : org.hibernate.dialect.MariaDB106Dialect
    4. PostgreSQL
        - org.hibernate.dialect.PostgreSQLDialect
        - PostgreSQL 10.x : org.hibernate.dialect.PostgreSQL10Dialect
        - PostgreSQL 9.5 : org.hibernate.dialect.PostgreSQL95Dialect
    5. H2 Database
        - org.hibernate.dialect.H2Dialect

### spring.jpa.properties.hibernate.jdbc.batch_size

- inset 시 해당 크기만큼 한번에 insert query 실행
- 주의 ID가 자동증가 값인 경우(IDENTITY) batch insert는 실행되지 않는다

### spring.jpa.properties.hibernate.default_batch_fetch_size

- @OneToMany, @ManyToMany 등의 특정 연관 관계에서 사용
- 부모 엔티티와 많은 자식 엔티티가 있는 경우, 한 번의 쿼리로 여러 자식 엔티티를 가져오도록 하면 데이터베이스의 부하를 줄일 수 있다.

### spring.jpa.open-in-view

- 뷰 렌더링 중에 데이터베이스 세션을 열어 두어, 지연 로딩을 뷰 레이어까지 확장하기 위해 사용
- 기본값 : true
- 단점이 많기 때문에 사용 지양
    - 성능 관련 :  HTTP 요청이 처리되는 동안 데이터베이스 세션이 열려 있으므로, 세션이 오래 유지되면 성능 저하가 발생
    - 서비스 레이어에서 트랜잭션을 명확하게 관리하는 것이 어렵게 된다.
      비즈니스 로직은 서비스 레이어에서 처리하고, 뷰 레이어는 단순히 데이터를 표현하는 역할을 해야 하는 원칙이 깨질 수 있다.

### spring.jpa.defer-datasource-initializatio

- 애플리케이션이 시작될 때 데이터베이스 연결 설정은 완료되지만 실제 데이터 소스 초기화는 첫 번째 데이터베이스 접근 시점까지 지연시킨다.
- 첫 번째 데이터베이스 접근 시에 데이터 소스가 초기화되므로, 이 점을 고려하여 애플리케이션의 동작을 설계해야 한다.

### spring.jpa.properties.enable_lazy_load_no_trans

- Hibernate의 옵션 중 하나로, JPA 엔티티의 지연 로딩(Lazy Loading) 처리 방식을 제어하는 고급 설정
- true : 트랜잭션 없이도 지연 로딩을 수행할 수 있도록 설정
- false(default) : 지연 로딩을 수행할 때 트랜잭션 범위 내에서만 가능하도록 제한
- 애플리케이션 동작에 부작용을 초래할 수 있으므로 기본 값인 false로 사용하는 것을 권장한다.

### spring.jpa.defer-datasource-initialization

- Spring Boot에서 제공하는 데이터 소스 초기화를 지연시키는 옵션
- true : 애플리케이션이 시작될 때 데이터 소스가 초기화되지 않고, 대신 첫 번째 데이터베이스 연결 시점까지 초기화를 유예
- false(default) : 데이터 소스 초기화를 즉시 실행
- 애플리케이션 동작에 부작용을 초래할 수 있으므로 기본 값인 false로 사용하는 것을 권장한다.

### 네이밍전략

- spring.jpa.properties.hibernate.naming.physical-strategy
    - 데이터베이스 테이블 및 컬럼의 실제 이름을 결정하는 전략
    - 기본적으로는 Camel Case -> Snake Case 로 처리된다.
    - PhysicalNamingStrategyStandardImpl
        - Entity 네이밍 그대로 사용하고자 할 때 사용
        - MyHibernateConfiguration과 같이 PhysicalNamingStrategyStandardImpl Bean 등록해줘야 적용된다.
- spring.jpa.properties.hibernate.naming.implicit-strategy
    - Java 엔티티 클래스와 데이터베이스 테이블 및 컬럼 간의 이름 매핑을 제어
    - default : ImplicitNamingStrategyJpaCompliantImpl

### Query Plan Cash

- 참고링크
    - https://hyos-dev-log.tistory.com/29
    - https://meetup.nhncloud.com/posts/211

```
spring:
  jpa:
    properties:
      hibernate:
        query:
          plan_cache_max_size: 1024  # QueryPlanCache size 설정, 기본값 2048
          in_clause_parameter_padding: true  # QueryPlanCache padding 설정, 기본값 false
          plan_parameter_metadata_max_size: 64  # ParameterMetadata size 설정, 기본값 128, 네이티브 쿼리인 경우 하이버네이트에서 파라미터와 반환 타입에 대한 정보를 저장
```

---

### 로그관련

### spring.jpa.show-sql, spring.jpa.properties.show_sql

- 실행되는 SQL을 출력할지 여부, 정렬된 형태가 아닌 한 줄로 출력
- System.out을 이용하여 하이버네이트 SQL 실행 쿼리를 출력
- System.out을 이용하여 출력하기 때문에 운영 서버에서 사용하는 것을 권장하지 않음

```sql
Hibernate
:
select member0_.member_id as member_i1_0_, member0_.name as name2_0_
from member member0_
```

### spring.datasource.jpa.properties.hibernate.use_sql_comments

- System.out을 이용하여 하이버네이트 JPQL 쿼리를 출력
- SQL 실행 쿼리 앞에 comment `/* ... */` 형태로 출력
- 출력되는 쿼리(show_sql, org.hibernate.SQL)가 없으면 use_sql_comments도 출력되지 않음
- System.out을 이용하여 출력하기 때문에 운영 서버에서 사용하는 것을 권장하지 않음

```sql
Hibernate
: /* select member1.id
from Member member1
where member1.name = ?1 */
select m1_0.id
from member m1_0
where m1_0.name = ? limit ?
```

### spring.jpa.properties.format_sql

- 출력되는 SQL, JPQL을 정렬된 형태로 출력

```sql
Hibernate
: 
    /* select
        member1.id 
    from
        Member member1 
    where
        member1.name = ?1 */
select m1_0.id
from member m1_0
where m1_0.name = ? limit ?
```

### logging.level.org.hibernate.SQL

- logger를 통해 하이버네이트 실행 SQL을 출력
- logger를 통해 출력하기 때문에 보통 운영 서버에서 사용
- 아래 테스트는 format_sql 옵션을 추가한 결과

```sql
2023-03-01T14:34:08.922+09:00 DEBUG 3220 --- [           main] org.hibernate.SQL                        : 
    /* select
        member1.id 
    from
        Member member1 
    where
        member1.name = ?1 */
select m1_0.id
from member m1_0
where m1_0.name = ? limit ?
```

### logging.level.org.hibernate.type, logging.level.org.hibernate.orm.jdbc.bind

- SQL 실행 쿼리에 ?로 출력된 파라미터들을 출력
- 해당 쿼리에 어떤 파라미터가 바인딩되어 쿼리가 실행되는지 확인하고자 할 때 사용
- Spring Boot 버전에 따라 아래와 같이 설정해줘야 한다.
    - Spring Boot 2.X : org.hibernate.type
    - Spring Boot 3.X : org.hibernate.orm.jdbc.bind

```sql
2023-03-01T15:21:06.433+09:00 DEBUG 5196 --- [           main] org.hibernate.SQL                        : 
select m1_0.id
from member m1_0
where m1_0.name = ? limit ?
2023-03-01T15:21:06.436+09:00 TRACE 5196 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter [1] as [VARCHAR] - [test3500000]
2023-03-01T15:21:06.437+09:00 TRACE 5196 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter [2] as [INTEGER] - [1]
```