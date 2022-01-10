package com.blog.demo.config.auth;

import com.blog.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
// userDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장함.
public class PrincipalDetail implements UserDetails {
    private User user; // 컴포지션

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
//        return null;
        return user.getPassword();
    }

    @Override
    public String getUsername() {
//        return null;
        return user.getUsername();
    }

    // 계정 만료 여부를 리턴 (true: 만료 x, false: 만료)
    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    // 계정이 잠겨있는지 여부를 리턴 (true 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부를 리턴 (true: 만료 x, false: 만료)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 사용가능 여부를 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정이 갖고 있는 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_"+user.getRole(); // "ROLE_" 부분은 꼭 필요!
//            }
//        });
        // 윗 부분을 람다식으로는....
        collectors.add(()->{
            return "ROLE_"+user.getRole();
        });
        return collectors;
    }
}
