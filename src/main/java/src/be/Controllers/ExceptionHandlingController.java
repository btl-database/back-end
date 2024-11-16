package src.be.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import src.be.Models.Responses;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                          HttpServletRequest request) {
        return new ResponseEntity<>(
                new Responses(
                        new Date(),
                        "400",
                        "Không tìm thấy dữ liệu trong Body",
                        request.getRequestURL().toString())
                , HttpStatus.BAD_REQUEST
        );
    }
}
