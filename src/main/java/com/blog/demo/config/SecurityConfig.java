package com.blog.demo.config;

import com.blog.demo.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        // BCryptPasswordEncoder 사용 예:
        // "1234"를 암호화 해줌
        // String encodedPw = new BCryptPasswordEncoder().encode("1234");
        return new BCryptPasswordEncoder();
    }

    // DB에 있는 해쉬랑 비교하기. userDetailsService에 principalDetailService 를 넣어야
    // 패스워드를 비교함.
    // 로그인 과정: https://youtu.be/pHp2LGuukls?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&t=1943
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트용)
                .authorizeRequests()
                .antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll() // 위 경로로 들어오는 요청은 인증없이 들어올수 있게!
                .anyRequest().authenticated() // 그리고 그 외 요청은 인증 필요함!
            .and().formLogin().loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 이 주소로 오는 로그인을 가로채서 대신 로그인 해줌
                .defaultSuccessUrl("/");



    }

}
