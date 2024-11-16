package src.be.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import src.be.Models.UserData;
import src.be.Models.Responses;
import src.be.Repository.UserDataRepository;
import src.be.Services.AuthServies;
import src.be.Services.CookiesServices;
import src.be.Services.JwtServices;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    AuthServies authServies;

    @Autowired
    JwtServices jwtServices;

    @Autowired
    CookiesServices cookiesServices;

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpServletResponse res) {
        try {
            String username = body.get("username");
            String password = body.get("password");

            if(username == null || password == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy tên đăng nhập hoặc mật khẩu!");
            }

            UserData userData = userDataRepository.findByUsername(username);
            if(userData == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy user này!");
            }
            if(!userData.getPassword().equals(password)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sai mật khẩu");
            }

            cookiesServices.setCookieValue(res, "token", jwtServices.getJwt(username));

            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            "200",
                            "Đăng nhập thành công!",
                            "/auth/login"),
                    HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            e.getStatusCode().toString(),
                            e.getReason(),
                            "/auth/login"
                    ), e.getStatusCode()
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            "400",
                            e.getMessage(),
                            "/auth/login"
                    ), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest req, HttpServletResponse res) {
        try {
            String old_token = cookiesServices.getCookieValue(req, "token");
            String token = jwtServices.validateToken(old_token);

            cookiesServices.setCookieValue(res, "token", token);

            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            "200",
                            "Refresh Token thành công",
                            "/auth/refreshToken"),
                    HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            e.getStatusCode().toString(),
                            e.getReason(),
                            "/auth/refreshToken"
                    ), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            "400",
                            e.getMessage(),
                            "/auth/refreshToken"
                    ), HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/logOut")
    public ResponseEntity<?> logOut(HttpServletRequest req, HttpServletResponse res) {
        try {
            String token = cookiesServices.getCookieValue(req, "token");

            Cookie cookie = new Cookie("token", null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            res.addCookie(cookie);

            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            "200",
                            "Đăng xuất thành công",
                            "/auth/logOut"
                    ), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Responses(
                            new Date(),
                            "400",
                            e.getMessage(),
                            "/auth/logOut"
                    ), HttpStatus.BAD_REQUEST
            );
        }
    }

    private void checkIfExists(String username, String email) throws ResponseStatusException {
        if(userDataRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tên đăng nhập đã tồn tại");
        }

        if(userDataRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email đã tồn tại");
        }
    }
}
