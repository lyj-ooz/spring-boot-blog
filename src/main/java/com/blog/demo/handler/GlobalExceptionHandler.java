package com.blog.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // exception 이 발생하면 이 클래스로 들어오게 해줌
@RestController
public class GlobalExceptionHandler {

    // IllegalArgumentException 이 발생하면 그 exception을
    // 아래 함수에 전달함
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }

    // 또는 아래처럼 모든 exception을 다 잡도록 작성할 수도 있음
//    @ExceptionHandler(value = Exception.class)
//    public String handleArgumentException(Exception e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }
}
