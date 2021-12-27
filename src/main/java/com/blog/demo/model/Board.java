package com.blog.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터일때 사용
    private String content; // summernote 라이브러리

    @ColumnDefault("0") // 기본값 0
    private int count;

    // private int userId; 가 아니라 아래처럼 사용할 것임
    @ManyToOne // 여기서는 Board가 'Many', User가 'One' (1유저가 여러 게시글을 쓰니까)
    @JoinColumn(name = "userId")
    // 어노테이션 설명: 필드명은 userId가 되고 연관관계는 ManyToOne.
    private User user;
    // db는 오브젝트를 저장할 수 없기 때문에 Foreign Key가 필요할때는 위처럼 한다.

    @CreationTimestamp
    private Timestamp createDate;


}
