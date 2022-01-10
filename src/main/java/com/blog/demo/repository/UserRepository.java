package com.blog.demo.repository;

import com.blog.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// 자동으로 bean으로 등록된다 = 어디서나 injection 가능(DI)

// 아래 제네릭의 의미 <User, Integer>
// "이 repository는 User 테이블을 관리하며, 이 user 테이블의 PK는 Long.
public interface UserRepository extends JpaRepository<User, Long> {

    // JpaRepository의 findAll()
    // -> 이 테이블의 모든 행을 리턴

    // SELECT * FROM user WHERE username = ?1
    Optional<User> findByUsername(String username);

    // JPA Naming 쿼리 전략
    User findByUsernameAndPassword(String username, String password);
    // 위 처럼 작성하면 이런 쿼리를 실행해줌
    // SELECT * FROM user WHERE username = ?1 AND password = ?2

    // 혹은 아래처럼 쿼리를 직접 작성할 수도 있음
//    @Query(
//            value="SELECT * FROM user WHERE username = ?1 AND password = ?2",
//            nativeQuery = true)
//    User login(String username, String password);


}
