package bitcamp.myapp.security01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    // 필터 체인에 들어갈 필터를 설정한다.

    /*
    // 1) 어떤 요청에 대해 시큐리티 필터를 적용할 지 설정한다.
    // => 모든 요청에 대해 시큐리티 인증 필터를 통과하도록 설정한다.
    //    즉 인증받지 않은 사용자는 모든 요청이 거부된다.
    http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());

    // 2) 어떤 HTTP 요청에 대해 시큐리티 필터를 적용할 지 설정한다.
    // => Spring Security의 기본 설정을 그대로 사용한다.
    http.httpBasic(Customizer.withDefaults());

    // 3) 로그인 폼을 지정한다.
    // => Spring Security가 만들어주는 기본 로그인폼을 그대로 사용한다.
    http.formLogin(Customizer.withDefaults());
    */

    // 위의 설정 코드를 메서드 체인 방식으로 호출한다. (리턴값이 this)이기 때문에 가능
    http
        .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());

    // Http Security 객체에 설정한 대로 동작할 수 있는 필터를 구성한다.
    return http.build();
  }

  // 사용자 인증을 처리할 서비스 객체를 준비한다.
  @Bean
  public UserDetailsService userDetailsService() {
    // 로그인 사용자 정보
    UserDetails userDetails = User.withDefaultPasswordEncoder()
        .username("user1")
        .password("1111")
        .roles("USER")
        .build();

    // 로그인 폼에 입력된 값을 위의 사용자 정보와 비교하여 일치할 경우
    // 세션에 사용자 정보를 보관하는 일을 할 객체이다.
    return new InMemoryUserDetailsManager(userDetails);
  }
}
