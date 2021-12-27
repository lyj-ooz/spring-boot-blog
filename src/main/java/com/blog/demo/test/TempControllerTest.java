package com.blog.demo.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @RestContoller: 문자열 자체를 리턴
// @Controller: src/main/resources/static 아래 있는 파일을 리턴
@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        // @Controller는 파일을 리턴하는 것
        // 파일 리턴하는 기본 경로: src/main/resources/static
        // 그래서 아래 리턴에서 '/'을 맨 앞에 붙여야함
        return "/home.html";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        // prefix: /WEB-INF/views
        // suffix: .jsp
        // 리턴값 앞뒤로 prefix, suffix가 붙음
        // 경로: /WEB-INF/views/test.jsp
        return "/test";
    }

}
