스프링 부트 사이드 프로젝트를 진행하면서 repository, entity, dto, vo와 같이 데이터와 관련된 패키지들이 많아지면서 구분이 모호해져 각각의 개념을 확실히해야 할 것같아서 정리를 하게 되었다.

개념을 확실히 정리해두고 각자의 쓰임에 맞게 패키지를 구성하도록 하자!

## DAO(Data Access Object)

### repository package

- 실제로 DB에 접근하는 객체(DB를 사용해 데이터 CRUD 담당) → persistence layer
- domain logic을 persistence layer에서 분리하기 위해서 사용
    - HTTP Request를 Web Application이 받게 되면 Thread를 생성하게 되는데 비즈니스 로직이 DB로부터 데이터를 얻어오기 위해 매번 Driver를 로드하고 Connection 객체를 생성하게 되면 엄청 많은 커넥션이 일어나므로 **DAO를 하나 만들어 DB 전용 객체로만 쓰는 것**
    이다. 이러면 부담이 줄어들게 된다.
    - 추가로 DBCP에 대해서 공부해보기!
- Service와 DB를 연결
- interface로 정의하고 implements한 클래스에서 사용하기도 한다.
    - 서비스에 맞춰서 필요한 경우에만 이렇게 구현!
    - interface로 정의할 경우 implements한 클래스에 @Repository을 붙여준다.

## DTO(Data Transfer Object)

### dto package

- 계층간 데이터 교환을 위한 객체
- 로직을 가지고 있지 않는 순수한 데이터 객체이며, getter/setter 메서드만을 갖는다.
    - 무결성을 위해서 setter 사용은 지양한다.
    - 생성자를 통해서 값을 할당
- toEntity() 메서드를 통해서 필요한 부분을 Entity로 만들 수 있다.
- VO와 같은 의미로 사용하기도 한다.
    - VO는 read only의 속성을 가진다.

## Entity

domain package

- 실제 DB의 테이블과 매칭될 클래스
- 최대한 외부에서 Entity 클래스의 getter 메서드를 사용하지 않도록 해당 클래스 안에서 필요한 로직을 구현한다.
    - domain logic만 가지고 있어야 하고 presentation logic을 가지고 있어서는 안된다.
    - 여기서 구현한 메서드는 주로 Service Layer에서 사용된다.
- Entity 클래스와 DTO 클래스를 분리하는 이유
    - View Layer와 DB Layer의 역할을 철저하게 분리하기 위해서(SoC)
    - 테이블과 매핑되는 Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 View와 통신하는 DTO 클래스(Request / Response 클래스)는 자주 변경되므로 분리해야 한다.
    - Domain Model을 아무리 잘 설계했다고 해도 각 View 내에서 Domain Model의 getter만을 이용해서 원하는 정보를 표시하기가 어려운 경우가 종종 있다. 이런 경우 Domain Model 내에 Presentation을 위한 필드나 로직을 추가하게 되는데, 이러한 방식이 모델링의 순수성을 깨고 Domain Model 객체를 망가뜨리게 된다.
    - 또한 Domain Model을 복잡하게 조합한 형태의 Presentation 요구사항들이 있기 때문에 Domain Model을 직접 사용하는 것은 어렵다.
    - 즉 DTO는 Domain Model을 복사한 형태로, 다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체는 Persistent만을 위해서 사용한다.