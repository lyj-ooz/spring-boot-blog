package com.blog.demo.test;

import com.blog.demo.model.RoleType;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

// html 파일이 아니라 데이터를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("dummy/user")
    public Page<User> pageList(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<User> pagingUsers = userRepository.findAll(pageable);
//        List<User> users = pagingUsers.getContent();
        return pagingUsers;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id) {
        // 아래 findById 메소드는 Optional임.
        // 값이 있을수도 있지만 null일수도 있다는 뜻
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저가 없음!");
            }
        });
        // 위에 것을 람다식으로 바꾸면.......
//        User user = userRepository.findById(id).orElseThrow(() -> {
//            return new IllegalArgumentException("해당 유저가 없음!");
//        });

        // 아래 리턴에서 user는 자바 오브젝트임.
        // 웹브라우저는 자바 오브젝트를 이해하지 못하므로 JSON으로 변환해야 함.
        // MessageConverter가 Jackson 라이브러리를 호출하여
        // 자바 오브젝트를 json으로 바꾸어 줌.
        return user;
    }

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
