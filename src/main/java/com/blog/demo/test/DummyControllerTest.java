package com.blog.demo.test;

import com.blog.demo.model.RoleType;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username " + user.getUsername());
        System.out.println("password " + user.getPassword());
        System.out.println("email " + user.getEmail());
        System.out.println("role " + user.getRole());
        System.out.println("createDate " + user.getCreateDate());

        user.setRole(RoleType.USER);

        userRepository.save(user);
        return "회원가입 완료!";
    }
}
