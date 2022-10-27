package springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // SpringRunner 라는 스프링 실행자를 사용하여, 스프링 부트 테스트와 JUnit 사이의 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC) 에 집중할 수 있는 어노테이션
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 웹 API 를 테스트할 때 사용하며, 스프링 MVC 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc 를 통해 /hello 주소로 HTTP GET 요청을 함.
                .andExpect(status().isOk()) // HTTP Header의 Status를 검증(ex 200, 400, 500..)
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증(Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증)
    }
}
