package com.stock.togetherStock.post.service;

import com.stock.togetherStock.post.domain.Post;
import com.stock.togetherStock.post.domain.PostDto;
import com.stock.togetherStock.post.exception.PostErrorCode;
import com.stock.togetherStock.post.exception.PostException;
import com.stock.togetherStock.post.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시물 목록 조회 로직
     */
    public List<Post> postList() {
        return postRepository.findAll();
    }

    /**
     * 게시물 작성 로직
     */
    @Transactional
    public Post write(PostDto postDto) {
        PostDto savedDto = PostDto.builder()
            .title(postDto.getTitle())
            .content(postDto.getContent())
            .regiPostDate(LocalDateTime.now())
            .build();

        return postRepository.save(savedDto.toEntity());
    }

    /**
     * 게시물 수정
     */
    @Transactional
    public Long PostUpdate(Long postId, PostDto postDto) {

        Post post = postRepository.findByPostId(postId)
            .orElseThrow(() -> new PostException(PostErrorCode.POST_ERROR_CODE));

        post.update(postDto.getTitle(),
                    postDto.getContent()
                    );

        return postId;
    }

    /**
     * 게시물 삭제
     */
    @Transactional
    public boolean delete(Long postId) {

        postRepository.deleteById(postId);

        return true;
    }
}
