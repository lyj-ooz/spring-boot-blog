package com.blog.demo.service;

import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// 아래 Service 어노테이션을 붙여야 스프링이 컴포넌트 스캔해서 bean에 등록해준다.
// bean에 등록 = IoC = 메모리에 띄워준다
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // DI

    // Transactional 어노테이션을 붙이면 아래 save 안에 있는 것들이 하나의 트랜잭션으로 묶인다
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
