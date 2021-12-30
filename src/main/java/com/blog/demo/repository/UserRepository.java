package com.blog.demo.repository;

import com.blog.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 자동으로 bean으로 등록된다 = 어디서나 injection 가능(DI)

// 아래 제네릭의 의미 <User, Integer>
// "이 repository는 User 테이블을 관리하며, 이 user 테이블의 PK는 Long.
public interface UserRepository extends JpaRepository<User, Long> {

    // JpaRepository의 findAll()
    // -> 이 테이블의 모든 행을 리턴



}
