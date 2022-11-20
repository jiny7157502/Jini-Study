package springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // Getter : 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // NoArgsConstructor : 기본 생성자 자동 추가, public Posts(){}와 같은 효과
@Entity // Entity : 테이블과 링크될 클래스임을 나타냄.
public class Posts { // Posts 클래스 : 실제 DB의 테이블과 매칭될 클래스
    // Setter를 무작정 생성하는 경우 -> 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경 시 정말 복잡해짐.
    // -> 생성자 대신에 @Builder를 통해 데이터 삽입
    @Id // Id : 해당 테이블의 PK 필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue : PK의 생성 규칙을 나타냄, GenerationType.IDENTITY 옵션을 추가하면 auto_increment가 된다.
    private Long id;

    // Column : 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 사용하는 이유는 기본 값 외에 추가로 변경이 필요한 옵션이 있으면 사용 ex) 문자열의 사이즈를 늘리거나, 타입 변경 등
    @Column(length = 500, nullable = false) 
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // Builder : 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
