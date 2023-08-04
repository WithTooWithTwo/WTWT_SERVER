package cupitoo.wtwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IOException.class)
    public Error IOExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return new Error(HttpStatus.EXPECTATION_FAILED, "이미지를 저장할 수 없습니다.");
    }

    @ExceptionHandler
    public Error AllExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return new Error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
