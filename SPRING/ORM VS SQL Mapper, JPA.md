# ORM VS SQL Mapper, JPA

## ORM(Object Relationship Mapper)

- 데이터베이스를 객체로 매핑함으로써 객체 간의 관계를 바탕으로 테이블을 만들고 관리하는 것
- SQL문이 아닌 클래스의 메서드를 통해 데이터베이스를 조작 가능
- 데이터의 관계가 복잡할수록 구현하기 어렵고 복잡한 SQL문 처리하기 어려움
- JPA, Hibernate

## SQL Mapper

- 작성한 SQL 구문으로 RDB에 직접 질의하여 그 결과 값을 객체에 매핑 시켜줌.
- 관계를 정확히 명시할 수 있고, 복잡한 SQL문 처리 가능
- SQL구문을 직접 작성..
- MyBatis, **JdbcTemplate**


### JPA : Java Persistence API의 약자

- 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
- 말 그대로 인터페이스이지 특정 기능을 하는 라이브러리가 아님

### Hibernate : JPA라는 명세의 구현체

- 자바 기바의 ORM
- 자바 언어를 위한 객체 관계 매칭 프레임워크(자바 클래스와 DB 테이블을 매핑)
- 객체 지향 도메인 모델을 관계형 데이터베이스로 매핑을 위한 프레임워크

### Spring Data JPA : JPA를 쓰기 편하게 만들어놓은 모듈

- 개발자가 JPA를 더 쉽고 편하게 사용하게 Spring에서 제공하는 모듈
- JPA를 한 단계 추상화시킨 Repository 라는 인터페이스를 제공함