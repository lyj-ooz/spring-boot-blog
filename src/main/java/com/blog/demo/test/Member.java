package com.blog.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data // @Getter + @Setter
@AllArgsConstructor // 생성자
// @RequiredArgsConstructor // final 붙은 멤버변수만 가지고 생성자 생성
@NoArgsConstructor // 빈 생성자
public class Member {
    // 변수는 다 private으로 만든다
    // 변수를 업데이트 하고 싶을 땐 메소드로 한다
    private int id;
    private String username;
    private String password;
    private String email;

}
