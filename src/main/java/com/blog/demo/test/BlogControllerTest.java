package com.blog.demo.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 스프링이 com.blog.demo 패키지 이하를 스캔하여 이 어노테이션이
// 붙은 클래스 파일들을 'new'하여 스프링 컨테이너에서 관리해줌 (IoC)

public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>hello spring boot!!</h1>";
    }
}
