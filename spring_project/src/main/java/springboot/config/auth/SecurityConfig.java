package springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springboot.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화시킴.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 시킴.
                .csrf().disable().headers().frameOptions().disable()
                .and()
                    // authorizeRequest() : URL별 권한 관리를 설정하는 옵션의 시작점, antMatchers 옵션을 사용할 수 있게 함.
                    .authorizeRequests()
                    // antMatchers : 권한 관리 대상을 지정하는 옵션, URL, HTTP 메소드별로 관리가 가능함.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // permitAll() 옵션을 통해 전체 열람 권한 지정
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한을 가진 사람만 가능하도록 설정
                    .anyRequest().authenticated() // anyRequest : 설정된 값들 이외 나머지 URL들을 나타냄, authenticated : 인증된(로그인한) 사용자들에게만 허용
                .and()
                    // 로그아웃 기능에 대한 여러 설정의 진입점
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 "/" 주소로 이동
                .and()
                    // OAuto 2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                        // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당.
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
