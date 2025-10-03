package com.dawull.book.springboot.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
<<<<<<< HEAD
import lombok.Getter;
=======
>>>>>>> 485cda2dd8f3aab0172317b7d9a455b6377efbb1
import lombok.NoArgsConstructor;
/* 롬복어노테이션은 필수가 아님.
*  Entity를 클래스와 가까이 둔 이유: 코틀린 등의
* 새언어 전환으로 롬복이 더 이상 필요없을 경우 삭제하기 땜.*/
<<<<<<< HEAD
@Getter
=======
>>>>>>> 485cda2dd8f3aab0172317b7d9a455b6377efbb1
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500 , nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts (String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
