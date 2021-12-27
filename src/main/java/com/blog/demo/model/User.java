package com.blog.demo.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 클래스를 Mysql에 테이블로 만들어줌
public class User {
    @Id //Primary key
    // GenerationType.IDENTITY: 이 프로젝트에 연결된 DB의 넘버링을 따라감
    // 예: mysql이면 auto increment를 사용하게됨.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // auto increment

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100) // 해쉬로 암호화할것이기 때문에 넉넉히
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'") // 'user' 로 설정함
    private String role; // 사실 이런 컬럼은 Enum을 쓰는게 좋음(도메인, 범위를 정할 수 있으므로)

    @CreationTimestamp // 시간이 자동 입력됨
    private Timestamp createDate;
}
