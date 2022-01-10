package com.blog.demo.controller;

import com.blog.demo.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"","/"}) // 아무것도 안 적었을때 & '/' 붙였을때 !!!
    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
        System.out.println("로그인 사용자 아이디 " + principal.getUsername());

        // /WEB-INF/views/index.jsp
        return "index";

        // 이 주소는 로그인 후 오는 주소이기도 함
        // 그러면 컨트롤러에서 세션을 어떻게 찾나? -> @AuthenticationPrincipal


    }
}
