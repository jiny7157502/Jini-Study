# Spring 웹 계층

> 스프링을 공부하면서 스프링 웹 계층의 구성 요소인 domain, controller, service 등의 개념이 나오면서 확실히 이해하고 코딩을 해야 좋은 코드를 작성하고 공부의 효율이 올라갈 것 같아 간단하게 정리하였다.

<br>

## Spring 웹 계층
- Web Layer, Service Layer, Repository Layer
- DTOs : Web Layer + Service layer
- Domain Model : Service Layer + Repository Layer

<br>

### Web Layer
- 흔히 사용하는 컨트롤러와 JSP/Freemarker 등의 뷰 템플릿 영역
- 이외에도 필터, 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 외부 요청과 응답에 대한 전반적인 영역을 이야기한다.

<br>

### Service Layer
- @Service에 사용되는 서비스 영역
- 일반적으로 Controller와 Dao의 중간 영역에서 사용됩니다.
- @Transactional이 사용되어야 하는 영역이기도 함.

<br>

### Service Layer
- @Service에 사용되는 서비스 영역
- 일반적으로 Controller와 Dao의 중간 영역에서 사용됩니다.
- @Transactional이 사용되어야 하는 영역이기도 함.

<br>

### Repository Layer
- Database와 같이 데이터 저장소에 접근하는 영역
- DAO(Data Access Object) 영역으로 이해할 수 있음

<br>

### Dtos
- Dto(Data Transfer Object)는 계층 간에 데이터 교환을 위한 객체를 이야기하며 Dtos는 이들의 영역을 나타냄.
- ex) 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등이 이들을 이야기함.

<br>

### Domain Model
- 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것
- @Entity가 사용된 영역
- 무조건 데이터베이스의 테이블과 관계가 있어야 하진 않음 -> ex) VO와 같은 값 객체들