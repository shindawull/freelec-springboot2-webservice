package com.dawull.book.springboot.web.dto;

import com.dawull.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    /* PostsResponseDto는 Entity 필드 중 일부만 사용하므로
     * 받아서 필드에 값을 넣는다.*/
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
