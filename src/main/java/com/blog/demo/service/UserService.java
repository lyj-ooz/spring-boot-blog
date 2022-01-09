package com.blog.demo.service;

import com.blog.demo.model.RoleType;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 아래 Service 어노테이션을 붙여야 스프링이 컴포넌트 스캔해서 bean에 등록해준다.
// bean에 등록 = IoC = 메모리에 띄워준다
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // DI

    @Autowired
    private BCryptPasswordEncoder encoder;

    // Transactional 어노테이션을 붙이면 아래 save 안에 있는 것들이 하나의 트랜잭션으로 묶인다
    @Transactional
    public void save(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword); // 해쉬
        user.setPassword(encodedPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    // readOnly = true로 해놓으면
    // select할 때 트랜잭션 시작되고 이 서비스 종료시에 트랜잭션도 종료되기 까지의 정합성을 유지할 수 있다.
    // 정합성: select를 여러번 하더라도 같은 데이터가 찾아짐
    @Transactional(readOnly = true)
    public User loginOld(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
