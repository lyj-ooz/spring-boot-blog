package com.blog.demo.controller.api;

import com.blog.demo.dto.ResponseDto;
import com.blog.demo.model.RoleType;
import com.blog.demo.model.User;
import com.blog.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService; // DI

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("/api/user 호출!");
        user.setRole(RoleType.USER);
        userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 아래처럼 하는 것은 전통적인 방식
    // 대부분 스프링 시큐리티를 이용해서 로그인함.
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user) {
        System.out.println("/api/user/login 호출!");

        // principal: 접근주체
        User principal = userService.login(user);

        if(principal != null) {
            // 세션 만들기
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
