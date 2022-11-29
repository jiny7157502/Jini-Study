package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // JPA Auditing 어노테이션들을 모두 활성화할 수 있도록 설정
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정해줌, 프로젝트 최상단에 위치
// 메인 클래스
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS 실행(언제 어디서나 같은 환경에서 스프링 부트를 배포 가능)
    }
}
