package com.blog.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 클래스를 Mysql에 테이블로 만들어줌
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴
public class User {
    @Id //Primary key
    // GenerationType.IDENTITY: 이 프로젝트에 연결된 DB의 넘버링을 따라감
    // 예: mysql이면 auto increment를 사용하게됨.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // auto increment

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100) // 해쉬로 암호화할것이기 때문에 넉넉히
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // private String role; // 사실 이런 컬럼은 Enum을 쓰는게 좋음(도메인, 범위를 정할 수 있으므로)
    // 이렇게 아래처럼 enum을 사용한다.
    // 다만 DB에는 RoleType 이라는 타입이 없으니 아래와 같은 어노테이션을 붙여준다
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 시간이 자동 입력됨
    private Timestamp createDate;
}
