package com.blog.demo.config.auth;

import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링 시큐리티가 로그인 요청을 가로챌 때, password, username 도 가로채는데
    // password 부분은 알아서 처리하므로 우리는 username이 DB에 있는지만 확인하면 된다.
    // 아래 메소드가 그 역할을 할 것임
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User principal = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없음..!" + username);
        });

        return new PrincipalDetail(principal); // 이 때 시큐리티 세션에 유저 정보가 저장됨
    }
}
