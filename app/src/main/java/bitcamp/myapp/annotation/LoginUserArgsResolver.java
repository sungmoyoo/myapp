package bitcamp.myapp.annotation;

import bitcamp.myapp.vo.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgsResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {

    return parameter.hasParameterAnnotation(LoginUser.class) &&
        parameter.getParameterType().isAssignableFrom(Member.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpSession session = ((HttpServletRequest) webRequest.getNativeRequest()).getSession();

    return session.getAttribute(parameter.getParameterName());
  }

  public static void main(String[] args) {
    class Car {}
    class Truck extends Car {}

    System.out.println(Truck.class.isAssignableFrom(Car.class));
    System.out.println(Car.class.isAssignableFrom(Truck.class));
  }
}
