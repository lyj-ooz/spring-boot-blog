package com.blog.demo.test;

import org.springframework.web.bind.annotation.*;

// 사용자의 요청에 대한 응답(html파일)하는 컨트롤러
// @Controller

// 사용자의 요청에 대한 응답(data)을 하는 컨트롤러
@RestController
public class HttpControllerTest {
    // 참고: 인터넷 브라우저에서는 get 요청밖에 못함
    // 그래서 post, put, delete 같은 다른 요청을 하기 위해 포스트맨이 있음

    // RequestParam으로 데이터를 하나씩 전달하거나 아니면 아래처럼 객체로 하거나
    // get 방식으로 데이터를 요청하는 방식은 쿼리스트링으로 전달하는 것 뿐임.
    // ex. ?id=1&username=user

//    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get 요청 " + id + " " + username;
//    }

    @GetMapping("/http/get")
    // 전해진 데이터를 받아 Member m에 맵핑하는 것은 스프링부트의
    // MessageConverter가 알아서 해준다
    public String getTest(Member m) {
        return "get 요청 " + m.getId() + " " + m.getUsername();
    }

    // post, put, delete 요청을 통해 전해진 데이터는 @RequestBody 어노테이션을 붙여 받을 수 있다.
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "post 요청 " + m.getId() + " " + m.getUsername();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody Member m) {
        return "delete 요청";
    }

}
