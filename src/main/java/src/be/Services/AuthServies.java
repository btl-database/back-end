package src.be.Services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class AuthServies {
    public void setCookie(String token, HttpServletResponse res) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .maxAge(60 * 60 * 13)
                .sameSite("None")
                .build();

        res.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
