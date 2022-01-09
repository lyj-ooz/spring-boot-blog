package com.blog.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// 스프링 시큐리티에서 아래 3가지 어노테이션은 한 셋트와 같다!
@Configuration // 빈 등록 (빈 Bean 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것)
@EnableWebSecurity // 시큐리티 필터와 그에 대한 설정 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한, 인증을 체크한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        // BCryptPasswordEncoder 사용 예:
        // "1234"를 암호화 해줌
        // String encodedPw = new BCryptPasswordEncoder().encode("1234");
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트용)
                .authorizeRequests()
                .antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll() // 위 경로로 들어오는 요청은 인증없이 들어올수 있게!
                .anyRequest().authenticated() // 그리고 그 외 요청은 인증 필요함!
            .and().formLogin().loginPage("/auth/loginForm");



    }

}
