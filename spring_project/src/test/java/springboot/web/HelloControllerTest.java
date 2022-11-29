package springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import springboot.config.auth.SecurityConfig;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // SpringRunner 라는 스프링 실행자를 사용하여, 스프링 부트 테스트와 JUnit 사이의 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
}) // Web(Spring MVC) 에 집중할 수 있는 어노테이션
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 웹 API 를 테스트할 때 사용하며, 스프링 MVC 테스트의 시작점

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc 를 통해 /hello 주소로 HTTP GET 요청을 함.
                .andExpect(status().isOk()) // HTTP Header의 Status를 검증(ex 200, 400, 500..)
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증(Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증)
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // param : API 테스트할 때 사용될 요청 파라미터를 설정한다. 값은 String만 허용
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
