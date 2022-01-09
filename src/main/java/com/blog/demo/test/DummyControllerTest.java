package com.blog.demo.test;

import com.blog.demo.model.RoleType;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

// html 파일이 아니라 데이터를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id) {
        try {
            // 없는 id로 지우려고 할 수도 있으니까 exception을 잡기 위해서
            // try catch로 감싼다.
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다.";
        }
        return "삭제되었음";
    }

    @Transactional // 함수 종료시에 자동으로 commmit 됨
    @PutMapping("/dummy/user/{id}")
    // Json으로 받으려면 @RequestBody 어노테이션 필요
    // json 데이터를 MessageConverter가 자바 오브젝트로 바꿔줌
    public User updateUser(@PathVariable Long id, @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());
        requestUser.setId(id);
        // JPA의 save 함수를 업데이트 할 때도 사용하려면 아래처럼 해야함
        // 1. id로 user를 찾고
        // 2. json으로 받은 password, email을 찾은 user에 업데이트
        // 3. 이 user로 save
        // 그래야 여기서 받은 email, password 외의 값에 null이 들어가지 않음
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        // userRepository.save(user);

        // 그런데 save 함수 말고 다른 방법은... @Transactional 사용임. (더티체킹)
        // @Transactional 을 사용하면 .save 함수를 사용하지 않아도 업데이트 된다.
        return user;
    }

// patch ㅌㅔ스트
    @Transactional
    @PatchMapping("/dummy/user/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });

        requestUser.setId(id);

        if(requestUser.getUsername() != null) {
            user.setUsername(requestUser.getUsername());
        }

        if(requestUser.getEmail() != null) {
            user.setEmail(requestUser.getEmail());
        }

        if(requestUser.getPassword() != null) {
            user.setPassword(requestUser.getPassword());
        }

        return user;
    }
//

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
