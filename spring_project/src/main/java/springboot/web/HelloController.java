package springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @RestController : 컨드롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
public class HelloController {
    @GetMapping // @GetMapping : HTTP 메소드인 GET의 요청을 받을 수 있는 API를 만들어 줌
    public String hello() {
        return "hello"; // /hello로 요청이 오면 반환
    }
}
