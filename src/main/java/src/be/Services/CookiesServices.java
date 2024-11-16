package src.be.Services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookiesServices {
    public String getCookieValue(HttpServletRequest request, String cookieName) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }

        throw new Exception("Không tìm thấy cookie " + cookieName);
    }

    public void setCookieValue(HttpServletResponse response, String cookieName, String value) throws Exception {
        ResponseCookie cookie = ResponseCookie.from(cookieName, value)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .maxAge(60 * 60 * 13)
                .sameSite("None")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
