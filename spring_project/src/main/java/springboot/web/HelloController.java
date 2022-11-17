package springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.web.dto.HelloResponseDto;

@RestController // @RestController : 컨드롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
public class HelloController {
    @GetMapping("/hello") // @GetMapping : HTTP 메소드인 GET의 요청을 받을 수 있는 API를 만들어 줌
    public String hello() {
        return "hello"; // /hello로 요청이 오면 반환
    }

    @GetMapping("/hello/dto")
    // @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션, 외부에서 name이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
