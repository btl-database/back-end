package src.be.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import src.be.Services.CookiesServices;
import src.be.Services.JwtServices;

@Component
public class ApiInterCeptors implements HandlerInterceptor {
    @Autowired
    CookiesServices cookiesServices;

    @Autowired
    JwtServices jwtServices;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception, ResponseStatusException {
        String token = cookiesServices.getCookieValue(request, "token");

        if(token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Api yêu cầu đăng nhập");
        }

        String username = jwtServices.getUsernameFromToken(token);

        request.setAttribute("username", username);

        return true;
    }
}
