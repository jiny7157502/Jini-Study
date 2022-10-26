# TDD(Test Driven Development)

## 개요

> TDD 방법론에 대한 개념과 적용해야 중요하다는 것은 알고 있었지만 정작 왜 써야하고(목적) 어떤 방식으로 쓰이는지(과정) 확인하기 위해 공부하였다.

<br>

## TDD
* Test Driven Development로 테스트가 개발을 주도한다는 개념

<br>

## TDD를 적용하는 이유
* TDD는 실제 개발코드보다도 테스트 코드를 먼저 작성하게 되는데, 코드가 먼저 작성된 상태에서 테스트를 작성하는 것도 어려운데, 테스트 코드를 먼저 만드는 건 훨씬 어렵다고 많은 사람들이 생각한다. 
* **하지만, 개발된 코드를 테스트하는 것이 훨씬 어렵다.**
* 실무에서 개발된 코드에 대해 테스트 코드를 짜려면 어떤 코드들에서 테스트 코드를 짜야하지?, Spring Bean에 등록된 객체를 어떻게 테스트하지? 생성하는 범위는 어떻게 정해야하지?, DB 연결은 어떻게하지? … 등 수많은 고려사항들을 만나게 된다.
> TDD를 적용하게 되면 기능에만 집중하여 에러 파악이 쉽기 때문에 '코드 구현 -> 서버 실행 -> 수동 입력 -> 실행 -> 에러 -> 에러 분석 -> 해결 or 버그' 반복 과정이 줄어들어 전체 개발 시간이 단축된다!

<br>

## TDD의 목적
* 새로운 버그의 발생을 즉시 파악
* 잘 작동하는 깔끔한 코드
* 방치된 1개의 실패는 전체의 실패(100-1=0)
> TDD의 목적은 리팩토링, 품질보장이 되는 코드 생성

<br>

## TDD의 목표
* 버그 발생을 파악할 수 있어야 한다.
* 내일, 모레, 1년, 10년 후에도 정상 작동해야 한다.
* 재사용, 자동화가 가능해야 한다.
* 수정/보완된 코드로 인해 기존 코드에 버그가 발생하지 않음을 보장한다.

<br>

## TDD 기본 과정()
1. 항상 실패하는 테스트를 먼저 작성하고(RED)
2. 테스트를 통과하는 프로덕션 코드를 작성하고(Green)
3. 테스트가 통과하면 프로덕현 코드를 리팩토링한다.(Refactor) 
> 실패하는 테스트 작성 > 통과하는 개발 코드 작성 > 리팩토링 > 실패하는 테스트 작성 (반복)

<br>

## Given - When - Then Pattern
- 주니어 개발자들이 단위 테스트를 접할 때 적용하면 좋은 기법으로 하나의 단위 테스트를 3가지 단계로 나누어 처리하는 패턴
1. given(준비) : 어떠한 데이터가 준비되었을 때
2. when(실행) : 어떠한 함수를 실행할 때
3. then(검증) : 위에서 실행한 함수에 대한 결과가 나와야함

```java
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
```

<br>
<br>

### 참고사이트
> https://velog.io/@dchecheb/SpringBoot-TDD-%EC%A0%81%EC%9A%A9%EA%B8%B0

> https://taes-k.github.io/2019/09/27/spring-junit-testing-strategy/

> https://otrodevym.tistory.com/entry/Spring%EC%97%90%EC%84%9C-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BC%80%EC%9D%B4%EC%8A%A4-TDD-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0

> https://taes-k.github.io/2021/03/19/spring-tdd-practice/

> https://brunch.co.kr/@springboot/292

<br>

김영한님 단위테스트 & 통합테스트 강의
> https://youtu.be/KPYb-f9xzMQ

> https://youtu.be/pBnxPrGdDac