package bitcamp.myapp.security;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {

  MemberService memberService;
  private static final Log log = LogFactory.getLog(MyUserDetailsService.class) ;

  public MyUserDetailsService(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Member member = memberService.get(username);

    log.debug("member: "+ member);

    if (member == null) {
      throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
    }
    // 해당 이메일을 가진 사용자가 존재한다면
    // 그 사용자 정보를 UserDetails 객체에 담아서 리턴한다.
    // 그러면 Spring Security는 리턴된 UserDetails의 username, password와
    // 클라이언트가 보낸 username, password를 비교하여 로그인 처리를 한다.

    return User.builder()
        .username(member.getEmail())
        .password(member.getPassword())
        .roles("USER")
        .build();
  }


}
