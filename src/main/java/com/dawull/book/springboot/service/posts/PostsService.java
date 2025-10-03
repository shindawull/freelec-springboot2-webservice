package com.dawull.book.springboot.service.posts;

import com.dawull.book.springboot.domain.posts.Posts;
import com.dawull.book.springboot.domain.posts.PostsRepository;
import com.dawull.book.springboot.web.dto.PostsResponseDto;
import com.dawull.book.springboot.web.dto.PostsSaveRequestDto;
import com.dawull.book.springboot.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }
}
