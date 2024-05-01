package bitcamp.myapp.security03;

import bitcamp.myapp.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
  private static final Log log = LogFactory.getLog(SecurityConfig.class);

  public SecurityConfig() {
    log.debug("SecurityConfig() 객체 생성됨");
  }

  // Spring Security를 처리할 필터 체인을 준비한다.
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults())
        // 람다/메서드체인 적용
        .formLogin(formLoginConfigurer -> {
            formLoginConfigurer
                .loginPage("/auth/form")
                .loginProcessingUrl("/auth/login") // 클라이언트가 로그인을 하기 위해 요청하는 url (페이지 컨트롤러와 상관없다)
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .permitAll(); // 모든 권한 부여
        });

    // Http Security 객체에 설정한 대로 동작할 수 있는 필터를 구성한다.
    return http.build();
  }

  // 사용자 정보를 리턴해주는 객체
  @Bean
  public UserDetailsService userDetailsService(MemberService memberService) {
    // 우리가 만든 UserDetailsService 객체를 사용한다.
    // => DB에서 사용자 정보를 가져올 것이다.

    // 메모리에 사용자 정보를 보관한다(UserDetails 객체)를 보관한다.
    return new MyUserDetailsService(memberService);
  }
}
